package view.carnet;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 1/05/13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class PanneauContact extends JPanel {
	private final static String [] TITRES = {"Mr", "Mme", "Melle"};
	private final static String [] LOCALITY = {
			"Bruxelles Capital",
			"Brabant Flamand",
			"Brabant Wallon",
			"Namur",
			"Li�ge",
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
	private JTextField saisieAddressStreetName = new JTextField(60);
	private JTextField saisieAddressStreetNumber = new JTextField(10);
	private JTextField saisieAddressStreetBox = new JTextField(10);
	private JTextField saisieAddressPosteCode = new JTextField(10);
	private JTextField saisieAddressCity = new JTextField(50);
	private JComboBox saisieAddressLocality = new JComboBox(LOCALITY);
	private JTextField saisieAddressCountry = new JTextField(40);

	public PanneauContact() {
		JPanel panneauLabels = new JPanel(new GridLayout(10, 1, 5, 5));
		panneauLabels.add(new JLabel("Titre :"));
		panneauLabels.add(new JLabel("Nom :"));
		panneauLabels.add(new JLabel("Prenom :"));
		panneauLabels.add(new JLabel("Nom de rue :"));
		panneauLabels.add(new JLabel("N� d'habitation :"));
		panneauLabels.add(new JLabel("N� de bo�te :"));
		panneauLabels.add(new JLabel("Code postale :"));
		panneauLabels.add(new JLabel("Ville :"));
		panneauLabels.add(new JLabel("Province :"));
		panneauLabels.add(new JLabel("Pays :"));

		JPanel panneauSaisie = new JPanel(new GridLayout(10, 1, 5, 5));
		panneauSaisie.add(this.saisieTitre);
		panneauSaisie.add(this.saisieNom);
		panneauSaisie.add(this.saisiePrenom);
		panneauSaisie.add(this.saisieAddressStreetName);
		panneauSaisie.add(this.saisieAddressStreetNumber);
		panneauSaisie.add(this.saisieAddressStreetBox);
		panneauSaisie.add(this.saisieAddressPosteCode);
		panneauSaisie.add(this.saisieAddressCity);
		panneauSaisie.add(this.saisieAddressLocality);
		panneauSaisie.add(this.saisieAddressCountry);

		setLayout(new BorderLayout(5,5));
		add(panneauLabels, BorderLayout.WEST);
		add(panneauSaisie, BorderLayout.CENTER);
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
}