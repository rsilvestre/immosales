/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.buyer;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import mvc.App;
import mvc.controller.buyer.FindBienDbEvent;
import mvc.controller.city.CityFindBienEvent;
import mvc.controller.city.CpFindBienEvent;
import mvc.model.DB.product.Bien;
import mvc.model.DB.product.address.City;
import mvc.model.DB.product.address.Region;
import mvc.model.FooModelLocator;
import mvc.model.buyer.FindBienModel;
import mvc.view.bien.BienWindow;
import mvc.view.city.CityWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
	private JButton offerButton;

	private String[] colomnHead = {"Ref", "Nom", "Ville", "Prix", "D�tail"};
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
		defaultTableModel = new DefaultTableModel(colomnHead, 0);
		table1.setModel(defaultTableModel);
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
			defaultTableModel.addRow(new String[]{bien.getId().toString(), bien.getName(), bien.getCity().getCity(), bien.getPrice().toString(), "Detail"});
		}
		table1.revalidate();
		table1.repaint();
	}

	private void addListener() {
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					//setSelectedPerson(getAPersonSelected(target.getSelectedRow()));

					if (target.getSelectedColumn() == 4) {
						String idBien = target.getValueAt(target.getSelectedRow(), 0).toString();
						Bien bien = App.em.load(Bien.class, Long.parseLong(idBien));
						FooModelLocator locator = FooModelLocator.getInstance();
						BienWindow bienWindow = new BienWindow(bien);
						locator.setBienWindow(bienWindow);
						bienWindow.setModal(true);
						bienWindow.pack();
						bienWindow.setVisible(true);
					}
				}
			}
		});
		cVille.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (stopListener) return;
				CityFindBienEvent event = new CityFindBienEvent((String) itemEvent.getItem(), findBienModel);
				event.dispatch();
			}
		});
		cCP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (stopListener) return;
				CpFindBienEvent event = new CpFindBienEvent((String) itemEvent.getItem(), findBienModel);
				event.dispatch();
			}
		});
		cVille.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				FindBienDbEvent event = new FindBienDbEvent((String) cVille.getSelectedItem(), (String) cCP.getSelectedItem(), (String) cType.getSelectedItem(), (String) cProvince.getSelectedItem(), findBienModel);
				event.dispatch();
			}
		});

		cCP.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				FindBienDbEvent event = new FindBienDbEvent((String) cVille.getSelectedItem(), (String) cCP.getSelectedItem(), (String) cType.getSelectedItem(), (String) cProvince.getSelectedItem(), findBienModel);
				event.dispatch();
			}
		});
		cType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				FindBienDbEvent event = new FindBienDbEvent((String) cVille.getSelectedItem(), (String) cCP.getSelectedItem(), (String) cType.getSelectedItem(), (String) cProvince.getSelectedItem(), findBienModel);
				event.dispatch();
			}
		});
		cProvince.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				FindBienDbEvent event = new FindBienDbEvent((String) cVille.getSelectedItem(), (String) cCP.getSelectedItem(), (String) cType.getSelectedItem(), (String) cProvince.getSelectedItem(), findBienModel);
				event.dispatch();
			}
		});
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					//setSelectedPerson(getAPersonSelected(target.getSelectedRow()));
					if (target.getSelectedColumn() == 4) {
						editRow(target);
					}
				}
			}
		});
	}

	private void editRow(JTable target) {

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
		panel8.setLayout(new FormLayout("fill:103px:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel8, cc.xy(3, 1));
		followButton = new JButton();
		followButton.setText("Suivre");
		panel8.add(followButton, cc.xy(1, 3));
		offerButton = new JButton();
		offerButton.setText("Offre");
		panel8.add(offerButton, cc.xy(1, 5));
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:331px:grow"));
		panel1.add(panel9, cc.xy(1, 3));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel9.add(scrollPane1, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}