package mvc.view.main;

import core.Session;
import mvc.controller.main.ConnexionEvent;
import mvc.model.FooModelLocator;
import mvc.model.DB.identity.*;
import mvc.model.buyer.BienRecorderAndOfferModel;
import mvc.model.buyer.BuyerModel;
import mvc.model.main.MainModel;
import mvc.model.owner.OwnerModel;
import mvc.model.user.UserModel;
import mvc.view.buyer.BienRecorderAndOfferWindow;
import mvc.view.owner.OwnerWindow;
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
	private JButton createAdvertising;
	private OwnerWindow ownerWindow;

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

	private void linkModel() {
		//text.setText(model.getText());
		//text.setForeground(model.getColor());
		textArea.setText(mainModel.getText());
		mainScreen.add(new JScrollPane(textArea));

		JMenu fileMenu = new JMenu("Fichier");
		jMenuBar.add(fileMenu);
		fileMenu.add(menuConnexion);
		fileMenu.add(menuLeave);

		mainModel.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				if (name.equals("text")) {
					String value = (String) evt.getNewValue();
					textArea.setText(value);
				} else if (name.equals("aPerson")) {
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

	private void connectionRespone(APerson aPerson) {
		Person person = aPerson.getPerson();
		JTextField connectionLabel = new JTextField(
				"Bienvenu " + person.getTitre() + " " + person.getLastName() +
				" Connecté en tant que : " + aPerson.getUserType().toString()
		);
		createAdvertising = new JButton("Nouveau bien");

		remove(box);
		box = Box.createHorizontalBox();
		box.add(createAdvertising);
		box.add(Box.createRigidArea(new Dimension(31,1)));
		box.add(Box.createHorizontalGlue());
		box.add(connectionLabel);

		mainScreen.removeAll();

		mainScreen.add(contentSelector());

		add(box, BorderLayout.SOUTH);
		mainScreen.revalidate();
		this.repaint();
	}

	private Component contentSelector() {
		if (Session.getInstance().getAPerson() instanceof Owner) {
			OwnerModel ownerModel = new OwnerModel();
			return new OwnerWindow(ownerModel);
		} else if (Session.getInstance().getAPerson() instanceof Buyer) {
			BienRecorderAndOfferModel bienRecorderAndOfferModel = new BienRecorderAndOfferModel();
			//return new BuyerWindow(buyerModel);
			return new BienRecorderAndOfferWindow(bienRecorderAndOfferModel);
		}
		return new JLabel("No Frame for this userType now!!!");
	}

	private void addListeners() {
		int toucheRaccourcis = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		menuConnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, toucheRaccourcis));
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
				ConnexionEvent event = new ConnexionEvent(Session.getInstance().getAPerson(), mainModel);
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
