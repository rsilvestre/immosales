package view.carnet;

import core.DataManager;
import model.identity.Address;
import model.identity.Person;
import net.sf.jeasyorm.EntityManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 1/05/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class CarnetAdresses {

	public static void main(String [] args) {

		Properties configFile = new Properties();
		try {
			configFile.load(CarnetAdresses.class.getClassLoader().getResourceAsStream("config/config.properties"));
			
			DataManager.getInstance().initManager("immosales","org.postgresql.Driver",
					"jdbc:postgresql://" + configFile.getProperty("NAME_DATABASE"), configFile.getProperty("USER_DATABASE"),
					configFile.getProperty("DB_PASSWORD"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final EntityManager em = DataManager.getInstance().getEntityManager();

		final JFrame fenetre = new JFrame("Contacts");

		String [] colonnes = {"Titre", "Nom", "Prénom", "Adresse"};

		final DefaultTableModel modele = new DefaultTableModel(colonnes, 0);

		JTable tableau = new JTable(modele);
		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		//TableColumnAdjuster tableColumnAdjuster = new TableColumnAdjuster(tableau);
		//tableColumnAdjuster.adjustColumns();

		fenetre.add(new JScrollPane(tableau));

		tableau.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					String prenom = (String)target.getModel().getValueAt(row, 2);
					String nom = (String)target.getModel().getValueAt(row, 1);
					Person person1 = em.findUnique(Person.class, "where first_name = ? and last_name = ?", prenom, nom);
					Address address = person1.getAddresses().get(0);
					PanneauContact panneau = new PanneauContact(person1, address);

					int reponse = JOptionPane.showConfirmDialog(fenetre, panneau, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (reponse == JOptionPane.OK_OPTION) {
						//modele.addRow(new String [] {panneau.getTitre(), panneau.getNom(), panneau.getPrenom(), getAddress(panneau)});
						person1.setTitre(panneau.getTitre());
						person1.setFirstName(panneau.getPrenom());
						person1.setLastName(panneau.getNom());
						em.update(person1);

						address.setStreetName(panneau.getAddressStreetName());
						address.setStreetNumber(panneau.getAddressStreetNumber());
						address.setStreetBox(panneau.getAddressStreetBox());
						address.setCity(panneau.getAddressCity());
						address.setLocality(panneau.getAddressLocality());
						address.setPosteCode(panneau.getAddressPosteCode());
						address.setCountry(panneau.getAddressCountry());
						em.update(address);

						target.getModel().setValueAt(panneau.getTitre(), row, 0);
						target.getModel().setValueAt(panneau.getNom(), row, 1);
						target.getModel().setValueAt(panneau.getPrenom(), row, 2);
						target.getModel().setValueAt(getAddress(panneau), row, 3);
						target.repaint();
					}

					// do some action if appropriate column
				}
			}
		});

		List<Person> people = em.find(Person.class, "order by id");
		for (Person personRow : people) {
			modele.addRow(new String [] {personRow.getTitre(), personRow.getLastName(), personRow.getFirstName(), getAddressString(personRow.getAddresses())});
		}

		int toucheRaccourcis = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		JMenuItem menuNouveau = new JMenuItem("Nouveau", 'N');
		menuNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, toucheRaccourcis));

		menuNouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				//To change body of implemented methods use File | Settings | File Templates.
				PanneauContact panneau = new PanneauContact();

				int reponse = JOptionPane.showConfirmDialog(fenetre, panneau, "Nouveau contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (reponse == JOptionPane.OK_OPTION) {

					modele.addRow(new String [] {panneau.getTitre(), panneau.getNom(), panneau.getPrenom(), getAddress(panneau)});
					Person person = new Person(panneau.getTitre(), panneau.getPrenom(), panneau.getNom());


					em.insert(person);
					Address address = new Address(
						person,
						panneau.getAddressStreetName(),
						panneau.getAddressStreetNumber(),
						panneau.getAddressStreetBox(),
						panneau.getAddressCity(),
						panneau.getAddressLocality(),
						panneau.getAddressPosteCode(),
						panneau.getAddressCountry()
					);
					em.insert(address);
				}
			}
		});

		JMenuItem menuQuitter = new JMenuItem("Quitter", 'Q');
		menuQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, toucheRaccourcis));
		menuQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				//To change body of implemented methods use File | Settings | File Templates.
				if (JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		fenetre.setJMenuBar(menuBar);
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		menuFichier.add(menuNouveau);
		menuFichier.add(menuQuitter);


		fenetre.setSize(300, 200);
		fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fenetre.setVisible(true);
	}

	private static String getAddress(PanneauContact panneau) {
		String addressRow = panneau.getAddressStreetName() + " " +
			panneau.getAddressStreetNumber() + "/" +
			panneau.getAddressStreetBox() + "\n" +
			panneau.getAddressPosteCode() + " " +
			panneau.getAddressCity() + " - " +
			panneau.getAddressLocality() + "\n" +
			panneau.getAddressCountry();
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
