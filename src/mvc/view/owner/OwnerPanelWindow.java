/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.owner;

import mvc.model.DB.product.Bien;
import mvc.model.owner.OwnerPanelModel;
import util.Range;

import javax.swing.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class OwnerPanelWindow extends JDialog {
	public Boolean validDialog = false;
	public static Range YEAR_CONSTRUCTION_RANGE;
	public static Range FACADE_NUMBER_RANGE;
	public static Range FLOOR_NUMBER_RANGE;
	public static String [] CPEB_RANGE;

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
		FACADE_NUMBER_RANGE 	= new Range(1,5);
		FLOOR_NUMBER_RANGE 		= new Range(1,7);
		CPEB_RANGE 				= new String [] {"A", "B", "C", "D", "E", "F"};

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
		this.tPrice.setText(""+tPrice);
	}

	public Integer gettFaceWide() {
		//Util.checkNumérique(tFaceWide.getText());
		return Integer.parseInt(tFaceWide.getText());
	}

	public void settFaceWide(Integer tFaceWide) {
		this.tFaceWide.setText(""+tFaceWide);
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
		return (Integer)cYearConstruction.getItemAt(this.cYearConstruction.getSelectedIndex());
	}

	public void setcYearConstruction(Integer cYearConstruction) {
		this.cYearConstruction.setSelectedItem(cYearConstruction);
	}

	public Integer getcNFrontage() {
		return (Integer)cNFrontage.getItemAt(this.cNFrontage.getSelectedIndex());
	}

	public void setcNFrontage(Integer cNFrontage) {
		this.cNFrontage.setSelectedItem(cNFrontage);
	}

	public Integer getcNFloor() {
		return (Integer)cNFloor.getItemAt(this.cNFloor.getSelectedIndex());
	}

	public void setcNFloor(Integer cNFloor) {
		this.cNFloor.setSelectedItem(cNFloor);
	}

	public String getcCpeb() {
		return (String)cCpeb.getItemAt(this.cCpeb.getSelectedIndex());
	}

	public void setcCpeb(String cCpeb) {
		this.cCpeb.setSelectedItem(cCpeb);
	}
}
