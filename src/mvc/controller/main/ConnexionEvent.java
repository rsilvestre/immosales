/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.controller.main;

import com.dmurph.mvc.MVCEvent;
import core.Session;
import mvc.model.main.MainModel;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class ConnexionEvent extends MVCEvent {

	public final Session session;
	public final MainModel model;

	public ConnexionEvent(Session argSession, MainModel argModel) {
		super(MainController.CONNEXION);
		model = argModel;
		session = argSession;
	}
}
