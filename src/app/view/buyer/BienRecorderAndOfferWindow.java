/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.buyer;

import app.view.base.AbstractListWindow;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import core.Session;
import app.App;
import app.model.DB.identity.Buyer;
import app.model.DB.immo.Interest;
import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import app.model.FooModelLocator;
import app.model.bien.FindBienModel;
import app.view.InputDialog.FormatDialogWindow;
import app.view.bien.FindBienWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class BienRecorderAndOfferWindow extends AbstractListWindow {
	private JPanel panel1;
	private JList intéresséList;
	private JList offreDAchatList;
	private JButton bCreateOffer;
	private JButton bRemoveOffer;
	private JButton bRemoveInterest;
	private JButton bRechercheButton;

	private BienRecorderAndOfferWindow bienRecorderAndOfferWindow = this;

	public BienRecorderAndOfferWindow() {
		this.add(panel1);
		initComponents();
		addListener();
		populateLocal();
	}

	private void initComponents() {
		intéresséList.setModel(getInterestModel());
		intéresséList.setMinimumSize(new Dimension(200, 100));
		intéresséList.setPrototypeCellValue("                                                            ");
		offreDAchatList.setModel(getOfferModel());
		offreDAchatList.setMinimumSize(new Dimension(200, 100));
		offreDAchatList.setPrototypeCellValue("                                                            ");
	}

	private void addListener() {
		bRechercheButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				findAction((Buyer) Session.getInstance().getAPerson());
			}
		});
		intéresséList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting() && getInterestModel().getSize() > 0) {
					ListObject listObject = (ListObject) intéresséList.getSelectedValue();
					Interest interest = App.em.load(Interest.class, listObject.getId());
					if (Interest.Status.fromString(interest.getStatus()).equals(Interest.Status.VISITED)) {
						bCreateOffer.setEnabled(true);
						for (Offer offer : interest.getBien().getOffers()) {
							if (offer.getBuyerId().equals(Session.getInstance().getAPerson().getId())) {
								bCreateOffer.setEnabled(false);
							}
						}
					} else {
						bCreateOffer.setEnabled(false);
					}
					if (Interest.Status.fromString(interest.getStatus()).equals(Interest.Status.TOVISIT)) {
						bRemoveInterest.setEnabled(true);
					} else {
						bRemoveInterest.setEnabled(false);
					}
				}
			}
		});
		intéresséList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) intéresséList.getSelectedValue());
				}
			}
		});
		offreDAchatList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting() && getOfferModel().getSize() > 0) {
					ListObject listObject = (ListObject) offreDAchatList.getSelectedValue();
					Offer offer = App.em.load(Offer.class, listObject.getId());
					if (Offer.Status.fromString(offer.getStatus()).equals(Offer.Status.SUBMIT)) {
						bRemoveOffer.setEnabled(true);
					} else {
						bRemoveOffer.setEnabled(false);
					}
				}
			}
		});
		offreDAchatList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) offreDAchatList.getSelectedValue());
				}
			}
		});
		bCreateOffer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ListObject listObject = (ListObject) intéresséList.getSelectedValue();
				Bien bien = App.em.load(Interest.class, listObject.getId()).getBien();

				FormatDialogWindow formatDialogWindow = new FormatDialogWindow("Montant de l'offre: ", bien);
				formatDialogWindow.pack();
				formatDialogWindow.setVisible(true);
				if (formatDialogWindow.getValue() == "-1") {
					return;
				}
				saveOffer(bien, Long.parseLong(formatDialogWindow.getValue()), formatDialogWindow.getDateChooser());
			}
		});
		bRemoveInterest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ListObject listObject = (ListObject) intéresséList.getSelectedValue();
				Interest interest = App.em.load(Interest.class, listObject.getId());
				if (Interest.Status.fromString(interest.getStatus()).equals(Interest.Status.TOVISIT)) {
					interest.setStatus(Interest.Status.CANCELED.toString());
					App.em.update(interest);
					populateLocal();
				}
			}
		});
		bRemoveOffer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ListObject listObject = (ListObject) offreDAchatList.getSelectedValue();
				Offer offer = App.em.load(Offer.class, listObject.getId());
				if (Offer.Status.fromString(offer.getStatus()).equals(Offer.Status.SUBMIT)) {
					offer.setStatus(Offer.Status.CANCELED.toString());
					App.em.update(offer);
					populateLocal();
				}
			}
		});
	}

	private void findAction(Buyer buyer) {
		FooModelLocator locator = FooModelLocator.getInstance();
		FindBienModel findBienModel = new FindBienModel();
		FindBienWindow findBienWindow = new FindBienWindow(findBienModel);
		locator.setFindBienWindow(findBienWindow);
		findBienWindow.pack();
		findBienWindow.setVisible(true);
		if (findBienWindow.getSearchResultValue() == null) {
			return;
		}
		saveInterest(findBienWindow, buyer);
	}

	private boolean isNewOffer(Long bienId, Buyer buyer) {
		Offer findOffer = App.em.findUnique(Offer.class, "where bien_id = ? and buyer_id = ?", bienId, buyer.getId());
		if (findOffer != null) {
			return false;
		}
		return true;
	}

	private void saveOffer(Bien bien, Long offerValue, Calendar calendar) {
		Timestamp endDate = new Timestamp(calendar.getTimeInMillis());
		Offer offer = new Offer((Buyer) Session.getInstance().getAPerson(), bien, Offer.Status.SUBMIT, offerValue, endDate);
		App.em.insert(offer);
		getOfferModel().addElement(getOfferList(offer));
	}

	private void saveInterest(FindBienWindow findBienWindow, Buyer buyer) {
		Interest findInterest = App.em.findUnique(Interest.class, "where bien_id = ? and buyer_id = ?", findBienWindow.getSearchResultValue(), buyer.getId());
		if (findInterest != null) {
			JOptionPane.showMessageDialog(this, "Vous avez déjà sélectionné ce bien ultérieurement");
			return;
		}
		Bien bien = App.em.load(Bien.class, findBienWindow.getSearchResultValue());
		Interest interest = new Interest((Buyer) Session.getInstance().getAPerson(), bien, Interest.Status.TOVISIT);
		App.em.insert(interest);
		getInterestModel().addElement(getInterestList(interest));
	}

	private void populateLocal() {
		bRemoveInterest.setEnabled(false);
		bRemoveOffer.setEnabled(false);
		bCreateOffer.setEnabled(false);
		List<Interest> interests = App.em.find(Interest.class, "where buyer_id = ?", Session.getInstance().getAPerson().getId());
		List<Offer> offers = App.em.find(Offer.class, "where buyer_id = ?", Session.getInstance().getAPerson().getId());

		this.buildInterestModel(interests);
		this.buildOfferModel(offers);
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
		panel1.setLayout(new FormLayout("fill:max(d;4px):noGrow", "center:max(d;4px):noGrow"));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:795px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xy(1, 1));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow"));
		panel2.add(panel3, cc.xy(1, 1));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FormLayout("fill:144px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel4, cc.xy(3, 1));
		bCreateOffer = new JButton();
		bCreateOffer.setEnabled(false);
		bCreateOffer.setText("Faire une offre");
		panel4.add(bCreateOffer, cc.xy(1, 1));
		bRemoveOffer = new JButton();
		bRemoveOffer.setEnabled(false);
		bRemoveOffer.setText("Retirer une offre");
		panel4.add(bRemoveOffer, cc.xy(1, 3));
		bRemoveInterest = new JButton();
		bRemoveInterest.setEnabled(false);
		bRemoveInterest.setText("Annuler une visite");
		panel4.add(bRemoveInterest, cc.xy(1, 5));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:317px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
		panel3.add(panel5, cc.xy(5, 1));
		final JLabel label1 = new JLabel();
		label1.setText("Offre d'achat");
		panel5.add(label1, cc.xy(1, 1));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel5.add(scrollPane1, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		offreDAchatList = new JList();
		scrollPane1.setViewportView(offreDAchatList);
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:318px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
		panel3.add(panel6, cc.xy(1, 1));
		final JLabel label2 = new JLabel();
		label2.setText("Rendez-vous");
		panel6.add(label2, cc.xy(1, 1));
		final JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setMinimumSize(new Dimension(170, 80));
		panel6.add(scrollPane2, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		intéresséList = new JList();
		intéresséList.setMinimumSize(new Dimension(170, 80));
		scrollPane2.setViewportView(intéresséList);
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow"));
		panel2.add(panel7, cc.xy(1, 3));
		bRechercheButton = new JButton();
		bRechercheButton.setText("Nouvelle recherche");
		panel7.add(bRechercheButton, cc.xy(3, 1));
		final Spacer spacer1 = new Spacer();
		panel7.add(spacer1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel7.add(spacer2, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		label1.setLabelFor(scrollPane1);
		label2.setLabelFor(scrollPane2);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
