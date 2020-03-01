package model.workspace;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import actions.ActionType;
import command.CommandManager;
import graf.elements.SlotDevice;
import observer.IPublisher;
import observer.ISubscriber;
import state.StateManager;

@SuppressWarnings("serial")
public class Page extends DefaultMutableTreeNode implements MyTreeNode, IPublisher, Serializable, Cloneable {

	private String name;
	private Document parent;
	private transient ArrayList<ISubscriber> subscribers;

	private transient StateManager stateManager;
	private transient SlotDevice selectedSlot = null;
	private ArrayList<SlotDevice> slotDevices;
	private ArrayList<SlotDevice> selectedSlots = new ArrayList<SlotDevice>();
	
	private ArrayList<SlotDevice> circles;
	private ArrayList<SlotDevice> triangles;
	private ArrayList<SlotDevice> rectangles;

	private transient CommandManager commandManager;

	public Page(MyTreeNode parent) {
		super();
		this.parent = (Document) parent;
		subscribers = new ArrayList<ISubscriber>();
		slotDevices = new ArrayList<SlotDevice>();
		circles = new ArrayList<>();
		triangles = new ArrayList<>();
		rectangles = new ArrayList<>();
	}

	

	public Page(String name, Document parent) {
		super();
		subscribers = new ArrayList<ISubscriber>();
		slotDevices = new ArrayList<SlotDevice>();
		
		circles = new ArrayList<>();
		triangles = new ArrayList<>();
		rectangles = new ArrayList<>();
		
		this.parent = parent;
		
		commandManager = new CommandManager();
		this.name = name;
	}

	public CommandManager getCommandManager() {
		if (commandManager == null)
			commandManager = new CommandManager();
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	public ArrayList<SlotDevice> getSelectedSlots() {
		return selectedSlots;
	}

	public void setSelectedSlots(ArrayList<SlotDevice> selectedSlots) {
		this.selectedSlots = selectedSlots;
	}

	public void setName(String name) {
		this.name = name;
		notifySubscribers(ActionType.RENAME_PAGE, this);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return (Document) parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public boolean isLeaf() {

		return true;
	}

	public Enumeration<TreeNode> children() {
		return null;
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
		return;
	}

	public void notifyObserver(Object event, ActionType e) {
		for (ISubscriber subscriber : subscribers) {
			subscriber.update(e, event);
		}
	}

	public void addSlotDevice(SlotDevice slot) {
		slotDevices.add(slot);
		
		if(slot.getName().startsWith("circle"))
			circles.add(slot);
		else if(slot.getName().startsWith("triangle")) 
			triangles.add(slot);
		else if (slot.getName().startsWith("rectangle"))
			rectangles.add(slot);
		
		notifyObserver(slot, ActionType.DRAW);

		Document doc = (Document) this.getParent();
		Project p = (Project) doc.getParent();
		p.setChanged(true);
		Workspace w = (Workspace) p.getParent();
		w.setChanged(true);
	}

	public void removeDevice(SlotDevice slot) {
		slotDevices.remove(slot);
		
		if(slot.getName().startsWith("circle"))
			circles.remove(slot);
		else if(slot.getName().startsWith("triangle")) 
			triangles.remove(slot);
		else if (slot.getName().startsWith("rectangle"))
			rectangles.remove(slot);
		
		notifyObserver(slot, ActionType.DRAW);

		Document doc = (Document) this.getParent();
		Project p = (Project) doc.getParent();
		p.setChanged(true);
		Workspace w = (Workspace) p.getParent();
		w.setChanged(true);

	}

	public ArrayList<ISubscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(ArrayList<ISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public SlotDevice getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(SlotDevice selectedSlot) {
		this.selectedSlot = selectedSlot;
	}

	public ArrayList<SlotDevice> getSlotDevices() {
		return slotDevices;
	}

	public void setSlotDevices(ArrayList<SlotDevice> slotDevices) {
		this.slotDevices = slotDevices;
	}

	public void selectElements(Rectangle2D rectangleForSelection) {

		for (SlotDevice device : slotDevices) {
			Rectangle2D rectangle = new Rectangle2D.Double(device.getPositionOFSlot().getX(),
					device.getPositionOFSlot().getY(), device.getDimension().getWidth(),
					device.getDimension().getHeight());

			if (rectangleForSelection.intersects(rectangle)) {
				if (!selectedSlots.contains(device))
					selectedSlots.add(device);
			} else if (selectedSlots.contains(device))
				selectedSlots.remove(device);
		}
	}

	public Page clone(Page page, Document document) {
		Page clone = new Page(page.getName(), document);
		for (SlotDevice slotDevice : page.slotDevices) {
			clone.getSlotDevices().add((SlotDevice) slotDevice.clone());
			document.notifySubscribers(ActionType.DRAW, clone);
		}
		return clone;
	}
	
	public ArrayList<SlotDevice> getRectangles() {
		return rectangles;
	}
	
	public ArrayList<SlotDevice> getTriangles() {
		return triangles;
	}
	
	public ArrayList<SlotDevice> getCircles() {
		return circles;
	}

}
