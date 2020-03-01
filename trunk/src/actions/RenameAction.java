package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gui.MainFrame;
import gui.ProjectView;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class RenameAction extends AbsGEDAction {

	public RenameAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/rename.png"));
		putValue(NAME, "Rename");
		putValue(SHORT_DESCRIPTION, "Rename");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath path = MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath();

		if (path == null) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please select a node to rename.",
					"No node selected", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Object node = path.getLastPathComponent();

		if (node == null) {
			return;
		}

		if (node instanceof Workspace) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "You can't rename workspace.", "Workspace selected",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		MainFrame.getInstance().getWorkspaceTree().startEditingAtPath(path);
	}

}
