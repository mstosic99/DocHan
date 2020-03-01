package factory;

import model.workspace.MyTreeNode;

public abstract class NodeFactory {

	public void makeNode(MyTreeNode parentNode) {
		MyTreeNode node = newNode(parentNode);
		parentNode.addChildNode(node);

	}

	public abstract MyTreeNode newNode(MyTreeNode parentNode);
}
