package actions.save;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import actions.AbsGEDAction;
import gui.MainFrame;
import gui.SwitchWorkspaceDialog;
import model.workspace.Workspace;

@SuppressWarnings("serial")
public class SaveWorkspaceAction extends AbsGEDAction {

	public SaveWorkspaceAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		putValue(NAME, "Save Workspace");
		putValue(SHORT_DESCRIPTION, "Save Workspace");
		putValue(SMALL_ICON, loadIcon("images/savews.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Workspace workspace = (Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot();
		File workspaceFile = workspace.getWorkspaceFile();

		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(workspaceFile));
			os.writeObject(workspace);
			workspace.setWorkspaceFile(workspaceFile);
			workspace.setChanged(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "You have successfully saved your workspace!");
		SwitchWorkspaceDialog.getInstance().repaint();
		SwitchWorkspaceDialog.getInstance().populateWorkspaces();

	}

}
