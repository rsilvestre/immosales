package identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
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

	protected EntityManager getEm() {
		return em;
	}
}
