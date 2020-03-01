package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import gui.HangingProjectsDialog;
import gui.MainFrame;
import model.workspace.Document;
import model.workspace.Project;

public class ChooseProjectForStoringDocsAction extends AbsGEDAction {
	
	public ArrayList<Document> documents;
	
	public ChooseProjectForStoringDocsAction() {
		documents = new ArrayList<Document>();
		putValue(NAME, "Choose");
		putValue(SHORT_DESCRIPTION, "Choose existing");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!((Project) HangingProjectsDialog.getInstance().getProjects().getSelectedItem()).getName().equals("Delete all")) {
			Project project = (Project) HangingProjectsDialog.getInstance().getProjects().getSelectedItem();
						
			if(documents == null || documents.isEmpty()) return;
			for(Document d : documents) {
				project.addDocument(d);
				d.setParent(project);
			}
			MainFrame.getInstance().getDesktop().getProjectView(project).getTabPane().removeAll();
			project.generateView();
			HangingProjectsDialog.getInstance().setVisible(false);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		} else {
			HangingProjectsDialog.getInstance().setVisible(false);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
			return;
		}
			
	}
	
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	public ArrayList<Document> getDocuments() {
		return documents;
	}
}
