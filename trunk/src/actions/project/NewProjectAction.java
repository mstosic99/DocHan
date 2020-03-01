package actions.project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.AbsGEDAction;
import factory.NodeFactory;
import factory.ProjectFactory;
import gui.MainFrame;
import gui.ProjectView;
import gui.WorkspaceTree;
import model.workspace.Document;
import model.workspace.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;

@SuppressWarnings("serial")
public class NewProjectAction extends AbsGEDAction {

	public NewProjectAction() {
		this.setEnabled(true);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/newproject1.png"));
		putValue(NAME, "New Project");
		putValue(SHORT_DESCRIPTION, "New Project");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object node = MainFrame.getInstance().getWorkspaceModel().getRoot();
		if (node == null || !(node instanceof Workspace))
			return;
		Workspace ws = (Workspace) node;
		NodeFactory factory = new ProjectFactory();
		factory.makeNode(ws);

		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());

	}

}
