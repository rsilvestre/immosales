/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.command.city;

import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;
import app.controller.city.CpFindBienEvent;

/**
 * Created by michaelsilvestre on 26/05/13.
 */
public class CpFindBienCommand implements ICommand {

	/**
	 * Lance l'événement de recherche d'un bien sur base du code postal
	 * @param mvcEvent
	 */
	@Override
	public void execute(MVCEvent mvcEvent) {
		CpFindBienEvent event = (CpFindBienEvent)mvcEvent;
		event.model.setCpValue(event.cpValue);
	}
}
