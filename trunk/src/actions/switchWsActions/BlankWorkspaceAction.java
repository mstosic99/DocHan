package actions.switchWsActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import actions.AbsGEDAction;
import gui.MainFrame;
import gui.SwitchWorkspaceDialog;
import utilities.WorkspaceUtilites;

public class BlankWorkspaceAction extends AbsGEDAction {

	public BlankWorkspaceAction() {
		putValue(NAME, "Blank Workspace");
		putValue(SHORT_DESCRIPTION, "Blank Workspace");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean successful = WorkspaceUtilites.makeBlankWorkspace();
		if (successful) {
			SwitchWorkspaceDialog.getInstance().setVisible(false);
		}
		SwitchWorkspaceDialog.getInstance().repaint();
	}

}
