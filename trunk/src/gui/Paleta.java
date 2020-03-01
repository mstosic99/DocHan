package gui;

import javax.swing.JToolBar;

import javax.swing.BoxLayout;

public class Paleta extends JToolBar {

	public Paleta() {
		super(JToolBar.VERTICAL);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		initializePaleta();

	}

	private void initializePaleta() {

		addSeparator();

		add(MainFrame.getInstance().getActionManager().getRectangleAction());

		addSeparator();

		add(MainFrame.getInstance().getActionManager().getCircleAction());

		addSeparator();

		add(MainFrame.getInstance().getActionManager().getTriangleAction());

		addSeparator();

		add(MainFrame.getInstance().getActionManager().getRotateAction());

		addSeparator();
		add(MainFrame.getInstance().getActionManager().getResizeAction());

		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSelectAction());
		setFloatable(true);
	}

}
