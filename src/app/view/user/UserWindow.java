/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.user;

import core.Session;
import app.App;
import app.model.FooModelLocator;
import app.model.DB.identity.*;
import app.model.user.UserPanelModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */

/**
 * Dialog de gestion des utilisateurs
 */
public class UserWindow extends JPanel {
	private JComboBox userType;
	private JButton create;
	private JTable jTable;
	private DefaultTableModel tableModel;

	private APerson selectedPerson = null;
	private List<APerson> aPersons = null;

	/**
	 * Constructeur
	 */
	public UserWindow() {
		initComponents();
		addListeners();

		populateLocale();
	}

	/**
	 * Initialisation des composants de base fait à la main
	 */
	private void initComponents() {
		//this.setTitle("Contact");
		userType = new JComboBox(APerson.UserTypeEnum.getUserType());
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
		//box.add(login);
		box.add(create);
		box.add(Box.createRigidArea(new Dimension(30,1)));
		box.add(Box.createHorizontalGlue());
		//box.add(logout);

		add(box, BorderLayout.SOUTH);

	}

	/**
	 * Ajout des écouteurs d'événement
	 */
	private void addListeners() {
		// Lancement d'un fenêtre de création d'un nouvelle utilisateur
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				createUser(Session.getInstance().getAPerson());
			}
		});
		// Modification d'un utilisateur existant (pas de rôle actuellement)
		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					setSelectedPerson(getAPersonSelected(target.getSelectedRow()));
					if (target.getSelectedColumn() == 4) {
						editRow(target);
					}
				}
			}
		});
		// Changement de contexte utilisateur
		userType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.SELECTED) {
					refreshTable();
				}
			}
		});
	}

	/**
	 * Mapping de l'édition d'un utilisateur
	 * @param target
	 */
	private void editRow(JTable target) {
		int row = target.getSelectedRow();
		APerson aPerson = getAPersonSelected(row);

		FooModelLocator locator = FooModelLocator.getInstance();
		UserPanelModel userPanelModel = new UserPanelModel();
		UserPanelWindow userPanelWindow = new UserPanelWindow(userPanelModel, aPerson);
		locator.setUserPanelWindow(userPanelWindow);

		int reponse = JOptionPane.showConfirmDialog(UserWindow.this, userPanelWindow, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

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
			if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Buyer) {
				App.em.update((Buyer)aPerson);
			} else if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Owner) {
				App.em.update((Owner)aPerson);
			} else if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Saler) {
				App.em.update((Saler)aPerson);
			}

			target.getModel().setValueAt(userPanelWindow.getTitre(), row, 0);
			target.getModel().setValueAt(userPanelWindow.getNom(), row, 1);
			target.getModel().setValueAt(userPanelWindow.getPrenom(), row, 2);
			target.getModel().setValueAt(getAddress(userPanelWindow), row, 3);
			target.repaint();
		}
	}

	/**
	 * mapping de création d'un utilisateur
	 * @param aPerson
	 */
	private void createUser(APerson aPerson) {
		FooModelLocator locator = FooModelLocator.getInstance();
		UserPanelModel userPanelModel = new UserPanelModel();
		UserPanelWindow userPanelWindow = null;
		if (aPerson != null) {
			if (Session.getInstance().getAPerson() != null) {
				if (JOptionPane.showConfirmDialog(UserWindow.this, "Voulez-vous utiliser les mêmes données que pour votre connexion actuelle ?", "Oui", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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

		int response = JOptionPane.showConfirmDialog(UserWindow.this, userPanelWindow, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

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
			if (APerson.UserTypeEnum.fromString(typeUtilisateur) == APerson.UserTypeEnum.Buyer) {
				Buyer buyer = new Buyer(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(buyer);
				addAPersons(buyer);
			} else if (APerson.UserTypeEnum.fromString(typeUtilisateur) == APerson.UserTypeEnum.Owner) {
				Owner owner = new Owner(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(owner);
				addAPersons(owner);
			} else if (APerson.UserTypeEnum.fromString(typeUtilisateur) == APerson.UserTypeEnum.Saler) {
				Saler saler = new Saler(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
				App.em.insert(saler);
				addAPersons(saler);
			}
		}
	}

	/**
	 * Ajout des textes sur les élements de la page
	 */
	private void populateLocale() {
		create.setText("Créer un utilisateur");
		refreshTable();

	}

	/**
	 * Rafraichissement du tableau contenant les utilisateurs disponnibles
	 * Pourrait être réfactoré pour utiliser la reflection
	 */
	private void refreshTable() {
		// Erase all previous row
		for (int i = ((DefaultTableModel)jTable.getModel()).getRowCount()-1;i>=0;i--) {
			((DefaultTableModel)jTable.getModel()).removeRow(i);
		}

		// Efface aPersons list
		clearAPersons();

		// Select new user type
		String currentUserType = getCurrentUserType();

		if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Buyer) {
			List<Buyer> buyers = App.em.find(Buyer.class, "order by id");
			for (Buyer buyer : buyers) {
				addAPersons(buyer);
				tableAddRow(buyer);
			}
		}
		if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Owner) {
			List<Owner> owners = App.em.find(Owner.class, "order by id");
			for (Owner owner : owners) {
				addAPersons(owner);
				tableAddRow(owner);
			}
		}
		if (APerson.UserTypeEnum.fromString(currentUserType) == APerson.UserTypeEnum.Saler) {
			List<Saler> salers = App.em.find(Saler.class, "order by id");
			for (Saler saler : salers) {
				addAPersons(saler);
				tableAddRow(saler);
			}
		}
	}

	/**
	 * Ajout de row dans le tableau
	 * @param aPersonRow
	 */
	private void tableAddRow(APerson aPersonRow) {
		tableModel.addRow(aPersonRow.getTableRow(Session.getInstance()));
	}

	/**
	 * Récupération de l'utilisateur courrant
	 * @return
	 */
	private String getCurrentUserType() {
		return (String) userType.getItemAt(userType.getSelectedIndex());
	}

	/**
	 * toString sur l'adresse de l'utlisateur
	 * @param userPanelWindow
	 * @return
	 */
	private static String getAddress(UserPanelWindow userPanelWindow) {
		return userPanelWindow.getAddressStreetName() + " " +
			userPanelWindow.getAddressStreetNumber() + "/" +
			userPanelWindow.getAddressStreetBox() + "\n" +
			userPanelWindow.getAddressPosteCode() + " " +
			userPanelWindow.getAddressCity() + " - " +
			userPanelWindow.getAddressLocality() + "\n" +
			userPanelWindow.getAddressCountry();
	}

	/**
	 * Récuparation de l'utilisateur sélectionné
	 * @return
	 */
	public APerson getSelectedPerson() {
		return selectedPerson;
	}

	/**
	 * Set de l'utilisateur sélectionné
	 * @param selectedPerson
	 */
	private void setSelectedPerson(APerson selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	/**
	 * Getter de APerson
	 * @return
	 */
	private List<APerson> getAPersons() {
		return aPersons;
	}

	/**
	 * Getter de l'APerson selectionné
	 * @param index
	 * @return
	 */
	private APerson getAPersonSelected(int index) {
		return getAPersons().get(index);
	}

	/**
	 * Ajout d'un APerson à la liste des APerson
	 * @param aPerson
	 */
	private void addAPersons(APerson aPerson) {
		this.aPersons.add(aPerson);
	}

	/**
	 * Effacer toutes la liste des APerson
	 */
	private void clearAPersons() {
		if (aPersons != null) {
			aPersons.clear();
		}
	}
}
