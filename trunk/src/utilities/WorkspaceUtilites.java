package utilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import gui.Desktop;
import gui.MainFrame;
import gui.SwitchWorkspaceDialog;
import gui.WorkspaceTree;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;
import model.workspace.WorkspaceModel;
import mouse.listeners.RightClickTreeListener;

public class WorkspaceUtilites {

	public static boolean makeBlankWorkspace() {

		WorkspaceModel wsModel = new WorkspaceModel();
		Workspace workspace = (Workspace) wsModel.getRoot();
		workspace.setupFile();
		if (workspace.getWorkspaceFile() == null) {
			return false;
		}
		WorkspaceTree workspaceTree = new WorkspaceTree();
		workspaceTree.setModel(wsModel);
		Desktop desktop = new Desktop(workspace);
		desktop.setLayout(new BorderLayout());

		workspaceTree.addMouseListener(new RightClickTreeListener());

		MainFrame.getInstance().setWorkspaceModel(wsModel);
		MainFrame.getInstance().setWorkspaceTree(workspaceTree);
		MainFrame.getInstance().setWorkspace(workspace);
		MainFrame.getInstance().setDesktop(desktop);
		MainFrame.getInstance().getSplitPane().setRightComponent(desktop);
		MainFrame.getInstance().getSplitPane().setLeftComponent(new JScrollPane(workspaceTree));
		MainFrame.getInstance().getSplitPane().setDividerLocation(250);
		MainFrame.getInstance().setVisible(true);

		return true;

	}

	public static boolean makeChosenWorkspace() {

		String string = (String) SwitchWorkspaceDialog.getInstance().getWorkspaces().getSelectedItem();
		if (string == "(Choose workspace)")
			return false;

		string = "switchWorkspace\\" + string;
		List<String> listOfFileStrings = getListOfWorkspaceFileStrings();
		for (String str : listOfFileStrings) {
			str = str.substring(16);
			System.out.println("");
		}

		WorkspaceModel workspaceModel = new WorkspaceModel();
		WorkspaceTree workspaceTree = new WorkspaceTree();
		workspaceTree.setModel(workspaceModel);
		workspaceTree.addMouseListener(new RightClickTreeListener());

		MainFrame.getInstance().setWorkspaceModel(workspaceModel);
		MainFrame.getInstance().setWorkspaceTree(workspaceTree);

		for (String s : listOfFileStrings) {
			if (s.equals(string)) {
				try {
					File file = new File(string);
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					Workspace ws = null;
					try {
						ws = (Workspace) ois.readObject();
					} catch (Exception e1) {
						e1.printStackTrace();
						ois.close();
						return false;
					}

					MainFrame.getInstance().setWorkspace(ws);
					Desktop desktop = new Desktop(ws);
					desktop.setLayout(new BorderLayout());
					MainFrame.getInstance().setDesktop(desktop);

					MainFrame.getInstance().getSplitPane().setRightComponent(desktop);
					MainFrame.getInstance().getSplitPane().setLeftComponent(new JScrollPane(workspaceTree));
					MainFrame.getInstance().getSplitPane().setDividerLocation(250);
					ws.generateView();
					desktop.repaintProjectViews();
					MainFrame.getInstance().revalidate();
					MainFrame.getInstance().repaint();
					MainFrame.getInstance().setVisible(true);
					ois.close();

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	public static List<String> getListOfWorkspaceFileStrings() {
		List<String> stringsToReturn = null;
		try (Stream<Path> walk = Files.walk(Paths.get("switchWorkspace"))) {

			stringsToReturn = walk.map(x -> x.toString()).filter(f -> f.endsWith(".wgrd")).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringsToReturn;
	}

	public static void askToSaveWorkspaceOnExit(WindowEvent e) {
		JFrame frame = (JFrame) e.getComponent();
		if (MainFrame.getInstance().getWorkspaceModel() == null) {
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			return;
		}
		Workspace workspace = (Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot();

		if (workspace.isChanged() == true) {
			int code = JOptionPane.showConfirmDialog(frame, "Do you want to save the workspace?", "Closing the app?",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (code == JOptionPane.YES_OPTION) {
				MainFrame.getInstance().getActionManager().getSaveWorkspaceAction().actionPerformed(null);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			} else if (code == JOptionPane.NO_OPTION) {
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			} else {
				frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			}
		} else {
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
	}

	public static void askToSaveWorkspace() {

		Workspace workspace = (Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot();

		if (workspace.isChanged() == true) {
			int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to save the workspace?",
					"Switching workspace?", JOptionPane.YES_NO_OPTION);
			if (code == JOptionPane.YES_OPTION) {
				MainFrame.getInstance().getActionManager().getSaveWorkspaceAction().actionPerformed(null);
			}
		}
	}
}
