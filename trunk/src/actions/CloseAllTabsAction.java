package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.Desktop;
import gui.MainFrame;
import gui.ProjectView;

public class CloseAllTabsAction extends AbsGEDAction {

	public CloseAllTabsAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/close1.png"));
		putValue(NAME, "Close All Tabs");
		putValue(SHORT_DESCRIPTION, "Close All Tabs");
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
		projectView.getTabPane().removeAll();

	}

}
