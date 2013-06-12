/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.model.bien;

import com.dmurph.mvc.model.AbstractModel;
import core.Session;
import mvc.App;
import mvc.model.DB.identity.Buyer;
import mvc.model.DB.product.Bien;
import mvc.model.DB.product.address.City;
import mvc.model.DB.product.address.Locality;
import mvc.model.DB.product.address.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class FindBienModel extends AbstractModel {
	private List<Bien> listBiens;
	private HashMap<String, String> requestFieldDatas = new HashMap<String, String>();
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

	public List<Bien> getSearchBien() {

		String localityIdsList = "";

		if (requestFieldDatas.get("provinceField") != null && requestFieldDatas.get("provinceField") != "") {
			Region region = App.em.findUnique(Region.class, "where region like '" + requestFieldDatas.get("provinceField").replace("'", "''") + "'");
			List<Locality> localities = region.getLocalities();

			for (Locality locality : localities) {
				localityIdsList += locality.getId().toString()+",";
			}
			localityIdsList = " and locality_id in ("+localityIdsList.substring(0,localityIdsList.length()-1) + ")";
		}


		String where1 = (requestFieldDatas.get("cityField") != null && requestFieldDatas.get("cityField") != "") ? " and city like '%"+requestFieldDatas.get("cityField").replace("'", "''")+"%'" : "";
		String where2 = (requestFieldDatas.get("cpField") != null && requestFieldDatas.get("cpField") != "") ? " and poste_code like '%"+requestFieldDatas.get("cpField")+"%'" : "";
		List<City> cities = App.em.find(City.class, "where 1=1" + where1 + where2 + localityIdsList);

		if (cities.size() == 0) {
			return new ArrayList<Bien>();
		}

		String cityIdList = "";
		for (City city : cities) {
			cityIdList += city.getId().toString()+",";
		}
		cityIdList = "("+cityIdList.substring(0,cityIdList.length()-1) + ")";

		String typeProduct = (requestFieldDatas.get("typeField") != null && requestFieldDatas.get("typeField") != "") ? " and type_product like '%"+requestFieldDatas.get("typeField")+"%'" : "";
		String bienStatus = Session.getInstance().getAPerson() instanceof Buyer ? " and status like 'Complet'" : "";
		List<Bien> result = App.em.find(Bien.class, "where city_id in " + cityIdList + typeProduct + bienStatus);
		return result;
	}

	public void launchSearch() {
		List<Bien> oldListBiens = listBiens;
		listBiens = this.getSearchBien();
		firePropertyChange("listBiens", oldListBiens, listBiens);
	}

	public void setRequestFieldDatas(HashMap<String, String> requestFieldDatas) {
		this.requestFieldDatas = requestFieldDatas;
	}
}
