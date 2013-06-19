/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.owner;

import app.model.DB.product.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import app.App;
import app.controller.city.CityOwnerEvent;
import app.controller.city.CpOwnerEvent;
import app.model.DB.product.address.City;
import app.model.DB.product.address.Country;
import app.model.DB.product.address.Region;
import app.model.owner.OwnerPanelModel;
import app.view.city.CityWindow;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import components.filechooser.ImageFileView;
import components.filechooser.ImageFilter;
import components.filechooser.ImagePreview;
import util.Range;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;

public class OwnerPanelWindow extends JDialog {

	private final static String[] comboBoxDefaultValue = {""};

	public Boolean validDialog = false;
	public static Range YEAR_CONSTRUCTION_RANGE;
	public static Range FACADE_NUMBER_RANGE;
	public static Range FLOOR_NUMBER_RANGE;
	public static String[] CPEB_RANGE;

	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField tName;
	private JTextField tDescription;
	private JTextField tStreetName;
	private JTextField tStreetNumber;
	private JTextField tStreetBox;
	private JTextField tFaceWide;
	private JComboBox cTypeProduct;
	private JComboBox cYearConstruction;
	private JComboBox cNFrontage;
	private JComboBox cNFloor;
	private JComboBox cCpeb;
	private JComboBox cCity;
	private JComboBox cLocality;
	private JComboBox cCountry;
	private JComboBox cPosteCode;
	private JFormattedTextField tPrice;
	private JPanel imageContainer;

	private JButton bAddImage;
	private JPanel mainContainer;
	static private String newline = "\n";
	private JTextArea log;
	private JFileChooser fc;
	private JLabel picLabel;
	private File file;

	private OwnerPanelModel ownerPanelModel;

	private boolean stopListener = false;
	private Long id;

	public OwnerPanelWindow(OwnerPanelModel argOwnerPanelModel) {
		stopListener = true;
		this.ownerPanelModel = argOwnerPanelModel;
		$$$setupUI$$$();
		initComponents();
		linkModel();
		addListener();
		populate();
		stopListener = false;
	}

	private void initComponents() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		YEAR_CONSTRUCTION_RANGE = new Range(GregorianCalendar.getInstance().get(GregorianCalendar.YEAR), 1899, -1);
		FACADE_NUMBER_RANGE = new Range(1, 5);
		FLOOR_NUMBER_RANGE = new Range(1, 7);
		CPEB_RANGE = new String[]{"A", "B", "C", "D", "E", "F"};

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		picLabel = new JLabel();
		imageContainer.add(picLabel, BorderLayout.CENTER);
		URL url = getClass().getResource("/ressources/images/welcome.png");

