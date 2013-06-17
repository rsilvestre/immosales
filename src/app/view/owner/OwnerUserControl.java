/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.owner;

import app.model.DB.product.Images;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import core.Session;
import app.App;
import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import app.model.FooModelLocator;
import app.model.owner.OwnerModel;
import app.model.owner.OwnerPanelModel;
import util.CopyFile;
import util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelsilvestre on 9/06/13.
 */
public class OwnerUserControl extends JPanel {
	private JPanel panel1;
	private JList listOffer;
	private DefaultListModel listOfferModel;
	private JButton createNewOwn;
	private JTable table1;
	private String[] columns = {"Id", "Type", "Label", "Description", "Prix", "Edit"};

	private DefaultTableModel defaultTableModel;

	private OwnerModel ownerModel;
	private List<Bien> biens = null;

	public OwnerUserControl(OwnerModel argOwnerModel) {
		ownerModel = argOwnerModel;
		initComponents();
		linkModel();
		addListeners();

		populateLocale();
	}

	private void initComponents() {
		this.add(panel1);
		biens = new ArrayList<Bien>();

		defaultTableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table1.setModel(defaultTableModel);

		listOfferModel = new DefaultListModel();
		listOffer.setModel(listOfferModel);

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
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				//super.mouseClicked(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					//setSelectedPerson(getAPerson(target.getSelectedRow()));
					if (target.getSelectedColumn() == 5) {
						editRow(target);
					}
				}
			}
		});
	}

	private void editRow(JTable target) {
		int row = target.getSelectedRow();

		Bien oldBien = getBien(row);

		FooModelLocator locator = FooModelLocator.getInstance();
		OwnerPanelModel ownerPanelModel = new OwnerPanelModel();
		OwnerPanelWindowMapping ownerPanelWindowMapping = new OwnerPanelWindowMapping(ownerPanelModel, oldBien);
		OwnerPanelWindow ownerPanelWindow = ownerPanelWindowMapping.getOwnerPanelWindowMapping();
		locator.setOwnerPanelWindow(ownerPanelWindow);
		ownerPanelWindow.pack();
		ownerPanelWindow.setVisible(true);

		if (ownerPanelWindow.validDialog) {
			OwnerPanelWindowMapping ownerPanelWindowMappingUpdate = new OwnerPanelWindowMapping(ownerPanelWindow);
			Bien bienUpdated = ownerPanelWindowMappingUpdate.getBienMapping(oldBien);

			App.em.update(bienUpdated);

			//replaceBien(bien, bienToUpdate);

			target.getModel().setValueAt(bienUpdated.getId(), row, 0);
			target.getModel().setValueAt(bienUpdated.getTypeProduct(), row, 1);
			target.getModel().setValueAt(bienUpdated.getName(), row, 2);
			target.getModel().setValueAt(bienUpdated.getDescription(), row, 3);
			target.getModel().setValueAt(bienUpdated.getPrice(), row, 4);
			target.repaint();

			// save image
			if (ownerPanelWindow.getFile() == null || ownerPanelWindow.getFile().getName() == null) {
				return;
			}

			saveFile(bienUpdated, ownerPanelWindow.getFile());

			Images image = null;

			for (Images oldImage : oldBien.getImages()) {
				if (oldImage.getImageName() == ownerPanelWindow.getFile().getName()) {
					return;
				}
				oldImage.setImageName(ownerPanelWindow.getFile().getName());
				App.em.update(oldImage);
				return;
				//}
			}

			image = new Images(bienUpdated, ownerPanelWindow.getFile().getName());
			App.em.insert(image);
		}

	}

	private void createOwn() {
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

			// save image
			Util.checkObject(bienToSave.getId());
			saveFile(bienToSave, ownerPanelWindow.getFile());
			// insert image in database
			Images image = new Images(bienToSave, ownerPanelWindow.getFile().getName());
			App.em.insert(image);

			defaultTableModel.addRow(bienToSave.getTableRow());
			addBien(bienToSave);
		}
	}

	private void saveFile(Bien bien, File file) {
		// create folder if not exist
		createFolderIfNotExist(bien);
		// copy image in folder
		copyAndReplaceFile(bien, file);
	}

	private void copyAndReplaceFile(Bien bien, File file) {
		File dest = new File("ressources/images/biens/" + bien.getId() + "/" + file.getName());
		CopyFile.copyfile(file, dest);
	}

	private void createFolderIfNotExist(Bien bien) {
		File theDir = new File("ressources/images/biens/" + bien.getId());
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + bien.getId());
			boolean result = theDir.mkdirs();
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

	private void populateLocale() {
		//defaultTableModel.addRow(new String [] {"1", "Maison", "Villa Etterbeek", "Grande villa ˆ vendre ˆ Etterbeek", "534"});
		List<Bien> biens = App.em.find(Bien.class, "where owner_id = ? order by id", Session.getInstance().getAPerson().getId());
		List<Offer> offerList = new ArrayList<Offer>();
		for (Bien bien : biens) {
			for (Offer offer : bien.getOffers()) {
				offerList.add(offer);
			}
			addBien(bien);
			tableAddRow(bien);
		}
		for (Offer offer : offerList) {
			listOfferModel.addElement(offer.getBien().getName() + ": " + offer.getBuyer().toString() + " - " + offer.getOffer().toString() + "€");
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

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel1 = new JPanel();
		panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:d:grow", "center:100px:grow,top:3dlu:noGrow,center:100px:grow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xywh(1, 1, 1, 3));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel2.add(scrollPane1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		final JScrollPane scrollPane2 = new JScrollPane();
		panel2.add(scrollPane2, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
		listOffer = new JList();
		scrollPane2.setViewportView(listOffer);
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel1.add(panel3, cc.xy(1, 5));
		createNewOwn = new JButton();
		createNewOwn.setText("Créer un nouveau bien");
		panel3.add(createNewOwn, cc.xy(3, 1));
		final Spacer spacer1 = new Spacer();
		panel3.add(spacer1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel3.add(spacer2, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
