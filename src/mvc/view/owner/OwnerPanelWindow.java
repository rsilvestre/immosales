/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.owner;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import mvc.model.DB.product.Bien;
import mvc.model.owner.OwnerPanelModel;
import util.Range;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class OwnerPanelWindow extends JDialog {
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
	private JTextField tCity;
	private JTextField tLocality;
	private JTextField tPosteCode;
	private JTextField tCountry;
	private JTextField tPrice;
	private JTextField tFaceWide;
	private JComboBox cTypeProduct;
	private JComboBox cYearConstruction;
	private JComboBox cNFrontage;
	private JComboBox cNFloor;
	private JComboBox cCpeb;

	private OwnerPanelModel ownerPanelModel;

	public OwnerPanelWindow(OwnerPanelModel argOwnerPanelModel) {
		this.ownerPanelModel = argOwnerPanelModel;
		initComponents();
		populate();
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
	}

	private void onOK() {
		// add your code here
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
		return tCity.getText();
	}

	public void settCity(String tCity) {
		this.tCity.setText(tCity);
	}

	public String gettLocality() {
		return tLocality.getText();
	}

	public void settLocality(String tLocality) {
		this.tLocality.setText(tLocality);
	}

	public String gettPosteCode() {
		return tPosteCode.getText();
	}

	public void settPosteCode(String tPosteCode) {
		this.tPosteCode.setText(tPosteCode);
	}

	public String gettCountry() {
		return tCountry.getText();
	}

	public void settCountry(String tCountry) {
		this.tCountry.setText(tCountry);
	}

	public Float gettPrice() {
		//Util.checkNumérique(tPrice.getText());
		return Float.parseFloat(tPrice.getText());
	}

	public void settPrice(Float tPrice) {
		this.tPrice.setText("" + tPrice);
	}

	public Integer gettFaceWide() {
		//Util.checkNumérique(tFaceWide.getText());
		return Integer.parseInt(tFaceWide.getText());
	}

	public void settFaceWide(Integer tFaceWide) {
		this.tFaceWide.setText("" + tFaceWide);
	}

	public String getcTypeProduct() {
		return (String) this.cTypeProduct.getItemAt(this.cTypeProduct.getSelectedIndex());
	}

	public Bien.TypeProduct getcTypeProductEnum() {
		return Bien.TypeProduct.valueOf((String) this.cTypeProduct.getItemAt(this.cTypeProduct.getSelectedIndex()));
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
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
		panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		buttonOK = new JButton();
		buttonOK.setText("OK");
		panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonCancel = new JButton();
		buttonCancel.setText("Cancel");
		panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Libellé");
		panel4.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Description");
		panel4.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Type de bien");
		panel4.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Nom de la rue");
		panel4.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Numéro de l'habitation");
		panel4.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("Boîte");
		panel4.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label7 = new JLabel();
		label7.setText("Ville");
		panel4.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label8 = new JLabel();
		label8.setText("Localité");
		panel4.add(label8, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label9 = new JLabel();
		label9.setText("Code postal");
		panel4.add(label9, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label10 = new JLabel();
		label10.setText("Pays");
		panel4.add(label10, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label11 = new JLabel();
		label11.setText("Prix");
		panel4.add(label11, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label12 = new JLabel();
		label12.setText("Année de construction");
		panel4.add(label12, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label13 = new JLabel();
		label13.setText("Largeur de la facade principale");
		panel4.add(label13, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label14 = new JLabel();
		label14.setText("Nombre de facade");
		panel4.add(label14, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label15 = new JLabel();
		label15.setText("Nombre d'étage");
		panel4.add(label15, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label16 = new JLabel();
		label16.setText("CPEB");
		panel4.add(label16, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayoutManager(16, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		tName = new JTextField();
		panel5.add(tName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tDescription = new JTextField();
		panel5.add(tDescription, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetName = new JTextField();
		panel5.add(tStreetName, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetNumber = new JTextField();
		panel5.add(tStreetNumber, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tStreetBox = new JTextField();
		panel5.add(tStreetBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tCity = new JTextField();
		panel5.add(tCity, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tLocality = new JTextField();
		panel5.add(tLocality, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tPosteCode = new JTextField();
		panel5.add(tPosteCode, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tCountry = new JTextField();
		panel5.add(tCountry, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tPrice = new JTextField();
		panel5.add(tPrice, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		tFaceWide = new JTextField();
		panel5.add(tFaceWide, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		cTypeProduct = new JComboBox();
		panel5.add(cTypeProduct, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cYearConstruction = new JComboBox();
		panel5.add(cYearConstruction, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cNFrontage = new JComboBox();
		panel5.add(cNFrontage, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cNFloor = new JComboBox();
		panel5.add(cNFloor, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cCpeb = new JComboBox();
		panel5.add(cCpeb, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel3.add(panel6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return contentPane;
	}
}
