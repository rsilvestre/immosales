/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.controller.buyer;

import com.dmurph.mvc.control.FrontController;
import mvc.command.buyer.FindBienDbCommand;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class BuyerController extends FrontController{
	public static final String FIND = "FIND_BIEN_DB";

		public BuyerController() {
			registerCommand(FIND, FindBienDbCommand.class);
		}
}
