package JMVC.controller.main;

import be.wilthard.essais.JMVC.command.main.ChangeColorCommand;
import be.wilthard.essais.JMVC.command.main.DisplayTextChangeCommand;
import com.dmurph.mvc.control.FrontController;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class MainController extends FrontController {
	// event keys.  The all capital letters is just my preference for the actual keys
	public static final String COLOR_CHANGE = "MAIN_COLOR_CHANGE";
	public static final String DISPLAY_TEXT_CHANGE = "MAIN_DISPLAY_TEXT_CHANGE";

	public MainController() {
		registerCommand(COLOR_CHANGE, ChangeColorCommand.class);
		registerCommand(DISPLAY_TEXT_CHANGE, DisplayTextChangeCommand.class);
	}
}
