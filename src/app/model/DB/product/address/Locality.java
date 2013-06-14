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
public class Locality {

	private Long id;

	private Long regionId;
	private String locality;

	private EntityManager em;

	@Transient
	private Region region;

	@Transient
	private List<City> cities;

	public Locality(EntityManager em) {
		this.em = em;
	}

	public Locality(Region region, String locality) {
		this.region = region;
		this.regionId = region.getId();
		this.locality = locality;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Region getRegion() {
		if (regionId == null) {
			return (region = null);
		} else if (region == null || !regionId.equals(region.getId())) {
			return (region = em.load(Region.class, regionId));
		} else {
			return region;
		}
	}

	public void setRegion(Region region) {
		this.region = region;
		this.regionId = region != null ? region.getId() : null;
	}

	public List<City> getCities() {
		if (cities == null && em != null) {
			setCities(em.find(City.class,"where locality_id = ?", id));
		}
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
		for (City city : cities) city.setLocality(this);
	}
}
