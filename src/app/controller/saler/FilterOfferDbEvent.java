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
	public final SalerModel model;
	public final Offer.OfferStatus offerStatus;

	public FilterOfferDbEvent(Offer.OfferStatus argOfferStatus, SalerModel salerModel) {
		super(SalerController.FILTER_OFFER_TYPE);
		offerStatus = argOfferStatus;
		model = salerModel;
	}
}
