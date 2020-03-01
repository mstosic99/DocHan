package gui;

import javax.swing.JPopupMenu;

import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class TreePopMenu extends JPopupMenu {

	public TreePopMenu(Object selected) {

		if (selected instanceof Workspace) {
			add(MainFrame.getInstance().getActionManager().getNewProjectAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getSwitchWorkspaceAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getOpenProjectAction());


		} else if (selected instanceof Project) {
			add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getRenameAction());
		} else if (selected instanceof Document) {
			add(MainFrame.getInstance().getActionManager().getNewPageAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getRenameAction());
			addSeparator();
			add(MainFrame.getInstance().getActionManager().getShareDocumentAction());
		} else if (selected instanceof Page) {
			add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
			add(MainFrame.getInstance().getActionManager().getRenameAction());

		}
	}

}
