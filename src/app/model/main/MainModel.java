package app.model.main;

import com.dmurph.mvc.model.AbstractModel;
import app.model.DB.identity.APerson;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class MainModel extends AbstractModel {

	/**
	 * Sauvegarde d'un type d'utilisateur
	 */
	private APerson aPerson;

	/**
	 * Mise à feu du changement d'utilisateur
	 * @param argAPerson
	 */
	public void setAPerson(APerson argAPerson) {
		APerson oldAPerson = aPerson;
		aPerson = argAPerson;
		firePropertyChange("aPerson", oldAPerson, aPerson);
	}
}
