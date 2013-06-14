/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.DB.product.address;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.util.List;

/**
 * Created by michaelsilvestre on 25/05/13.
 */
public class Region {

	private Long id;

	private Long countryId;
	private String region;

	private EntityManager em;

	@Transient
	private Country country;

	@Transient
	private List<Locality> localities;

	public Region(EntityManager em) {
		this.em = em;
	}

	public Region(Country country, String region) {
		this.country = country;
		this.countryId = country.getId();
		this.region = region;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Country getCountry() {
		if (countryId == null) {
			return (country = null);
		} else if (country == null || !countryId.equals(country.getId())) {
			return (country = em.load(Country.class, countryId));
		} else {
			return country;
		}
	}

	public void setCountry(Country country) {
		this.country = country;
		this.countryId = country != null ? country.getId() : null;

	}

	public List<Locality> getLocalities() {
		if (localities == null && em != null) {
			setLocalities(em.find(Locality.class,"where region_id = ?", id));
		}
		return localities;
	}

	public void setLocalities(List<Locality> localities) {
		this.localities = localities;
		for (Locality locality : localities) locality.setRegion(this);
	}
}
