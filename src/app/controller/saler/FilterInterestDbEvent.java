/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.saler;

import app.model.DB.immo.Interest;
import app.model.Saler.SalerModel;
import com.dmurph.mvc.MVC;
import com.dmurph.mvc.MVCEvent;

/**
 * Created by michaelsilvestre on 18/06/13.
 */
public class FilterInterestDbEvent extends MVCEvent {

	/**
	 * Model lié à l'événement
	 */
	public final SalerModel model;

	/**
	 * Changement du status de l'intérêt porté à un bien
	 */
	public final Interest.Status interestStatus;

	/**
	 * Constructeur
	 * @param interestStatus
	 * @param salerModel
	 */
	public FilterInterestDbEvent(Interest.Status interestStatus, SalerModel salerModel) {
		super(SalerController.FILTER_INTEREST_TYPE);
		this.interestStatus = interestStatus;
		this.model = salerModel;
	}
}
