package command;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import actions.ActionType;
import graf.elements.SlotDevice;
import model.workspace.Page;

public class ResizeCommand extends AbstractCommand {

	private Page page;
	private SlotDevice selectSlot;
	private ArrayList<SlotDevice> slotDeviceList = new ArrayList<>();
	private Dimension oldDimension;
	private Dimension newDimension;
	private Point oldPoint;
	private Point newPoint;
	private ArrayList<Dimension> oldSlotDimensionList = new ArrayList<>();
	private ArrayList<Point> oldSlotPoints = new ArrayList<>();

	public ResizeCommand(Page page, SlotDevice selectSlot, ArrayList<SlotDevice> slots, Dimension oldDim,
			Point oldPoint, ArrayList<Dimension> osd, ArrayList<Point> osp, Dimension newDim, Point newPoint) {
		super();

		this.page = page;

		for (SlotDevice sDevice1 : page.getSlotDevices()) {
			if (sDevice1.equals(selectSlot)) {
				this.selectSlot = sDevice1;
				break;
			}
		}

		for (SlotDevice sDevice2 : slots) {
			for (SlotDevice sDevice3 : page.getSlotDevices()) {
				if (sDevice3.equals(sDevice2)) {
					this.slotDeviceList.add(sDevice3);
					break;
				}
			}
		}
		this.oldPoint = oldPoint;
		this.oldDimension = oldDim;

		for (Point p : osp) {
			this.oldSlotPoints.add(p);
		}
		this.newPoint = newPoint;
		for (Dimension dim : osd) {
			this.oldSlotDimensionList.add(dim);
		}

		this.newDimension = newDim;

	}

	@Override
	public void doCommand() {

		selectSlot.setPositionOFSlot(newPoint);
		selectSlot.setDimension(newDimension);
		selectSlot.getSlotPainter().reDraw();

		for (int i = 0; i < slotDeviceList.size(); i++) {
			if (slotDeviceList.get(i) != selectSlot) {

				slotDeviceList.get(i).setPositionOFSlot(new Point(newPoint.x - oldPoint.x + oldSlotPoints.get(i).x,
						newPoint.y - oldPoint.y + oldSlotPoints.get(i).y));

				slotDeviceList.get(i)
						.setDimension(new Dimension(
								newDimension.width - oldDimension.width + oldSlotDimensionList.get(i).width,
								newDimension.height - oldDimension.height + oldSlotDimensionList.get(i).height));

				slotDeviceList.get(i).getSlotPainter().reDraw();
			}
		}
		page.notifyObserver(null, ActionType.DRAW);
	}

	@Override
	public void undoCommand() {

		selectSlot.setPositionOFSlot(oldPoint);
		selectSlot.setDimension(oldDimension);
		selectSlot.getSlotPainter().reDraw();

		for (int i = 0; i < slotDeviceList.size(); i++) {

			if (slotDeviceList.get(i).equals(selectSlot))
				continue;

			slotDeviceList.get(i).setPositionOFSlot(oldSlotPoints.get(i));
			slotDeviceList.get(i).setDimension(oldSlotDimensionList.get(i));

			slotDeviceList.get(i).getSlotPainter().reDraw();
		}
		page.notifyObserver(null, ActionType.DRAW);
	}

}
