/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package core;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 11/04/13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public class Connection {

	private Boolean isLogged = false;

	private Session session;

	private static Connection connectionInstance;

	public static Connection getInstance() {
		if (!(connectionInstance instanceof Connection)) {
			connectionInstance = new Connection();
		}
		return connectionInstance;
	}

	public Connection() {
		setSession(Session.getInstance());
	}

	public Boolean isLogged() {
		return isLogged;
	}

	public void login() {
		isLogged = true;
	}

	public void logout() {
		isLogged = false;
	}

	public Session getSession() {
		return session;
	}

	private void setSession(Session session) {
		this.session = session;
	}
}
