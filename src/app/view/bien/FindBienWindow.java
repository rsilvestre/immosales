/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.bien;

import app.model.DB.immo.Offer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import app.App;
import app.controller.bien.FindBienDbEvent;
import app.controller.city.CityFindBienEvent;
import app.controller.city.CpFindBienEvent;
import app.model.DB.product.Bien;
import app.model.DB.product.address.City;
import app.model.DB.product.address.Region;
import app.model.FooModelLocator;
import app.model.bien.FindBienModel;
import app.view.city.CityWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelsilvestre on 4/06/13.
 */
public class FindBienWindow extends JDialog {

	private JPanel panel1;
	private JComboBox cVille;
	private JComboBox cType;
	private JButton followButton;
	private JComboBox cCP;
	private JComboBox cProvince;
	private JTable table1;
	private JComboBox cPriceMoreThan;
	private JComboBox cPriceLessThan;
	private JList lOffer;
	private DefaultListModel lOfferModel;

	private Long searchResultValue;

	private String[] colomnHead = {"Ref", "Nom", "Ville", "Prix", "D�tail", "Status"};
	private DefaultTableModel defaultTableModel;

	private FindBienModel findBienModel;
	private boolean stopListener = false;

	private FindBienWindow findBienWindow = this;

	public FindBienWindow(FindBienModel findBienModel) {
		setModal(true);
		stopListener = true;
		this.findBienModel = findBienModel;
		this.add(panel1);

		initComponents();
		linkModel();
		addListener();
		populateLocal();
		stopListener = false;
	}

