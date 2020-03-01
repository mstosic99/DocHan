package actions.document;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.AbsGEDAction;
import factory.DocumentFactory;
import factory.NodeFactory;
import gui.MainFrame;
import model.workspace.Project;

@SuppressWarnings("serial")
public class NewDocumentAction extends AbsGEDAction {

	public NewDocumentAction() {
		this.setEnabled(false);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/newdocument.png"));
		putValue(NAME, "New Document");
		putValue(SHORT_DESCRIPTION, "New Document");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		TreePath path = MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath();
		if (path == null)
			return;

		Object node = path.getLastPathComponent();
		if (node == null || !(node instanceof Project))
			return;

		Project project = (Project) node;
		NodeFactory factory = new DocumentFactory();
		factory.makeNode(project);

		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());

		// MainFrame.getInstance().getWorkspaceModel().reload(prj);

	}

}
