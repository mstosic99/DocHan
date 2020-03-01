package gui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

//TODO
public class WorkspaceTreeController implements TreeSelectionListener {

	public void valueChanged(TreeSelectionEvent e) {

		TreePath selectedNode = e.getNewLeadSelectionPath();
		if (selectedNode == null)
			return;

		Object node = selectedNode.getLastPathComponent();
		if (node == null)
			return;

		if (node instanceof Workspace) {
			
			MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getNewDocumentAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getNewPageAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getShareDocumentAction().setEnabled(false);


		} else if (node instanceof Project) {
			
			MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getNewDocumentAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getNewPageAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getShareDocumentAction().setEnabled(false);
			
			Project project = (Project) node;
			ProjectView projectView = MainFrame.getInstance().getDesktop().getProjectView(project);
			for(ProjectView pv : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if(pv == projectView) {
					MainFrame.getInstance().getDesktop().add(pv);
					MainFrame.getInstance().getDesktop().setCurrentProjectView(pv);					
					pv.setVisible(true);
					MainFrame.getInstance().getDesktop().repaint();
					
				} else {
					MainFrame.getInstance().getDesktop().remove(pv);
					pv.setVisible(false);
					MainFrame.getInstance().getDesktop().repaint();
				}
			}

		} else if (node instanceof Document) {
			
			MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getNewDocumentAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getNewPageAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getShareDocumentAction().setEnabled(true);
			
			Document document = (Document) node;
			Project project =(Project) document.getParent();
			ProjectView projectView = MainFrame.getInstance().getDesktop().getProjectView(project);
			for(ProjectView pv : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if(pv == projectView) {
					MainFrame.getInstance().getDesktop().add(pv);
					MainFrame.getInstance().getDesktop().setCurrentProjectView(pv);					
					pv.setVisible(true);
				} else {
					MainFrame.getInstance().getDesktop().remove(pv);
					pv.setVisible(false);
				}
			}
			DocumentView documentView = projectView.getDocumentView(document);
			
			Component[] components = projectView.getTabPane().getComponents();
			boolean exists = false;
			for(int i = 0; i < components.length; i++) {
				if(components[i] == (Component)documentView)
					exists = true;
			}
			
			if(!exists) {
				projectView.getTabPane().add(documentView);
			}
			
			projectView.getTabPane().setSelectedComponent(documentView);

		} else if (node instanceof Page) { 
			
			MainFrame.getInstance().getActionManager().getNewProjectAction().setEnabled(true);
			MainFrame.getInstance().getActionManager().getNewDocumentAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getNewPageAction().setEnabled(false);
			MainFrame.getInstance().getActionManager().getShareDocumentAction().setEnabled(false);
			
			Page page = (Page) node;
			Document document =(Document) page.getParent();
			Project project = (Project) document.getParent();
			ProjectView projectView = MainFrame.getInstance().getDesktop().getProjectView(project);
			for(ProjectView pv : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if(pv == projectView) {
					MainFrame.getInstance().getDesktop().add(pv);
					MainFrame.getInstance().getDesktop().setCurrentProjectView(pv);					
					pv.setVisible(true);
				} else {
					MainFrame.getInstance().getDesktop().remove(pv);
					pv.setVisible(false);
				}
			}
			DocumentView documentView = projectView.getDocumentView(document);
			projectView.getTabPane().setSelectedComponent(documentView);
			PageView pageView = documentView.getPageView(page);
			documentView.setFocusPageView(pageView);
			
//			jp.scrollRectToVisible(pageView.getBounds());
			for(PageView pv : documentView.getPageViews()) {
				if(pv == pageView)
					pv.setVisible(true);
				else
					pv.setVisible(false);
			}
			
		}

	}
}