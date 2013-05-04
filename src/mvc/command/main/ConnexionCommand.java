/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.command.main;

import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;
import mvc.controller.main.ConnexionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class ConnexionCommand implements ICommand {

	@Override
	public void execute(MVCEvent mvcEvent) {
		//To change body of implemented methods use File | Settings | File Templates.
		ConnexionEvent event = (ConnexionEvent) mvcEvent;
		event.model.setSession(event.session);
	}
}