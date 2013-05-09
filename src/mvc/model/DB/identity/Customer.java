package mvc.model.DB.identity;

import net.sf.jeasyorm.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public class Customer extends APerson {

	private Long id;
	private Long personId;
	private String phoneNumber;
	private String email;

	public Customer(EntityManager em) {
		super(em);
	}

	public Customer(Person person, String phoneNumber, String email) {
		super(person);
		this.personId = person.getId();
		this.phoneNumber = phoneNumber;
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
