/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.owner;

import com.dmurph.mvc.model.AbstractModel;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 11/05/13
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class OwnerPanelModel extends AbstractModel {

	/**
	 * Changement de ville en fonction de la ville pour l'utilisateur
	 */
	private String city;

	/**
	 * Changement de ville en fonction du code postal pour l'utilisateur
	 */
	private String cpValue;

	/**
	 * Mise à feu du changement de ville
	 * @param argCity
	 */
	public void setCity(String argCity) {
		String oldCity = city;
		city = argCity;
		firePropertyChange("newCity", oldCity, city);
	}

	/**
	 * Set de la ville par défaut
	 * @param argCity
	 */
	public void setDefaultCity(String argCity) {
		city = argCity;
	}

	/**
	 * Mise à feu du changement de code postal
	 * @param argCpValue
	 */
	public void setCpValue(String argCpValue) {
		String oldCpValue = cpValue;
		cpValue = argCpValue;
		firePropertyChange("newCp", oldCpValue, cpValue);
	}

	/**
	 * Set du code postal par défaut
	 * @param argCpValue
	 */
	public void setDefaultCpValue(String argCpValue) {
		cpValue = argCpValue;
	}
}
