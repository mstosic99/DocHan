package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import actions.ActionManager;
import model.workspace.Document;
import model.workspace.Workspace;
import model.workspace.WorkspaceModel;
import mouse.listeners.MyWindowListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ClipboardOwner {

	private static MainFrame instance;
	private ActionManager actionManager;
	private MenuBarView menu;
	private ToolbarView toolbarView;
	private JScrollPane scroll;
	private Desktop desktop;
	private Document document;
	private WorkspaceTree workspaceTree;
	private WorkspaceModel workspaceModel;
	private Workspace workspace;
	private Paleta paleta;
	private JSplitPane splitPane;
	private Clipboard clipboard = new Clipboard("Genericki rukovalac dokumentima clipboard");

	private MainFrame() {

	}

	private void initialize() {

		setTitle("Genericki rukovalac dokumentima");
		actionManager = ActionManager.getInstance();

		initializeGUI();
	}

	private void initializeGUI() {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth * 2 / 3, screenHeight * 2 / 3);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);
		setLocationRelativeTo(null); // centriranje prozora
		Image img = kit.getImage("images/colorfulG1.png");
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new MyWindowListener());

		menu = new MenuBarView();
		this.setJMenuBar(menu);

		toolbarView = new ToolbarView();
		getContentPane().add(toolbarView, BorderLayout.NORTH);

		scroll = new JScrollPane();
		splitPane = new JSplitPane();

		scroll.setMinimumSize(new Dimension(200, 150));

		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(250);

		paleta = new Paleta();
		getContentPane().add(paleta, BorderLayout.EAST);


//		actionManager.getSwitchWorkspaceAction().actionPerformed(null);
//		SwitchWorkspaceDialog.getInstance().setVisible(true);

	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialize();
		}
		return instance;
	}

	public WorkspaceTree getWorkspaceTree() {
		return workspaceTree;
	}

	public void setWorkspaceTree(WorkspaceTree workspaceTree) {
		this.workspaceTree = workspaceTree;
	}

	public WorkspaceModel getWorkspaceModel() {
		return workspaceModel;
	}

	public void setWorkspaceModel(WorkspaceModel workspaceModel) {
		this.workspaceModel = workspaceModel;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public Desktop getDesktop() {
		return desktop;
	}

	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}

	public Document getDocument() {
		return document;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setWorkspace(Workspace workspace) {
		if (workspace == null)
			return;
		this.workspace = workspace;
		this.workspaceModel.setRoot(workspace);
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public void setClipboard(Clipboard clipboard) {
		this.clipboard = clipboard;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}

}