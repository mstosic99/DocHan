package command;

import java.util.ArrayList;

import gui.MainFrame;

public class CommandManager {

	// lista koja predstavlja stek na kome se nalaze konkretne izvrsene komande
	private ArrayList<AbstractCommand> commands;

	// pokazivac steka, sadrzi redni broj komande za undo / redo operaciju
	private int currentCommand = 0;

	public CommandManager() {
		commands = new ArrayList<>();
	}

	public void addCommand(AbstractCommand command) {
		while (currentCommand < commands.size())
			commands.remove(currentCommand);

		commands.add(command);
		doCommand();
	}

	// Metoda koja poziva izvršavanje konkretne komande

	public void doCommand() {
		if (currentCommand < commands.size()) {
			commands.get(currentCommand++).doCommand();
			MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
		}
		if (currentCommand == commands.size()) {
			MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);

		}
	}

	// Metoda koja poziva redo konkretne komande

	public void undoCommand() {
		if (currentCommand > 0) {
			MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
			commands.get(--currentCommand).undoCommand();
		}
		if (currentCommand == 0) {
			MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);

		}
	}
}
