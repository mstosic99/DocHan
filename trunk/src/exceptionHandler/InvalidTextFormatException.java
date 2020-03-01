package exceptionHandler;

import javax.swing.JOptionPane;

import gui.MainFrame;

@SuppressWarnings("serial")
public class InvalidTextFormatException extends Exception {

	public InvalidTextFormatException() {
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please try again.", "Invalid text format!",
				JOptionPane.ERROR_MESSAGE);
	}
}
