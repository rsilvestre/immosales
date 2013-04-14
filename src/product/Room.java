package product;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 7/04/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class Room extends ABien {
	@Transient
	public enum RoomType {
		TOILET("toilette"),
		CELLAR("Cave"),
		KITCHEN("Cuisine"),
		WORKSPACE("Profession Liberal"),
		BATHROOM("Salle de bain"),
		STUDY("Bureau"),
		LIVINGROOM("Living room"),
		SHOWERROOM("Salle de douche"),
		ATTIC("Grenier"),
		DININGROOM("Salle à manger");

		private String converter;

		RoomType(String c) {
			converter = c;
		}
		public String getValue() {
			return converter;
		}
	}

	private Long id;
	private String roomType;
	private Integer superficy;
	private Long bienId;

	public Room() {

	}

	public Room(EntityManager em) {
		super(em);
	}

	public Room(Bien bien, RoomType roomType) {
		super(bien);
		this.bienId = bien.getId();
		this.roomType = roomType.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getSuperficy() {
		return superficy;
	}

	public void setSuperficy(Integer superficy) {
		this.superficy = superficy;
	}

	public Long getBienId() {
		return bienId;
	}

	public void setBienId(Long bienId) {
		this.bienId = bienId;
	}
}
