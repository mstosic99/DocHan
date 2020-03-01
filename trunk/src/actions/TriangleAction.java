package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;

public class TriangleAction extends AbstractEditorAction {

	public TriangleAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/trianglenew.png"));
		putValue(SHORT_DESCRIPTION, "Triangle");
		putValue(NAME, "Triangle");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().startTriangleState();

	}
}
