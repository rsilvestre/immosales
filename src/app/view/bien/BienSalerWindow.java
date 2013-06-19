/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.bien;

import app.view.base.ISalerBusinessWindow;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import app.model.DB.product.Bien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BienSalerWindow extends JDialog implements ISalerBusinessWindow {
	protected JPanel contentPane;
	protected JButton buttonOK;
	protected JButton buttonCancel;
	protected JPanel panel1;
	protected JTextField tId;
	protected JTextField tOwner;
	protected JTextField tName;
	protected JTextArea tDescription;
	protected JTextArea tAddress;
	protected JTextField tPrice;
	protected JTextField tYearConstruction;
	protected JTextField tFaceWide;
	protected JTextField tFaceNumber;
	protected JTextField tFloorNumber;
	protected JTextField tCpeb;

	private JComboBox cStatus;
	private JTextField tBienType;

	private boolean validate = false;

	public BienSalerWindow() {
		initComponents();
	}

	public BienSalerWindow(Bien bien) {
		initComponents();
		populateLocal();
		setDefaultValue(bien);
		setDefaultValueLocal(bien);
	}

	private void setDefaultValueLocal(Bien bien) {
		cStatus.setSelectedItem(bien.getStatus());
	}

	private void populateLocal() {
		for (Bien.Status bienStatus : Bien.Status.values()) {
			cStatus.addItem(bienStatus.toString());
		}
	}

	public Bien.Status getStatus() {
		return Bien.Status.fromString((String) cStatus.getSelectedItem());
	}

	private void initComponents() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

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

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void setDefaultValue(Bien bien) {
		tId.setText(bien.getId().toString());
		tBienType.setText(bien.getTypeProduct());
		tOwner.setText(bien.getOwner().toString());
		tName.setText(bien.getName());
		tAddress.setText(bien.getAddress());
		tDescription.setText(bien.getDescription());
		tPrice.setText(bien.getPrice().toString());
		tYearConstruction.setText(bien.getYearConstruction().toString());
		tFaceWide.setText(bien.getFaceWide().toString());
		tFaceNumber.setText(bien.getnFrontage().toString());
		tFloorNumber.setText(bien.getnFloor().toString());
		tCpeb.setText(bien.getCpeb());
	}

	private void onOK() {
		validate = true;
		dispose();
	}

	private void onCancel() {
		dispose();
	}

	public boolean getValidate() {
		return validate;
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
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel2.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
		panel2.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		buttonOK = new JButton();
		buttonOK.setText("OK");
		panel3.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonCancel = new JButton();
		buttonCancel.setText("Cancel");
		panel3.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panel4.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		panel1 = new JPanel();
		panel1.setLayout(new FormLayout("fill:168px:grow,left:4dlu:noGrow,fill:325px:grow", "center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:72px:noGrow,top:3dlu:noGrow,center:94px:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel5.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Id");
		CellConstraints cc = new CellConstraints();
		panel1.add(label1, cc.xy(1, 1));
		final JLabel label2 = new JLabel();
		label2.setText("Propri�taire");
		panel1.add(label2, cc.xy(1, 3));
		tId = new JTextField();
		tId.setEditable(false);
		panel1.add(tId, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		tOwner = new JTextField();
		tOwner.setEditable(false);
		panel1.add(tOwner, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label3 = new JLabel();
		label3.setText("Nom du bien");
		panel1.add(label3, cc.xy(1, 7));
		tName = new JTextField();
		tName.setEditable(false);
		panel1.add(tName, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label4 = new JLabel();
		label4.setText("Description");
		panel1.add(label4, cc.xy(1, 9));
		final JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(30);
		scrollPane1.setVerticalScrollBarPolicy(20);
		panel1.add(scrollPane1, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.FILL));
		scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
		tDescription = new JTextArea();
		tDescription.setColumns(20);
		tDescription.setEditable(false);
		tDescription.setLineWrap(true);
		tDescription.setWrapStyleWord(true);
		scrollPane1.setViewportView(tDescription);
		final JLabel label5 = new JLabel();
		label5.setText("Adresse");
		panel1.add(label5, cc.xy(1, 11));
		final JScrollPane scrollPane2 = new JScrollPane();
		panel1.add(scrollPane2, cc.xy(3, 11, CellConstraints.FILL, CellConstraints.FILL));
		scrollPane2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
		tAddress = new JTextArea();
		tAddress.setEditable(false);
		tAddress.setLineWrap(true);
		tAddress.setWrapStyleWord(true);
		scrollPane2.setViewportView(tAddress);
		final JLabel label6 = new JLabel();
		label6.setText("Prix");
		panel1.add(label6, cc.xy(1, 13));
		tPrice = new JTextField();
		tPrice.setEditable(false);
		panel1.add(tPrice, cc.xy(3, 13, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label7 = new JLabel();
		label7.setText("Ann�e de construction");
		panel1.add(label7, cc.xy(1, 15));
		final JLabel label8 = new JLabel();
		label8.setText("Taille de la fa�ade");
		panel1.add(label8, cc.xy(1, 17));
		final JLabel label9 = new JLabel();
		label9.setText("Nombre de fa�ade");
		panel1.add(label9, cc.xy(1, 19));
		final JLabel label10 = new JLabel();
		label10.setText("Nombre d'�tage");
		panel1.add(label10, cc.xy(1, 21));
		final JLabel label11 = new JLabel();
		label11.setText("CPEB");
		panel1.add(label11, cc.xy(1, 23));
		tYearConstruction = new JTextField();
		tYearConstruction.setEditable(false);
		panel1.add(tYearConstruction, cc.xy(3, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
		tFaceWide = new JTextField();
		tFaceWide.setEditable(false);
		panel1.add(tFaceWide, cc.xy(3, 17, CellConstraints.FILL, CellConstraints.DEFAULT));
		tFaceNumber = new JTextField();
		tFaceNumber.setEditable(false);
		panel1.add(tFaceNumber, cc.xy(3, 19, CellConstraints.FILL, CellConstraints.DEFAULT));
		tFloorNumber = new JTextField();
		tFloorNumber.setEditable(false);
		panel1.add(tFloorNumber, cc.xy(3, 21, CellConstraints.FILL, CellConstraints.DEFAULT));
		tCpeb = new JTextField();
		tCpeb.setEditable(false);
		panel1.add(tCpeb, cc.xy(3, 23, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JLabel label12 = new JLabel();
		label12.setText("Type");
		panel1.add(label12, cc.xy(1, 5));
		tBienType = new JTextField();
		tBienType.setEditable(false);
		panel1.add(tBienType, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel4.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		cStatus = new JComboBox();
		panel6.add(cStatus, cc.xy(3, 1));
		final Spacer spacer2 = new Spacer();
		panel6.add(spacer2, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer3 = new Spacer();
		panel6.add(spacer3, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return contentPane;
	}
}
