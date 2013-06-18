/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.Saler;

import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import com.dmurph.mvc.model.AbstractModel;

/**
 * Created by michaelsilvestre on 12/06/13.
 */
public class SalerModel extends AbstractModel {
	private Bien.BienStatus filterBienType;
	private Offer.OfferStatus filterOfferType;

	public void setFilterBienType(Bien.BienStatus argFilterBienType) {
		Bien.BienStatus oldBienStatus = filterBienType;
		this.filterBienType = argFilterBienType;
		firePropertyChange("bienFilter", oldBienStatus, filterBienType);
	}

	public void setFilterOfferType(Offer.OfferStatus argFilterOfferType) {
		Offer.OfferStatus oldOfferStatus = filterOfferType;
		this.filterOfferType = argFilterOfferType;
		firePropertyChange("offerFilter", oldOfferStatus, filterOfferType);
	}
}
