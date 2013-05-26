/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.model.DB.product.address;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.util.List;

/**
 * Created by michaelsilvestre on 25/05/13.
 */
public class Lang {

	private Long id;

	private String lang;
	private String label_fr;

	private EntityManager em;

	@Transient
	private List<City> cities;

	public Lang(EntityManager em) {
		this.em = em;
	}

	public Lang(String lang, String label_fr) {
		this.lang = lang;
		this.label_fr = label_fr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel_fr() {
		return label_fr;
	}

	public void setLabel_fr(String label_fr) {
		this.label_fr = label_fr;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<City> getCities() {
		if (cities == null && em != null) {
			setCities(em.find(City.class, "where lang_id = ?", id));
		}
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
		for (City city : cities) city.setLang(this);
	}
}
