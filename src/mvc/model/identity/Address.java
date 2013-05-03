package mvc.model.identity;

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
	private String city;
	private String locality;
	private String posteCode;
	private String country;

	private Long personId;
	@Transient
	private Person person;

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
					String city,
					String locality,
					String posteCode,
					String country) {
		this.person = person;
		this.personId = person.getId();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.streetBox = streetBox;
		this.city = city;
		this.locality = locality;
		this.posteCode = posteCode;
		this.country = country;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPosteCode() {
		return posteCode;
	}

	public void setPosteCode(String posteCode) {
		this.posteCode = posteCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

}
