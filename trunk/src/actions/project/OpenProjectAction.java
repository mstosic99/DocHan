package actions.project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import actions.AbsGEDAction;
import actions.save.ProjectFileFilter;
import gui.MainFrame;
import model.workspace.Project;

@SuppressWarnings("serial")
public class OpenProjectAction extends AbsGEDAction {

	public OpenProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(NAME, "Open Project");
		putValue(SHORT_DESCRIPTION, "Open Project");
		putValue(SMALL_ICON, loadIcon("images/open.png"));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ProjectFileFilter());
		jfc.setCurrentDirectory(new File("savedProjects"));

		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				Project project = null;
				try {
					project = (Project) ois.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				MainFrame.getInstance().getWorkspaceTree().addProject(project);
				project.generateView();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
