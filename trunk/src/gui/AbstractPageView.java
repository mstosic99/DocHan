package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.workspace.Page;
import observer.ISubscriber;

@SuppressWarnings("serial")
public abstract class AbstractPageView extends JPanel implements ISubscriber {
	
	protected Page page;
	protected JLabel lbl;
	protected DocumentView documentView;
	
	public AbstractPageView(DocumentView documentView, Page page) {
		super();
		
		lbl = new JLabel();
		setLayout(new BorderLayout());
		this.page = page;
		this.documentView = documentView;
		this.page.addSubscriber(this);
		
		
		setBackground(Color.WHITE);
		revalidate();
		repaint();
	}
	
	public DocumentView getDocumentView() {
		return documentView;
	}

	public void setDocumentView(DocumentView docView) {
		this.documentView = docView;
	}
	
	public void setPage(Page page) {
		this.page = page;
		this.setName(page.getName());
		lbl.setText(page.getName());

	}

	public Page getPage() {
		return page;
	}
	
	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}
	
}
