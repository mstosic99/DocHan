package mouse.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gui.MainFrame;
import gui.WorkspaceTree;
import model.workspace.Project;

public class DoubledClickListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {/*
											 * if(e.getClickCount()==2 && SwingUtilities.isLeftMouseButton(e)) {
											 * 
											 * WorkspaceTree tree=MainFrame.getInstance().getWorkspaceTree(); TreePath
											 * path=tree.getPathForLocation(e.getX(),e.getY()); Object selected
											 * =tree.getLastSelectedPathComponent();
											 * 
											 * if(path==null) return;
											 * 
											 * tree.setSelectionPath(path);
											 * 
											 * if(selected instanceof Project) {
											 * 
											 * else if(selected instanceof) {
											 * 
											 * } else if() {
											 * 
											 * }
											 * 
											 * 
											 * 
											 * } }
											 */
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
