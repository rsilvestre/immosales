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
public class Country {

	private Long id;

	private String iso;
	private String label;
	private String labelFr;

	@Transient
	private List<Region> regions;

	private EntityManager em;

	public Country(EntityManager em) {
		this.em = em;
	}

	public Country(Long id, String iso, String label) {
		this.id = id;
		this.iso = iso;
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelFr() {
		return labelFr;
	}

	public void setLabelFr(String labelFr) {
		this.labelFr = labelFr;
	}

	public List<Region> getRegions() {
		if (regions == null && em != null) {
			setRegions(em.find(Region.class, "where country_id = ?", id));
		}
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
		for (Region region : regions) region.setCountry(this);
	}
}
