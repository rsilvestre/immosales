package mvc.model.DB.product;

import mvc.model.DB.identity.Owner;
import mvc.model.DB.immo.Offer;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 28/03/13
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public class Bien {
	private EntityManager em;

	@Transient
	public enum TypeProduct {
		APPARTMENT("Appartement"), HOUSE("Maison");

		private String converter;

		TypeProduct(String c) {
			converter = c;
		}
		public String getValue() {
			return converter;
		}
	}

	private Long id;
	private Long ownerId;
	private String name;
	private String description;
	private String typeProduct;

	private String streetName;
	private String streetNumber;
	private String streetBox;
	private String city;
	private String locality;
	private String posteCode;
	private String country;

	private Float price;

	private Integer yearConstruction;
	private Integer faceWide;
	private Integer nFrontage;
	private Integer nFloor;

	private Timestamp createdAt;
	private Timestamp updatedAt;

	@Transient
	private List<Room> roomes;
	@Transient
	private List<Options> optionses;
	@Transient
	private Owner owner;
	@Transient
	private List<Offer> offers;

	public Bien() {
		roomes = new ArrayList<Room>();
		optionses = new ArrayList<Options>();
		offers = new ArrayList<Offer>();
	}

	public Bien(EntityManager em) {
		this.em = em;
	}

	public Bien(Owner owner, String name, TypeProduct typeProduct) {
		this.owner = owner;
		this.ownerId = owner.getId();
		this.name = name;
		this.typeProduct = typeProduct.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetBox() {
		return streetBox;
	}

	public void setStreetBox(String streetBox) {
		this.streetBox = streetBox;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPosteCode() {
		return posteCode;
	}

	public void setPosteCode(String posteCode) {
		this.posteCode = posteCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getYearConstruction() {
		return yearConstruction;
	}

	public void setYearConstruction(Integer yearConstruction) {
		this.yearConstruction = yearConstruction;
	}

	public Integer getFaceWide() {
		return faceWide;
	}

	public void setFaceWide(Integer faceWide) {
		this.faceWide = faceWide;
	}

	public Integer getnFrontage() {
		return nFrontage;
	}

	public void setnFrontage(Integer nFrontage) {
		this.nFrontage = nFrontage;
	}

	public Integer getnFloor() {
		return nFloor;
	}

	public void setnFloor(Integer nFloor) {
		this.nFloor = nFloor;
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

	public List<Room> getRoomes() {
		if (roomes == null && em != null) {
			setRoomes(em.find(Room.class, "where bien_id = ?", id));
		}
		return roomes;
	}

	public void setRoomes(List<Room> roomes) {
		this.roomes = roomes;
		for (Room room : roomes) room.setBien(this);
	}

	public List<Options> getOptionses() {
		if (optionses == null && em != null) {
			setOptionses(em.find(Options.class, "where bien_id = ?", id));
		}
		return optionses;
	}

	public void setOptionses(List<Options> optionses) {
		this.optionses = optionses;
		for (Options options : optionses) options.setBien(this);
	}

	public Owner getOwner() {
		if (ownerId == null) {
			return (owner = null);
		} else if (owner == null || !ownerId.equals(owner.getId())) {
			return (owner = em.load(Owner.class, ownerId));
		} else {
			return owner;
		}
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
		this.ownerId = owner != null ? owner.getId() : null;
	}


	public List<Offer> getOffers() {
		if (offers == null && em != null) {
			setOffers(em.find(Offer.class, "where bien_id = ?", id));
		}
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
		for (Offer offer : offers) offer.setBien(this);
	}
}
