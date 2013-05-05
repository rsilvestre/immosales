package mvc.model;

import mvc.controller.main.MainController;
import mvc.controller.user.UserController;
import mvc.view.main.MainWindow;
import mvc.view.user.UserPanelWindow;
import mvc.view.user.UserWindow;

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

	private MainWindow mainWindow = null;
	private UserWindow userWindow = null;
	private UserPanelWindow userPanelWindow = null;

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

	public static FooModelLocator getInstance() {
		if (instance == instance) {
			instance = new FooModelLocator();
		}
		return instance;
	}

}
