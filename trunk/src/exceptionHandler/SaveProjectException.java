package exceptionHandler;

import javax.swing.JOptionPane;

import gui.MainFrame;

public class SaveProjectException extends Exception {

	public SaveProjectException() {
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please select a project to save.",
				"No project selected.", JOptionPane.ERROR_MESSAGE);
	}
}
