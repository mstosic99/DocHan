package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gui.HangingProjectsDialog;
import gui.MainFrame;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class RemoveNodeAction extends AbsGEDAction {
	public RemoveNodeAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/remove-icon.png"));
		putValue(NAME, "Remove");
		putValue(SHORT_DESCRIPTION, "Remove");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TreePath path = MainFrame.getInstance().getWorkspaceTree().getLeadSelectionPath();

		if (path == null) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please select a node to remove.",
					"No node selected", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Object node = path.getLastPathComponent();

		if (node instanceof Workspace) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "You can't remove workspace.", "Workspace selected",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (node instanceof Project) {
			Project project = (Project) node;
			Workspace parent = (Workspace) project.getParent();
			parent.removeProject(project);
			
			ArrayList<Document> hangDocuments = project.getDocuments();
			
			if(!hangDocuments.isEmpty()) {
				MainFrame.getInstance().getActionManager().getChooseProjectForStoringDocsAction().setDocuments(hangDocuments);
				HangingProjectsDialog.getInstance().populateProjects();
				HangingProjectsDialog.getInstance().setVisible(true);
			}
			
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());

		}

		else if (node instanceof Document) {
			Document document = (Document) node;
			Project parent = (Project) document.getParent();
			parent.removeDocument(document);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());

		}

		else if (node instanceof Page) {
			Page page = (Page) node;
			Document parent = (Document) page.getParent();
			parent.removePage(page);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		
		return;
	}
}
