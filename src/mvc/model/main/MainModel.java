package mvc.model.main;

import com.dmurph.mvc.model.AbstractModel;
import core.Session;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class MainModel extends AbstractModel {
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
