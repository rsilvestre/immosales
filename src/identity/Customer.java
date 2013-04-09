package identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public class Customer extends APerson {

	private Long id;
	private String phonenumber;
	private Long personId;

	public Customer(EntityManager em) {
		super(em);
	}

	public Customer(Person person, String phonenumber) {
		super(person);
		this.personId = person.getId();
		this.phonenumber = phonenumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
