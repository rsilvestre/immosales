/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.model.DB.product.address;

import mvc.model.DB.product.Bien;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.util.List;

/**
 * Created by michaelsilvestre on 25/05/13.
 */
public class City {

	private Long id;
	private Long localityId;
	private Long langId;
	private String posteCode;
	private String city;

	@Transient
	private Locality locality;

	@Transient
	private Lang lang;

	@Transient
	private List<Bien> biens;

	private EntityManager em;

	public City(EntityManager em) {
		this.em = em;
	}

	public City(Locality locality, Lang lang, String city, String posteCode) {
		this.locality = locality;
		this.localityId = locality.getId();
		this.lang = lang;
		this.langId = lang.getId();
		this.city = city;
		this.posteCode = posteCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocalityId() {
		return localityId;
	}

	public void setLocalityId(Long localityId) {
		this.localityId = localityId;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public String getPosteCode() {
		return posteCode;
	}

	public void setPosteCode(String posteCode) {
		this.posteCode = posteCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Locality getLocality() {
		if (localityId == null) {
			return (locality = null);
		} else if (locality == null || !localityId.equals(locality.getId())) {
			return (locality = em.load(Locality.class, localityId));
		} else {
			return locality;
		}
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
		this.localityId = locality != null ? locality.getId() : null;
	}

	public Lang getLang() {
		if (langId == null) {
			return (lang = null);
		} else if (lang == null || !langId.equals(lang.getId())) {
			return (lang = em.load(Lang.class, langId));
		} else {
			return lang;
		}
	}

	public void setLang(Lang lang) {
		this.lang = lang;
		this.langId = lang != null ? lang.getId() : null;
	}

	public List<Bien> getBiens() {
		if (biens == null) {
			setBiens(em.find(Bien.class, "where city_id = ?", id));
		}
		return biens;
	}

	public void setBiens(List<Bien> biens) {
		this.biens = biens;
		for (Bien bien : biens) bien.setCity(this);
	}

	@Override
	public String toString() {
		return this.getCity() + " " +
				this.getPosteCode() + "\n" +
				this.getLocality().getLocality() + " " +
				this.getLocality().getRegion().getRegion() + "\n" +
				this.getLocality().getRegion().getCountry().getLabelFr();
	}
}
