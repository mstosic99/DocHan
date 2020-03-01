package app;

import gui.MainFrame;
import gui.SwitchWorkspaceDialog;

public class Main {

	public static void main(String[] args) {
		SwitchWorkspaceDialog.getInstance().setVisible(true);
		MainFrame mf = MainFrame.getInstance();
		if(mf.getWorkspaceModel() == null) return;
		mf.setVisible(true);
	}

}
