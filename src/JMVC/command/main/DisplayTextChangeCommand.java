package JMVC.command.main;

import be.wilthard.essais.JMVC.controller.main.DisplayTextChangeEvent;
import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class DisplayTextChangeCommand implements ICommand {

	@Override
	public void execute(MVCEvent argEvent) {
		// TODO implement this
		DisplayTextChangeEvent event = (DisplayTextChangeEvent) argEvent;
		event.model.setText(event.text);
	}
}
