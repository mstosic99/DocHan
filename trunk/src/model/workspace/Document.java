package model.workspace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import actions.ActionType;
import observer.IPublisher;
import observer.ISubscriber;

@SuppressWarnings("serial")
public class Document extends DefaultMutableTreeNode implements MyTreeNode, IPublisher, Serializable {

	private String name;
	private Project parent;
	private ArrayList<Page> pages;
	private transient ArrayList<ISubscriber> subscribers;
	private int pageNum;
	private ArrayList<Project> proxyParents = new ArrayList<>();

	private MutableTreeNode parent2;

	public Document(MyTreeNode parent) {
		super();
		this.parent = (Project) parent;
		pages = new ArrayList<Page>();
	}

	public Document(String name, Project parent) {
		super();
		pageNum = 0;
		this.name = name;
		this.parent = parent;
		pages = new ArrayList<Page>();
		subscribers = new ArrayList<>();
	}

	// konstruktor za klon

	public Document(Document d) {
		super();
		this.name = d.getName();
		pages = new ArrayList<>();
		
		for (Page page : d.getPages()) {
			Page p = page.clone(page, this);
			this.pages.add(p);
			if (children == null) {
				children = new Vector<>();
			}
			children.add(p);
			setParent2(this); // ovde je bio problem
		}
	}

	public Document clone() {
		return new Document(this);
	}

	public void setParent2(MutableTreeNode newParent) {
		parent2 = newParent;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public String toString() {
		return name;
	}

	public void addPage(Page page) {

		pageNum++;
		Project project = (Project) getParent();
		Workspace ws = (Workspace) project.getParent();
		project.setChanged(true);
		ws.setChanged(true);

		pages.add(page);
		page.setParent(this);
		
		if (page.getName() == null) {
			page.setName("Page " + pageNum);
		}

		notifySubscribers(ActionType.NEW_PAGE, page);
	}

	public void removePage(Page p) {

		Project project = (Project) getParent();
		Workspace ws = (Workspace) project.getParent();
		project.setChanged(true);
		ws.setChanged(true);

		pages.remove(p);
		notifySubscribers(ActionType.REMOVE_PAGE, p);
	}

	public void setName(String name) {
		this.name = name;
		notifySubscribers(ActionType.RENAME_DOCUMENT, this);

	}

	public String getName() {
		return name;
	}

	/*
	 * @SuppressWarnings("unchecked") public Enumeration<Page> children() { return
	 * (Enumeration<Page>) pages; }
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<TreeNode> children() {
		return (Enumeration<TreeNode>) pages;
	}

	public Page getPage(int index) {
		return pages.get(index);
	}

	@Override
	public TreeNode getChildAt(int childIndex) {

		return getPage(childIndex);
	}

	@Override
	public int getChildCount() {

		return pages.size();
	}

	@Override
	public Project getParent() {
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		return pages.indexOf(node);
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

	public ArrayList<ISubscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(ArrayList<ISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public void addChildNode(MyTreeNode node) {
		this.addPage((Page) node);
	}

	public void generatePageViews() {

		for (Page page : pages) {
			notifySubscribers(ActionType.NEW_PAGE, page);
		}
	}

	public ArrayList<Project> getProxyParents() {
		return proxyParents;
	}

	public void addProxyParent(Project pp) {
		proxyParents.add(pp);
	}

}
