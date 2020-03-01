package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;

public class ResizeAction extends AbstractEditorAction {

	public ResizeAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/resize.png"));
		putValue(NAME, "Resize");
		putValue(SHORT_DESCRIPTION, "Resize");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().startResizeState();

	}

}
