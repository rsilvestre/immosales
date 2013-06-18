/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.base;

import app.App;
import app.model.DB.immo.Interest;
import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import app.view.bien.BienSalerWindow;
import app.view.offer.OfferDialogWindow;

import javax.swing.*;
import java.util.List;

/**
 * Created by michaelsilvestre on 18/06/13.
 */
abstract public class AbstractListWindow extends JPanel {
	private DefaultListModel interestModel;
	private DefaultListModel offerModel;
	private DefaultListModel bienModel;

	public AbstractListWindow() {
		interestModel = new DefaultListModel();
		offerModel = new DefaultListModel();
		bienModel = new DefaultListModel();
	}

	protected void buildBienModel(List<Bien> listBiens) {
		for (int index = bienModel.getSize() - 1; index >= 0; --index) {
			bienModel.removeElementAt(index);
		}
		for (Bien bien : listBiens) {
			bienModel.addElement(getBienList(bien));
		}
	}

	protected void buildOfferModel(List<Offer> listOffers) {
		for (int index = offerModel.getSize() - 1; index >= 0; --index) {
			offerModel.removeElementAt(index);
		}
		for (Offer offer : listOffers) {
			offerModel.addElement(getOfferList(offer));
		}
	}

	protected void buildInterestModel(List<Interest> listInterest) {
		for (int index = interestModel.getSize() - 1; index >= 0; --index) {
			interestModel.removeElementAt(index);
		}
		for (Interest interest : listInterest) {
			interestModel.addElement(getInterestList(interest));
		}
	}

	protected void showDetails(ListObject selectedValue) {
		if (selectedValue.getType() == ListObject.ListObjectType.BIEN) {
			showBienDetail(selectedValue);
			return;
		} else if (selectedValue.getType() == ListObject.ListObjectType.OFFER) {
			showOfferDetail(selectedValue);
			return;
		} else if (selectedValue.getType() == ListObject.ListObjectType.INTEREST) {
			showInterestDetail(selectedValue);
			return;
		}
		JOptionPane.showMessageDialog(this, "Pas d'action sur : " + selectedValue.getId() + " | " + selectedValue.toString());
	}

	private void showBienDetail(ListObject selectedValue) {
		Bien bien = App.em.load(Bien.class, selectedValue.getId());
		BienSalerWindow bienSalerWindow = new BienSalerWindow(bien);
		bienSalerWindow.pack();
		bienSalerWindow.setVisible(true);
		if (bienSalerWindow.getValidate() && !bien.getStatus().equals(bienSalerWindow.getBienStatus().toString())) {
			bien.setStatus(bienSalerWindow.getBienStatus().toString());
			App.em.update(bien);
			bienModel.setElementAt(getBienList(bien), bienModel.indexOf(selectedValue));
		}
	}

	private void showOfferDetail(ListObject selectedValue) {
		Offer offer = App.em.load(Offer.class, selectedValue.getId());
		OfferDialogWindow offerDialogWindow = new OfferDialogWindow(offer);
		offerDialogWindow.pack();
		offerDialogWindow.setVisible(true);
		if (offerDialogWindow.getValidate() && !offer.getStatus().equals(offerDialogWindow.getOfferStatus().toString())) {
			offer.setStatus(offerDialogWindow.getOfferStatus().toString());
			App.em.update(offer);
			offerModel.setElementAt(getOfferList(offer), offerModel.indexOf(selectedValue));
		}
	}

	private void showInterestDetail(ListObject selectedValue) {
		Bien bien = App.em.load(Interest.class, selectedValue.getId()).getBien();
		BienSalerWindow bienSalerWindow = new BienSalerWindow(bien);
		bienSalerWindow.pack();
		bienSalerWindow.setVisible(true);
		if (bienSalerWindow.getValidate() && !bien.getStatus().equals(bienSalerWindow.getBienStatus().toString())) {
			bien.setStatus(bienSalerWindow.getBienStatus().toString());
			App.em.update(bien);
			bienModel.setElementAt(getBienList(bien), bienModel.indexOf(selectedValue));
		}
	}

	protected ListObject getBienList(Bien bien) {
		return new ListObject(ListObject.ListObjectType.BIEN, bien.getId(), bien.getName() + " " + bien.getPrice() + " " + bien.getStatus());
	}

	protected ListObject getOfferList(Offer offer) {
		return new ListObject(ListObject.ListObjectType.OFFER, offer.getId(), offer.getBien().getName() + " " + offer.getOffer() + " " + offer.getStatus());
	}

	protected ListObject getInterestList(Interest interest) {
		return new ListObject(ListObject.ListObjectType.INTEREST, interest.getId(), interest.getBien().getName() + " " + interest.getBien().getPrice() + " " + interest.getBien().getStatus());
	}

	protected DefaultListModel getInterestModel() {
		return interestModel;
	}

	protected DefaultListModel getOfferModel() {
		return offerModel;
	}

	protected DefaultListModel getBienModel() {
		return bienModel;
	}

	protected static class ListObject {

		public enum ListObjectType {OFFER, BIEN, INTEREST}

		private Long id;
		private String row;
		private ListObjectType type;

		public ListObject(ListObjectType type, Long id, String row) {
			this.type = type;
			this.id = id;
			this.row = row;
		}

		public ListObjectType getType() {
			return type;
		}

		public Long getId() {
			return id;
		}

		@Override
		public String toString() {
			return row;
		}

	}
}
