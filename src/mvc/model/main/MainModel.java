package mvc.model.main;

import com.dmurph.mvc.model.AbstractModel;
import core.Session;
import mvc.model.identity.APerson;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class MainModel extends AbstractModel {
	private APerson aPerson;
	private String text = "test";

	public APerson getaPerson() {
		return aPerson;
	}

	public void setAPerson(APerson argAPerson) {
		APerson oldAPerson = aPerson;
		aPerson = argAPerson;
		firePropertyChange("aPerson", oldAPerson, aPerson);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
