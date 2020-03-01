package factory;

import model.workspace.Document;
import model.workspace.MyTreeNode;
import model.workspace.Page;

public class PageFactory extends NodeFactory {

	@Override
	public MyTreeNode newNode(MyTreeNode node) {
		MyTreeNode page = new Page(node);
		return page;
	}
}
