package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.DocumentView;
import gui.MainFrame;
import model.workspace.Page;

public class UndoAction extends AbstractEditorAction {

	UndoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, loadIcon("images/undo24.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Page p = ((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().getPage();

		p.getCommandManager().undoCommand();
	}

}
