package command;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import actions.ActionType;
import graf.elements.SlotDevice;
import model.workspace.Page;

public class RotateCommand extends AbstractCommand {

	private Page page;
	private SlotDevice selectedSlot;
	private ArrayList<SlotDevice> slots = new ArrayList<>();
	private double oldAngle;
	private double newAngle;

	public RotateCommand(Page page, SlotDevice selSlot, ArrayList<SlotDevice> slots, double oldAngle, double newAngle) {
		super();
		this.page = page;
		for (SlotDevice slo : page.getSlotDevices())
			if (slo.equals(selSlot)) {
				this.selectedSlot = slo;
				break;
			}

		for (SlotDevice sl : slots) {
			for (SlotDevice sll : page.getSlotDevices()) {
				if (sl.equals(sll)) {
					this.slots.add(sll);
				}
			}
		}
		this.oldAngle = oldAngle;
		this.newAngle = newAngle;
	}

	@Override
	public void doCommand() {

		Point figureCenter = new Point(selectedSlot.getPositionOFSlot().x + selectedSlot.getDimension().width / 2,
				selectedSlot.getPositionOFSlot().y + selectedSlot.getDimension().height / 2);
		AffineTransform af = AffineTransform.getRotateInstance(newAngle, figureCenter.x, figureCenter.y);
		selectedSlot.setAngleOFSlot(newAngle);
		selectedSlot.getSlotPainter().setShape(af.createTransformedShape(selectedSlot.getSlotPainter().getOldShape()));

		for (SlotDevice slotdevice : slots) {
			Point slotCenter = new Point(slotdevice.getPositionOFSlot().x + slotdevice.getDimension().width / 2,
					slotdevice.getPositionOFSlot().y + slotdevice.getDimension().height / 2);
			af = AffineTransform.getRotateInstance(newAngle, slotCenter.x, slotCenter.y);
			slotdevice.setAngleOFSlot(newAngle);
			slotdevice.getSlotPainter().setShape(af.createTransformedShape(slotdevice.getSlotPainter().getOldShape()));

		}
		page.notifyObserver(null, ActionType.DRAW);
	}

	@Override
	public void undoCommand() {
		Point figureCenter = new Point(selectedSlot.getPositionOFSlot().x + selectedSlot.getDimension().width / 2,
				selectedSlot.getPositionOFSlot().y + selectedSlot.getDimension().height / 2);
		AffineTransform af = AffineTransform.getRotateInstance(oldAngle, figureCenter.x, figureCenter.y);
		selectedSlot.setAngleOFSlot(oldAngle);
		selectedSlot.getSlotPainter().setShape(af.createTransformedShape(selectedSlot.getSlotPainter().getOldShape()));

		for (SlotDevice slotDevice : slots) {
			Point slotCenter = new Point(slotDevice.getPositionOFSlot().x + slotDevice.getDimension().width / 2,
					slotDevice.getPositionOFSlot().y + slotDevice.getDimension().height / 2);
			af = AffineTransform.getRotateInstance(oldAngle, slotCenter.x, slotCenter.y);
			slotDevice.setAngleOFSlot(oldAngle);
			slotDevice.getSlotPainter().setShape(af.createTransformedShape(slotDevice.getSlotPainter().getOldShape()));

		}

		page.notifyObserver(null, ActionType.DRAW);
	}

}
