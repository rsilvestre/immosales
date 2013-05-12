import core.DataManager;
import model.identity.*;
import model.immo.Offer;
import net.sf.jeasyorm.EntityManager;
import model.product.Bien;
import model.product.Options;
import model.product.Room;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 3/04/13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class Main {
	public static void main (String [] args) {

		Properties configFile = new Properties();
		try {
			configFile.load(Main.class.getClassLoader().getResourceAsStream("config/config.properties"));

			DataManager.getInstance().initManager("immosales","org.postgresql.Driver",
					"jdbc:postgresql://" + configFile.getProperty("NAME_DATABASE"), configFile.getProperty("USER_DATABASE"),
					configFile.getProperty("DB_PASSWORD"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (SQLException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		EntityManager em = DataManager.getInstance().getEntityManager();
		Person p = new Person("Mr", "Silvestre", "Michaël");
		em.insert(p);
		Long id = p.getId();
		Person p1 = em.load(Person.class, id);
		Address a = new Address(p);
		a.setStreetName("Rue des Francs");
		a.setStreetNumber("44");
		a.setCity("Etterbeek");
		em.insert(a);
		Long aId = a.getId();
		Person p2 = a.getPerson();
		Address a1 = em.load(Address.class, aId);
		Person p3 = a1.getPerson();
		List<Address> aList = p3.getAddresses();
		Customer c = new Customer(p3,"0472388896", "willtard@gmail.com");
		em.insert(c);
		Long cId = c.getId();
		Person p4 = c.getPerson();
		List<Address> aList2 = c.getPerson().getAddresses();
		Owner owner1 = new Owner(p2,"0472388896", "willtard@gmail.com");
		em.insert(owner1);
		Customer c1 = p3.getCustomer();
		Bien b = new Bien(owner1, "Maison pré du fa", Bien.TypeProduct.HOUSE);
		em.insert(b);
		Long bId = b.getId();
		Room r = new Room(b, Room.RoomType.BATHROOM);
		r.setSuperficy(4);
		em.insert(r);
		Bien b1 = r.getBien();
		Options o1 = new Options(b1,"Meublé", "oui");
		Options o2 = new Options(b1,"Garage", "oui");
		em.insert(o1);
		em.insert(o2);
		Buyer buyer1 = new Buyer(p2,"0472388896", "willtard@gmail.com");
		em.insert(buyer1);
		Offer offer1 = new Offer(buyer1, b1, Offer.OfferStatus.TOVISIT);
		em.insert(offer1);
		Long oId = offer1.getId();
		Buyer buyer2 = em.load(Buyer.class, buyer1.getId());
		List<Offer> offers = buyer2.getOffers();
		for (Offer offer : offers) {
			System.out.println(offer.getBien().getName());
		}
	}
}
