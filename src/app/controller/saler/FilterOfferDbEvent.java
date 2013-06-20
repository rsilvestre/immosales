/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.saler;

import app.model.DB.immo.Offer;
import app.model.Saler.SalerModel;
import com.dmurph.mvc.MVCEvent;

/**
 * Created by michaelsilvestre on 18/06/13.
 */
public class FilterOfferDbEvent extends MVCEvent {

	/**
	 * Model li� � l'�v�nement
	 */
	public final SalerModel model;

	/**
	 * Changement de status de l'offre sur un bien
	 */
	public final Offer.Status offerStatus;

	/**
	 * Constructeur
	 * @param argOfferStatus
	 * @param salerModel
	 */
	public FilterOfferDbEvent(Offer.Status argOfferStatus, SalerModel salerModel) {
		super(SalerController.FILTER_OFFER_TYPE);
		offerStatus = argOfferStatus;
		model = salerModel;
	}
}
