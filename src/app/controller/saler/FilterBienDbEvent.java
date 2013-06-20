/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.saler;

import app.model.DB.product.Bien;
import app.model.Saler.SalerModel;
import com.dmurph.mvc.MVCEvent;

/**
 * Created by michaelsilvestre on 18/06/13.
 */
public class FilterBienDbEvent extends MVCEvent {

	/**
	 * Model lié à l'événement
	 */
	public final SalerModel model;

	/**
	 * Changement du filtrage sur les biens
	 */
	public final Bien.Status filterBienType;

	/**
	 * Constructeur
	 * @param bienStatus
	 * @param salerModel
	 */
	public FilterBienDbEvent(Bien.Status bienStatus, SalerModel salerModel) {
		super(SalerController.FILTER_BIEN_TYPE);
		filterBienType = bienStatus;
		model = salerModel;
	}
}
