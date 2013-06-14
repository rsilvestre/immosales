/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.model.DB.immo;

import app.model.DB.identity.Buyer;
import app.model.DB.product.Bien;
import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.annotation.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 8/04/13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class Interest {

	private EntityManager em;

	private Long id;
	private Long buyerId;
	private Long bienId;

	@Transient
	private Bien bien;
	@Transient
	private Buyer buyer;

	public Interest(EntityManager em) {
		this.em = em;
	}

	public Interest(Buyer buyer, Bien bien) {
		this.buyer = buyer;
		this.buyerId = buyer.getId();
		this.bien = bien;
		this.bienId = bien.getId();
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
