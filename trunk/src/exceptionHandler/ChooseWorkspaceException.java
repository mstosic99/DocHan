package exceptionHandler;

import javax.swing.JOptionPane;

import gui.MainFrame;

@SuppressWarnings("serial")
public class ChooseWorkspaceException extends RuntimeException {
	public ChooseWorkspaceException() {
		JOptionPane.showMessageDialog(MainFrame.getInstance(),
				"Please select another workspace from a list, if none exist, please open a blank workspace.",
				"Invalid workspace file", JOptionPane.ERROR_MESSAGE);
		MainFrame.getInstance().getActionManager().getSwitchWorkspaceAction().actionPerformed(null);
	}

}
