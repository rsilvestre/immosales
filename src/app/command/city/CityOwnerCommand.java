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
import app.controller.city.CityOwnerEvent;

/**
 * Created by michaelsilvestre on 26/05/13.
 */
public class CityOwnerCommand implements ICommand {

	/**
	 * Lance l'�v�nement de recherche d'un bien cot� propri�taire sur base de la ville
	 * @param mvcEvent
	 */
	@Override
	public void execute(MVCEvent mvcEvent) {
		CityOwnerEvent event = (CityOwnerEvent)mvcEvent;
		event.model.setCity(event.cityName);
	}
}
