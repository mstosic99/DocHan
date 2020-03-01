package actions.share;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import actions.AbsGEDAction;
import gui.MainFrame;
import gui.ShareDialog;

@SuppressWarnings("serial")
public class ShareDocumentAction extends AbsGEDAction {
	
	public ShareDocumentAction() {
		this.setEnabled(false);
		putValue(SMALL_ICON, loadIcon("images/shareDocument.png"));
		putValue(NAME, "Share..");
		putValue(SHORT_DESCRIPTION, "Share document with another project.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ShareDialog.getInstance().populateProjects();
		ShareDialog.getInstance().revalidate();
		ShareDialog.getInstance().repaint();
		ShareDialog.getInstance().setVisible(true);
		
	}

}
