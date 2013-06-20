package app.model;

import app.controller.bien.BienController;
import app.controller.city.CityController;
import app.controller.main.MainController;
import app.controller.saler.SalerController;
import app.view.bien.BienBuyerWindow;
import app.view.bien.FindBienWindow;
import app.view.main.MainWindow;
import app.view.owner.OwnerPanelWindow;
import app.view.user.UserPanelWindow;
import app.view.user.UserWindow;

/**
 * Lanceur des enregistreurs d'événement et mémorisation dans ce singleton
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/05/13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class FooModelLocator {
	private static FooModelLocator instance = null;

	/**
	 * écouteur sur mainModel
	 */
	private MainController mainController = new MainController();

	/**
	 * écouteur sur buyerModel
	 */
	private BienController buyerController = new BienController();

	/**
	 * écouteur sur cityModel
	 */
	private CityController cityController = new CityController();

	/**
	 * écouteur sur salerModel
	 */
	private SalerController salerController = new SalerController();

	/**
	 * Enregistrement des fenêtres dans les quels sont placés les modèles écoutés
	 */
	/**
	 * Ecoute dans la vue MainWindow
	 */
	private MainWindow mainWindow = null;

	/**
	 * écoute dans la vue UserWindow
	 */
	private UserWindow userWindow = null;

	/**
	 * écoute dans la vue UserPanelWindow
	 */
	private UserPanelWindow userPanelWindow = null;

	/**
	 * écoute dans la vue OwnerPanelWindow
	 */
	private OwnerPanelWindow ownerPanelWindow = null;

	/**
	 * écoute dans la vue FindBienWindow
	 */
	private FindBienWindow findBienWindow;

	/**
	 * écoute dans la vue BienBuyerWindow
	 */
	private BienBuyerWindow bienBuyerWindow;

	/**
	 * Constructeur privé
	 */
	private FooModelLocator() {}

	/**
	 * Setter de MainWindow
	 * @param argMainWindow
	 */
	public void setMainWindow(MainWindow argMainWindow) {
		mainWindow = argMainWindow;
	}

	/**
	 * Setter de MainWindow
	 * @return
	 */
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	/**
	 * Setter de UserWindow
	 * @param argUserWindow
	 */
	public void setUserWindow(UserWindow argUserWindow) {
		this.userWindow = argUserWindow;
	}

	/**
	 * getter de UserWindow
	 * @return
	 */
	public UserWindow getUserWindow() {
		return userWindow;
	}

	/**
	 * Getter de UserPanelWindow
	 * @return
	 */
	public UserPanelWindow getUserPanelWindow() {
		return userPanelWindow;
	}

	/**
	 * Setter de UserPanelWindow
	 * @param userPanelWindow
	 */
	public void setUserPanelWindow(UserPanelWindow userPanelWindow) {
		this.userPanelWindow = userPanelWindow;
	}

	/**
	 * Getter de OwnerPanelWindow
	 * @return
	 */
	public OwnerPanelWindow getOwnerPanelWindow() {
		return ownerPanelWindow;
	}

	/**
	 * Setter de OwnerPanelWindow
	 * @param ownerPanelWindow
	 */
	public void setOwnerPanelWindow(OwnerPanelWindow ownerPanelWindow) {
		this.ownerPanelWindow = ownerPanelWindow;
	}

	/**
	 * Setter de FindBienWindow
	 * @param findBienWindow
	 */
	public void setFindBienWindow(FindBienWindow findBienWindow) {
		this.findBienWindow = findBienWindow;
	}

	/**
	 * Setter de BienBuyerWindow
	 * @param bienBuyerWindow
	 */
	public void setBienBuyerWindow(BienBuyerWindow bienBuyerWindow) {
		this.bienBuyerWindow = bienBuyerWindow;
	}

	/**
	 * Getter de FindBienWindow
	 * @return
	 */
	public FindBienWindow getFindBienWindow() {
		return findBienWindow;
	}

	/**
	 * Singleton de l'objet
	 * @return
	 */
	public static FooModelLocator getInstance() {
		if (instance == null) {
			instance = new FooModelLocator();
		}
		return instance;
	}
}
