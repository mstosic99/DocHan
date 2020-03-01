package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import graf.elements.SlotDevice;
import gui.DocumentView;
import gui.MainFrame;
import model.workspace.Document;
import model.workspace.DocumentElementSelection;
import model.workspace.PageElementSelection;

public class CopyAction extends AbstractEditorAction {

	CopyAction() {
		// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y,
		// ActionEvent.CTRL_MASK));
		// putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, loadIcon("images/copy24.png"));
		putValue(NAME, "Copy");
		putValue(SHORT_DESCRIPTION, "Copy");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
//za tree
		Object o = MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		DocumentElementSelection docElement;

		if (o != null && o instanceof Document) {
			Document d = (Document) o;

			docElement = new DocumentElementSelection(d);
			MainFrame.getInstance().getClipboard().setContents(docElement, MainFrame.getInstance());
			MainFrame.getInstance().getWorkspaceTree().updateUI();
			return;

		}
//za slotove
		ArrayList<SlotDevice> slotDevices;
		slotDevices = ((DocumentView) MainFrame.getInstance().getDesktop().getCurrentProjectView().getTabPane()
				.getSelectedComponent()).getFocusPageView().getPage().getSelectedSlots();
		PageElementSelection content;
		if (!slotDevices.isEmpty()) {
			content = new PageElementSelection(slotDevices);
			MainFrame.getInstance().getClipboard().setContents(content, MainFrame.getInstance());
		}

	}

}
