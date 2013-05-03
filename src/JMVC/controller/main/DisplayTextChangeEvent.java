package JMVC.controller.main;

import be.wilthard.essais.JMVC.controller.main.MainController;
import be.wilthard.essais.JMVC.model.main.MainModel;
import com.dmurph.mvc.MVCEvent;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class DisplayTextChangeEvent extends MVCEvent {

	public final String text;
	public final MainModel model;

	public DisplayTextChangeEvent(String argText, MainModel argModel) {
		super(MainController.DISPLAY_TEXT_CHANGE);
		model = argModel;
		text = argText;
	}
}
