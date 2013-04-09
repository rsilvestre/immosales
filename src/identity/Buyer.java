package identity;

import immo.Offer;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public class Buyer extends APerson {

	private Long id;
	private Long personId;
	@Transient
	private List<Offer> offers;

	public Buyer() {
		this.offers = new ArrayList<Offer>();
	}

	public Buyer(EntityManager em) {
		super(em);
	}

	public Buyer(Person person) {
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


	public List<Offer> getOffers() {
		if (offers == null && getEm() != null) {
			setOffers(getEm().find(Offer.class, "where buyer_id = ?", id));
		}
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
		for (Offer offer : offers) offer.setBuyer(this);
	}
}
