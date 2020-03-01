package actions.share;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.AbsGEDAction;
import exceptionHandler.SharedDocumentException;
import gui.MainFrame;
import gui.ShareDialog;
import model.workspace.Document;
import model.workspace.Project;
import model.workspace.Workspace;

@SuppressWarnings("serial")
public class ChooseDestinationProjectAction extends AbsGEDAction {
		
	public ChooseDestinationProjectAction() {
		putValue(NAME, "Choose");
		putValue(SHORT_DESCRIPTION, "Choose existing");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath path = MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath();
		if (path == null)
			return;

		Object node = path.getLastPathComponent();
		if (node == null || !(node instanceof Document))
			return;

		Document document = (Document) node;
		Project project = (Project) ShareDialog.getInstance().getProjects().getSelectedItem();
		if(project.getDocuments().contains(document)) {
			throw new SharedDocumentException();
		} else {
			if(document.getProxyParents().isEmpty())
				document.setName(document.getName() + " (shared)");
			document.addProxyParent(project);
			project.addDocument(document);
			ShareDialog.getInstance().setVisible(false);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		}
		
	}
}
