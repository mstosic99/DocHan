package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.Desktop;
import gui.DocumentView;
import gui.MainFrame;
import gui.ProjectView;

public class CloseTabAction extends AbsGEDAction {

	public CloseTabAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/closeTab.png"));
		putValue(NAME, "Close Tab");
		putValue(SHORT_DESCRIPTION, "Close Tab");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Desktop desktop = MainFrame.getInstance().getDesktop();
		ProjectView projectView = null;

		for (ProjectView pv : desktop.getProjectViews()) {
			if (pv.isVisible()) {
				projectView = pv;
			}
		}
		DocumentView documentView = (DocumentView) projectView.getTabPane().getSelectedComponent();
		projectView.getTabPane().remove(documentView);

	}
}
