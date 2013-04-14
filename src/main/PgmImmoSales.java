/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main;


import main.CustomerGui.CustomerGui;
import main.CustomerGui.PanelVue1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 9/04/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class PgmImmoSales extends JFrame {

	CustomerGui customerGui = new CustomerGui();
	PanelVue1 panelVue1 = new PanelVue1(customerGui);

	public static void main(String [] args) {
		PgmImmoSales f = new PgmImmoSales();
		f.setSize(800,600);
		f.setVisible(true);
	}

	public PgmImmoSales() {
		this.setTitle("Gestionaire de vente de bien immobiliers");

		this.add(panelVue1, BorderLayout.CENTER);



		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
				//super.windowClosing(windowEvent);    //To change body of overridden methods use File | Settings | File Templates.
			}
		});
	}
}
