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
		// Fenêtre de débuggeur d'événement
		MVC.showEventMonitor();

		// Connexion à la base de données
		databaseConnexion();

		// Enregistrement des écouteurs d'événements
		FooModelLocator locator = FooModelLocator.getInstance();
		// Model de démarrage
		MainModel mainModel = new MainModel();
		// Fenêtre de démarrage
		MainWindow window = new MainWindow(mainModel);
		// Enregistrement de la fenêtre de démarrage dans le singleton de l'application
		locator.setMainWindow(window);

		// Dimensionnement de le fenêtre principale
		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(800, 600));

		// Action à exécuter à la fin du programme
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Affichage de la fenêtre de démarrage
		window.setVisible(true);
	}

	/**
	 * Connexion à la base de données
	 */
	private static void databaseConnexion() {
		// Récupération du fichier contenant les paramètres de configuration de l'application
		Properties configFile = new Properties();
		try {
			// Lecture du fichier de configuration
			configFile.load(App.class.getClassLoader().getResourceAsStream("config/config.properties"));

			// Connexion à la base de donnée
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
