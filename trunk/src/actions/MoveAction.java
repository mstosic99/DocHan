package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;
import model.workspace.Page;

public class MoveAction extends AbstractEditorAction {

	MoveAction() {
		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		// ActionEvent.CTRL_MASK));
		// putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		// putValue(SMALL_ICON, loadIcon("images/redo24.png"));
		// putValue(NAME, "MOVE");
		// putValue(SHORT_DESCRIPTION, "Move");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ((DocumentView)
		// MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
		// .getSelectedComponent()).getFocusPageView().startMoveState();

	}

}
