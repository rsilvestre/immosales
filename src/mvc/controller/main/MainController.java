/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.controller.main;

import com.dmurph.mvc.control.FrontController;
import mvc.command.main.ConnexionCommand;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class MainController extends FrontController {
	public static final String CONNEXION = "MAIN_CONNEXION";

	public MainController() {
		registerCommand(CONNEXION, ConnexionCommand.class);
	}
}
