package gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import actions.ActionType;
import model.workspace.Project;
import model.workspace.Workspace;
import observer.ISubscriber;

@SuppressWarnings("serial")
public class Desktop extends JPanel implements ISubscriber {

	private Workspace workspace;
	private ProjectView currentProjectView = null;
	private ArrayList<ProjectView> projectViews;

	public Desktop(Workspace workspace) {
		super();
		this.workspace = workspace;
		workspace.addSubscriber(this);
		projectViews = new ArrayList<>();
	}

	public void addProjectView(ProjectView pv) {
		projectViews.add(pv);
		currentProjectView = pv;
		// add(pv);
	}

	public void removeProjectView(ProjectView pv) {
		projectViews.remove(pv);
		int last = projectViews.size();
		ProjectView pv2 = null;
		if (projectViews.size() > 0) {
			pv2 = projectViews.get(last - 1);
			pv2.setVisible(true);
		}
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
		workspace.addSubscriber(this);
	}

	public ArrayList<ProjectView> getProjectViews() {
		return projectViews;
	}

	public ProjectView getCurrentProjectView() {
		return currentProjectView;
	}

	public void setCurrentProjectView(ProjectView currentProjectView) {
		this.currentProjectView = currentProjectView;
		add(currentProjectView);
	}

	public ProjectView getProjectView(Project p) {
		ProjectView toReturn = null;
		for (ProjectView pv : projectViews) {
			if (pv.getProject() == p) {
				currentProjectView = pv;
				toReturn = pv;
			}
		}
		return toReturn;
	}

	public void repaintProjectViews() {
		for (ProjectView projectView : projectViews) {
			projectView.revalidate();
			projectView.repaint();
		}
	}

	@Override
	public void update(ActionType at, Object notification) {

		if (at.equals(ActionType.NEW_PROJECT)) {

			Project project = (Project) notification;
			ProjectView pv = new ProjectView(project);
			addProjectView(pv);
			JTabbedPane tp = new JTabbedPane();
			JLabel lbl = new JLabel(project.getName());
			pv.setLbl(lbl);
			pv.setTabPane(tp);

			for (ProjectView tmp : projectViews) {
				if (tmp.getProject() == (pv.getProject())) {
					add(tmp);
					tmp.setVisible(true);
				} else {
					remove(tmp);
					tmp.setVisible(false);
				}
			}
		} else if (at.equals(ActionType.REMOVE_PROJECT)) {
			Project p = (Project) notification;
			ProjectView pv = getProjectView(p);

			pv.update(at, notification);
		}
	}
}
