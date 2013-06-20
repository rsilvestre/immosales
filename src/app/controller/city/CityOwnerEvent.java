/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.controller.city;

import com.dmurph.mvc.MVCEvent;
import app.model.owner.OwnerPanelModel;

/**
 * Created by michaelsilvestre on 26/05/13.
 */
public class CityOwnerEvent extends MVCEvent {

	/**
	 * Model li� � l'�v�nement
	 */
	public final OwnerPanelModel model;

	/**
	 * Changement du nom de la ville
	 */
	public final String cityName;

	/**
	 * Constructeur
	 * @param argCityName
	 * @param argModel
	 */
	public CityOwnerEvent(String argCityName, OwnerPanelModel argModel) {
		super(CityController.OWNER_CITY_CITY);
		this.model = argModel;
		this.cityName = argCityName;
	}

}
