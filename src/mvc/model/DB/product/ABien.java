package mvc.model.DB.product;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 8/04/13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class ABien {
	private EntityManager em;

	private Long bienId;
	@Transient
	private Bien bien;

	public ABien(EntityManager em) {
		this.em = em;
	}

	public ABien() {

	}

	public ABien(Bien bien) {
		this.bien = bien;
		this.bienId = bien.getId();
	}

	public Long getBienId() {
		return bienId;
	}

	public void setBienId(Long bienId) {
		this.bienId = bienId;
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
