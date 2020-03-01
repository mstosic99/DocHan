package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.InfoDialog;
import gui.MainFrame;

public class InfoAction extends AbsGEDAction {

	public InfoAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/info.png"));
		putValue(NAME, "Info");
		putValue(SHORT_DESCRIPTION, "Info");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		InfoDialog.getInstance().setVisible(true);

	}

}
