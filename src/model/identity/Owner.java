package model.identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;
import model.product.Bien;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public class Owner extends APerson {

	private Long id;
	private Long personId;
	private String phoneNumber;
	private String email;

	@Transient
	private List<Bien> biens;

	public Owner(EntityManager em) {
		super(em);
	}

	public Owner(Person person) {
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


	public List<Bien> getBiens() {
		if (biens == null && getEm() != null) {
			setBiens(getEm().find(Bien.class, "where owner_id = ?", id));
		}
		return biens;
	}

	public void setBiens(List<Bien> biens) {
		this.biens = biens;
		for (Bien bien : biens) bien.setOwner(this);
	}
}
