package factory;

import model.workspace.MyTreeNode;
import model.workspace.Project;
import model.workspace.Workspace;

public class ProjectFactory extends NodeFactory {

	@Override
	public MyTreeNode newNode(MyTreeNode node) {
		MyTreeNode project = new Project(node);
		return project;
	}
}
