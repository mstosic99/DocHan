package exceptionHandler;

import javax.swing.JOptionPane;

import gui.MainFrame;

@SuppressWarnings("serial")
public class SharedDocumentException extends RuntimeException {
	
	public SharedDocumentException() {
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Selected project already contains this document",
				"Error", JOptionPane.ERROR_MESSAGE);
	}
}
