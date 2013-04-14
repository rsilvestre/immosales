/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package core;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 11/04/13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class Session {
	private UUID sessionId;

	/**
	 * Singleton container
	 */
	private static Session sessionInstance;

	/**
	 * Singelton
	 * @return Session
	 */
	public static Session getInstance() {
		if (!(sessionInstance instanceof Session)) {
			Session sessionInstance = new Session();
		}
		return sessionInstance;
	}

	public Session() {
		createSessionId();
	}

	private void createSessionId() {
		sessionId = UUID.randomUUID();
	}

	public UUID getSessionId() {
		return sessionId;
	}
}
