/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.command.saler;

import app.controller.saler.FilterOfferDbEvent;
import com.dmurph.mvc.MVCEvent;
import com.dmurph.mvc.control.ICommand;

/**
 * Created by michaelsilvestre on 18/06/13.
 */
public class FilterOfferDbCommand implements ICommand {
	/**
	 * Lance l'événement de filtrage des offres
	 * @param argEvent
	 */
	@Override
	public void execute(MVCEvent argEvent) {
		FilterOfferDbEvent event = (FilterOfferDbEvent)argEvent;
		event.model.setFilterOfferType(event.offerStatus);
	}
}
