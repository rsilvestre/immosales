package identity;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class Person {

	private EntityManager em;

	private Long id;
	private String titre;
	private String firstName;
	private String lastName;

	private Timestamp createdAt;
	private Timestamp updatedAt;

	@Transient private List<Address> addresses;
	@Transient private Customer customer;
	@Transient private Saler saler;
	@Transient private Owner owner;
	@Transient private Buyer buyer;

	public Person() {
		addresses = new ArrayList<Address>();
	}
	
	public Person(EntityManager em) {
		this.em = em;
	}

	public Person(String titre, String firstName, String lastName) {
		this.titre = titre;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Address> getAddresses() {
		if (addresses == null && em != null) {
			setAddresses(em.find(Address.class, "where person_id = ?", id));
		}
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
		for (Address address : addresses) address.setPerson(this);
	}

	public Customer getCustomer() {
		if (customer == null && em != null) {
			setCustomer(em.findUnique(Customer.class, "where person_id = ?", id));
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.customer.setPerson(this);
	}

	public Saler getSaler() {
		if (saler == null && em != null) {
			setSaler(em.findUnique(Saler.class, "where person_id = ?", id));
		}
		return saler;
	}

	public void setSaler(Saler saler) {
		this.saler = saler;
		this.saler.setPerson(this);
	}

	public Owner getOwner() {
		if (owner == null && em != null) {
			setOwner(em.findUnique(Owner.class, "where person_id = ?", id));
		}
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
		this.owner.setPerson(this);
	}

	public Buyer getBuyer() {
		if (buyer == null && em != null) {
			setBuyer(em.findUnique(Buyer.class, "where person_id = ?", id));
		}
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
		this.buyer.setPerson(this);
	}
}
