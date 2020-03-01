package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import actions.ActionType;
import model.workspace.Document;
import model.workspace.Project;
import observer.ISubscriber;

public class ProjectView extends JPanel implements ISubscriber {

	private JLabel lbl;
	private Project project;
	private ArrayList<DocumentView> documentViews;
	private JTabbedPane tabPane;

	public ProjectView(Project project) {
		super();
		setLayout(new BorderLayout());
		this.project = project;
		project.addSubscriber(this);
		tabPane = new JTabbedPane();
		lbl = new JLabel();
		this.add(lbl,BorderLayout.NORTH);
		this.add(tabPane,BorderLayout.CENTER);
		documentViews = new ArrayList<>();
		setVisible(true);
	}

	public Project getProject() {
		return project;
	}
	
	public DocumentView getDocumentView(Document d) {
		DocumentView toReturn = null;
		for(DocumentView dv : documentViews) {
			if(dv.getDocument() == d) {
				toReturn = dv;
			}
		}
		return toReturn;
	}

	public void addDocumentView(DocumentView dv) {
		documentViews.add(dv);
	}

	public void removeDocumentView(DocumentView dv) {
		documentViews.remove(dv);
		this.getTabPane().remove(dv);
		revalidate();
		repaint();
	}

	public void setProject(Project project) {
		this.project = project;
		this.setName(project.getName());
	}

	public ArrayList<DocumentView> getDocumentViews() {
		return documentViews;
	}

	@Override
	public void update(ActionType at, Object notification) {
		if (at.equals(ActionType.NEW_DOCUMENT)) {
			Document document = (Document) notification;
			DocumentView dv = new DocumentView(document);
			
			// Generisanje deljenih dokumenata (fix)
			if(document.getParent() != this.getProject()) {
				return;
			}
			addDocumentView(dv);
			ArrayList<ProjectView> projectViews = MainFrame.getInstance().getDesktop().getProjectViews();
			
			for(ProjectView pv: projectViews) {
				if(pv.getProject() == document.getParent()) {
					pv.getTabPane().addTab(dv.getName(), dv);
					MainFrame.getInstance().getWorkspaceTree()		//
					.expandPath(MainFrame.getInstance()				// expand automatski kad se dodaje dokument
					.getWorkspaceTree().getLeadSelectionPath());	//
				}
			}
		}
		else if(at.equals(ActionType.REMOVE_PROJECT)) {
			Project p = (Project) notification;
			ArrayList<ProjectView> projectViews = MainFrame.getInstance().getDesktop().getProjectViews(); 
			ProjectView projectView = null;
			
			for(ProjectView pv : projectViews) {
				if(pv.getProject() == p) {
					projectView = pv;
					pv.setVisible(false);
				}
			}
			MainFrame.getInstance().getDesktop().removeProjectView(projectView);
		}
		else if(at.equals(ActionType.REMOVE_DOCUMENT)) {
			Document d = (Document) notification;
			DocumentView dv = null;
			for(DocumentView documentView : getDocumentViews()) {
				if(documentView.getDocument() == d) {
					dv = documentView;
				}
			}
			dv.update(at, notification);
		}
		else if(at.equals(ActionType.RENAME_PROJECT)) {
			Project project = (Project) notification;
			this.getLbl().setText(project.getName());
			
			for(DocumentView documentView : documentViews) {
				Document document = documentView.getDocument();
				for(PageView pageView : documentView.getPageViews()) {
					pageView.getLbl().setText(project.getName() + " - " + document.getName() + " - " + pageView.getPage().getName());
				}
			}
		}
	}
	
	public JTabbedPane getTabPane() {
		return tabPane;
	}
	
	public void setTabPane(JTabbedPane tabPane) {
		this.tabPane = tabPane;
		this.add(tabPane, BorderLayout.CENTER);
	}
	
	public JLabel getLbl() {
		return lbl;
	}
	
	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
		this.add(lbl, BorderLayout.NORTH);
		lbl.setHorizontalTextPosition(SwingConstants.CENTER); // Ova linija je nekim cudom resila problem
															  // sa (ne)ucitavanjem prvog projekta na desni panel
		
		this.lbl.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
