package mvc.view.main;

import mvc.model.main.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: michaelsilvestre
 * Date: 4/05/13
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {
	private JPanel mainScreen;
	private JMenuBar jMenuBar;
	private JMenuItem menuConnexion;
	private JMenuItem menuLeave;

	private final MainWindow fenetre = this;

	private MainModel mainModel;

	public MainWindow(MainModel mainModel) {
		this.mainModel = mainModel;
		initComponents();
		addListener();
	}

	private void initComponents() {
		this.setTitle("ImmoSales");
		mainScreen = new JPanel(new BorderLayout(5,5));

		jMenuBar = new JMenuBar();
		menuConnexion = new JMenuItem("Connexion", 'C');
		menuLeave = new JMenuItem("Quitter", 'Q');

		setLayout(new BorderLayout(5,5));

		setJMenuBar(jMenuBar);
		JMenu fileMenu = new JMenu("Fichier");
		jMenuBar.add(fileMenu);
		fileMenu.add(menuConnexion);
		fileMenu.add(menuLeave);

		add(mainScreen);

		Box box = Box.createHorizontalBox();

		add(box);
	}

	private void addListener() {
		int toucheRaccourcis = java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		menuConnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, toucheRaccourcis));
		menuConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
			}
		});

		menuLeave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, toucheRaccourcis));
		menuLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//To change body of implemented methods use File | Settings | File Templates.
				if (JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}
}
