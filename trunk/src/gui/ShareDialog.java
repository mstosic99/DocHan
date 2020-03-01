package gui;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import model.workspace.Project;

public class ShareDialog extends JDialog {
	
	JButton chooseButton = new JButton();
	JComboBox<Project> projects = new JComboBox<>();

	private static ShareDialog instance;
	
	
	private ShareDialog() {
		setSize(new Dimension(450,200));
		setLocationRelativeTo(MainFrame.getInstance());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Choose a project");
		getContentPane().setLayout(null);
		
		chooseButton.setBounds(80, 80, 190, 23);
		projects.setBounds(80, 30, 190, 23);
		
		populateProjects();
		
		chooseButton.setAction(MainFrame.getInstance().getActionManager().getChooseDestinationProjectAction());
		
		getContentPane().add(chooseButton);
		getContentPane().add(projects);
	}
	
	public void populateProjects() {
		projects.removeAllItems();
		for(Project project : MainFrame.getInstance().getWorkspace().getProjects()) {
			if(!(project.getDocuments().contains(MainFrame.getInstance().
			getWorkspaceTree().getLeadSelectionPath().getLastPathComponent())))
				projects.addItem(project);
		}
	}
	
	public static ShareDialog getInstance() {
		if(instance == null)
			instance = new ShareDialog();
		return instance;
	}
	
	public JButton getChooseButton() {
		return chooseButton;
	}
	
	public JComboBox<Project> getProjects() {
		return projects;
	}
}
