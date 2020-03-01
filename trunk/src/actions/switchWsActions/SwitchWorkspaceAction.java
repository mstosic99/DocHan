package actions.switchWsActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import actions.AbsGEDAction;
import gui.MainFrame;
import gui.SwitchWorkspaceDialog;
import utilities.WorkspaceUtilites;

public class SwitchWorkspaceAction extends AbsGEDAction {

	// TODO
	public SwitchWorkspaceAction() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
//		putValue(SMALL_ICON, loadIcon("images/closeAllTabs.png"));
		putValue(NAME, "Switch Workspace");
		putValue(SHORT_DESCRIPTION, "Switch Workspace");
		putValue(SMALL_ICON, loadIcon("images/switch.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getWorkspace() != null)
			WorkspaceUtilites.askToSaveWorkspace();
		SwitchWorkspaceDialog.getInstance().populateWorkspaces();
		SwitchWorkspaceDialog.getInstance().revalidate();
		SwitchWorkspaceDialog.getInstance().repaint();
		SwitchWorkspaceDialog.getInstance().setVisible(true);
	}
}
