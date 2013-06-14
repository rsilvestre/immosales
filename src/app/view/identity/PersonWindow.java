package app.view.identity;

import app.model.DB.identity.Person;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 00:10
 * To change this template use File | Settings | File Templates.
 */
public class PersonWindow extends JPanel {
	private final static String [] TITRES = {"Mr", "Mme", "Melle"};

	private JComboBox inputTitre;
	private JTextField inputFirstName;
	private JTextField inputLastName;
	private JTextField inputPhoneNumber;
	private JTextField inputEmail;

	private Person person;

	public PersonWindow(Person argPerson) {
		person = argPerson;
		initComponents();
	}

	private void initComponents() {
		inputTitre = new JComboBox(TITRES);
		inputFirstName = new JTextField(10);
		inputLastName = new JTextField(10);
		inputPhoneNumber = new JTextField(10);
		inputEmail = new JTextField(10);

		JPanel panneauLabels = new JPanel(new GridLayout(5, 1, 5, 5));
		panneauLabels.add(new JLabel("Titre :"));
		panneauLabels.add(new JLabel("Nom :"));
		panneauLabels.add(new JLabel("Prenom :"));
		panneauLabels.add(new JLabel("Numéro de téléphone :"));
		panneauLabels.add(new JLabel("Adresse Email :"));

		JPanel panneauSaisie = new JPanel(new GridLayout(5, 1, 5, 5));
		panneauSaisie.add(inputTitre);
		panneauSaisie.add(inputFirstName);
		panneauSaisie.add(inputLastName);
		panneauSaisie.add(inputPhoneNumber);
		panneauSaisie.add(inputEmail);

		setLayout(new BorderLayout(5,5));
		add(panneauLabels, BorderLayout.WEST);
		add(panneauSaisie, BorderLayout.CENTER);
	}

	private void linkModel() {
		inputTitre.setSelectedItem(person.getTitre());
		inputFirstName.setText(person.getFirstName());
		inputLastName.setText(person.getLastName());
	}

}
