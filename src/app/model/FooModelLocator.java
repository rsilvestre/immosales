package app.model;

import app.controller.bien.BienController;
import app.controller.city.CityController;
import app.controller.main.MainController;
import app.controller.owner.OwnerController;
import app.controller.user.UserController;
import app.view.bien.BienWindow;
import app.view.bien.FindBienWindow;
import app.view.main.MainWindow;
import app.view.owner.OwnerPanelWindow;
import app.view.user.UserPanelWindow;
import app.view.user.UserWindow;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/05/13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class FooModelLocator {
	private static FooModelLocator instance = null;

	private MainController mainController = new MainController();
	private UserController userController = new UserController();
	private OwnerController ownerController = new OwnerController();
	private BienController buyerController = new BienController();
	private CityController cityController = new CityController();

	private MainWindow mainWindow = null;

	private UserWindow userWindow = null;
	private UserPanelWindow userPanelWindow = null;

	private OwnerPanelWindow ownerPanelWindow = null;
	private FindBienWindow findBienWindow;
	private BienWindow bienWindow;

	private FooModelLocator() {}

	public void setMainWindow(MainWindow argMainWindow) {
		mainWindow = argMainWindow;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setUserWindow(UserWindow argUserWindow) {
		this.userWindow = argUserWindow;
	}

	public UserWindow getUserWindow() {
		return userWindow;
	}

	public UserPanelWindow getUserPanelWindow() {
		return userPanelWindow;
	}

	public void setUserPanelWindow(UserPanelWindow userPanelWindow) {
		this.userPanelWindow = userPanelWindow;
	}

	public OwnerPanelWindow getOwnerPanelWindow() {
		return ownerPanelWindow;
	}

	public void setOwnerPanelWindow(OwnerPanelWindow ownerPanelWindow) {
		this.ownerPanelWindow = ownerPanelWindow;
	}

	public void setFindBienWindow(FindBienWindow findBienWindow) {
		this.findBienWindow = findBienWindow;
	}

	public void setBienWindow(BienWindow bienWindow) {
		this.bienWindow = bienWindow;
	}

	public FindBienWindow getFindBienWindow() {
		return findBienWindow;
	}

	public static FooModelLocator getInstance() {
		if (instance == null) {
			instance = new FooModelLocator();
		}
		return instance;
	}
}
