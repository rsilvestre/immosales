/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.buyer;

import app.model.DB.product.Bien;
import app.model.buyer.BuyerModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * Created by michaelsilvestre on 17/05/13.
 */
public class BuyerWindow extends JPanel {

	private String [] columns = {"Id", "Type", "Label", "Description", "Prix", "Edit"};

	private JTable jtable;
	private DefaultTableModel defaultTableModel;
	private JTextField txtCity;
	private JTextField txtPosteCode;
	private JCheckBox chkType;
	private JButton btnSearch;


	private BuyerModel buyerModel;

	private List<Bien> biens = null;

	public BuyerWindow(BuyerModel buyerModel) {
		this.buyerModel = buyerModel;

		initComponents();
		linkModel();
		addListeners();
		populateLocale();
	}

	private void initComponents() {
		biens = new ArrayList<Bien>();
		setLayout(new BorderLayout());

		add(new JLabel("Liste de vos biens"), BorderLayout.NORTH);

		defaultTableModel = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtable = new JTable(defaultTableModel);
		add(new JScrollPane(jtable), BorderLayout.CENTER);

		btnSearch = new JButton();
		txtCity = new JTextField();
		txtPosteCode = new JTextField();
		Box box = Box.createHorizontalBox();
		box.add(new JLabel("Ville :"));
		box.add(txtCity);
		box.add(new JLabel("CP :"));
		box.add(txtPosteCode);
		box.add(btnSearch);
		add(box, BorderLayout.SOUTH);

	}

	private void linkModel() {

	}

	private void addListeners() {
		txtPosteCode.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					evt.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent keyEvent) {

			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {

			}
		});

	}

	private void populateLocale() {

	}

}
