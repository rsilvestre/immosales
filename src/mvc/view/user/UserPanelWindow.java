/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.user;

import mvc.model.identity.APerson;
import mvc.model.identity.Address;
import mvc.model.identity.Person;
import mvc.model.user.UserPanelModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class UserPanelWindow extends JPanel {
	private final static String[] TITRES = {"Mr", "Mme", "Melle"};
	private final static String[] LOCALITY = {
			"Bruxelles Capital",
			"Brabant Flamand",
			"Brabant Wallon",
			"Namur",
			"Liège",
			"Hainaut",
			"Luxembourg",
			"Flandre Occidentale",
			"Flandre Orientale",
			"Anvers",
			"Limbourg",
	};

	private JComboBox saisieTitre = new JComboBox(TITRES);
	private JTextField saisieNom = new JTextField(10);
	private JTextField saisiePrenom = new JTextField(10);
	private JTextField saisieTelephone = new JTextField(10);
	private JTextField saisieEmail = new JTextField(10);
	private JTextField saisieAddressStreetName = new JTextField(20);
	private JTextField saisieAddressStreetNumber = new JTextField(10);
	private JTextField saisieAddressStreetBox = new JTextField(10);
	private JTextField saisieAddressPosteCode = new JTextField(10);
	private JTextField saisieAddressCity = new JTextField(20);
	private JComboBox saisieAddressLocality = new JComboBox(LOCALITY);
	private JTextField saisieAddressCountry = new JTextField(20);

	private UserPanelModel userPanelModel;

	public UserPanelWindow(UserPanelModel userPanelModel) {
		this.userPanelModel = userPanelModel;
		initComponents();
	}

	public UserPanelWindow(UserPanelModel userPanelModel, APerson aPerson) {
		this.userPanelModel = userPanelModel;
		setComponents(aPerson);
		this.initComponents();
	}

	private void initComponents() {

		JPanel panneauLabels = new JPanel(new GridLayout(12, 1, 5, 5));
		panneauLabels.add(new JLabel("Titre :"));
		panneauLabels.add(new JLabel("Nom :"));
		panneauLabels.add(new JLabel("Prenom :"));
		panneauLabels.add(new JLabel("Telephone :"));
		panneauLabels.add(new JLabel("Email :"));
		panneauLabels.add(new JLabel("Nom de rue :"));
		panneauLabels.add(new JLabel("N° d'habitation :"));
		panneauLabels.add(new JLabel("N° de boîte :"));
		panneauLabels.add(new JLabel("Code postale :"));
		panneauLabels.add(new JLabel("Ville :"));
		panneauLabels.add(new JLabel("Province :"));
		panneauLabels.add(new JLabel("Pays :"));

		JPanel panneauSaisie = new JPanel(new GridLayout(12, 1, 5, 5));
		panneauSaisie.add(this.saisieTitre);
		panneauSaisie.add(this.saisieNom);
		panneauSaisie.add(this.saisiePrenom);
		panneauSaisie.add(this.saisieTelephone);
		panneauSaisie.add(this.saisieEmail);
		panneauSaisie.add(this.saisieAddressStreetName);
		panneauSaisie.add(this.saisieAddressStreetNumber);
		panneauSaisie.add(this.saisieAddressStreetBox);
		panneauSaisie.add(this.saisieAddressPosteCode);
		panneauSaisie.add(this.saisieAddressCity);
		panneauSaisie.add(this.saisieAddressLocality);
		panneauSaisie.add(this.saisieAddressCountry);

		setLayout(new BorderLayout(5, 5));
		add(panneauLabels, BorderLayout.WEST);
		add(panneauSaisie, BorderLayout.CENTER);
	}

	private void setComponents(APerson aPerson) {
		Person person = aPerson.getPerson();
		Address address = person.getAddresses().get(0);
		this.setSaisieTitre(person.getTitre());
		this.setSaisiePrenom(person.getFirstName());
		this.setSaisieNom(person.getLastName());
		this.setSaisieTelephone(aPerson.getPhoneNumber());
		this.setSaisieEmail(aPerson.getEmail());
		this.setSaisieAddressStreetName(address.getStreetName());
		this.setSaisieAddressStreetNumber(address.getStreetNumber());
		this.setSaisieAddressStreetBox(address.getStreetBox());
		this.setSaisieAddressCity(address.getCity());
		this.setSaisieAddressLocality(address.getLocality());
		this.setSaisieAddressPosteCode(address.getPosteCode());
		this.setSaisieAddressCountry(address.getCountry());
	}

	public String getTitre() {
		return (String) this.saisieTitre.getItemAt(this.saisieTitre.getSelectedIndex());
	}

	public String getNom() {
		return this.saisieNom.getText();
	}

	public String getPrenom() {
		return this.saisiePrenom.getText();
	}

	public String getTelephone() {
		return this.saisieTelephone.getText();
	}

	public String getEmail() {
		return this.saisieEmail.getText();
	}

	public String getAddressStreetName() {
		return saisieAddressStreetName.getText();
	}

	public String getAddressStreetNumber() {
		return saisieAddressStreetNumber.getText();
	}

	public String getAddressStreetBox() {
		return saisieAddressStreetBox.getText();
	}

	public String getAddressPosteCode() {
		return saisieAddressPosteCode.getText();
	}

	public String getAddressCity() {
		return saisieAddressCity.getText();
	}

	public String getAddressLocality() {
		return (String) this.saisieAddressLocality.getItemAt(this.saisieAddressLocality.getSelectedIndex());
	}

	public String getAddressCountry() {
		return saisieAddressCountry.getText();
	}

	public void setSaisieTitre(String saisieTitre) {
		this.saisieTitre.setSelectedItem(saisieTitre);
	}

	public void setSaisieNom(String saisieNom) {
		this.saisieNom.setText(saisieNom);
	}

	public void setSaisieTelephone(String saisieTelephone) {
		this.saisieTelephone.setText(saisieTelephone);
	}

	public void setSaisieEmail(String saisieEmail) {
		this.saisieEmail.setText(saisieEmail);
	}

	public void setSaisiePrenom(String saisiePrenom) {
		this.saisiePrenom.setText(saisiePrenom);
	}

	public void setSaisieAddressStreetName(String saisieAddressStreetName) {
		this.saisieAddressStreetName.setText(saisieAddressStreetName);
	}

	public void setSaisieAddressStreetNumber(String saisieAddressStreetNumber) {
		this.saisieAddressStreetNumber.setText(saisieAddressStreetNumber);
	}

	public void setSaisieAddressStreetBox(String saisieAddressStreetBox) {
		this.saisieAddressStreetBox.setText(saisieAddressStreetBox);
	}

	public void setSaisieAddressPosteCode(String saisieAddressPosteCode) {
		this.saisieAddressPosteCode.setText(saisieAddressPosteCode);
	}

	public void setSaisieAddressCity(String saisieAddressCity) {
		this.saisieAddressCity.setText(saisieAddressCity);
	}

	public void setSaisieAddressLocality(String saisieAddressLocality) {
		this.saisieAddressLocality.setSelectedItem(saisieAddressLocality);
	}

	public void setSaisieAddressCountry(String saisieAddressCountry) {
		this.saisieAddressCountry.setText(saisieAddressCountry);
	}


}
