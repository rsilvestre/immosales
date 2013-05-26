package mvc.view.owner;

import core.Session;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private String [] columns = {"Id", "Type", "Label", "Description", "Prix", "Edit"};
	private JTable jtable;
	private DefaultTableModel defaultTableModel;

	private JButton createNewOwn;

	private OwnerModel ownerModel;
	private List<Bien> biens = null;

	public OwnerWindow(OwnerModel argOwnerModel) {
		ownerModel = argOwnerModel;
		initComponents();
		linkModel();
		addListeners();

		populateLocale();
	}

	private void initComponents() {
		biens = new ArrayList<Bien>();
		setLayout(new BorderLayout());

		add(new JLabel("Liste de vos biens"), BorderLayout.NORTH);

		defaultTableModel = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtable = new JTable(defaultTableModel);
		add(new JScrollPane(jtable), BorderLayout.CENTER);

		createNewOwn = new JButton();
		Box box = Box.createHorizontalBox();
		box.add(createNewOwn);
		add(box, BorderLayout.SOUTH);
	}

	private void linkModel() {

	}

	private void addListeners() {
		createNewOwn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				createOwn();
			}
		});
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				//super.mouseClicked(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					//setSelectedPerson(getAPerson(target.getSelectedRow()));
					if (target.getSelectedColumn()== 5) {
						editRow(target);
					}
				}
			}
		});
	}

	private void editRow(JTable target) {
		int row = target.getSelectedRow();

		Bien bien = getBien(row);

		FooModelLocator locator = FooModelLocator.getInstance();
		OwnerPanelModel ownerPanelModel = new OwnerPanelModel();
		OwnerPanelWindowMapping ownerPanelWindowMapping = new OwnerPanelWindowMapping(ownerPanelModel, bien);
		OwnerPanelWindow ownerPanelWindow = ownerPanelWindowMapping.getOwnerPanelWindowMapping();
		locator.setOwnerPanelWindow(ownerPanelWindow);
		ownerPanelWindow.pack();
		ownerPanelWindow.setVisible(true);

		if (ownerPanelWindow.validDialog) {
			OwnerPanelWindowMapping ownerPanelWindowMappingUpdate = new OwnerPanelWindowMapping(ownerPanelWindow);
			bien = ownerPanelWindowMappingUpdate.getBienMapping(bien);

			App.em.update(bien);

			//replaceBien(bien, bienToUpdate);

			target.getModel().setValueAt(bien.getId(), row, 0);
			target.getModel().setValueAt(bien.getTypeProduct(), row, 1);
			target.getModel().setValueAt(bien.getName(), row, 2);
			target.getModel().setValueAt(bien.getDescription(), row, 3);
			target.getModel().setValueAt(bien.getPrice(), row, 4);
			target.repaint();
		}

	}

	private void createOwn() {
		//To change body of created methods use File | Settings | File Templates.
		FooModelLocator locator = FooModelLocator.getInstance();
		OwnerPanelModel ownerPanelModel = new OwnerPanelModel();
		OwnerPanelWindow ownerPanelWindow = new OwnerPanelWindow(ownerPanelModel);
		locator.setOwnerPanelWindow(ownerPanelWindow);
		ownerPanelWindow.pack();
		ownerPanelWindow.setVisible(true);

		if (ownerPanelWindow.validDialog) {
			OwnerPanelWindowMapping ownerPanelWindowMappingInsert = new OwnerPanelWindowMapping(ownerPanelWindow);
			Bien bienToSave = ownerPanelWindowMappingInsert.getBienMapping();
			App.em.insert(bienToSave);
			defaultTableModel.addRow(bienToSave.getTableRow());
			addBien(bienToSave);
		}
	}

	private void populateLocale() {
		createNewOwn.setText("Créer un nouveau bien");
		//defaultTableModel.addRow(new String [] {"1", "Maison", "Villa Etterbeek", "Grande villa ˆ vendre ˆ Etterbeek", "534"});
		List<Bien> biens = App.em.find(Bien.class, "where owner_id = ? order by id", Session.getInstance().getAPerson().getId());
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

	private void replaceBien(Bien oldBien, Bien newBien) {
		int indexOldBien = this.biens.indexOf(oldBien);
		this.biens.add(indexOldBien, newBien);
	}

	private void clearBien() {
		if (this.biens != null) {
			this.biens.clear();
		}
	}

}
