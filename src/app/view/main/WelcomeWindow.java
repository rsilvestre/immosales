/*
 * Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package app.view.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by michaelsilvestre on 16/06/13.
 */
public class WelcomeWindow extends JPanel {
	private BufferedImage image;

	public WelcomeWindow() {
		initComponents();
	}

	private void initComponents() {
		try {
			String path = getClass().getClassLoader().getResource(".").getPath() + "ressources/images/welcome.png";
			File file = new File(path);
			image = ImageIO.read(getClass().getResource("/ressources/images/welcome.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0,0,null);
	}
}
