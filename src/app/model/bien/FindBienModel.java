/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.bien;

import com.dmurph.mvc.model.AbstractModel;
import core.Session;
import app.App;
import app.model.DB.identity.Buyer;
import app.model.DB.product.Bien;
import app.model.DB.product.address.City;
import app.model.DB.product.address.Locality;
import app.model.DB.product.address.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Syst�me de recherche au changement de combobox
 * Created by michaelsilvestre on 20/05/13.
 */
public class FindBienModel extends AbstractModel {

	/**
	 * Liste de bien
	 */
	private List<Bien> listBiens;

	/**
	 * Valeur de la ville stock�
	 */
	private String city;

	/**
	 * Valeur du code postal stock�
	 */
	private String cpValue;

	/**
	 * Alume le feu pour la recherche de villes sur base du nom de celle-ci
	 * @param argCity
	 */
	public void setCity(String argCity) {
		String oldCity = city;
		city = argCity;
		firePropertyChange("newCity", oldCity, city);
	}

	/**
	 * Alume le feu pour la recherche de ville sur base du code postal
	 * @param argCpValue
	 */
	public void setCpValue(String argCpValue) {
		String oldCpValue = cpValue;
		cpValue = argCpValue;
		firePropertyChange("newCp", oldCpValue, cpValue);
	}

	/**
	 * Syst�me de recherche d'un bien suivant certain crit�res de s�lection
	 * @return une liste de ville
	 */
	private List<Bien> getSearchBien(HashMap<String, String> requestFieldDatas) {

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
		String bienStatus = Session.getInstance().getAPerson() instanceof Buyer ? " and status like 'Disponible' or status like 'Acte sign�'" : "";
		List<Bien> result = App.em.find(Bien.class, "where city_id in " + cityIdList + typeProduct + bienStatus);
		return result;
	}

	/**
	 * Alume le feu pour une recherche � l'aide de combobox sur base de certains crit�res
	 */
	public void launchSearch(HashMap<String, String> requestFieldDatas) {
		List<Bien> oldListBiens = listBiens;
		listBiens = this.getSearchBien(requestFieldDatas);
		firePropertyChange("listBiens", oldListBiens, listBiens);
	}
}
