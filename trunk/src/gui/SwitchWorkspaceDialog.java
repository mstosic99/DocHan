package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import utilities.WorkspaceUtilites;


@SuppressWarnings("serial")
public class SwitchWorkspaceDialog extends JDialog {
	
	private static SwitchWorkspaceDialog instance;
	JButton blankButton = new JButton();
	JButton chooseButton = new JButton();
	JComboBox<String> workspaces = new JComboBox<String>(); 
	
	private SwitchWorkspaceDialog() {
		
		setSize(new Dimension(450,200));
		setLocationRelativeTo(MainFrame.getInstance());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Choose your desired workspace");
		getContentPane().setLayout(null);
		
		
		blankButton.setBounds(10, 80, 190, 23);
		chooseButton.setBounds(234, 80, 190, 23);
		workspaces.setBounds(237, 30, 187, 22);
		
		populateWorkspaces();
		
		blankButton.setAction(MainFrame.getInstance().getActionManager().getBlankWorkspaceAction());
		chooseButton.setAction(MainFrame.getInstance().getActionManager().getChooseWorkspaceAction());
		
		getContentPane().add(blankButton);
		getContentPane().add(chooseButton);
		getContentPane().add(workspaces);
		
		
	}
	
	
	public void populateWorkspaces() {
		
		workspaces.removeAllItems();
		List<String> stringsToUse = WorkspaceUtilites.getListOfWorkspaceFileStrings();
		workspaces.addItem("(Choose workspace)");
		
		for(String string : stringsToUse) {
			string = string.substring(16);
			workspaces.addItem(string);
		}
	}


	public static SwitchWorkspaceDialog getInstance() {
		if(instance == null) {
			instance = new SwitchWorkspaceDialog();
		}
		return instance;
	}
	
	public JComboBox<String> getWorkspaces() {
		return workspaces;
	}
	
	public JButton getBlankButton() {
		return blankButton;
	}
	
	public JButton getChooseButton() {
		return chooseButton;
	}
	
}
