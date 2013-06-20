package app.view.main;

import core.Session;
import app.controller.main.ConnexionEvent;
import app.model.FooModelLocator;
import app.model.DB.identity.*;
import app.model.Saler.SalerModel;
import app.model.main.MainModel;
import app.view.Saler.SalerUserControlWindow;
import app.view.buyer.BienRecorderAndOfferWindow;
import app.view.owner.OwnerUserControl;
import app.view.user.UserWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Silvestre
 * Date: 4/05/13
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {
	private JPanel mainScreen;
	private Box box;
	private JMenuBar jMenuBar;
	private JMenuItem menuConnexion;
	private JMenuItem menuLeave;
	private JTextArea textArea;

	private MainModel mainModel;

	/**
	 * Constructeur
	 * @param mainModel
	 */
	public MainWindow(MainModel mainModel) {
		this.mainModel = mainModel;
		initComponents();
		linkModel();
		addListeners();
	}

	/**
	 * Initialisation des composants de base
	 */
	private void initComponents() {
		this.setTitle("ImmoSales");
		this.setMinimumSize(new Dimension(640, 480));
		mainScreen = new JPanel();
		box = Box.createHorizontalBox();


		jMenuBar = new JMenuBar();
		menuConnexion = new JMenuItem("Connexion", 'C');
		menuLeave = new JMenuItem("Quitter", 'Q');

		textArea = new JTextArea(20, 40);

		setLayout(new BorderLayout(5,5));

		add(mainScreen, BorderLayout.CENTER);

		setJMenuBar(jMenuBar);
	}

	/**
	 * Liaison des écouteur avec les lanceurs dans les modèles
	 * Setting des valeurs par défaut
	 */
	private void linkModel() {
		// set de l'image de démarrage
		try {
			BufferedImage myPicture = ImageIO.read(getClass().getResource("/ressources/images/welcome2.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			mainScreen.add(picLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Décore le menu principale
		JMenu fileMenu = new JMenu("Fichier");
		jMenuBar.add(fileMenu);
		fileMenu.add(menuConnexion);
		fileMenu.add(menuLeave);

		// Ecouteur d'événement
		mainModel.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				// Ecoute si on change d'utilisateur et adapte le contenu de la page en fonction
				if (name.equals("aPerson")) {
					if (Session.getInstance().isConnected()) {
						connectionRespone((APerson)evt.getNewValue());
						textArea.append(" Connected!\n");
						Person person = ((APerson)evt.getNewValue()).getPerson();
						textArea.append("Bienvenu " + person.getFirstName() + " " + person.getLastName() + "\n");

						textArea.append("Connecté en tant que : " + ((APerson) evt.getNewValue()).getUserType().toString());
					}
				}
			}
		});
	}

	/**
	 * Adapte le contenu de la page principale en fonction de l'utilisateur
	 * @param aPerson
	 */
	private void connectionRespone(APerson aPerson) {
		Person person = aPerson.getPerson();
		JTextField connectionLabel = new JTextField(
				"Bienvenu " + person.getTitre() + " " + person.getLastName() +
				" Connecté en tant que : " + aPerson.getUserType().toString()
		);

		remove(box);
		box = Box.createHorizontalBox();
		box.add(Box.createRigidArea(new Dimension(31,1)));
		box.add(Box.createHorizontalGlue());
		box.add(connectionLabel);

		mainScreen.removeAll();

		mainScreen.add(contentSelector());

		add(box, BorderLayout.SOUTH);
		mainScreen.revalidate();
		this.repaint();
	}

	/**
	 * Ajoute un control utilisateur à l'écran principale suivant le type d'utilisateur
	 * @return
	 */
	private Component contentSelector() {
		// Pour les propriétaires
		if (Session.getInstance().getAPerson() instanceof Owner) {
			return new OwnerUserControl();
		// Pour les acheteurs
		} else if (Session.getInstance().getAPerson() instanceof Buyer) {
			return new BienRecorderAndOfferWindow();
		// Pour les agent immobiliers
		} else if(Session.getInstance().getAPerson() instanceof Saler) {
			SalerModel salerModel = new SalerModel();
			return new SalerUserControlWindow(salerModel);
		}
		// page d'erreur 404. Wink wink
		return new JLabel("No Frame for this userType now!!!");
	}

	/**
	 * Lanceur d'événement
	 */
	private void addListeners() {
		// Touche de raccourci pour aller directement à l'écran de connexion
		int toucheRaccourcis = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		menuConnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, toucheRaccourcis));

		// Lancement d'un dialog pour inviter à se connecter à l'application
		menuConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Instanciation de l'optionpane de connexion
				FooModelLocator locator = FooModelLocator.getInstance();
				UserWindow userWindow = new UserWindow();
				locator.setUserWindow(userWindow);
				userWindow.setSize(650, 300);

				// Lancement d'un dialog
				int connexionDialog = JOptionPane.showConfirmDialog(MainWindow.this, userWindow, "Connexion", JOptionPane.OK_CANCEL_OPTION);
				if (connexionDialog == JOptionPane.OK_OPTION) {
					// Conenxion à l'application si un utilisateur est selectionné et sauvegarde en session
					if (userWindow.getSelectedPerson() != null) {
						Session.getInstance().setConnected(true);
						Session.getInstance().setAPerson(userWindow.getSelectedPerson());
					}
				}
				// Adaptation du contenu de la page principale suivant l'utilisateur sélectionné
				ConnexionEvent event = new ConnexionEvent(Session.getInstance().getAPerson(), mainModel);
				event.dispatch();
			}
		});

		// Touche de raccourci pour la fermeture de l'application
		menuLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, toucheRaccourcis));
		// Dialog à la fermeture de l'application
		menuLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				if (JOptionPane.showConfirmDialog(MainWindow.this, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}
}