	private void initComponents() {
		defaultTableModel = new DefaultTableModel(colomnHead, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		lOfferModel = new DefaultListModel();
		table1.setModel(defaultTableModel);
		lOffer.setModel(lOfferModel);
	}

	private void linkModel() {

		findBienModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				stopListener = true;

				if (name.contains("listBiens")) {
					rebuildTableModel((List<Bien>) evt.getNewValue());
					stopListener = false;
				} else if (name.equals("newCity") && !evt.getNewValue().equals("")) {
					City city = App.em.findUnique(City.class, "where city = ?", evt.getNewValue());
					Region region = city.getLocality().getRegion();
					cCP.setSelectedItem(city.getPosteCode());
					cProvince.setSelectedItem(region.getRegion());
					stopListener = false;

				} else if (name.equals("newCp") && !evt.getNewValue().equals("")) {
					List<City> cities = App.em.find(City.class, "where poste_code = ?", evt.getNewValue());

					City city = null;

					if (cities.size() < 1) {
						stopListener = false;
						return;
					} else if (cities.size() > 1) {
						CityWindow cityWindow = new CityWindow((String) evt.getNewValue(), cities);
						cityWindow.pack();
						cityWindow.setVisible(true);
						city = cityWindow.getCity();
						if (city == null) {
							stopListener = false;
							return;
						}
					} else {
						city = cities.get(0);
					}
					Region region = city.getLocality().getRegion();
					cVille.setSelectedItem(city.getCity());
					cProvince.setSelectedItem(region.getRegion());
					stopListener = false;
				}
			}
		});
	}

	private void rebuildTableModel(List<Bien> listBiens) {
		for (int index = defaultTableModel.getRowCount() - 1; index >= 0; --index) {
			defaultTableModel.removeRow(index);
		}
		for (Bien bien : listBiens) {
			String status = "DISPONIBLE";
			if (Bien.Status.fromString(bien.getStatus()).equals(Bien.Status.SOLD)) {
				status = "VENDU";
			} else if (Bien.Status.fromString(bien.getStatus()).equals(Bien.Status.SIGNED)) {
				status = "COMPROMIS SIGNE";
			} else if (bien.getOfferAccepted() != null) {
				status = "OPTION";
			}
			defaultTableModel.addRow(new String[]{bien.getId().toString(), bien.getName(), bien.getCity().getCity(), bien.getPrice().toString(), "Detail", status});
		}
		table1.revalidate();
		table1.repaint();
	}

	private void addListener() {
		followButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					setSearchResult();
					dispose();
				}
			}
		});
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				if (evt.getClickCount() == 1 || evt.getClickCount() == 2) {
					JTable target = (JTable) evt.getSource();
					fillListOffer(target);
					//setSelectedPerson(getAPersonSelected(target.getSelectedRow()));

					if (target.getSelectedColumn() == 4 || evt.getClickCount() == 2) {
						String idBien = target.getValueAt(target.getSelectedRow(), 0).toString();
						Bien bien = App.em.load(Bien.class, Long.parseLong(idBien));
						FooModelLocator locator = FooModelLocator.getInstance();
						BienBuyerWindow bienBuyerWindow = new BienBuyerWindow(bien);
						locator.setBienBuyerWindow(bienBuyerWindow);
						bienBuyerWindow.setModal(true);
						bienBuyerWindow.pack();
						bienBuyerWindow.setVisible(true);
					}
				}
			}
		});
		cVille.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CityFindBienEvent event = new CityFindBienEvent((String) itemEvent.getItem(), findBienModel);
					event.dispatch();
				}
			}
		});
		cCP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CpFindBienEvent event = new CpFindBienEvent((String) itemEvent.getItem(), findBienModel);
					event.dispatch();
				}
			}
		});
		cVille.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FindBienDbEvent event = new FindBienDbEvent(getRequestFieldDatas(), findBienModel);
					event.dispatch();
				}
			}
		});
		cCP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FindBienDbEvent event = new FindBienDbEvent(getRequestFieldDatas(), findBienModel);
					event.dispatch();
				}
			}
		});
		cType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FindBienDbEvent event = new FindBienDbEvent(getRequestFieldDatas(), findBienModel);
					event.dispatch();
				}
			}
		});
		cProvince.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FindBienDbEvent event = new FindBienDbEvent(getRequestFieldDatas(), findBienModel);
					event.dispatch();
				}
			}
		});
	}

	private void fillListOffer(JTable target) {
		int rowSelected = target.getSelectedRow();
		if (rowSelected == -1) {
			return;
		}
		for (int index = lOfferModel.getSize() - 1; index >= 0; --index) {
			lOfferModel.removeElementAt(index);
		}
		Long cellValue = Long.parseLong(table1.getValueAt(rowSelected, 0).toString());
		List<Offer> offers = App.em.load(Bien.class, cellValue).getOffers();
		for (Offer offer : offers) {
			lOfferModel.addElement(offer.getBienId() + ": " + offer.getBuyer().toString() + ": " + offer.getOffer() + "� " + offer.getStatus());
		}

	}

	private HashMap<String, String> getRequestFieldDatas() {
		HashMap<String, String> requestFieldDatas = new HashMap<String, String>();
		requestFieldDatas.put("cityField", (String) cVille.getSelectedItem());
		requestFieldDatas.put("cpField", (String) cCP.getSelectedItem());
		requestFieldDatas.put("typeField", (String) cType.getSelectedItem());
		requestFieldDatas.put("provinceField", (String) cProvince.getSelectedItem());
		return requestFieldDatas;
	}

	private void setSearchResult() {
		int rowSelected = table1.getSelectedRow();
		if (rowSelected == -1) {
			return;
		}
		Long cellValue = Long.parseLong(table1.getValueAt(rowSelected, 0).toString());
		setSearchResultValue(cellValue);
	}

	private void populateLocal() {
		cType.addItem("");
		cProvince.addItem("");
		cVille.addItem("");
		cCP.addItem("");
		for (Bien.TypeProduct typeProduct : Bien.TypeProduct.values()) {
			cType.addItem(typeProduct.toString());
		}
		for (Region region : App.em.find(Region.class, "select * from tregion")) {
			cProvince.addItem(region.getRegion());
		}
		for (City city : App.em.find(City.class, "select * from tcity order by city")) {
			cVille.addItem(city.getCity());
		}
		for (City city : App.em.find(City.class, "select distinct poste_code from tcity order by poste_code")) {
			cCP.addItem(city.getPosteCode());
		}
		for (int value = 0; value < 1000000; value += 10000) {
			cPriceMoreThan.addItem(value);
			cPriceLessThan.addItem(1000000 - value);
		}
	}

	public Long getSearchResultValue() {
		return searchResultValue;
	}

	private void setSearchResultValue(Long searchResultValue) {
		this.searchResultValue = searchResultValue;
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:3dlu:noGrow,center:336px:noGrow"));
		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xy(1, 1));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow"));
		panel2.add(panel3, cc.xy(1, 3));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FormLayout("fill:385px:noGrow", "center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel4, cc.xy(1, 1));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:140px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:172px:noGrow", "center:d:noGrow"));
		panel4.add(panel5, cc.xy(1, 1));
		final JLabel label1 = new JLabel();
		label1.setText("Ville");
		panel5.add(label1, cc.xy(1, 1));
		cVille = new JComboBox();
		panel5.add(cVille, cc.xy(3, 1));
		final JLabel label2 = new JLabel();
		label2.setText("CP");
		panel5.add(label2, cc.xy(5, 1));
		cCP = new JComboBox();
		panel5.add(cCP, cc.xy(7, 1));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:137px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:157px:noGrow", "center:d:noGrow"));
		panel4.add(panel6, cc.xy(1, 3));
		final JLabel label3 = new JLabel();
		label3.setText("Type");
		panel6.add(label3, cc.xy(1, 1));
		cType = new JComboBox();
		panel6.add(cType, cc.xy(3, 1));
		final JLabel label4 = new JLabel();
		label4.setText("Prov.");
		panel6.add(label4, cc.xy(5, 1));
		cProvince = new JComboBox();
		panel6.add(cProvince, cc.xy(7, 1));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:133px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:155px:noGrow", "center:d:noGrow"));
		panel4.add(panel7, cc.xy(1, 5));
		final JLabel label5 = new JLabel();
		label5.setText("Prix>");
		panel7.add(label5, cc.xy(1, 1));
		cPriceMoreThan = new JComboBox();
		panel7.add(cPriceMoreThan, cc.xy(3, 1));
		final JLabel label6 = new JLabel();
		label6.setText("Prix<");
		panel7.add(label6, cc.xy(5, 1));
		cPriceLessThan = new JComboBox();
		panel7.add(cPriceLessThan, cc.xy(7, 1));
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new FormLayout("fill:103px:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel8, cc.xy(3, 1));
		followButton = new JButton();
		followButton.setText("Visite");
		panel8.add(followButton, cc.xy(1, 3));
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:331px:grow"));
		panel1.add(panel9, cc.xy(1, 3));
		final JPanel panel10 = new JPanel();
		panel10.setLayout(new FormLayout("fill:d:grow", "center:142px:grow,top:3dlu:noGrow,center:154px:grow"));
		panel9.add(panel10, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel10.add(scrollPane1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		final JScrollPane scrollPane2 = new JScrollPane();
		panel10.add(scrollPane2, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		lOffer = new JList();
		scrollPane2.setViewportView(lOffer);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
