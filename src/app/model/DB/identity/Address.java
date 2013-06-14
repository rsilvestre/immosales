package app.model.DB.identity;

import app.model.DB.product.address.City;
import app.model.DB.product.address.Locality;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class Address {

	private EntityManager em;

	private Long id;

	private String streetName;
	private String streetNumber;
	private String streetBox;
	private Long cityId;

	private Long personId;

	@Transient
	private Person person;

	@Transient
	private City city;

	public Address() {
		// nothing to do
	}

	public Address(EntityManager em) {
		this.em = em;
	}

	public Address(Person person) {
		this.person = person;
		this.personId = person.getId();
	}

	public Address(Person person,
					String streetName,
					String streetNumber,
					String streetBox,
					City city) {
		this.person = person;
		this.personId = person.getId();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.streetBox = streetBox;
		this.city = city;
		this.cityId = city.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetBox() {
		return streetBox;
	}

	public void setStreetBox(String streetBox) {
		this.streetBox = streetBox;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public City getCity() {
		if (cityId == null) {
			return (city = null);
		} else if (city == null || !cityId.equals(city.getId())) {
			return (city = em.load(City.class, cityId));
		} else {
			return city;
		}
	}

	public void setCity(City city) {
		this.city = city;
		this.cityId = city != null ? city.getId() : null;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long id) {
		this.personId = id;
	}

	public Person getPerson() {
		if (personId == null) {
			return (person = null);
		} else if (person == null || !personId.equals(person.getId())) {
			return (person = em.load(Person.class, personId));
		} else {
			return person;
		}
	}

	public void setPerson(Person person) {
		this.person = person;
		this.personId = person != null ? person.getId() : null;
	}

	public String getAddressString() {
		Locality locality = null;
		return this.getStreetName() + " " +
			this.getStreetNumber() + "/" +
			this.getStreetBox() + "\n" +
			this.getCity().getPosteCode() + " " +
			this.getCity() + " - " +
				(locality = this.getCity().getLocality()) + "\n" +
			locality.getRegion().getCountry() + "\n";
	}

}
