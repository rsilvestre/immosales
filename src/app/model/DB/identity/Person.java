package app.model.DB.identity;

import com.dmurph.mvc.model.AbstractModel;
import net.sf.jeasyorm.EntityManager;
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
public class Person extends AbstractModel {

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

	public void setId(Long argId) {
		Long oldId = this.id;
		this.id = argId;
		firePropertyChange("id", oldId, id);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String argTitre) {
		String oldTitre = titre;
		this.titre = argTitre;
		firePropertyChange("titre", oldTitre, titre);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String argFirstName) {
		String oldFirstName = firstName;
		this.firstName = argFirstName;
		firePropertyChange("firstName", oldFirstName, firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String argLastName) {
		String oldLastName = lastName;
		this.lastName = argLastName;
		firePropertyChange("lastName", oldLastName, lastName);
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp argCreatedAt) {
		Timestamp oldCreateAt = createdAt;
		this.createdAt = argCreatedAt;
		firePropertyChange("createAt", oldCreateAt, createdAt);
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp argUpdatedAt) {
		Timestamp oldUpdateAt = updatedAt;
		this.updatedAt = argUpdatedAt;
		firePropertyChange("updateAt", oldUpdateAt, updatedAt);
	}

	public List<Address> getAddresses() {
		if (addresses == null && em != null) {
			setAddresses(em.find(Address.class, "where person_id = ?", id));
		}
		return addresses;
	}

	public void setAddresses(List<Address> argAddresses) {
		this.addresses = argAddresses;
		for (Address address : addresses) address.setPerson(this);
	}

	public void addAddresses(Address argAddresses) {
		if (addresses == null) {
			this.addresses = new ArrayList<Address>();
		}
		this.addresses.add(argAddresses);
	}

	public Customer getCustomer() {
		if (customer == null && em != null) {
			setCustomer(em.findUnique(Customer.class, "where person_id = ?", id));
		}
		return customer;
	}

	public void setCustomer(Customer argCustomer) {
		this.customer = argCustomer;
		this.customer.setPerson(this);
	}

	public Saler getSaler() {
		if (saler == null && em != null) {
			setSaler(em.findUnique(Saler.class, "where person_id = ?", id));
		}
		return saler;
	}

	public void setSaler(Saler argSaler) {
		this.saler = argSaler;
		this.saler.setPerson(this);
	}

	public Owner getOwner() {
		if (owner == null && em != null) {
			setOwner(em.findUnique(Owner.class, "where person_id = ?", id));
		}
		return owner;
	}

	public void setOwner(Owner argOwner) {
		this.owner = argOwner;
		this.owner.setPerson(this);
	}

	public Buyer getBuyer() {
		if (buyer == null && em != null) {
			setBuyer(em.findUnique(Buyer.class, "where person_id = ?", id));
		}
		return buyer;
	}

	public void setBuyer(Buyer argBuyer) {
		this.buyer = argBuyer;
		this.buyer.setPerson(this);
	}

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}
}
