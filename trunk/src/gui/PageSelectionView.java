package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import actions.ActionType;
import graf.elements.SlotDevice;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;

@SuppressWarnings("serial")
public class PageSelectionView extends AbstractPageView {

	private JLabel rectangles;
	private JLabel circles;
	private JLabel triangles;

	private int numRectangles;
	private int numCircles;
	private int numTriangles;

	public PageSelectionView(DocumentView documentView, Page page) {
		super(documentView, page);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		numRectangles = 0;
		numCircles = 0;
		numTriangles = 0;

		rectangles = new JLabel("Rectangles: " + numRectangles);
		rectangles.setFont(new Font(rectangles.getFont().getName(), Font.ITALIC+Font.BOLD, rectangles.getFont().getSize()));
		
		circles = new JLabel("Circles: " + numCircles);
		circles.setFont(new Font(circles.getFont().getName(), Font.ITALIC+Font.BOLD, circles.getFont().getSize()));

		triangles = new JLabel("Triangles: " + numTriangles);
		triangles.setFont(new Font(triangles.getFont().getName(), Font.ITALIC+Font.BOLD, triangles.getFont().getSize()));

		lbl.setText(this.getPage().getName());
		
		setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(lbl);
		add(rectangles);
		add(circles);
		add(triangles);
		
		setPreferredSize(new Dimension(100, 130));
		addMouseListener(new MouseController(this));

	}

	@Override
	public void update(ActionType at, Object notification) {
		
		if (at.equals(ActionType.RENAME_PAGE)) {

			Page page = (Page) notification;
			lbl.setText(page.getName());
		} else if (at.equals(ActionType.REMOVE_PAGE)) {
			Page page = (Page) notification;
			Document document = (Document) page.getParent();
			Project project = (Project) document.getParent();
			ProjectView projectView = null;
			DocumentView documentView = null;
			PageSelectionView pageSelectionView = null;

			for (ProjectView pv : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if (pv.getProject() == project) {
					projectView = pv;
				}
			}

			for (DocumentView dv : projectView.getDocumentViews()) {
				if (dv.getDocument() == document) {
					documentView = dv;
				}
			}

			for (PageSelectionView psv : documentView.getPageSelectionViews()) {
				if (psv.getPage() == page) {
					pageSelectionView = psv;
				}
			}
			documentView.removePageSelectionView(pageSelectionView);
		} else if (notification instanceof SlotDevice && at == ActionType.DRAW) {
			
			rectangles.setText("Rectangles: " + this.getPage().getRectangles().size());
			triangles.setText("Triangles: " + this.getPage().getTriangles().size());
			circles.setText("Circles: " + this.getPage().getCircles().size());
			
		}
		MainFrame.getInstance().revalidate();
		MainFrame.getInstance().repaint();
		
	}
	
	private class MouseController implements MouseListener {
		private PageSelectionView pageSelectionView;

		public MouseController(PageSelectionView p) {
			pageSelectionView = p;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			documentView.setFocusPageSelectionView(pageSelectionView);
			if(e.getClickCount()==2 && SwingUtilities.isLeftMouseButton(e)) {
				for(PageView pv : documentView.getPageViews()) {
					if(pv.getPage() == pageSelectionView.getPage()) {
//						documentView.getRightPanel().scrollRectToVisible(pv.getBounds());
						pv.setVisible(true);
						documentView.setFocusPageView(pv);
					} else {
						pv.setVisible(false);
					}
				}
				
				
				
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {			
		}

		@Override
		public void mouseReleased(MouseEvent e) {			
		}

		@Override
		public void mouseEntered(MouseEvent e) {			
		}

		@Override
		public void mouseExited(MouseEvent e) {			
		}	
	}
	
	public int getNumCircles() {
		return numCircles;
	}
	
	public void setNumCircles(int numCircles) {
		this.numCircles = numCircles;
	}
	
	public int getNumRectangles() {
		return numRectangles;
	}
	
	public void setNumRectangles(int numRectangles) {
		this.numRectangles = numRectangles;
	}
	
	public int getNumTriangles() {
		return numTriangles;
	}
	
	public void setNumTriangles(int numTriangles) {
		this.numTriangles = numTriangles;
	}

}
