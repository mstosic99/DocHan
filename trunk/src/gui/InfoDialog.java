package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoDialog extends JDialog {

	private static InfoDialog instance;

	JPanel panelImg1;
	JPanel panelImg2;

	JPanel panelBottom;
	JLabel iconLabel1;
	JLabel iconLabel2;
	ImageIcon icon1;
	ImageIcon icon2;
	JLabel name1;
	JLabel name2;

	public InfoDialog() {
		setTitle("Informacije o clanovima tima");

		setSize(600, 350);
		setLocationRelativeTo(null);

		panelImg1 = new JPanel();
		add(panelImg1, BorderLayout.WEST);
		panelImg1.setPreferredSize(new Dimension(230, 300));
		icon1 = new ImageIcon("images/rsz_profil.jpg");
		iconLabel1 = new JLabel();
		iconLabel1.setIcon(icon1);
		iconLabel1.setPreferredSize(new Dimension(200, 250));
		panelImg1.add(iconLabel1);

		panelImg2 = new JPanel();
		add(panelImg2, BorderLayout.EAST);
		panelImg2.setPreferredSize(new Dimension(230, 300));
		icon2 = new ImageIcon("images/mixa.jpg");
		iconLabel2 = new JLabel();
		iconLabel2.setIcon(icon2);
		iconLabel2.setPreferredSize(new Dimension(200, 250));
		panelImg2.add(iconLabel2);

		panelBottom = new JPanel();
		add(panelBottom, BorderLayout.SOUTH);
		name1 = new JLabel("Sanja Vasiljkovic RN 40/2018                         ");
		name2 = new JLabel("                       Mihajlo Stosic RN 26/2018");
		panelBottom.add(name1);
		panelBottom.add(name2);

		setResizable(false);
		validate();
	}

	public static InfoDialog getInstance() {
		if (instance == null) {
			instance = new InfoDialog();
		}
		return instance;
	}

}
