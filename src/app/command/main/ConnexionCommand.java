/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.command.main;

import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;
import app.controller.main.ConnexionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class ConnexionCommand implements ICommand {

	/**
	 * Lance l'�v�nement de connexion d'un utilisateur � l'application
	 * @param mvcEvent
	 */
	@Override
	public void execute(MVCEvent mvcEvent) {
		ConnexionEvent event = (ConnexionEvent) mvcEvent;
		event.model.setAPerson(event.aPerson);
	}
}
