package app;

import com.dmurph.mvc.MVC;
import core.DataManager;
import app.model.FooModelLocator;
import app.model.main.MainModel;
import app.view.main.MainWindow;
import net.sf.jeasyorm.EntityManager;

import javax.swing.*;
import java.awt.*;
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

	/**
	 * EntityManager
	 */
	public static EntityManager em = null;

	/**
	 * Lanceur de l'application
	 * @param args
	 */
	public static void main (String [] args) {
		// Fen�tre de d�buggeur d'�v�nement
		MVC.showEventMonitor();

		// Connexion � la base de donn�es
		databaseConnexion();

		// Enregistrement des �couteurs d'�v�nements
		FooModelLocator locator = FooModelLocator.getInstance();
		// Model de d�marrage
		MainModel mainModel = new MainModel();
		// Fen�tre de d�marrage
		MainWindow window = new MainWindow(mainModel);
		// Enregistrement de la fen�tre de d�marrage dans le singleton de l'application
		locator.setMainWindow(window);

		// Dimensionnement de le fen�tre principale
		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(800, 600));

		// Action � ex�cuter � la fin du programme
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Affichage de la fen�tre de d�marrage
		window.setVisible(true);
	}

	/**
	 * Connexion � la base de donn�es
	 */
	private static void databaseConnexion() {
		// R�cup�ration du fichier contenant les param�tres de configuration de l'application
		Properties configFile = new Properties();
		try {
			// Lecture du fichier de configuration
			configFile.load(App.class.getClassLoader().getResourceAsStream("config/config.properties"));

			// Connexion � la base de donn�e
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
