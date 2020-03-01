package model.workspace;

import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import actions.ActionType;
import gui.MainFrame;
import gui.WorkspaceTreeCellRenderer;
import observer.IPublisher;
import observer.ISubscriber;

@SuppressWarnings("serial")
public class Project extends DefaultMutableTreeNode implements MyTreeNode, IPublisher, Serializable {

	private String name;
	private Workspace parent;
	private ArrayList<Document> documents;
	private transient ArrayList<ISubscriber> subscribers;
	private File projectFile;
	private transient boolean changed;
	private int documentNum;

	public Project(MyTreeNode parent) {
		super();
		documentNum = 0;
		this.parent = (Workspace) parent;
		documents = new ArrayList<>();
		subscribers = new ArrayList<>();
		projectFile = null;
		changed = true;
	}

	public Project(String name, Workspace parent) {
		super();
		this.name = name;
		this.parent = parent;
		documents = new ArrayList<>();
	}

	public void generateView() {
		for (Document document : documents) {
//			document.getSubscribers().clear();
			if (document.getParent() == this) {
				this.notifySubscribers(ActionType.NEW_DOCUMENT, document); // TODO generating only one tab per document
				document.generatePageViews();
			}
		}
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void addDocument(Document document) {

		documentNum++;
		boolean isNew = false;
		if (document.getProxyParents().isEmpty())
			isNew = true;
		
		document.setParent(this);
		this.setChanged(true);

		documents.add(document);
		if (document.getName() == null) {
			document.setName("Document " + documentNum);
		}
		if (isNew)
			notifySubscribers(ActionType.NEW_DOCUMENT, document);
	}

	public String toString() {
		return name;
	}

	public void removeDocument(Document document) {

		Workspace ws = (Workspace) getParent();
		ws.setChanged(true);
		this.setChanged(true);

		documents.remove(document);
		notifySubscribers(ActionType.REMOVE_DOCUMENT, document);
	}

	public Document getDocument(int index) {
		return documents.get(index);
	}

	public void setName(String name) {
		this.name = name;
		notifySubscribers(ActionType.RENAME_PROJECT, this);

	}

	public String getName() {
		return name;
	}

	/*
	 * @SuppressWarnings("unchecked") public Enumeration<Document> children() {
	 * return (Enumeration<Document>) documents; }
	 */

	@SuppressWarnings("unchecked")
	public Enumeration children1() {
		return (Enumeration) documents;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {

		return getDocument(childIndex);
	}

	@Override
	public int getChildCount() {
		return documents.size();
	}

	@Override
	public TreeNode getParent() {
		return (Workspace) parent;
	}

	@Override
	public int getIndex(TreeNode node) {

		return documents.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void addSubscriber(ISubscriber subscriber) {
		if (subscriber == null)
			return;
		if (this.subscribers == null)
			this.subscribers = new ArrayList<>();
		if (this.subscribers.contains(subscriber))
			return;
		this.subscribers.add(subscriber);
	}

	@Override
	public void removeSubscriber(ISubscriber subscriber) {
		if (subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
			return;
		subscribers.remove(subscriber);
	}

	@Override
	public void notifySubscribers(ActionType at, Object notification) {
		if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
			return;
		for (ISubscriber s : subscribers) {
			s.update(at, notification);
		}
	}

	@Override
	public void addChildNode(MyTreeNode node) {
		this.addDocument((Document) node);
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	public void setChanged(boolean changed) {
		if (this.changed != changed) {
			this.changed = changed;
			Workspace ws = (Workspace) this.getParent();
			ws.setChanged(true);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		}
	}

	public boolean isChanged() {
		return changed;
	}

	public void updatePerformed(EventObject e) {
		setChanged(true);
	}

	public void paste() {
		Transferable clipBoardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		Document document = null;

		if (clipBoardContent != null
				&& clipBoardContent.isDataFlavorSupported(DocumentElementSelection.elementFlavour)) {
			try {

				document = ((Document) clipBoardContent.getTransferData(DocumentElementSelection.elementFlavour))
						.clone();
				this.addDocument(document);
				document.generatePageViews();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}