package actions.switchWsActions;

import java.awt.event.ActionEvent;
import actions.AbsGEDAction;
import exceptionHandler.ChooseWorkspaceException;
import gui.MainFrame;
import gui.SwitchWorkspaceDialog;
import utilities.WorkspaceUtilites;

@SuppressWarnings("serial")
public class ChooseWorkspaceAction extends AbsGEDAction {


	public ChooseWorkspaceAction() {
		putValue(NAME, "Choose");
		putValue(SHORT_DESCRIPTION, "Choose existing");
	}

	@Override
	public void actionPerformed(ActionEvent e) throws ChooseWorkspaceException {
		
		boolean successful = WorkspaceUtilites.makeChosenWorkspace();
		if (successful) {
			SwitchWorkspaceDialog.getInstance().setVisible(false);
		} else {
			throw new ChooseWorkspaceException();
		}
	}
}
