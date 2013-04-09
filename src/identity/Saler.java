package identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class Saler extends APerson {

	private Long id;
	private Long personId;

	public Saler(EntityManager em) {
		super(em);
	}

	public Saler(Person person) {
		super(person);
		this.personId = person.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
