package app.view.owner;

import core.Session;
import app.model.DB.product.address.City;
import app.App;
import app.model.DB.identity.Owner;
import app.model.DB.product.Bien;
import app.model.owner.OwnerPanelModel;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 12/05/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class OwnerPanelWindowMapping {

	private OwnerPanelWindow ownerPanelWindow;
	private Bien bien;
	private OwnerPanelModel ownerPanelModel;

	public OwnerPanelWindowMapping(OwnerPanelModel argOwnerPanelModel, Bien bien) {
		this.ownerPanelModel = argOwnerPanelModel;
		this.bien = bien;
	}

	public OwnerPanelWindowMapping(OwnerPanelWindow ownerPanelWindow) {
		this.ownerPanelWindow = ownerPanelWindow;
	}

	public Bien getBienMapping() {
		return getBienMappingDetail(
			new Bien(
				(Owner)Session.getInstance().getAPerson(),
				getOwnerPanelWindow().gettName(),
				Bien.TypeProduct.fromString(getOwnerPanelWindow().getcTypeProductEnum()),
				App.em.findUnique(City.class, "where city = ? and poste_code = ?", getOwnerPanelWindow().gettCity(), getOwnerPanelWindow().gettPosteCode()),
				Bien.Status.WAITING
			)
		);
	}

	public Bien getBienMapping(Bien bienResult) {
		bienResult.setName(getOwnerPanelWindow().gettName());
		bienResult.setTypeProduct(getOwnerPanelWindow().getcTypeProduct());
		return getBienMappingDetail(bienResult);
	}

	private Bien getBienMappingDetail(Bien bienResult) {
		bienResult.setDescription(getOwnerPanelWindow().gettDescription());
		bienResult.setStreetName(getOwnerPanelWindow().gettStreetName());
		bienResult.setStreetNumber(getOwnerPanelWindow().gettStreetNumber());
		bienResult.setStreetBox(getOwnerPanelWindow().gettStreetBox());
		bienResult.setCity(App.em.findUnique(City.class, "where city = ? and poste_code = ?", getOwnerPanelWindow().gettCity(), getOwnerPanelWindow().gettPosteCode()));
		bienResult.setPrice(getOwnerPanelWindow().gettPrice());
		bienResult.setYearConstruction(getOwnerPanelWindow().getcYearConstruction());
		bienResult.setFaceWide(getOwnerPanelWindow().gettFaceWide());
		bienResult.setnFrontage(getOwnerPanelWindow().getcNFrontage());
		bienResult.setnFloor(getOwnerPanelWindow().getcNFloor());
		bienResult.setCpeb(getOwnerPanelWindow().getcCpeb());

		return bienResult;
	}

	public OwnerPanelWindow getOwnerPanelWindowMapping() {
		OwnerPanelWindow ownerPanelWindowResult = new OwnerPanelWindow(getArgOwnerPanelModel());

		ownerPanelWindowResult.setId(getBien().getId());
		ownerPanelWindowResult.settName(getBien().getName());
		ownerPanelWindowResult.settDescription(getBien().getDescription());
		ownerPanelWindowResult.setcTypeProduct(getBien().getTypeProduct());
		ownerPanelWindowResult.settStreetName(getBien().getStreetName());
		ownerPanelWindowResult.settStreetNumber(getBien().getStreetNumber());
		ownerPanelWindowResult.settStreetBox(getBien().getStreetBox());
		ownerPanelWindowResult.settCity(getBien().getCity().getCity());
		ownerPanelWindowResult.settLocality(getBien().getCity().getLocality().getRegion().getRegion());
		ownerPanelWindowResult.settPosteCode(getBien().getCity().getPosteCode());
		ownerPanelWindowResult.settCountry(getBien().getCity().getLocality().getRegion().getCountry().getLabelFr());
		ownerPanelWindowResult.settPrice(getBien().getPrice());
		ownerPanelWindowResult.setcYearConstruction(getBien().getYearConstruction());
		ownerPanelWindowResult.settFaceWide(getBien().getFaceWide());
		ownerPanelWindowResult.setcNFrontage(getBien().getnFrontage());
		ownerPanelWindowResult.setcNFloor(getBien().getnFloor());
		ownerPanelWindowResult.setcCpeb(getBien().getCpeb());
		ownerPanelWindowResult.setImages(getBien().getImages());

		ownerPanelWindowResult.setCanChange(getBien());

		return ownerPanelWindowResult;
	}

	private Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	private OwnerPanelModel getArgOwnerPanelModel() {
		return ownerPanelModel;
	}

	private OwnerPanelWindow getOwnerPanelWindow() {
		return ownerPanelWindow;
	}
}
