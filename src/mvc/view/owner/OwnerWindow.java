package mvc.view.owner;

import mvc.App;
import mvc.model.DB.product.Bien;
import mvc.model.FooModelLocator;
import mvc.model.owner.OwnerModel;
import mvc.model.owner.OwnerPanelModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 9/05/13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class OwnerWindow extends JPanel {
	private String [] columns = {"Id", "Type", "Label", "Description", "Prix"};
	private JTable jtable;
	private DefaultTableModel defaultTableModel;

	private JButton createNewOwn;

	private OwnerModel ownerModel;
	private List<Bien> biens = null;

	public OwnerWindow(OwnerModel argOwnerModel) {
		ownerModel = argOwnerModel;
		initComponents();
		addListeners();

		populateLocale();
	}

	private void initComponents() {
		biens = new ArrayList<Bien>();
		setLayout(new BorderLayout());

		add(new JLabel("Liste de vos biens"), BorderLayout.NORTH);

		defaultTableModel = new DefaultTableModel(columns,0);
		jtable = new JTable(defaultTableModel);
		add(new JScrollPane(jtable), BorderLayout.CENTER);

		createNewOwn = new JButton();
		Box box = Box.createHorizontalBox();
		box.add(createNewOwn);
		add(box, BorderLayout.SOUTH);
	}

	private void addListeners() {
		createNewOwn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				createOwn();
			}
		});
	}

	private void createOwn() {
		//To change body of created methods use File | Settings | File Templates.
		FooModelLocator locator = FooModelLocator.getInstance();
		OwnerPanelModel ownerPanelModel = new OwnerPanelModel();
		OwnerPanelWindow ownerPanelWindow = new OwnerPanelWindow(ownerPanelModel);
		locator.setOwnerPanelWindow(ownerPanelWindow);
		ownerPanelWindow.pack();
		ownerPanelWindow.setVisible(true);

	}

	private void populateLocale() {
		createNewOwn.setText("Créer un nouveau bien");
		defaultTableModel.addRow(new String [] {"1", "Maison", "Villa Etterbeek", "Grande villa à vendre à Etterbeek", "534"});
		List<Bien> biens = App.em.find(Bien.class, "order by id");
		for (Bien bien : biens) {
			addBien(bien);
			tableAddRow(bien);
		}
	}

	private void tableAddRow(Bien argBien) {
		defaultTableModel.addRow(argBien.getTableRow());
	}

	private List<Bien> getBiens() {
		return biens;
	}

	private Bien getBien(int index) {
		return getBiens().get(index);
	}

	private void addBien(Bien argBien) {
		this.biens.add(argBien);
	}

	private void clearAPersons() {
		if (this.biens != null) {
			this.biens.clear();
		}
	}

}
