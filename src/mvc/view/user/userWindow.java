/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.user;

import core.Session;
import mvc.App;
import mvc.model.DB.product.address.City;
import mvc.model.FooModelLocator;
import mvc.model.DB.identity.*;
import mvc.model.user.UserModel;
import mvc.model.user.UserPanelModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class UserWindow extends JPanel {

	private final UserWindow userWindow = this;
	//private final static String[] USER_TYPE = {"Acheteur", "Vendeur", "Commercial"};
	private final static String[] USER_TYPE = {
		APerson.userTypeEnum.Buyer.toString(),
		APerson.userTypeEnum.Owner.toString(),
		APerson.userTypeEnum.Saler.toString()
	};

	private JComboBox userType;
	private JButton login;
	private JButton logout;
	private JButton create;
	private JTable jTable;
	private DefaultTableModel tableModel;

	private APerson selectedPerson = null;
	private List<APerson> aPersons = null;

	private UserModel userModel;

	public UserWindow(UserModel argUserModel) {
		userModel = argUserModel;
		initComponents();
		addListeners();

		populateLocale();
	}

	/**
	 * Initialisation des composants de base
	 */
	private void initComponents() {
		//this.setTitle("Contact");
		userType = new JComboBox(USER_TYPE);
		login = new JButton();
		logout = new JButton();
		create = new JButton();
		aPersons = new ArrayList<APerson>();

		setLayout(new BorderLayout(3,1));

		JPanel northUserType = new JPanel();
		northUserType.add(userType);
		add(northUserType, BorderLayout.NORTH);

		String [] colonnes = {"Titre", "Nom", "Prénom", "Adresse", "Edit", "Connect"};
		tableModel = new DefaultTableModel(colonnes,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable = new JTable(tableModel);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		add(new JScrollPane(jTable), BorderLayout.CENTER);

		Box box = Box.createHorizontalBox();
		box.add(login);
		box.add(create);
		box.add(Box.createRigidArea(new Dimension(30,1)));
		box.add(Box.createHorizontalGlue());
		box.add(logout);

		add(box, BorderLayout.SOUTH);

	}

	/**
	 * Ajout des écouteurs d'événement
	 */
	private void addListeners() {
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				createUser(Session.getInstance().getAPerson());
			}
		});
		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					setSelectedPerson(getAPersonSelected(target.getSelectedRow()));
					if (target.getSelectedColumn()== 4) {
						editRow(target);
					}
				}
			}
		});
		userType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.

				refreshTable();
			}
		});
	}

	private void editRow(JTable target) {
		int row = target.getSelectedRow();

		/*String prenom = (String)target.getModel().getValueAt(row, 2);
		String nom = (String)target.getModel().getValueAt(row, 1);
		Person person1 = App.em.findUnique(Person.class, "where firstname = ? and lastname = ?", prenom, nom);
		Address address = person1.getAddresses().get(0);

		String test = getCurrentUserType();
		APerson aPerson = null;
		if (userTypeEnum.fromString(test) == userTypeEnum.Buyer) {
			aPerson = person1.getBuyer();
		} else if (userTypeEnum.fromString(test) == userTypeEnum.Owner) {
			aPerson = person1.getOwner();
		} else if (userTypeEnum.fromString(test) == userTypeEnum.Saler) {
			aPerson = person1.getSaler();
		}*/
		APerson aPerson = getAPersonSelected(row);

		FooModelLocator locator = FooModelLocator.getInstance();
		UserPanelModel userPanelModel = new UserPanelModel();
		UserPanelWindow userPanelWindow = new UserPanelWindow(userPanelModel, aPerson);
		locator.setUserPanelWindow(userPanelWindow);

		int reponse = JOptionPane.showConfirmDialog(userWindow, userPanelWindow, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (reponse == JOptionPane.OK_OPTION) {
			//modele.addRow(new String [] {userPanelWindow.getTitre(), userPanelWindow.getNom(), userPanelWindow.getPrenom(), getAddress(userPanelWindow)});
			Person person = aPerson.getPerson();
			Address address = person.getAddresses().get(0);
			person.setTitre(userPanelWindow.getTitre());
			person.setFirstName(userPanelWindow.getPrenom());
			person.setLastName(userPanelWindow.getNom());
			App.em.update(person);

			address.setStreetName(userPanelWindow.getAddressStreetName());
			address.setStreetNumber(userPanelWindow.getAddressStreetNumber());
			address.setStreetBox(userPanelWindow.getAddressStreetBox());
			address.setCity(userPanelWindow.getCity());
			App.em.update(address);

			aPerson.setPhoneNumber(userPanelWindow.getTelephone());
			aPerson.setEmail(userPanelWindow.getEmail());
			String currentUserType = getCurrentUserType();
			if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Buyer) {
				App.em.update((Buyer)aPerson);
			} else if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Owner) {
				App.em.update((Owner)aPerson);
			} else if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Saler) {
				App.em.update((Saler)aPerson);
			}

			target.getModel().setValueAt(userPanelWindow.getTitre(), row, 0);
			target.getModel().setValueAt(userPanelWindow.getNom(), row, 1);
			target.getModel().setValueAt(userPanelWindow.getPrenom(), row, 2);
			target.getModel().setValueAt(getAddress(userPanelWindow), row, 3);
			target.repaint();
		}
	}

	private void createUser(APerson aPerson) {
		FooModelLocator locator = FooModelLocator.getInstance();
		UserPanelModel userPanelModel = new UserPanelModel();
		UserPanelWindow userPanelWindow = null;
		if (aPerson != null) {
			if (Session.getInstance().getAPerson() != null) {
				if (JOptionPane.showConfirmDialog(userWindow, "Voulez-vous utiliser les mêmes données que pour votre connexion actuelle ?", "Oui", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					userPanelWindow = new UserPanelWindow(userPanelModel, aPerson);
				} else {
					userPanelWindow = new UserPanelWindow(userPanelModel);
				}
			} else {
				userPanelWindow = new UserPanelWindow(userPanelModel);
			}
		} else {
			userPanelWindow = new UserPanelWindow(userPanelModel);
		}

		locator.setUserPanelWindow(userPanelWindow);

		int response = JOptionPane.showConfirmDialog(userWindow, userPanelWindow, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (response == JOptionPane.OK_OPTION) {
			tableModel.addRow(new String[] {
				userPanelWindow.getTitre(),
				userPanelWindow.getNom(),
				userPanelWindow.getPrenom(),
				getAddress(userPanelWindow),
				"Edit",
				""
			});

			Person person = new Person(userPanelWindow.getTitre(), userPanelWindow.getPrenom(), userPanelWindow.getNom());
			App.em.insert(person);

			Address address = new Address(
				person,
				userPanelWindow.getAddressStreetName(),
				userPanelWindow.getAddressStreetNumber(),
				userPanelWindow.getAddressStreetBox(),
				userPanelWindow.getCity()
			);
			App.em.insert(address);
			person.addAddresses(address);

			String typeUtilisateur = (getCurrentUserType());
			if (APerson.userTypeEnum.fromString(typeUtilisateur) == APerson.userTypeEnum.Buyer) {
				Buyer buyer = new Buyer(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(buyer);
				addAPersons(buyer);
			} else if (APerson.userTypeEnum.fromString(typeUtilisateur) == APerson.userTypeEnum.Owner) {
				Owner owner = new Owner(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(owner);
				addAPersons(owner);
			} else if (APerson.userTypeEnum.fromString(typeUtilisateur) == APerson.userTypeEnum.Saler) {
				Saler saler = new Saler(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(saler);
				addAPersons(saler);
			}
		}
	}

	private void populateLocale() {
		login.setText("Connexion");
		create.setText("Créer un utilisateur");
		logout.setText("Déconnexion");

		refreshTable();

	}

	private void refreshTable() {
		// Erase all previous row
		for (int i = ((DefaultTableModel)jTable.getModel()).getRowCount()-1;i>=0;i--) {
			((DefaultTableModel)jTable.getModel()).removeRow(i);
		}

		// Efface aPersons list
		clearAPersons();

		// Select new user type
		String currentUserType = getCurrentUserType();

		if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Buyer) {
			List<Buyer> buyers = App.em.find(Buyer.class, "order by id");
			for (Buyer buyer : buyers) {
				addAPersons(buyer);
				tableAddRow(buyer);
			}
		}
		if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Owner) {
			List<Owner> owners = App.em.find(Owner.class, "order by id");
			for (Owner owner : owners) {
				addAPersons(owner);
				tableAddRow(owner);
			}
		}
		if (APerson.userTypeEnum.fromString(currentUserType) == APerson.userTypeEnum.Saler) {
			List<Saler> salers = App.em.find(Saler.class, "order by id");
			for (Saler saler : salers) {
				addAPersons(saler);
				tableAddRow(saler);
			}
		}
	}

	private void tableAddRow(APerson aPersonRow) {
		tableModel.addRow(aPersonRow.getTableRow(Session.getInstance()));
	}

	private String getCurrentUserType() {
		return (String) userType.getItemAt(userType.getSelectedIndex());
	}

	private static String getAddress(UserPanelWindow userPanelWindow) {
		return userPanelWindow.getAddressStreetName() + " " +
			userPanelWindow.getAddressStreetNumber() + "/" +
			userPanelWindow.getAddressStreetBox() + "\n" +
			userPanelWindow.getAddressPosteCode() + " " +
			userPanelWindow.getAddressCity() + " - " +
			userPanelWindow.getAddressLocality() + "\n" +
			userPanelWindow.getAddressCountry();
	}

	private static String getAddressString(List<Address> addresses) {
		String addressRow = "";

		for(Address address : addresses) {
			addressRow += address.getAddressString();
		}

		return addressRow.substring(0, addressRow.length()-1);
	}

	public APerson getSelectedPerson() {
		return selectedPerson;
	}

	private void setSelectedPerson(APerson selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	private List<APerson> getAPersons() {
		return aPersons;
	}

	private APerson getAPersonSelected(int index) {
		return getAPersons().get(index);
	}

	private void addAPersons(APerson aPerson) {
		this.aPersons.add(aPerson);
	}

	private void clearAPersons() {
		if (aPersons != null) {
			aPersons.clear();
		}
	}
}
