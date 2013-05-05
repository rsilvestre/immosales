package mvc.view.main;

import core.Session;
import mvc.controller.main.ConnexionEvent;
import mvc.model.FooModelLocator;
import mvc.model.main.MainModel;
import mvc.model.user.UserModel;
import mvc.view.user.UserWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {
	private JPanel mainScreen;
	private JMenuBar jMenuBar;
	private JMenuItem menuConnexion;
	private JMenuItem menuLeave;
	private JTextArea textArea;

	private final MainWindow fenetre = this;

	private MainModel mainModel;

	public MainWindow(MainModel mainModel) {
		this.mainModel = mainModel;
		initComponents();
		linkModel();
		addListeners();
	}

	private void initComponents() {
		this.setTitle("ImmoSales");
		mainScreen = new JPanel();

		jMenuBar = new JMenuBar();
		menuConnexion = new JMenuItem("Connexion", 'C');
		menuLeave = new JMenuItem("Quitter", 'Q');

		textArea = new JTextArea(20, 40);

		setLayout(new BorderLayout(5,5));

		add(mainScreen, BorderLayout.CENTER);

		//mainScreen.setLayout(new BorderLayout(5,5));


		setJMenuBar(jMenuBar);
		JMenu fileMenu = new JMenu("Fichier");
		jMenuBar.add(fileMenu);
		fileMenu.add(menuConnexion);
		fileMenu.add(menuLeave);

		//add(mainScreen);

		Box box = Box.createHorizontalBox();

		add(box, BorderLayout.SOUTH);
	}

	private void linkModel() {
		//text.setText(model.getText());
		//text.setForeground(model.getColor());
		textArea.setText(mainModel.getText());
		mainScreen.add(new JScrollPane(textArea));

		mainModel.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				if (name.equals("text")) {
					String value = (String) evt.getNewValue();
					textArea.setText(value);
				} else if (name.equals("color")) {
					Color value = (Color) evt.getNewValue();
					//text.setForeground(value);
				}
			}
		});
	}

	private void addListeners() {
		int toucheRaccourcis = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		menuConnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, toucheRaccourcis));
		menuConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.

				FooModelLocator locator = FooModelLocator.getInstance();
				UserModel userModel = new UserModel();
				UserWindow userWindow = new UserWindow(userModel);
				locator.setUserWindow(userWindow);
				userWindow.setSize(650, 300);

				int connexionDialog = JOptionPane.showConfirmDialog(fenetre, userWindow, "Connexion", JOptionPane.OK_CANCEL_OPTION);
				if (connexionDialog == JOptionPane.OK_OPTION) {
					if (userWindow.getSelectedPerson() != null) {
						Session.getInstance().setConnected(true);
						Session.getInstance().setAPerson(userWindow.getSelectedPerson());
					}
				}
				ConnexionEvent event = new ConnexionEvent(Session.getInstance(), mainModel);
				event.dispatch();
			}
		});

		menuLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, toucheRaccourcis));
		menuLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				if (JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}
}
