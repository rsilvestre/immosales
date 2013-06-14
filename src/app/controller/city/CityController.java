/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.city;

import com.dmurph.mvc.control.FrontController;
import app.command.city.*;

/**
 * Created by michaelsilvestre on 26/05/13.
 */
public class CityController extends FrontController {
	public static final String USER_CITY_CITY = "USER_CITY_CITY_CONTROLLER";
	public static final String USER_CITY_CP = "USER_CITY_CP_CONTROLLER";
	public static final String OWNER_CITY_CITY = "OWNER_CITY_CITY_CONTROLLER";
	public static final String OWNER_CITY_CP = "OWNER_CITY_CP_CONTROLLER";
	public static final String FIND_BIEN_CITY_CITY = "FIND_BIEN_CITY_CITY_CONTROLLER";
	public static final String FIND_BIEN_CITY_CP = "FIND_BIEN_CITY_CP_CONTROLLER";

	public CityController() {
		registerCommand(USER_CITY_CITY, CityUserCommand.class);
		registerCommand(USER_CITY_CP, CpUserCommand.class);
		registerCommand(OWNER_CITY_CITY, CityOwnerCommand.class);
		registerCommand(OWNER_CITY_CP, CpOwnerCommand.class);
		registerCommand(FIND_BIEN_CITY_CITY, CityFindBienCommand.class);
		registerCommand(FIND_BIEN_CITY_CP, CpFindBienCommand.class);
	}
}
