/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mvc.view.user;

import mvc.App;
import mvc.controller.city.CityUserEvent;
import mvc.controller.city.CpUserEvent;
import mvc.model.DB.identity.APerson;
import mvc.model.DB.identity.Address;
import mvc.model.DB.identity.Person;
import mvc.model.DB.product.address.City;
import mvc.model.DB.product.address.Country;
import mvc.model.DB.product.address.Region;
import mvc.model.user.UserPanelModel;
import mvc.view.city.CityWindow;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class UserPanelWindow extends JPanel {
	private final static String[] TITRES = {"Mr", "Mme", "Melle"};
	private final static String [] comboBoxDefaultValue = {""};

	private JComboBox saisieTitre = new JComboBox(TITRES);
	private JTextField saisieNom = new JTextField(10);
	private JTextField saisiePrenom = new JTextField(10);
	private JTextField saisieTelephone = new JTextField(10);
	private JTextField saisieEmail = new JTextField(10);
	private JTextField saisieAddressStreetName = new JTextField(20);
	private JTextField saisieAddressStreetNumber = new JTextField(10);
	private JTextField saisieAddressStreetBox = new JTextField(10);
	private JComboBox saisieAddressPosteCode = new JComboBox(comboBoxDefaultValue);
	private JComboBox saisieAddressCity = new JComboBox(comboBoxDefaultValue);
	private JComboBox saisieAddressLocality = new JComboBox(comboBoxDefaultValue);
	private JComboBox saisieAddressCountry = new JComboBox();

	private Boolean stopListener = false;

	private UserPanelModel userPanelModel;

	public UserPanelWindow(UserPanelModel userPanelModel) {
		this.userPanelModel = userPanelModel;
		initComponents();
		linkModel();
		addListener();
		populateLocal();
	}

	public UserPanelWindow(UserPanelModel userPanelModel, APerson aPerson) {
		this.userPanelModel = userPanelModel;
		initComponents();
		linkModel();
		populateLocal();
		setComponents(aPerson);
		addListener();
	}

	private void initSubComponents() {
		initComponents();
		linkModel();
		addListener();
		populateLocal();
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

		userPanelModel.setDefaultCity(address.getCity().getCity());
		userPanelModel.setDefaultCpValue(address.getCity().getPosteCode());

		this.setSaisieTitre(person.getTitre());
		this.setSaisiePrenom(person.getFirstName());
		this.setSaisieNom(person.getLastName());
		this.setSaisieTelephone(aPerson.getPhoneNumber());
		this.setSaisieEmail(aPerson.getEmail());
		this.setSaisieAddressStreetName(address.getStreetName());
		this.setSaisieAddressStreetNumber(address.getStreetNumber());
		this.setSaisieAddressStreetBox(address.getStreetBox());
		this.setSaisieAddressCity(address.getCity().getCity());
		this.setSaisieAddressLocality(address.getCity().getLocality().getRegion().getRegion());
		this.setSaisieAddressPosteCode(address.getCity().getPosteCode());
		this.setSaisieAddressCountry(address.getCity().getLocality().getRegion().getCountry().getLabelFr());
	}

	private void linkModel() {
		userPanelModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();
				stopListener = true;
				if (name.equals("newCity") && !evt.getNewValue().equals("")) {

					City city = App.em.findUnique(City.class,"where city = ?", evt.getNewValue());
					Region region = city.getLocality().getRegion();
					saisieAddressPosteCode.setSelectedItem(city.getPosteCode());
					saisieAddressLocality.setSelectedItem(region.getRegion());
					saisieAddressCountry.setSelectedItem(region.getCountry().getLabelFr());
					stopListener = false;
				} else if (name.equals("newCp") && !evt.getNewValue().equals("")) {
					List<City> cities = App.em.find(City.class, "where poste_code = ?", evt.getNewValue());

					City city = null;

					if (cities.size()  < 1) {
						stopListener = false;
						return;
					} else if (cities.size() > 1) {
						CityWindow cityWindow = new CityWindow((String)evt.getNewValue(), cities);
						cityWindow.pack();
						cityWindow.setVisible(true);
						city = cityWindow.getCity();
						if (city == null) {
							stopListener = false;
							return;
						}
					} else {
						city = cities.get(0);
					}
					Region region = city.getLocality().getRegion();
					saisieAddressCity.setSelectedItem(city.getCity());
					saisieAddressLocality.setSelectedItem(region.getRegion());
					saisieAddressCountry.setSelectedItem(region.getCountry().getLabelFr());
					stopListener = false;
				}

			}
		});
	}

	private void addListener() {
		saisieAddressCity.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CityUserEvent event = new CityUserEvent((String)itemEvent.getItem(), userPanelModel);
					event.dispatch();
				}
			}
		});
		saisieAddressPosteCode.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (stopListener) return;
					CpUserEvent event = new CpUserEvent((String) itemEvent.getItem(), userPanelModel);
					event.dispatch();
				}
			}
		});
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
		return (String)saisieAddressPosteCode.getSelectedItem();
	}

	public String getAddressCity() {
		return (String)saisieAddressCity.getSelectedItem();
	}

	public City getCity() {
		return App.em.findUnique(City.class, "where city = ?", getAddressCity());
	}

	public String getAddressLocality() {
		return (String) this.saisieAddressLocality.getItemAt(this.saisieAddressLocality.getSelectedIndex());
	}

	public String getAddressCountry() {
		return (String)saisieAddressCountry.getSelectedItem();
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
		this.saisieAddressPosteCode.setSelectedItem(saisieAddressPosteCode);
	}

	public void setSaisieAddressCity(String saisieAddressCity) {
		this.saisieAddressCity.setSelectedItem(saisieAddressCity);
	}

	public void setSaisieAddressLocality(String saisieAddressLocality) {
		this.saisieAddressLocality.setSelectedItem(saisieAddressLocality);
	}

	public void setSaisieAddressCountry(String saisieAddressCountry) {
		this.saisieAddressCountry.setSelectedItem(saisieAddressCountry);
	}


	private void populateLocal() {

		for (Country country : App.em.find(Country.class, "select * from tcountry")) {
			saisieAddressCountry.addItem(country.getLabelFr());
		}
		for (Region region : App.em.find(Region.class, "select * from tregion")) {
			saisieAddressLocality.addItem(region.getRegion());
		}
		for (City city : App.em.find(City.class, "select * from tcity order by city")) {
			saisieAddressCity.addItem(city.getCity());
		}
		for (City city : App.em.find(City.class, "select distinct poste_code from tcity order by poste_code")) {
			saisieAddressPosteCode.addItem(city.getPosteCode());
		}
	}
}
