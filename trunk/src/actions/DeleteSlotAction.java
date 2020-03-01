package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import graf.elements.SlotDevice;
import gui.DocumentView;
import gui.MainFrame;
import gui.PageView;
import model.workspace.PageElementSelection;

public class DeleteSlotAction extends AbstractEditorAction {

	DeleteSlotAction() {
		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		// ActionEvent.CTRL_MASK));
		// putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, loadIcon("images/deletebin24.png"));
		putValue(NAME, "Delete slot");
		putValue(SHORT_DESCRIPTION, "Delete slot");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		DocumentView dv = ((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent());
		for (PageView p : dv.getPageViews()) {
			for (SlotDevice selectedElement : p.getPage().getSelectedSlots()) {
				if (p.getPage().getSelectedSlots().isEmpty()) {
					dv.getFocusPageView().startDeleteSlotState();
					dv.getFocusPageView().repaint();
				}

			}
		}

	}

}
