package model.workspace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;

import actions.ActionType;
import gui.MainFrame;
import observer.IPublisher;
import observer.ISubscriber;
import utilities.WorkspaceUtilites;

@SuppressWarnings("serial")
public class Workspace implements MyTreeNode, IPublisher, Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 4960283869617355179L;
	private ArrayList<Project> projects;
	private transient ArrayList<ISubscriber> subscribers;
	private transient boolean changed;
	private File workspaceFile;
	private int projectNum;

	public Workspace() {
		super();
		projectNum = 0;
		this.projects = new ArrayList<>();
		this.subscribers = new ArrayList<>();
	}

	public void setupFile() {
		String str = JOptionPane.showInputDialog("Enter a file name");
		if (str == null) {
			return;
		}
		str = str.trim();
		while (str.isEmpty() || !checkFileName(str)) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(),
					"This file name is in invalid format or it already exists, please try again.", "Error 404",
					JOptionPane.ERROR_MESSAGE);
			str = JOptionPane.showInputDialog("Enter a file name");
			if (str != null)
				str.trim();
		}

		workspaceFile = new File("switchWorkspace\\" + str + ".wgrd");

		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(workspaceFile));
			os.writeObject(this);
			this.setWorkspaceFile(workspaceFile);
			this.setChanged(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public boolean checkFileName(String fileName) {

		List<String> result = WorkspaceUtilites.getListOfWorkspaceFileStrings();
		for (String string : result) {
			if (string.equalsIgnoreCase("switchWorkspace\\" + fileName + ".wgrd")) {
				return false;
			}

		}
		return true;
	}

	public String toString() {
		return "Workspace";
	}

	public ArrayList<Project> getProjects() {
		return (ArrayList<Project>) projects;
	}

	public void addProject(Project project) {

		projectNum++;
		setChanged(true);
		projects.add(project);
		if (project.getName() == null) {
			project.setName("Project " + projectNum);
		}
		notifySubscribers(ActionType.NEW_PROJECT, project);

	}

	public void removeProject(Project project) {
		setChanged(true);
		projects.remove(project);

		notifySubscribers(ActionType.REMOVE_PROJECT, project);
	}

	public Project getProject(int index) {
		return projects.get(index);
	}

	public TreeNode getParent() {
		return null;
	}

	public int getChildCount() {
		return projects.size();
	}

	public int getIndex(TreeNode node) {
		return projects.indexOf(node);
	}

	public TreeNode getChildAt(int index) {
		return getProject(index);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return false;
	}

	@SuppressWarnings("unchecked")
	public Enumeration<Project> children() {
		return (Enumeration<Project>) projects;
	}

	@Override
	public void addSubscriber(ISubscriber subscriber) {
		if (subscriber == null)
			return;
		if (this.subscribers == null)
			this.subscribers = new ArrayList<>();
		if (this.subscribers.contains(subscriber))
			return;
		this.subscribers.add(subscriber);
	}

	@Override
	public void removeSubscriber(ISubscriber subscriber) {
		if (subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
			return;
		subscribers.remove(subscriber);
	}

	@Override
	public void notifySubscribers(ActionType at, Object notification) {
		if (notification == null || this.subscribers == null || this.subscribers.isEmpty()) {
			return;
		}
		for (ISubscriber s : subscribers) {
			s.update(at, notification);
		}
	}

	@Override
	public void addChildNode(MyTreeNode node) {
		this.addProject((Project) node);
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public File getWorkspaceFile() {
		return workspaceFile;
	}

	public void setWorkspaceFile(File workspaceFile) {
		this.workspaceFile = workspaceFile;
	}

	public void generateView() {
		for (Project project : projects) {
			this.notifySubscribers(ActionType.NEW_PROJECT, project);
			project.generateView();
		}
	}

	public void updatePerformed(EventObject e) {
		setChanged(true);
	}

}
