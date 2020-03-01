package gui;

import javax.swing.DropMode;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.workspace.Project;
import model.workspace.WorkspaceModel;

public class WorkspaceTree extends JTree {

	public WorkspaceTree() {
////		setShowsRootHandles(true);
//		addTreeSelectionListener(new );
//	    setCellEditor(new WorkspaceTreeEditor(this,new DefaultTreeCellRenderer()));
//	    setCellRenderer(new WorkspaceTreeCellRenderer());
//	    setEditable(true);	

		setShowsRootHandles(true);
		setDropMode(DropMode.ON);
		addTreeSelectionListener(new WorkspaceTreeController());
		setCellRenderer(new WorkspaceTreeCellRenderer());

		setCellEditor(new WorkspaceTreeEditor(this, new DefaultTreeCellRenderer()));
		setEditable(true);
		// addMouseListener(new MouseControler());

	}

	public void addProject(Project project) {
		((WorkspaceModel) getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}

}
