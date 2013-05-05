package mvc.model.identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class APerson {

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
}
