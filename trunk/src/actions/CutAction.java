package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import app.Main;
import graf.elements.SlotDevice;
import gui.DocumentView;
import gui.MainFrame;
import model.workspace.Document;
import model.workspace.DocumentElementSelection;
import model.workspace.PageElementSelection;
import model.workspace.Project;

public class CutAction extends AbstractEditorAction {

	CutAction() {
		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		// ActionEvent.CTRL_MASK));
		// putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, loadIcon("images/cut24.png"));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// za tree
		Object o = MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		DocumentElementSelection content;
		PageElementSelection contentTree;

		if (o != null && o instanceof Document) {
			Document d = (Document) o;
			Project p = (Project) d.getParent();
			p.removeDocument(d);
			content = new DocumentElementSelection(d);
			MainFrame.getInstance().getClipboard().setContents(content, MainFrame.getInstance());
			MainFrame.getInstance().getWorkspaceTree().updateUI();
			return;

		}

		// za slotove
		ArrayList<SlotDevice> slotDevices = ((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView()
				.getTabPane().getSelectedComponent()).getFocusPageView().getPage().getSelectedSlots();

		if (!slotDevices.isEmpty()) {
			contentTree = new PageElementSelection(slotDevices);
			MainFrame.getInstance().getClipboard().setContents(contentTree, MainFrame.getInstance());

			((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
					.getSelectedComponent()).getFocusPageView().getPage().getSlotDevices().removeAll(slotDevices);

			((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
					.getSelectedComponent()).getFocusPageView().getPage().getSelectedSlots().removeAll(slotDevices);

			((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
					.getSelectedComponent()).getFocusPageView().repaint();

		}

	}
}
