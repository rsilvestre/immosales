/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.user;

import mvc.App;
import mvc.model.FooModelLocator;
import mvc.model.identity.*;
import mvc.model.user.UserModel;
import mvc.model.user.UserPanelModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class UserWindow extends JFrame {
	private static enum userTypeEnum {
		Buyer("Acheteur"), Owner("Vendeur"), Saler("Commercial");

		private String converteur;

		userTypeEnum(String value) {
			converteur = value;
		}

		@Override
		public String toString() {
			return converteur;
		}
		public static userTypeEnum fromString(String text) {
			if (text != null) {
				for (userTypeEnum b : userTypeEnum.values()) {
					if (text.equalsIgnoreCase(b.converteur)) {
						return b;
					}
				}
			}
			return null;
		}

	}
	private final UserWindow userWindow = this;
	//private final static String[] USER_TYPE = {"Acheteur", "Vendeur", "Commercial"};
	private final static String[] USER_TYPE = {
		userTypeEnum.Buyer.toString(),
		userTypeEnum.Owner.toString(),
		userTypeEnum.Saler.toString()
	};

	private JComboBox userType;
	private JButton login;
	private JButton logout;
	private JButton create;
	private JTable jTable;
	private DefaultTableModel tableModel;

	private UserModel userModel;

	public UserWindow(UserModel argUserModel) {
		userModel = argUserModel;
		initComponents();
		addListeners();

		populateLocale();
	}

	private void initComponents() {
		this.setTitle("Contact");
		userType = new JComboBox(USER_TYPE);
		login = new JButton();
		logout = new JButton();
		create = new JButton();

		setLayout(new BorderLayout(3,1));

		add(userType, BorderLayout.NORTH);

		String [] colonnes = {"Titre", "Nom", "Prénom", "Adresse"};
		tableModel = new DefaultTableModel(colonnes,0);
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

	private void addListeners() {
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				FooModelLocator locator = FooModelLocator.getInstance();
				UserPanelModel userPanelModel = new UserPanelModel();
				UserPanelWindow userPanelWindow = new UserPanelWindow(userPanelModel);

				int response = JOptionPane.showConfirmDialog(userWindow, userPanelWindow, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (response == JOptionPane.OK_OPTION) {
					tableModel.addRow(new String[] {
						userPanelWindow.getTitre(),
						userPanelWindow.getNom(),
						userPanelWindow.getPrenom(),
						getAddress(userPanelWindow)
					});
					Person person = new Person(userPanelWindow.getTitre(), userPanelWindow.getPrenom(), userPanelWindow.getNom());


					App.em.insert(person);
					Address address = new Address(
						person,
						userPanelWindow.getAddressStreetName(),
						userPanelWindow.getAddressStreetNumber(),
						userPanelWindow.getAddressStreetBox(),
						userPanelWindow.getAddressCity(),
						userPanelWindow.getAddressLocality(),
						userPanelWindow.getAddressPosteCode(),
						userPanelWindow.getAddressCountry()
					);
					App.em.insert(address);

					String typeUtilisateur = (getCurrentUserType());
					if (userTypeEnum.fromString(typeUtilisateur) == userTypeEnum.Buyer) {
						Buyer buyer = new Buyer(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
						App.em.insert(buyer);
					} else if (userTypeEnum.fromString(typeUtilisateur) == userTypeEnum.Owner) {
						Owner owner = new Owner(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
						App.em.insert(owner);
					} else if (userTypeEnum.fromString(typeUtilisateur) == userTypeEnum.Saler) {
						Saler saler = new Saler(person, userPanelWindow.getTelephone(), userPanelWindow.getEmail());
						App.em.insert(saler);
					}
				}
			}
		});
	}

	private void populateLocale() {
		login.setText("Connexion");
		create.setText("Créer un utilisateur");
		logout.setText("Déconnexion");

		refreshTable();

	}

	private void refreshTable() {

		String test = getCurrentUserType();

		if (userTypeEnum.fromString(test) == userTypeEnum.Buyer) {
			List<Buyer> buyers = App.em.find(Buyer.class, "order by id");
			for (Buyer buyer : buyers) {
				Person personRow = buyer.getPerson();
				tableModel.addRow(new String [] {personRow.getTitre(), personRow.getLastName(), personRow.getFirstName(), getAddressString(personRow.getAddresses())});
			}
		}
		if (userTypeEnum.fromString(test) == userTypeEnum.Owner) {
			List<Owner> owners = App.em.find(Owner.class, "order by id");
			for (Owner owner : owners) {
				Person personRow = owner.getPerson();
				tableModel.addRow(new String [] {personRow.getTitre(), personRow.getLastName(), personRow.getFirstName(), getAddressString(personRow.getAddresses())});
			}
		}
		if (userTypeEnum.fromString(test) == userTypeEnum.Saler) {
			List<Saler> salers = App.em.find(Saler.class, "order by id");
			for (Saler saler : salers) {
				Person personRow = saler.getPerson();
				tableModel.addRow(new String [] {personRow.getTitre(), personRow.getLastName(), personRow.getFirstName(), getAddressString(personRow.getAddresses())});
			}
		}
	}

	private String getCurrentUserType() {
		return (String) userType.getItemAt(userType.getSelectedIndex());
	}

	private static String getAddress(UserPanelWindow userPanelWindow) {
		String addressRow = userPanelWindow.getAddressStreetName() + " " +
			userPanelWindow.getAddressStreetNumber() + "/" +
			userPanelWindow.getAddressStreetBox() + "\n" +
			userPanelWindow.getAddressPosteCode() + " " +
			userPanelWindow.getAddressCity() + " - " +
			userPanelWindow.getAddressLocality() + "\n" +
			userPanelWindow.getAddressCountry();
		return addressRow;
	}

	private static String getAddressString(List<Address> addresses) {
		String addressRow = "";

		for(Address address : addresses) {
			addressRow += address.getStreetName() + " " +
			address.getStreetNumber() + "/" +
			address.getStreetBox() + "\n" +
			address.getPosteCode() + " " +
			address.getCity() + " - " +
			address.getLocality() + "\n" +
			address.getCountry() + "\n";
		}

		return addressRow.substring(0, addressRow.length()-1);
	}


}
