package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;

public class RotateAction extends AbstractEditorAction {

	public RotateAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/rotate.png"));
		putValue(NAME, "Rotate");
		putValue(SHORT_DESCRIPTION, "Rotate");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().startRotateState();

	}
}
