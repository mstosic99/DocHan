package gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer {

	public WorkspaceTreeCellRenderer() {

	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expended, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expended, leaf, row, hasFocus);
		
		if (value instanceof Workspace)
			setIcon(new ImageIcon("images/treewokspace.png"));
		else if (value instanceof Project)
			setIcon(new ImageIcon("images/treeProject.png"));
		else if (value instanceof Document) 
			setIcon(new ImageIcon("images/treeDocument.png"));
		else if (value instanceof Page)
			setIcon(new ImageIcon("images/treePage.png"));
		return this;
	}
}
