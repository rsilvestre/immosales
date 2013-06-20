/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.owner;

import app.model.DB.product.Images;
import app.view.base.AbstractListWindow;
import app.view.bien.BienOwnerWindow;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import core.Session;
import app.App;
import app.model.DB.immo.Offer;
import app.model.DB.product.Bien;
import app.model.FooModelLocator;
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
public class OwnerUserControl extends AbstractListWindow {
	private JPanel panel1;
	private JList listOffer;
	private JButton createNewOwn;
	private JTable table1;
	private String[] columns = {"Id", "Type", "Label", "Description", "Prix", "Edit", "Status"};

	private DefaultTableModel defaultTableModel;
	private List<Bien> biens = null;

	public OwnerUserControl() {
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

		listOffer.setModel(getOfferModel());

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
				if (evt.getClickCount() == 1) {
					JTable target = (JTable) evt.getSource();
					//setSelectedPerson(getAPerson(target.getSelectedRow()));
					if (target.getSelectedColumn() == 5) {
						editRow(target);
					}
				}
				if (evt.getClickCount() == 2) {
					JTable target = (JTable) evt.getSource();
					showBien(target);
				}
			}
		});
		listOffer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					showDetails((ListObject) listOffer.getSelectedValue());
				}
			}
		});
	}

	private void showBien(JTable target) {
		int row = target.getSelectedRow();

		Bien bien = getBien(row);
		BienOwnerWindow bienOwnerWindow = new BienOwnerWindow(bien);
		bienOwnerWindow.pack();
		bienOwnerWindow.setVisible(true);
		if (bienOwnerWindow.getValidate() && !bien.getStatus().equals(bienOwnerWindow.getStatus().toString())) {
			bien.setStatus(bienOwnerWindow.getStatus().toString());
			App.em.update(bien);
			target.setValueAt(bien.getStatus(), row, 6);
			//defaultTableModel.row .setElementAt(getBienList(bien), bienModel.indexOf(selectedValue));
		}

	}

	private void editRow(JTable target) {
		int row = target.getSelectedRow();

		Bien oldBien = App.em.load(Bien.class, getBien(row).getId());

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

			if (!bienUpdated.equals(oldBien)) {
				App.em.update(bienUpdated);
				replaceBien(oldBien, bienUpdated);

				target.getModel().setValueAt(bienUpdated.getId(), row, 0);
				target.getModel().setValueAt(bienUpdated.getTypeProduct(), row, 1);
				target.getModel().setValueAt(bienUpdated.getName(), row, 2);
				target.getModel().setValueAt(bienUpdated.getDescription(), row, 3);
				target.getModel().setValueAt(bienUpdated.getPrice(), row, 4);
				target.repaint();
			}

			// save image
			if (ownerPanelWindow.getFile() == null || ownerPanelWindow.getFile().getName().equals("")) {
				return;
			}
			List<Images> imagesList = oldBien.getImages();
			if (imagesList.size() > 0) {
				for (Images oldImage : imagesList) {
					String oldImageName = oldImage.getImageName(), newImageName = ownerPanelWindow.getFile().getName();
					if (oldImageName.equals(newImageName)) {
						return;
					}
					saveFile(bienUpdated, ownerPanelWindow.getFile());
					oldImage.setImageName(ownerPanelWindow.getFile().getName());
					App.em.update(oldImage);
					return;
					//}ressources/images/biens/56/Capture d’écran 2013-06-19 à 16.11.48.png Capture d’écran 2013-06-19 à 16.11.48.png
				}
			} else {
				saveFile(bienUpdated, ownerPanelWindow.getFile());
				Images image = new Images(bienUpdated, ownerPanelWindow.getFile().getName());
				App.em.insert(image);
			}

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
		buildOfferOwnerModel(offerList);
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
		if (indexOldBien == -1) {
			return;
		}
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
		panel1.setLayout(new FormLayout("fill:d:grow", "center:max(d;4px):noGrow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new FormLayout("fill:762px:grow", "center:100px:grow,top:3dlu:noGrow,center:max(d;4px):noGrow,top:3dlu:noGrow,center:100px:grow"));
		CellConstraints cc = new CellConstraints();
		panel1.add(panel2, cc.xywh(1, 3, 1, 3));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
		panel2.add(panel3, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
		final JScrollPane scrollPane1 = new JScrollPane();
		panel3.add(scrollPane1, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		final JPanel panel4 = new JPanel();
		panel4.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
		panel2.add(panel4, cc.xy(1, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
		final JScrollPane scrollPane2 = new JScrollPane();
		panel4.add(scrollPane2, cc.xy(1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		listOffer = new JList();
		scrollPane2.setViewportView(listOffer);
		final JPanel panel5 = new JPanel();
		panel5.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel2.add(panel5, cc.xy(1, 3));
		final JLabel label1 = new JLabel();
		label1.setText("Offres reçues");
		panel5.add(label1, cc.xy(7, 1));
		final Spacer spacer1 = new Spacer();
		panel5.add(spacer1, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer2 = new Spacer();
		panel5.add(spacer2, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer3 = new Spacer();
		panel5.add(spacer3, cc.xy(9, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer4 = new Spacer();
		panel5.add(spacer4, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer5 = new Spacer();
		panel5.add(spacer5, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JPanel panel6 = new JPanel();
		panel6.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel1.add(panel6, cc.xy(1, 7));
		createNewOwn = new JButton();
		createNewOwn.setText("Créer un nouveau bien");
		panel6.add(createNewOwn, cc.xy(3, 1));
		final Spacer spacer6 = new Spacer();
		panel6.add(spacer6, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer7 = new Spacer();
		panel6.add(spacer7, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final JPanel panel7 = new JPanel();
		panel7.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:grow"));
		panel1.add(panel7, cc.xy(1, 1));
		final JLabel label2 = new JLabel();
		label2.setText("Biens mis en vente");
		panel7.add(label2, cc.xy(7, 1));
		final Spacer spacer8 = new Spacer();
		panel7.add(spacer8, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer9 = new Spacer();
		panel7.add(spacer9, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer10 = new Spacer();
		panel7.add(spacer10, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer11 = new Spacer();
		panel7.add(spacer11, cc.xy(9, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		final Spacer spacer12 = new Spacer();
		panel7.add(spacer12, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel1;
	}
}
