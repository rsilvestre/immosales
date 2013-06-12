/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.buyer;

import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import core.Session;
import mvc.App;
import mvc.model.DB.identity.Buyer;
import mvc.model.DB.identity.Owner;
import mvc.model.DB.immo.Interest;
import mvc.model.DB.immo.Offer;
import mvc.model.DB.product.Bien;
import mvc.model.FooModelLocator;
import mvc.model.buyer.BienRecorderAndOfferModel;
import mvc.model.bien.FindBienModel;
import mvc.view.InputDialog.FormatDialogWindow;
import mvc.view.bien.FindBienWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class BienRecorderAndOfferWindow extends JPanel {
	private JPanel panel1;
	private JList intéresséList;
	private JList offreDAchatList;
	private DefaultListModel interestModel;
	private DefaultListModel offerModel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton nouvelleRechercheButton;

	private BienRecorderAndOfferModel bienRecorderAndOfferModel;

	private BienRecorderAndOfferWindow bienRecorderAndOfferWindow = this;

	public BienRecorderAndOfferWindow(BienRecorderAndOfferModel bienRecorderAndOfferModel) {
		this.add(panel1);
		initComponents();
		addListener();
		populateLocal();
	}

	private void initComponents() {
		interestModel = new DefaultListModel();
		intéresséList.setModel(interestModel);
		intéresséList.setMinimumSize(new Dimension(200, 100));
		intéresséList.setPrototypeCellValue("                                                            ");
		offerModel = new DefaultListModel();
		offreDAchatList.setModel(offerModel);
		offreDAchatList.setMinimumSize(new Dimension(200, 100));
		offreDAchatList.setPrototypeCellValue("                                                            ");
	}

	private void addListener() {
		nouvelleRechercheButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				findAction((Buyer) Session.getInstance().getAPerson());
			}
		});
	}

	private void findAction(Buyer buyer) {
		FindBienWindow findBienWindow = getFindBienWindow();
		if (findBienWindow.getSearchResultType() == FindBienWindow.FindBienType.NULL) {
			return;
		}

		if (findBienWindow.getSearchResultType() == FindBienWindow.FindBienType.INTEREST) {
			saveInterest(findBienWindow, buyer);
		}
		if (findBienWindow.getSearchResultType() == FindBienWindow.FindBienType.OFFRE) {
			FormatDialogWindow formatDialogWindow = new FormatDialogWindow("Montant de l'offre :");
			formatDialogWindow.pack();
			formatDialogWindow.setVisible(true);
			if (formatDialogWindow.getValue() == "-1") {
				return;
			}
			saveOffer(findBienWindow, Long.parseLong(formatDialogWindow.getValue()), buyer);
		}
	}

	private void saveOffer(FindBienWindow findBienWindow, Long offerValue, Buyer buyer) {
		Offer findOffer = App.em.findUnique(Offer.class, "where bien_id = ? and buyer_id = ?", findBienWindow.getSearchResultValue(), buyer.getId());
		if (findOffer != null) {
			JOptionPane.showMessageDialog(this, "Vous avez déjà sélectionné ce bien ultérieurement");
			return;
		}
		Bien bien = App.em.load(Bien.class, findBienWindow.getSearchResultValue());
		Offer offer = new Offer((Buyer) Session.getInstance().getAPerson(), bien, Offer.OfferStatus.SUBMIT, offerValue);
		App.em.insert(offer);
		offerModel.addElement(bien.getName());
	}

	private void saveInterest(FindBienWindow findBienWindow, Buyer buyer) {
		Interest findInterest = App.em.findUnique(Interest.class, "where bien_id = ? and buyer_id = ?", findBienWindow.getSearchResultValue(), buyer.getId());
		if (findInterest != null) {
			JOptionPane.showMessageDialog(this, "Vous avez déjà sélectionné ce bien ultérieurement");
			return;
		}
		Bien bien = App.em.load(Bien.class, findBienWindow.getSearchResultValue());
		Interest interest = new Interest((Buyer) Session.getInstance().getAPerson(), bien);
		App.em.insert(interest);
		interestModel.addElement(bien.getName());
	}

	private FindBienWindow getFindBienWindow() {
		//JOptionPane.showMessageDialog(null, "alert");
		FooModelLocator locator = FooModelLocator.getInstance();
		FindBienModel findBienModel = new FindBienModel();
		FindBienWindow findBienWindow = new FindBienWindow(findBienModel);
		locator.setFindBienWindow(findBienWindow);
		findBienWindow.pack();
		findBienWindow.setVisible(true);
		return findBienWindow;
	}

	private void populateLocal() {
		List<Interest> interestList = App.em.find(Interest.class, "where buyer_id = ?", Session.getInstance().getAPerson().getId());
		List<Offer> offerList = App.em.find(Offer.class, "where buyer_id = ?", Session.getInstance().getAPerson().getId());
		for (Interest interest : interestList) {
			interestModel.addElement(interest.getBien().getName());
		}
		for (Offer offer : offerList) {
			offerModel.addElement(offer.getBien().getName());
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
		panel1.setLayout(new FormLayout("fill:max(d;4px):noGrow", "center:max(d;4px):noGrow"));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:636px:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xy(1, 1));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow"));
		panel2.add(panel3, cc.xy(1, 1));
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		panel3.add(panel4, cc.xy(3, 1));
		final JLabel label1 = new JLabel();
		label1.setText("Label");
		panel4.add(label1, cc.xy(1, 1));
		final JLabel label2 = new JLabel();
		label2.setText("Label");
		panel4.add(label2, cc.xy(1, 3));
		final JLabel label3 = new JLabel();
		label3.setText("Label");
		panel4.add(label3, cc.xy(1, 5));
		button1 = new JButton();
		button1.setText("Button");
		panel4.add(button1, cc.xy(3, 1));
		button2 = new JButton();
		button2.setText("Button");
		panel4.add(button2, cc.xy(3, 3));
		button3 = new JButton();
		button3.setText("Button");
		panel4.add(button3, cc.xy(3, 5));
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
		panel3.add(panel5, cc.xy(5, 1));
		final JLabel label4 = new JLabel();
		label4.setText("Offre d'achat");
		panel5.add(label4, cc.xy(1, 1));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel5.add(scrollPane1, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		offreDAchatList = new JList();
		scrollPane1.setViewportView(offreDAchatList);
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
		panel3.add(panel6, cc.xy(1, 1));
		final JLabel label5 = new JLabel();
		label5.setText("Intéressé");
		panel6.add(label5, cc.xy(1, 1));
		final JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setMinimumSize(new Dimension(170, 80));
		panel6.add(scrollPane2, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		intéresséList = new JList();
		intéresséList.setMinimumSize(new Dimension(170, 80));
		scrollPane2.setViewportView(intéresséList);
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new FormLayout("fill:247px:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow"));
		panel2.add(panel7, cc.xy(1, 3));
		final JPanel panel8 = new JPanel();
		panel8.setLayout(new FormLayout("fill:254px:noGrow", "center:d:noGrow"));
		panel7.add(panel8, cc.xy(1, 1));
		final Spacer spacer1 = new Spacer();
		panel8.add(spacer1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		nouvelleRechercheButton = new JButton();
		nouvelleRechercheButton.setText("Nouvelle recherche");
		panel7.add(nouvelleRechercheButton, cc.xy(3, 1));
		label4.setLabelFor(scrollPane1);
		label5.setLabelFor(scrollPane2);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
