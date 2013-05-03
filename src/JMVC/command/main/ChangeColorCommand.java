package JMVC.command.main;

import JMVC.controller.main.ColorChangeEvent;
import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class ChangeColorCommand implements ICommand {

	@Override
	public void execute(MVCEvent argEvent) {
		ColorChangeEvent event = (ColorChangeEvent) argEvent;
		event.model.setColor(event.color);
	}
}
