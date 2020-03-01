package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBarView extends JMenuBar {

	private JMenu fileM;
	private JMenu newM;
	private JMenu editM;
	private JMenu aboutM;

	private JMenuItem projectMI;
	private JMenuItem documentMI;
	private JMenuItem openMI;
	private JMenuItem saveMI;
	private JMenuItem saveAllMI;
	private JMenuItem closeMI;
	private JMenuItem closeAllMI;
	private JMenuItem exitMI;
	private JMenuItem cutMI;
	private JMenuItem copyMI;
	private JMenuItem pasteMI;
	private JMenuItem undoMI;
	private JMenuItem redoMI;

	public MenuBarView() {

		fileM = new JMenu("File");
		newM = new JMenu("New");
		fileM.add(newM);
		fileM.addSeparator();
		newM.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
		newM.add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
		newM.add(MainFrame.getInstance().getActionManager().getNewPageAction());

		fileM.add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
		fileM.add(MainFrame.getInstance().getActionManager().getRenameAction());
		fileM.addSeparator();
		fileM.add(MainFrame.getInstance().getActionManager().getSwitchWorkspaceAction());
		fileM.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
		fileM.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
		fileM.add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
		fileM.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());

//		editM = new JMenu("Edit");

		aboutM = new JMenu("About");
		aboutM.add(MainFrame.getInstance().getActionManager().getInfoAction());

		add(fileM);
//		add(editM);
		add(aboutM);
	}

	public JMenuItem getProjectMI() {
		return projectMI;
	}

	public JMenuItem getDocumentMI() {
		return documentMI;
	}
}
