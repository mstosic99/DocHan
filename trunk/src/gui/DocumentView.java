package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

import actions.ActionType;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import observer.ISubscriber;
import gui.PageView;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements ISubscriber {

	private PageSelectionView focusPageSelectionView = null;
	private PageView focusPageView = null;
	private Document document;
	private ArrayList<PageView> pageViews;
	private ArrayList<PageSelectionView> pageSelectionViews;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;

	public DocumentView(Document document) {

		this.document = document;
		document.addSubscriber(this);
		pageViews = new ArrayList<>();
		pageSelectionViews = new ArrayList<>();

		this.setName(document.getName());
		this.setLayout(new BorderLayout());

		rightPanel = new JPanel();
		rightPanel.setLayout(new CardLayout());

		leftPanel = new JPanel();
		BoxLayout box = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(box);
		leftPanel.add(Box.createHorizontalGlue());

		leftScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		leftScroll.setBorder(BorderFactory.createEmptyBorder());
		leftScroll.getVerticalScrollBar().setUnitIncrement(25); // Podesavanje brzine scrollovanja
		leftScroll.setViewportView(leftPanel);
		this.add(leftScroll, BorderLayout.WEST);

		rightScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightScroll.setBorder(BorderFactory.createEmptyBorder());
		rightScroll.getVerticalScrollBar().setUnitIncrement(25); // Podesavanje brzine scrollovanja
		rightScroll.setViewportView(rightPanel);
		this.add(rightScroll, BorderLayout.CENTER);

		setVisible(true);

	}

	public void setDocument(Document document) {
		this.document = document;
		this.setName(document.getName());

	}

	public PageSelectionView getPageSelectionView(Page p) {
		PageSelectionView toReturn = null;
		for (PageSelectionView psv : pageSelectionViews) {
			if (psv.getPage() == p) {
				focusPageSelectionView = psv;
				toReturn = psv;
			}
		}
		return toReturn;
	}

	public void addPageSelectionView(PageSelectionView psv) {
		pageSelectionViews.add(psv);
		setFocusPageSelectionView(psv);
		getLeftPanel().add(psv);
	}

	public void removePageSelectionView(PageSelectionView psv) {
		pageSelectionViews.remove(psv);
		this.getLeftPanel().remove(psv);
		revalidate();
		repaint();
	}

	public PageView getPageView(Page p) {
		PageView toReturn = null;
		for (PageView pv : pageViews) {
			if (pv.getPage() == p) {
				focusPageView = pv;
				toReturn = pv;
			}
		}
		return toReturn;
	}

	public void addPageView(PageView pv) {
		pageViews.add(pv);
		setFocusPageView(pv);
		getRightPanel().add(pv);
//		getRightPanel().repaint();
	}

	public void removePageView(PageView pv) {
		pageViews.remove(pv);
		this.getRightPanel().remove(pv);
		revalidate();
		repaint();
	}

	public Document getDocument() {
		return document;
	}

	@Override
	public void update(ActionType at, Object notification) {
		if (at.equals(ActionType.NEW_PAGE)) {

			// PageView
			Page page = (Page) notification;
			PageView pv = new PageView(this, page);
			this.addPageView(pv);
			MainFrame.getInstance().getWorkspaceTree() //
					.expandPath(MainFrame.getInstance() // expand automatski kad se dodaje stranica
							.getWorkspaceTree().getLeadSelectionPath()); //
			revalidate();
			repaint();

			// PageSelectionView
			PageSelectionView psv = new PageSelectionView(this, page);
			this.addPageSelectionView(psv);
			focusPageView = pv;

			revalidate();
			repaint();

		} else if (at.equals(ActionType.REMOVE_DOCUMENT)) {
			Document d = (Document) notification;
			Project p = (Project) d.getParent();
			ProjectView pv = null;
			DocumentView dv = null;

			for (ProjectView projectView : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if (projectView.getProject() == p) {
					pv = projectView;
				}
			}

			for (DocumentView documentView : pv.getDocumentViews()) {
				if (documentView.getDocument() == d) {
					dv = documentView;
				}
			}
			pv.removeDocumentView(dv);

		} else if (at.equals(ActionType.REMOVE_PAGE)) {

			// PageView
			Page p = (Page) notification;
			PageView pv = null;
			for (PageView pageView : getPageViews()) {
				if (pageView.getPage() == p) {
					pv = pageView;
				}
			}
			if (pv != null)
				pv.update(at, notification);

			// PageSelectionView
			PageSelectionView psv = null;
			for (PageSelectionView pageSelectionView : getPageSelectionViews()) {
				if (pageSelectionView.getPage() == p) {
					psv = pageSelectionView;
				}
			}
			if (psv != null)
				psv.update(at, notification);

		} else if (at.equals(ActionType.RENAME_DOCUMENT)) {
			Document document = (Document) notification;
			Project project = (Project) document.getParent();
			this.setName(document.getName());
			JTabbedPane tabPane = (JTabbedPane) this.getParent();
			int index = tabPane.getSelectedIndex();
			tabPane.setTitleAt(index, document.getName());
			for (PageView pageView : getPageViews()) {
				pageView.getLbl()
						.setText(project.getName() + " - " + document.getName() + " - " + pageView.getPage().getName());
			}
		}

	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel panel) {
		this.rightPanel = panel;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public ArrayList<PageView> getPageViews() {
		return pageViews;
	}

	public void setPageViews(ArrayList<PageView> pageViews) {
		this.pageViews = pageViews;
	}

	public ArrayList<PageSelectionView> getPageSelectionViews() {
		return pageSelectionViews;
	}

	public void setPageSelectionViews(ArrayList<PageSelectionView> pageSelectionViews) {
		this.pageSelectionViews = pageSelectionViews;
	}

	public PageSelectionView getFocusPageSelectionView() {
		return focusPageSelectionView;
	}

	public void setFocusPageSelectionView(PageSelectionView focusPageSelectionView) {

		if (focusPageSelectionView == null)
			return;

		for (PageSelectionView pageSelectionView : pageSelectionViews) {
			pageSelectionView.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
//			pageSelectionView.setBorder(BorderFactory.createEmptyBorder(10, 4, 10, 20));
		}
		this.focusPageSelectionView = focusPageSelectionView;
		focusPageSelectionView.setBorder(BorderFactory.createDashedBorder(Color.RED, 1.6f, 1.6f, 1.6f, true));
	}

	public PageView getFocusPageView() {
		return focusPageView;
	}

	public void setFocusPageView(PageView focusPageView) {
		if (focusPageView == null)
			return;
		this.focusPageView = focusPageView;

	}
}
