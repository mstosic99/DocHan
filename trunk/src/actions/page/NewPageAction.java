package actions.page;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.AbsGEDAction;
import factory.NodeFactory;
import factory.PageFactory;
import factory.ProjectFactory;
import gui.MainFrame;
import gui.PageView;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;

@SuppressWarnings("serial")
public class NewPageAction extends AbsGEDAction {

	public NewPageAction() {
		this.setEnabled(false);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/newpage.png"));
		putValue(NAME, "New Page");
		putValue(SHORT_DESCRIPTION, "New Page");
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
		NodeFactory factory = new PageFactory();
		factory.makeNode(document);

		MainFrame.getInstance().getDesktop().repaint();
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
	}

}
