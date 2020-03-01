package gui;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import model.workspace.Project;

public class HangingProjectsDialog extends JDialog {
	
	JButton chooseButton = new JButton();
	JComboBox<Project> projects = new JComboBox<>();

	private static HangingProjectsDialog instance;
	
	
	private HangingProjectsDialog() {
		setSize(new Dimension(450,200));
		setLocationRelativeTo(MainFrame.getInstance());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Choose what to do with documents from the deleted project.");
		getContentPane().setLayout(null);
		
		chooseButton.setBounds(80, 80, 190, 23);
		projects.setBounds(80, 30, 190, 23);
		
		populateProjects();
		
		chooseButton.setAction(MainFrame.getInstance().getActionManager().getChooseProjectForStoringDocsAction());
		
		getContentPane().add(chooseButton);
		getContentPane().add(projects);
	}
	
	public void populateProjects() {
		projects.removeAllItems();
		projects.addItem(new Project("Delete all", null));
		for(Project project : MainFrame.getInstance().getWorkspace().getProjects()) {
			projects.addItem(project);
		}
	}
	
	public static HangingProjectsDialog getInstance() {
		if(instance == null)
			instance = new HangingProjectsDialog();
		return instance;
	}
	
	public JButton getChooseButton() {
		return chooseButton;
	}
	
	public JComboBox<Project> getProjects() {
		return projects;
	}
}