		this.repaint();
		addImage(url);
	}

	private void addImage(URL url) {
		try {
			BufferedImage image = ImageIO.read(url);
			if (image == null) {
				return;
			}
			picLabel.setIcon(getImageResized(new ImageIcon(image)));
			imageContainer.repaint();
			OwnerPanelWindow.this.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Icon getImageResized(ImageIcon img) {
		int width = img.getIconWidth() * (400) / img.getIconHeight();
		int height = 400;
		return new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	private void linkModel() {
		ownerPanelModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();
				stopListener = true;
				if (name.equals("newCity") && !evt.getNewValue().equals("")) {

					City city = App.em.findUnique(City.class, "where city = ?", evt.getNewValue());
					Region region = city.getLocality().getRegion();
					cPosteCode.setSelectedItem(city.getPosteCode());
					cLocality.setSelectedItem(region.getRegion());
					cCountry.setSelectedItem(region.getCountry().getLabelFr());
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
					cCity.setSelectedItem(city.getCity());
					cLocality.setSelectedItem(region.getRegion());
					cCountry.setSelectedItem(region.getCountry().getLabelFr());
					stopListener = false;
				}

			}
		});
	}

	private void addListener() {
		bAddImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//Set up the file chooser.
				if (fc == null) {
					fc = new JFileChooser();

					//Add a custom file filter and disable the default
					//(Accept All) file filter.
					fc.addChoosableFileFilter(new ImageFilter());
					fc.setAcceptAllFileFilterUsed(false);

					//Add custom icons for file types.
					fc.setFileView(new ImageFileView());

					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				//Show it.
				int returnVal = fc.showDialog(OwnerPanelWindow.this,
						"Attach");

				//Process the results.
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					// log.append("Attaching file: " + file.getName() + "." + newline);
					try {
						addImage(file.toURL());
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				} else {
					// log.append("Attachment cancelled by user." + newline);
				}
				// log.setCaretPosition(log.getDocument().getLength());

				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
			}
		});
		cCity.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CityOwnerEvent event = new CityOwnerEvent((String) itemEvent.getItem(), ownerPanelModel);
					event.dispatch();
				}
			}
		});
		cPosteCode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CpOwnerEvent event = new CpOwnerEvent((String) itemEvent.getItem(), ownerPanelModel);
					event.dispatch();
				}
			}
		});
		tPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() < KeyEvent.VK_0 || evt.getKeyChar() > KeyEvent.VK_9) {//input<'0' or input>'9'?
					evt.consume();
				}
			}
		});
	}

	private void populate() {
		for (int i : YEAR_CONSTRUCTION_RANGE) {
			cYearConstruction.addItem(i);
		}
		for (int i : FACADE_NUMBER_RANGE) {
			cNFrontage.addItem(i);
		}
		for (int i : FLOOR_NUMBER_RANGE) {
			cNFloor.addItem(i);
		}
		for (String i : CPEB_RANGE) {
			cCpeb.addItem(i);
		}
		for (Bien.TypeProduct typeProduct : Bien.TypeProduct.values()) {
			cTypeProduct.addItem(typeProduct.toString());
		}
		for (Country country : App.em.find(Country.class, "select * from tcountry")) {
			cCountry.addItem(country.getLabelFr());
		}
		for (Region region : App.em.find(Region.class, "select * from tregion")) {
			cLocality.addItem(region.getRegion());
		}
		for (City city : App.em.find(City.class, "select * from tcity order by city")) {
			cCity.addItem(city.getCity());
		}
		for (City city : App.em.find(City.class, "select distinct poste_code from tcity order by poste_code")) {
			cPosteCode.addItem(city.getPosteCode());
		}
	}

	private void onOK() {
		// add your code here
		for (Field field : OwnerPanelWindow.class.getDeclaredFields()) {
			if (field.getType().isAssignableFrom(JTextField.class)) {
				String result = "";
				Method meth;
				try {
					meth = this.getClass().getMethod("get" + field.getName());
					if (meth.getReturnType().isAssignableFrom(String.class)) {
						result = (String) meth.invoke(this);
					} else if (meth.getReturnType().isAssignableFrom(Integer.class) || meth.getReturnType().isAssignableFrom(Long.class)) {
						result = meth.invoke(this).toString();
					}

					if (result.equals("") && !field.getName().equals("tStreetBox")) {
						JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis: " + field.getName(), "Confirmation", JOptionPane.OK_OPTION);
						return;
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					return;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					return;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return;
				}
			}
		}
		validDialog = true;
		dispose();
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

	public String gettName() {
		return tName.getText();
	}

	public void settName(String tName) {
		this.tName.setText(tName);
	}

	public String gettDescription() {
		return tDescription.getText();
	}

	public void settDescription(String tDescription) {
		this.tDescription.setText(tDescription);
	}

	public String gettStreetName() {
		return tStreetName.getText();
	}

	public void settStreetName(String tStreetName) {
		this.tStreetName.setText(tStreetName);
	}

	public String gettStreetNumber() {
		return tStreetNumber.getText();
	}

	public void settStreetNumber(String tStreetNumber) {
		this.tStreetNumber.setText(tStreetNumber);
	}

	public String gettStreetBox() {
		return tStreetBox.getText();
	}

	public void settStreetBox(String tStreetBox) {
		this.tStreetBox.setText(tStreetBox);
	}

	public String gettCity() {
		return (String) cCity.getSelectedItem();
	}

	public void settCity(String tCity) {
		ownerPanelModel.setDefaultCity(tCity);
		this.cCity.setSelectedItem(tCity);
	}

	public String gettLocality() {
		return (String) cLocality.getSelectedItem();
	}

	public void settLocality(String tLocality) {
		this.cLocality.setSelectedItem(tLocality);
	}

	public String gettPosteCode() {
		return (String) cPosteCode.getSelectedItem();
	}

	public void settPosteCode(String tPosteCode) {
		ownerPanelModel.setDefaultCpValue(tPosteCode);
		this.cPosteCode.setSelectedItem(tPosteCode);
	}

	public String gettCountry() {
		return (String) cCountry.getSelectedItem();
	}

	public void settCountry(String tCountry) {
		this.cCountry.setSelectedItem(tCountry);
	}

	public Integer gettPrice() {
		return Integer.parseInt(tPrice.getText());
	}

	public void settPrice(Integer tPrice) {
		this.tPrice.setText("" + tPrice);
	}

	public Integer gettFaceWide() {
		return Integer.parseInt(tFaceWide.getText());
	}

	public void settFaceWide(Integer tFaceWide) {
		this.tFaceWide.setText("" + tFaceWide);
	}

	public String getcTypeProduct() {
		return (String) this.cTypeProduct.getItemAt(this.cTypeProduct.getSelectedIndex());
	}

	public String getcTypeProductEnum() {
		return (String) this.cTypeProduct.getSelectedItem();
	}

	public void setcTypeProduct(String cTypeProduct) {
		this.cTypeProduct.setSelectedItem(cTypeProduct);
	}

	public Integer getcYearConstruction() {
		return (Integer) cYearConstruction.getItemAt(this.cYearConstruction.getSelectedIndex());
	}

	public void setcYearConstruction(Integer cYearConstruction) {
		this.cYearConstruction.setSelectedItem(cYearConstruction);
	}

	public Integer getcNFrontage() {
		return (Integer) cNFrontage.getItemAt(this.cNFrontage.getSelectedIndex());
	}

	public void setcNFrontage(Integer cNFrontage) {
		this.cNFrontage.setSelectedItem(cNFrontage);
	}

	public Integer getcNFloor() {
		return (Integer) cNFloor.getItemAt(this.cNFloor.getSelectedIndex());
	}

	public void setcNFloor(Integer cNFloor) {
		this.cNFloor.setSelectedItem(cNFloor);
	}

	public String getcCpeb() {
		return (String) cCpeb.getItemAt(this.cCpeb.getSelectedIndex());
	}

	public void setcCpeb(String cCpeb) {
		this.cCpeb.setSelectedItem(cCpeb);
	}

	public File getFile() {
		return file;
	}

	public void setImages(List<Images> images) {
		for (Images image : images) {
			if (image != null) {
				this.file = new File("ressources/images/biens/" + getId() + "/" + image.getImageName());
				try {
					addImage(this.file.toURL());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long getId() {
		return id;
	}

	private void createUIComponents() {
		// TODO: place custom component creation code here
	}

	public void setCanChange(Bien bien) {
		if (!Bien.Status.fromString(bien.getStatus()).equals(Bien.Status.WAITING)) {
			tName.setEnabled(false);
			tDescription.setEnabled(false);
			tStreetName.setEnabled(false);
			tStreetNumber.setEnabled(false);
			tStreetBox.setEnabled(false);
			tFaceWide.setEnabled(false);
			cTypeProduct.setEnabled(false);
			cYearConstruction.setEnabled(false);
			cNFrontage.setEnabled(false);
			cNFloor.setEnabled(false);
			cCpeb.setEnabled(false);
			cCity.setEnabled(false);
			cLocality.setEnabled(false);
			cCountry.setEnabled(false);
			cPosteCode.setEnabled(false);
			tPrice.setEnabled(false);

			bAddImage.setEnabled(false);
		}
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
		mainContainer = new JPanel();
		mainContainer.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(mainContainer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		mainContainer.add(panel1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		imageContainer = new JPanel();
		imageContainer.setLayout(new BorderLayout(0, 0));
		panel1.add(imageContainer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		mainContainer.add(panel2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Libellé");
		panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Description");
		panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Type de bien");
		panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Nom de la rue");
		panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Numéro de l'habitation");
		panel3.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("Boîte");
		panel3.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label7 = new JLabel();
		label7.setText("Ville");
		panel3.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setText("Province");
		panel3.add(label8, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label9 = new JLabel();
		label9.setText("Pays");
		panel3.add(label9, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label10 = new JLabel();
		label10.setText("Prix");
		panel3.add(label10, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label11 = new JLabel();
		label11.setText("Année de construction");
		panel3.add(label11, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label12 = new JLabel();
		label12.setText("Largeur de la facade principale");
		panel3.add(label12, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label13 = new JLabel();
		label13.setText("Nombre de facade");
		panel3.add(label13, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label14 = new JLabel();
		label14.setText("Nombre d'étage");
		panel3.add(label14, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label15 = new JLabel();
		label15.setText("CPEB");
		panel3.add(label15, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label16 = new JLabel();
		label16.setText("Code postal");
		panel3.add(label16, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel2.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		tName = new JTextField();
		panel4.add(tName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tDescription = new JTextField();
		panel4.add(tDescription, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetName = new JTextField();
		panel4.add(tStreetName, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetNumber = new JTextField();
		panel4.add(tStreetNumber, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetBox = new JTextField();
		panel4.add(tStreetBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tFaceWide = new JTextField();
		panel4.add(tFaceWide, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		cTypeProduct = new JComboBox();
		panel4.add(cTypeProduct, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cYearConstruction = new JComboBox();
		panel4.add(cYearConstruction, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cNFrontage = new JComboBox();
		panel4.add(cNFrontage, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cNFloor = new JComboBox();
		panel4.add(cNFloor, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cCpeb = new JComboBox();
		panel4.add(cCpeb, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cCity = new JComboBox();
		panel4.add(cCity, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cCountry = new JComboBox();
		panel4.add(cCountry, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cPosteCode = new JComboBox();
		panel4.add(cPosteCode, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cLocality = new JComboBox();
		panel4.add(cLocality, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		tPrice = new JFormattedTextField();
		panel4.add(tPrice, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		mainContainer.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		bAddImage = new JButton();
		bAddImage.setText("Ajout d'une image");
		CellConstraints cc = new CellConstraints();
		panel5.add(bAddImage, cc.xy(3, 1));
		final Spacer spacer1 = new Spacer();
		panel5.add(spacer1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel5.add(spacer2, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		mainContainer.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel6.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
		panel6.add(panel7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		buttonOK = new JButton();
		buttonOK.setText("OK");
		panel7.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonCancel = new JButton();
		buttonCancel.setText("Cancel");
		panel7.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return contentPane;
	}
}
