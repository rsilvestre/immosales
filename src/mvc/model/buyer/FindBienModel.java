/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.model.buyer;

import com.dmurph.mvc.model.AbstractModel;
import mvc.App;
import mvc.model.DB.product.Bien;

import java.util.HashMap;
import java.util.List;

/**
 * Created by michaelsilvestre on 20/05/13.
 */
public class FindBienModel extends AbstractModel {
	private List<Bien> listBiens;
	private HashMap<String, String> requestFieldDatas = new HashMap<String, String>();

	public FindBienModel() {

	}

	public List<Bien> getSearchBien() {
		return App.em.find(Bien.class,
				"where city like ? and poste_code like ? and type_product like ?",
				"%"+requestFieldDatas.get("cityField")+"%", "%"+requestFieldDatas.get("cpField")+"%", "%"+requestFieldDatas.get("typeField")+"%");
	}

	public void launchSearch() {
		List<Bien> oldListBiens = listBiens;
		listBiens = this.getSearchBien();
		firePropertyChange("listBiens", oldListBiens, listBiens);
	}

	public void setRequestFieldDatas(HashMap<String, String> requestFieldDatas) {
		this.requestFieldDatas = requestFieldDatas;
	}
}
