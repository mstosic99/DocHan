package actions.save;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ProjectFileFilter extends FileFilter {
	@Override
	public String getDescription() {
		return "GeRuDok project files (*.pgrd)";
	}

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || f.getName().toLowerCase().endsWith(".pgrd"));
	}
}
