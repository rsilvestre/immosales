/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.user;

import com.dmurph.mvc.model.AbstractModel;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class UserPanelModel extends AbstractModel {

	/**
	 * Rercherche de ville sur base du nom de la ville au niveau de l'utilisateur
	 */
	private String city;

	/**
	 * Recherche de ville sur base du code postal au niveau de l'utilisateur
	 */
	private String cpValue;

	/**
	 * Mise à feu de recherche de ville
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
	 * Set du code postal par défaut
	 * @return
	 */
	public String getCpValue() {
		return cpValue;
	}

	/**
	 * Mise à feu du code postal
	 * @param argCpValue
	 */
	public void setCpValue(String argCpValue) {
		String oldCpValue = cpValue;
		cpValue = argCpValue;
		firePropertyChange("newCp", oldCpValue, cpValue);
	}

	/**
	 * Code postal par défaut
	 * @param argCpValue
	 */
	public void setDefaultCpValue(String argCpValue) {
		cpValue = argCpValue;
	}
}
