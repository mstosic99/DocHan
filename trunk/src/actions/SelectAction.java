package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;

public class SelectAction extends AbstractEditorAction {

	SelectAction() {

		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,
		// ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/select24.png"));
		putValue(SHORT_DESCRIPTION, "Select");
		putValue(NAME, "Select");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().startSelectState();

	}

}
