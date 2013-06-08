package mvc.model.DB.identity;

import core.Session;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class APerson {

	@Transient
	public static enum userTypeEnum {
		Buyer("Acheteur"), Owner("Vendeur"), Saler("Commercial");

		private String converter;

		userTypeEnum(String value) {
			converter = value;
		}

		@Override
		public String toString() {
			return converter;
		}
		public static userTypeEnum fromString(String text) {
			if (text != null) {
				for (userTypeEnum b : userTypeEnum.values()) {
					if (text.equalsIgnoreCase(b.converter)) {
						return b;
					}
				}
			}
			return null;
		}

	}

	private EntityManager em;

	private Long personId;

	@Transient
	private Person person;

	public APerson() {

	}

	public APerson(EntityManager em) {
		this.em = em;
	}

	public APerson(Person person) {
		this.person = person;
		this.personId = person.getId();
	}

	abstract public Long getId();
	abstract public void setPhoneNumber(String phoneNumber);
	abstract public String getPhoneNumber();
	abstract public void setEmail(String email);
	abstract public String getEmail();

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

	protected void setPersonId(Long personId) {
		this.personId = personId;
	}

	protected EntityManager getEm() {
		return em;
	}

	public userTypeEnum getUserType() {
		userTypeEnum userType = null;
		if (this instanceof Buyer) {
			userType = userTypeEnum.Buyer;
		} else if (this instanceof Owner) {
			userType = userTypeEnum.Owner;
		} else if (this instanceof Saler) {
			userType = userTypeEnum.Saler;
		}
		return userType;
	}

	public String [] getTableRow(Session argSession) {
		Person personRow = this.getPerson();
		return new String [] {
			personRow.getTitre(),
			personRow.getLastName(),
			personRow.getFirstName(),
			personRow.getAddresses().get(0).getAddressString(),
			"Edit",
			(argSession.getAPerson() != null && this.getId() == argSession.getAPerson().getId()) ? "Connected":""
		};
	}

	@Override
	public String toString() {
		return this.getPerson().toString();
	}
}
