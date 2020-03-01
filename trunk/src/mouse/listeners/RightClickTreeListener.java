package mouse.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gui.MainFrame;
import gui.TreePopMenu;
import gui.WorkspaceTree;

public class RightClickTreeListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			WorkspaceTree tree = MainFrame.getInstance().getWorkspaceTree();
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());

			if (path == null)
				return;

			tree.setSelectionPath(path);
			TreePopMenu popMenu = new TreePopMenu(tree.getLastSelectedPathComponent());
			popMenu.show(e.getComponent(), e.getX(), e.getY());

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
