/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.model.user;

import com.dmurph.mvc.model.AbstractModel;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class UserPanelModel extends AbstractModel {
	private String city;
	private String cpValue;

	public String getCity() {
		return city;
	}

	public void setCity(String argCity) {
		String oldCity = city;
		city = argCity;
		firePropertyChange("newCity", oldCity, city);
	}

	public void setDefaultCity(String argCity) {
		city = argCity;
	}

	public String getCpValue() {
		return cpValue;
	}

	public void setCpValue(String argCpValue) {
		String oldCpValue = cpValue;
		cpValue = argCpValue;
		firePropertyChange("newCp", oldCpValue, cpValue);
	}

	public void setDefaultCpValue(String argCpValue) {
		cpValue = argCpValue;
	}
}
