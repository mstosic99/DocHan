package model.workspace;

import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class WorkspaceModel extends DefaultTreeModel {

	public WorkspaceModel() {
		super(new Workspace());

	}

	public void addProject(Project project) {
		((Workspace) getRoot()).addProject(project);
	}

}
