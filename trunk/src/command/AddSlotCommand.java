package command;

import java.awt.geom.Point2D;

import graf.elements.SlotDevice;

import model.workspace.Page;

public class AddSlotCommand extends AbstractCommand {

	Point2D lastPosition;
	int deviceType;
	private Page page;
	private SlotDevice slotDevice = null;

	public AddSlotCommand(Page page, SlotDevice slotDevice) {
		super();
		this.page = page;
		this.slotDevice = slotDevice;
	}

	@Override
	public void doCommand() {
		page.getSelectedSlots().clear();
		page.setSelectedSlot(null);
		page.addSlotDevice(slotDevice);

		/*
		 * if (device==null) if (deviceType==DiagramView.CIRCLE){
		 * device=CircleElement.createDefault(lastPosition,model.getElementsCount());
		 * }else if (deviceType==DiagramView.RECTANGLE){
		 * device=RectangleElement.createDefault(lastPosition,model.getElementsCount());
		 * }
		 * 
		 * selectionModel.removeAllFromSelectionList(); model.addDiagramElement(device);
		 * selectionModel.addToSelectionList(device);
		 */

	}

	@Override
	public void undoCommand() {
		page.getSelectedSlots().clear();
		page.setSelectedSlot(null);
		page.removeDevice(slotDevice);

	}

}
