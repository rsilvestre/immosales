/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.city;

import com.dmurph.mvc.MVCEvent;
import app.model.user.UserPanelModel;

/**
 * Created by michaelsilvestre on 26/05/13.
 */
public class CpUserEvent extends MVCEvent {

	/**
	 * Model lié à l'événement
	 */
	public final UserPanelModel model;

	/**
	 * Changement du code postal
	 */
	public final String cpValue;

	/**
	 * Constructeur
	 * @param argCpValue
	 * @param argModel
	 */
	public CpUserEvent(String argCpValue, UserPanelModel argModel) {
		super(CityController.USER_CITY_CP);
		this.model = argModel;
		this.cpValue = argCpValue;
	}
}
