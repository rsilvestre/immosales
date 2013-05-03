package JMVC.view.main;

import be.wilthard.essais.JMVC.controller.main.ColorChangeEvent;
import be.wilthard.essais.JMVC.controller.main.DisplayTextChangeEvent;
import be.wilthard.essais.JMVC.model.main.MainModel;
import be.wilthard.essais.TestForm.test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 27/04/13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {

	private JLabel text;
	private JButton redButton;
	private JButton greenButton;
	private JButton blueButton;
	private JButton editTextButton;

	private MainModel model;

	public MainWindow(MainModel argModel) {
		model = argModel;
		initComponents();
		linkModel();
		addListeners();
		populateLocale();
	}

	private void initComponents() {
		text = new JLabel("", JLabel.CENTER);
		redButton = new JButton();
		greenButton = new JButton();
		blueButton = new JButton();
		editTextButton = new JButton();

		setLayout(new GridLayout(2, 1));

		add(text);

		Box box = Box.createHorizontalBox();
		box.add(redButton);
		box.add(greenButton);
		box.add(blueButton);
		box.add(Box.createRigidArea(new Dimension(30, 1)));
		box.add(Box.createHorizontalGlue());
		box.add(editTextButton);

		add(box);
	}

	private void linkModel() {
		text.setText(model.getText());
		text.setForeground(model.getColor());

		model.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();

				if (name.equals("text")) {
					String value = (String) evt.getNewValue();
					text.setText(value);
				} else if (name.equals("color")) {
					Color value = (Color) evt.getNewValue();
					text.setForeground(value);
				}
			}
		});
	}

	// we're only updating this method
	private void addListeners() {
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChangeEvent event = new ColorChangeEvent(Color.red, model);
				event.dispatch();
			}
		});

		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChangeEvent event = new ColorChangeEvent(Color.green, model);
				event.dispatch();
			}
		});

		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorChangeEvent event = new ColorChangeEvent(Color.blue, model);
				event.dispatch();
			}
		});

		editTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayTextChangeEvent event = new DisplayTextChangeEvent("Text Change", model);
				event.dispatch();
			}
		});
	}

	private void populateLocale() {
		// this is where you would normally get the
		// locale strings from your model locator, but
		// that's out of the scope of this example
		redButton.setText("Red");
		greenButton.setText("Green");
		blueButton.setText("Blue");

		editTextButton.setText("Edit Text");
	}
}
