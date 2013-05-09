package mvc.model.DB.product;

import net.sf.jeasyorm.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 14/04/13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class Image extends ABien {

	private Long id;
	private String imageName;

	private Long bienId;

	public Image() {}

	public Image(EntityManager em) {
		super (em);
	}

	public Image(Bien bien, String imageName) {
		super(bien);
		this.bienId = bien.getId();
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getBienId() {
		return bienId;
	}

	public void setBienId(Long bienId) {
		this.bienId = bienId;
	}
}
