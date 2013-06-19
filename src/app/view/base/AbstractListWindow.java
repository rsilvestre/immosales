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
import app.view.Saler.SalerUserControlWindow;
import app.view.bien.BienBuyerWindow;
import app.view.bien.BienSalerWindow;
import app.view.buyer.BienRecorderAndOfferWindow;
import app.view.interet.InterestBuyerWindow;
import app.view.interet.InterestSalerWindow;
import app.view.offer.OfferBuyerDialogWindow;
import app.view.offer.OfferOwnerDialogWindow;
import app.view.offer.OfferSalerDialogWindow;
import app.view.owner.OwnerUserControl;

import javax.swing.*;
import java.util.Enumeration;
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

	protected void buildOfferOwnerModel(List<Offer> listOffers) {
		for (int index = offerModel.getSize() - 1; index >= 0; --index) {
			offerModel.removeElementAt(index);
		}
		for (Offer offer : listOffers) {
			offerModel.addElement(getOfferOwnerList(offer));
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
		if (this instanceof BienRecorderAndOfferWindow) {
			showBienBuyerDetail(selectedValue);
		}
		if (this instanceof SalerUserControlWindow) {
			showBienSalerDetail(selectedValue);
		}
	}

	private void showBienBuyerDetail(ListObject selectedValue) {
		Bien bien = App.em.load(Bien.class, selectedValue.getId());
		BienBuyerWindow bienBuyerWindow = new BienBuyerWindow(bien);
		bienBuyerWindow.pack();
		bienBuyerWindow.setVisible(true);
	}

	private void showBienSalerDetail(ListObject selectedValue) {
		Bien bien = App.em.load(Bien.class, selectedValue.getId());
		BienSalerWindow bienSalerWindow = new BienSalerWindow(bien);
		bienSalerWindow.pack();
		bienSalerWindow.setVisible(true);
		if (bienSalerWindow.getValidate() && !bien.getStatus().equals(bienSalerWindow.getStatus().toString())) {
			bien.setStatus(bienSalerWindow.getStatus().toString());
			App.em.update(bien);
			bienModel.setElementAt(getBienList(bien), bienModel.indexOf(selectedValue));
		}
	}

	private void showOfferDetail(ListObject selectedValue) {
		if (this instanceof BienRecorderAndOfferWindow) {
			showOfferBuyerDetail(selectedValue);
		}
		if (this instanceof SalerUserControlWindow) {
			showOfferSalerDetail(selectedValue);
		}
		if (this instanceof OwnerUserControl) {
			showOfferOwnerDetail(selectedValue);
		}
	}

	private void showOfferBuyerDetail(ListObject selectedValue) {
		Offer offer = App.em.load(Offer.class, selectedValue.getId());
		OfferBuyerDialogWindow offerBuyerDialogWindow = new OfferBuyerDialogWindow(offer);
		offerBuyerDialogWindow.pack();
		offerBuyerDialogWindow.setVisible(true);
	}

	private void showOfferSalerDetail(ListObject selectedValue) {
		Offer offer = App.em.load(Offer.class, selectedValue.getId());
		OfferSalerDialogWindow offerSalerDialogWindow = new OfferSalerDialogWindow(offer);
		offerSalerDialogWindow.pack();
		offerSalerDialogWindow.setVisible(true);
		if (offerSalerDialogWindow.getValidate() && !offer.getStatus().equals(offerSalerDialogWindow.getStatus().toString())) {
			offerBusiness(offer, offerSalerDialogWindow.getStatus());
			offer.setStatus(offerSalerDialogWindow.getStatus().toString());
			App.em.update(offer);
			offerModel.setElementAt(getOfferList(offer), offerModel.indexOf(selectedValue));
		}
	}

	private void offerBusiness(Offer argOffer, Offer.Status status) {
		if (!status.equals(Offer.Status.ACCEPTED)) {
			return;
		}
		for (Offer offer : App.em.find(Offer.class, "where status like 'Offre envoyée' and bien_id  = ? and id != ?", argOffer.getBienId(), argOffer.getId())) {
			offer.setStatus(Offer.Status.REFUSED.toString());
			App.em.update(offer);
			Enumeration<ListObject> listObjectEnumeration = (Enumeration<ListObject>) offerModel.elements();
			while (listObjectEnumeration.hasMoreElements()) {
				ListObject listObject = listObjectEnumeration.nextElement();
				if (listObject.getId().equals(offer.getId())) {
					offerModel.setElementAt(getOfferList(offer), offerModel.indexOf(listObject));
				}
			}
		}
	}

	private void showOfferOwnerDetail(ListObject selectedValue) {
		Offer offer = App.em.load(Offer.class, selectedValue.getId());
		OfferOwnerDialogWindow offerOwnerDialogWindow = new OfferOwnerDialogWindow(offer);
		offerOwnerDialogWindow.pack();
		offerOwnerDialogWindow.setVisible(true);
		if (offerOwnerDialogWindow.getValidate() && !offer.getStatus().equals(offerOwnerDialogWindow.getStatus().toString())) {
			offer.setStatus(offerOwnerDialogWindow.getStatus().toString());
			App.em.update(offer);
			offerModel.setElementAt(getOfferOwnerList(offer), offerModel.indexOf(selectedValue));
		}
	}

	private void showInterestDetail(ListObject selectedValue) {
		if (this instanceof BienRecorderAndOfferWindow) {
			showInterestBuyerDetail(selectedValue);
		}
		if (this instanceof SalerUserControlWindow) {
			showInterestSalerDetail(selectedValue);
		}
	}

	private void showInterestBuyerDetail(ListObject selectedValue) {
		Interest interest = App.em.load(Interest.class, selectedValue.getId());
		InterestBuyerWindow interestBuyerWindow = new InterestBuyerWindow(interest);
		interestBuyerWindow.pack();
		interestBuyerWindow.setVisible(true);
	}

	private void showInterestSalerDetail(ListObject selectedValue) {
		Interest interest = App.em.load(Interest.class, selectedValue.getId());
		InterestSalerWindow interestSalerWindow = new InterestSalerWindow(interest);
		interestSalerWindow.pack();
		interestSalerWindow.setVisible(true);
		if (interestSalerWindow.getValidate() && !interest.getStatus().equals(interestSalerWindow.getStatus().toString())) {
			interest.setStatus(interestSalerWindow.getStatus().toString());
			App.em.update(interest);
			interestModel.setElementAt(getInterestList(interest), interestModel.indexOf(selectedValue));
		}
	}

	protected ListObject getBienList(Bien bien) {
		return new ListObject(ListObject.ListObjectType.BIEN, bien.getId(), bien.getName() + " " + bien.getPrice() + "€ " + bien.getStatus());
	}

	protected ListObject getOfferList(Offer offer) {
		return new ListObject(ListObject.ListObjectType.OFFER, offer.getId(), offer.getBien().getName() + " " + offer.getOffer() + "€ " + offer.getStatus());
	}

	protected ListObject getOfferOwnerList(Offer offer) {
		return new ListObject(ListObject.ListObjectType.OFFER, offer.getId(), offer.getBuyer().toString() + ": " + offer.getOffer() + "€ " + offer.getStatus());
	}

	protected ListObject getInterestList(Interest interest) {
		return new ListObject(ListObject.ListObjectType.INTEREST, interest.getId(), interest.getBien().getName() + " " + interest.getBien().getPrice() + "€ " + interest.getStatus());
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
