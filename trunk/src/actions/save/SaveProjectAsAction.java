package actions.save;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import actions.AbsGEDAction;
import exceptionHandler.SaveProjectException;
import gui.MainFrame;
import model.workspace.Project;

@SuppressWarnings("serial")
public class SaveProjectAsAction extends AbsGEDAction {

	public SaveProjectAsAction() {

		putValue(NAME, "Save Project As");
		putValue(SMALL_ICON, loadIcon("images/saveas2.png"));
		putValue(SHORT_DESCRIPTION, "Save Project As");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ProjectFileFilter());
		jfc.setCurrentDirectory(new File("savedProjects"));

		TreePath path = MainFrame.getInstance().getWorkspaceTree().getSelectionPath();
		try {
			if (path == null || path.getPathCount() == 1) {
				throw new SaveProjectException();
			}
		} catch (SaveProjectException e1) {

		}

		Project project = null;
		for (int i = 0; i < path.getPathCount(); i++) {
			if (path.getPathComponent(i) instanceof Project) {
				project = (Project) path.getPathComponent(i);
			}
		}

		File projectFile = project.getProjectFile();

		if (project.getProjectFile() == null) {
			if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
				projectFile = jfc.getSelectedFile();
				String filePath = projectFile.getAbsolutePath();
				if (!filePath.endsWith(".pgrd")) {
					projectFile = new File(filePath + ".pgrd");
				}

			} else {

				return;
			}

		}

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(projectFile));
			oos.writeObject(project);
			project.setProjectFile(projectFile);
			project.setChanged(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
