package immo;

import identity.Buyer;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;
import net.sf.jeasyorm.annotation.Transient;
import product.Bien;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 8/04/13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class Offer {

	private EntityManager em;

	@Transient
	public enum OfferStatus {
		AVAILABLE("Disponnible"),
		TOVISIT("Demande de visite"),
		VISITED("Visité"),
		SUBMIT("Offre envoyée"),
		ACCEPTED("Offre acceptée"),
		REFUSED("Offre refusée"),
		SIGNED("Acte signé"),
		SOLD("Vendu");

		private String converter;

		OfferStatus(String c) {
			this.converter = c;
		}
		public String getValue() {
			return converter;
		}
	}

	private Long id;
	private Long buyerId;
	private Long bienId;
	private String status;
	private Long offer;
	private Timestamp endDate;

	private Timestamp created_at;
	private Timestamp updated_at;

	@Transient
	private Bien bien;
	@Transient
	private Buyer buyer;

	public Offer(EntityManager em) {
		this.em = em;
	}

	public Offer(Buyer buyer, Bien bien, OfferStatus offerStatus) {
		this.buyer = buyer;
		this.buyerId = buyer.getId();
		this.bien = bien;
		this.bienId = bien.getId();
		this.status = offerStatus.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getBienId() {
		return bienId;
	}

	public void setBienId(Long bienId) {
		this.bienId = bienId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOffer() {
		return offer;
	}

	public void setOffer(Long offer) {
		this.offer = offer;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}


	public Buyer getBuyer() {
		if (buyerId == null) {
			return (buyer = null);
		} else if (buyer == null || !buyerId.equals(buyer.getId())) {
			return (buyer = em.load(Buyer.class, buyerId));
		} else {
			return buyer;
		}
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
		this.buyerId = buyer != null ? buyer.getId() : null;
	}

	public Bien getBien() {
		if (bienId == null) {
			return (bien = null);
		} else if (bien == null || !bienId.equals(bien.getId())) {
			return (bien = em.load(Bien.class, bienId));
		} else {
			return bien;
		}
	}
	public void setBien(Bien bien) {
		this.bien = bien;
		this.bienId = bien != null ? bien.getId() : null;
	}
}
