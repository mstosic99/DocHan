package actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import gui.DocumentView;
import gui.MainFrame;
import model.workspace.Project;

public class PasteAction extends AbstractEditorAction {

	PasteAction() {
		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		// ActionEvent.CTRL_MASK));
		// putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, loadIcon("images/paste24.png"));
		putValue(NAME, "Paste");
		putValue(SHORT_DESCRIPTION, "Paste");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// za tree
		Object o = MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

		if (o != null && o instanceof Project) {
			Project p = (Project) o;
			p.paste();
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
			return;
		}
//za slotove
		((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().paste();

	}

}
