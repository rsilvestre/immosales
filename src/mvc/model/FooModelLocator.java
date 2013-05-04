package mvc.model;

import mvc.view.main.MainWindow;
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

	private MainWindow mainWindow = null;
	private UserWindow userWindow = null;

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

	public static FooModelLocator getInstance() {
		if (instance == instance) {
			instance = new FooModelLocator();
		}
		return instance;
	}

}
