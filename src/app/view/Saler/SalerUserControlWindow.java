/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.Saler;

import app.controller.saler.FilterBienDbEvent;
import app.controller.saler.FilterInterestDbEvent;
import app.controller.saler.FilterOfferDbEvent;
import app.model.DB.immo.Interest;
import app.view.base.AbstractListWindow;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import app.App;
import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import app.model.Saler.SalerModel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Created by michaelsilvestre on 12/06/13.
 */
public class SalerUserControlWindow extends AbstractListWindow {
	private JPanel panel1;
	private JList bienList;
	private JList interestList;
	private JList offerList;

	private JComboBox cInterestList;
	private JComboBox cBienList;
	private JComboBox cOfferList;

	private SalerModel salerModel;

	public SalerUserControlWindow(SalerModel salerModel) {
		this.salerModel = salerModel;
		this.add(panel1);
		initComponents();
		linkModel();
		addListener();
		populateLocal();
	}

	private void initComponents() {
		bienList.setModel(getBienModel());
		offerList.setModel(getOfferModel());
		interestList.setModel(getInterestModel());
	}

	private void linkModel() {

		salerModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				if (name.equals("bienFilter")) {
					String statusValue = (evt.getNewValue() != null && !evt.getNewValue().equals("")) ? " and status like '" + evt.getNewValue() + "'" : "";
					List<Bien> biens = App.em.find(Bien.class, "where 1=1" + statusValue);
					SalerUserControlWindow.this.buildBienModel(biens);
				}
				if (name.equals("offerFilter")) {
					String statusValue = (evt.getNewValue() != null && !evt.getNewValue().equals("")) ? " and status like '" + evt.getNewValue() + "'" : "";
					List<Offer> offers = App.em.find(Offer.class, "where 1=1" + statusValue);
					SalerUserControlWindow.this.buildOfferModel(offers);
				}
				if (name.equals("interestFilter")) {
					String statusValue = (evt.getNewValue() != null && !evt.getNewValue().equals("")) ? " and status like '" + evt.getNewValue() + "'" : "";
					List<Interest> interests = App.em.find(Interest.class, "where 1=1" + statusValue);
					SalerUserControlWindow.this.buildInterestModel(interests);
				}
			}
		});
	}

	private void addListener() {
		bienList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) bienList.getSelectedValue());
				}
			}
		});
		offerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) offerList.getSelectedValue());
				}
			}
		});
		interestList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) interestList.getSelectedValue());
				}
			}
		});
		cBienList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FilterBienDbEvent event = new FilterBienDbEvent(Bien.Status.fromString((String) cBienList.getSelectedItem()), salerModel);
					event.dispatch();
				}
			}
		});
		cOfferList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FilterOfferDbEvent event = new FilterOfferDbEvent(Offer.Status.fromString((String) cOfferList.getSelectedItem()), salerModel);
					event.dispatch();
				}
			}
		});
		cInterestList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					FilterInterestDbEvent event = new FilterInterestDbEvent(Interest.Status.fromString((String) cInterestList.getSelectedItem()), salerModel);
					event.dispatch();
				}
			}
		});
	}

	private void populateLocal() {
		cBienList.addItem("");
		for (Bien.Status bienStatus : Bien.Status.values()) {
			cBienList.addItem(bienStatus.toString());
		}
		cOfferList.addItem("");
		for (Offer.Status offerStatus : Offer.Status.values()) {
			cOfferList.addItem(offerStatus.toString());
		}
		cInterestList.addItem("");
		for (Interest.Status interestStatus : Interest.Status.values()) {
			cInterestList.addItem(interestStatus.toString());
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
		panel1.setLayout(new FormLayout("fill:780px:grow", "center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow"));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xy(1, 3));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel1.add(panel3, cc.xy(1, 1));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:361px:grow"));
		panel3.add(panel4, cc.xy(1, 3));
		cBienList = new JComboBox();
		panel4.add(cBienList, cc.xy(1, 3));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel4.add(panel5, cc.xy(1, 1));
		final JLabel label1 = new JLabel();
		label1.setText("Biens");
		panel5.add(label1, cc.xy(3, 1));
		final Spacer spacer1 = new Spacer();
		panel5.add(spacer1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel5.add(spacer2, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel4.add(scrollPane1, cc.xy(1, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
		bienList = new JList();
		scrollPane1.setViewportView(bienList);
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel6, cc.xy(3, 3));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:143px:grow"));
		panel6.add(panel7, cc.xy(1, 1));
		cInterestList = new JComboBox();
		panel7.add(cInterestList, cc.xy(1, 3));
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel7.add(panel8, cc.xy(1, 1));
		final JLabel label2 = new JLabel();
		label2.setText("Demande de visite");
		panel8.add(label2, cc.xy(3, 1));
		final Spacer spacer3 = new Spacer();
		panel8.add(spacer3, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer4 = new Spacer();
		panel8.add(spacer4, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JScrollPane scrollPane2 = new JScrollPane();
		panel7.add(scrollPane2, cc.xy(1, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
		interestList = new JList();
		scrollPane2.setViewportView(interestList);
		final JPanel panel9 = new JPanel();
		panel9.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:156px:grow"));
		panel6.add(panel9, cc.xy(1, 3));
		cOfferList = new JComboBox();
		panel9.add(cOfferList, cc.xy(1, 5));
		final JPanel panel10 = new JPanel();
		panel10.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel9.add(panel10, cc.xy(1, 3));
		final JLabel label3 = new JLabel();
		label3.setText("Offres");
		panel10.add(label3, cc.xy(3, 1));
		final Spacer spacer5 = new Spacer();
		panel10.add(spacer5, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer6 = new Spacer();
		panel10.add(spacer6, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JScrollPane scrollPane3 = new JScrollPane();
		panel9.add(scrollPane3, cc.xy(1, 7, CellConstraints.DEFAULT, CellConstraints.FILL));
		offerList = new JList();
		scrollPane3.setViewportView(offerList);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
