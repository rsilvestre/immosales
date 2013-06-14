package app;

import com.dmurph.mvc.MVC;
import core.DataManager;
import app.model.FooModelLocator;
import app.model.main.MainModel;
import app.view.main.MainWindow;
import net.sf.jeasyorm.EntityManager;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
public class App {

	public static EntityManager em = null;

	public static void main (String [] args) {
		MVC.showEventMonitor();

		databaseConnexion();

		FooModelLocator locator = FooModelLocator.getInstance();
		MainModel mainModel = new MainModel();
		MainWindow window = new MainWindow(mainModel);
		locator.setMainWindow(window);

		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setVisible(true);
	}

	private static void databaseConnexion() {
		//To change body of created methods use File | Settings | File Templates.
		Properties configFile = new Properties();
		try {
			configFile.load(App.class.getClassLoader().getResourceAsStream("config/config.properties"));

			DataManager.getInstance().initManager("immosales","org.postgresql.Driver",
				"jdbc:postgresql://" + configFile.getProperty("NAME_DATABASE"), configFile.getProperty("USER_DATABASE"),
				configFile.getProperty("DB_PASSWORD"));
			em = DataManager.getInstance().getEntityManager();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
