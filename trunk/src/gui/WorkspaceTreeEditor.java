
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import exceptionHandler.InvalidTextFormatException;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;

public class WorkspaceTreeEditor extends DefaultTreeCellEditor implements ActionListener {

	private Object stavka = null;
	private JTextField edit = null;

	public WorkspaceTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
			int arg5) {

		// super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
		stavka = arg1;

		edit = new JTextField(arg1.toString());
		edit.addActionListener(this);
		return edit;
	}

	public boolean isCellEditable(EventObject arg0) {
		if (arg0 instanceof MouseEvent)
			if (((MouseEvent) arg0).getClickCount() == 3) {
//				actionPerformed(null);
				return true;
			}
		if (arg0 == null)
			return true;
		return false;
	}

	
	public void actionPerformed(ActionEvent e) {
		stavka = this.tree.getLastSelectedPathComponent();
		if (stavka instanceof Project) {
			if(!check(e))
				return;
			((Project) stavka).setName(e.getActionCommand().trim());
		} else if (stavka instanceof Document) {
			if(!check(e))
				return;
			((Document) stavka).setName(e.getActionCommand().trim());
		} else if (stavka instanceof Page) {
			if(!check(e))
				return;
			((Page) stavka).setName(e.getActionCommand().trim());
		}
		MainFrame.getInstance().getWorkspaceModel().reload();
	}
	
	private boolean check(ActionEvent e) {
		String s = "";
		boolean b = true;
		try {
			if(e.getActionCommand().trim().equalsIgnoreCase(s)) {
				b = false;
				throw new InvalidTextFormatException();
			}
		} catch (Exception e1) {
		}
		return b;	
	}
}
