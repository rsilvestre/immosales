package model.product;

import net.sf.jeasyorm.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 8/04/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class Options extends ABien {

	private Long id;
	private String keyData;
	private String valueData;
	private Long bienId;

	public Options() {

	}

	public Options(EntityManager em) {
		super(em);
	}

	public Options(Bien bien, String keyData, String valueData) {
		super(bien);
		this.bienId = bien.getId();
		this.keyData = keyData;
		this.valueData = valueData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return keyData;
	}

	public void setKey(String keyData) {
		this.keyData = keyData;
	}

	public String getValue() {
		return valueData;
	}

	public void setValue(String valueData) {
		this.valueData = valueData;
	}

	public Long getBienId() {
		return bienId;
	}

	public void setBienId(Long bienId) {
		this.bienId = bienId;
	}
}
