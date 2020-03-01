package factory;

import model.workspace.Document;
import model.workspace.MyTreeNode;
import model.workspace.Project;

public class DocumentFactory extends NodeFactory {

	@Override
	public MyTreeNode newNode(MyTreeNode node) {
		MyTreeNode document = new Document(node);
		return document;
	}
}
