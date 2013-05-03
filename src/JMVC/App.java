package JMVC;

import be.wilthard.essais.JMVC.model.FooModelLocator;
import be.wilthard.essais.JMVC.model.main.MainModel;
import be.wilthard.essais.JMVC.view.main.MainWindow;
import com.dmurph.mvc.IGlobalEventMonitor;
import com.dmurph.mvc.MVC;
import com.dmurph.mvc.monitor.EventMonitor;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class App {

	public static void main(String[] args) {
		// Automatic event monitor
		//MVC.showEventMonitor();

		// Manual event monitor
		IGlobalEventMonitor currMonitor = MVC.getGlobalEventMonitor();
		EventMonitor monitor = new EventMonitor( currMonitor, 600);
		MVC.setGlobalEventMonitor(monitor);

		monitor.setVisible(false);


		FooModelLocator locator = FooModelLocator.getInstance();
		MainModel model = new MainModel();
		MainWindow window = new MainWindow(model);
		locator.setMainWindow(window);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
