/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.CustomerGui;

import core.Connection;
import util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 11/04/13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class PanelVue1 extends JPanel implements Observer {

	private CustomerGui model;

	JButton jButtonLogin = new JButton("Connexion");
	JButton jButtonLogout = new JButton("Déconnexion");
	JButton jButtonCreate = new JButton("Créer");

	public PanelVue1(CustomerGui model) {
		this.model = model;
		model.addObserver(this);

		//construct panel
		this.setBorder(new TitledBorder("Action"));
		this.setLayout(new GridLayout());
		if (!Connection.getInstance().isLogged()) {
			this.add(jButtonLogin, BorderLayout.WEST);
			this.add(jButtonCreate, BorderLayout.CENTER);
			jButtonLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					//To change body of implemented methods use File | Settings | File Templates.
				}
			});
			jButtonCreate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					//To change body of implemented methods use File | Settings | File Templates.
				}
			});
		} else {
			this.add(jButtonLogout, BorderLayout.WEST);
		}
	}


	@Override
	public void update() {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
