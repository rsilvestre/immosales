package mvc.view.owner;

import mvc.model.owner.OwnerModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

	private OwnerModel ownerModel;

	public OwnerWindow(OwnerModel argOwnerModel) {
		ownerModel = argOwnerModel;
		initComponents();
		addListeners();

		populateLocale();
	}

	private void initComponents() {
		defaultTableModel = new DefaultTableModel(columns,0);
		jtable = new JTable(defaultTableModel);
		add(new JScrollPane(jtable));
	}

	private void addListeners() {
	}

	private void populateLocale() {
		defaultTableModel.addRow(new String [] {"1", "Maison", "Villa Etterbeek", "Grande villa à vendre à Etterbeek", "534"});
	}

}
