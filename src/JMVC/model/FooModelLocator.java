package JMVC.model;

import be.wilthard.essais.JMVC.controller.main.MainController;
import be.wilthard.essais.JMVC.view.main.MainWindow;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class FooModelLocator {
	private static FooModelLocator instance = null;

	// controllers
	private MainController mainController = new MainController();

	private MainWindow mainWindow = null;

	private FooModelLocator() {}

	public void setMainWindow(MainWindow argWindow) {
		mainWindow = argWindow;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public static FooModelLocator getInstance() {
		if (instance == null) {
			instance = new FooModelLocator();
		}
		return instance;
	}
}
