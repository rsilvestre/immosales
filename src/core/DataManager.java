package core;

import net.sf.jeasyorm.EntityManager;
import net.sf.jeasyorm.PostgreSQLEntityManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class DataManager {

	private String name;
	private String url;
	private String username;
	private String password;
	private Connection conn;

	private static DataManager instance;

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public DataManager() {

	}

	public void initManager(String name, String driver, String url, String username,
					   String password) throws ClassNotFoundException, SQLException {
		this.name = name;
		Class.forName(driver);
		this.url = url;
		this.username = username;
		this.password = password;
		this.conn = getConnection();
	}

	public DataManager(String name, String driver, String url, String username,
					   String password) throws ClassNotFoundException {
		this.name = name;
		Class.forName(driver);
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public EntityManager getEntityManager(Connection conn) {
		return EntityManager.getInstance(name, conn);
		//return EntityManager.getInstance(name, conn);
	}

	public EntityManager getEntityManager() {
		if (conn == null) {
			return null;
		}
		return EntityManager.getInstance(name, conn);
	}
}
