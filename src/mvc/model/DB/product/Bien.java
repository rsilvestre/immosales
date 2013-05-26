package mvc.model.DB.product;

import mvc.model.DB.product.address.City;
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
		// Ne pas changer. Nécessaire pour ne pas avoir de conversion lors de l'introduction dans la base de données
		public String toString() {
			return converter;
		}
		public static TypeProduct fromString(String text) {
			if (text != null) {
				for (TypeProduct b : TypeProduct.values()) {
					if (text.equalsIgnoreCase(b.converter)) {
						return b;
					}
				}
			}
			return null;
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
	private Long cityId;

	private Float price;

	private Integer yearConstruction;
	private Integer faceWide;
	private Integer nFrontage;
	private Integer nFloor;

	private String cpeb;

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
	@Transient
	private City city;

	public Bien() {
		roomes = new ArrayList<Room>();
		optionses = new ArrayList<Options>();
		offers = new ArrayList<Offer>();
	}

	public Bien(EntityManager em) {
		this.em = em;
	}

	public Bien(Owner owner, String name, TypeProduct typeProduct, Long cityId) {
		this.owner = owner;
		this.ownerId = owner.getId();
		this.name = name;
		this.typeProduct = typeProduct.toString();
		this.cityId = cityId;
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public City getCity() {
		if (cityId == null) {
			return (city = null);
		} else if (city == null || !cityId.equals(city.getId())) {
			return (city = em.load(City.class, cityId));
		} else {
			return city;
		}
	}

	public void setCity(City city) {
		this.city = city;
		cityId = city != null ? city.getId() : null;
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

	public String getCpeb() {
		return cpeb;
	}

	public void setCpeb(String cpeb) {
		this.cpeb = cpeb;
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

	public String [] getTableRow() {
		return new String [] {
			this.getId().toString(),
			this.getTypeProduct(),
			this.getName(),
			this.getDescription(),
			this.getPrice().toString(),
			"Edit"
			};
	}

	public String getTypeProductEnumString() {
		return TypeProduct.valueOf(this.getTypeProduct()).toString();
	}

	public TypeProduct getTypeProductEnum() {
		return TypeProduct.valueOf(this.getTypeProduct());
	}
}
