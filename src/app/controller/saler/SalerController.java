/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.saler;

import app.command.saler.FilterBienDbCommand;
import app.command.saler.FilterOfferDbCommand;
import com.dmurph.mvc.control.FrontController;

/**
 * Created by michaelsilvestre on 12/06/13.
 */
public class SalerController extends FrontController {
	public static final String FILTER_BIEN_TYPE = "FILTER_BIEN_TYPE_CONTROLLER";
	public static final String FILTER_OFFER_TYPE = "FILTER_OFFER_TYPE_CONTROLLER";

	public SalerController() {
		registerCommand(FILTER_BIEN_TYPE, FilterBienDbCommand.class);
		registerCommand(FILTER_OFFER_TYPE, FilterOfferDbCommand.class);
	}
}
