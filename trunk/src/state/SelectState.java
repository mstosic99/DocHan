package state;

import java.awt.Point;
import java.awt.event.MouseEvent;

import graf.elements.SlotDevice;
import gui.PageView;

public class SelectState extends State {

	public SelectState(PageView page) {
		super();
		mediator = page;

	}

	@Override
	public void mousePressed(MouseEvent e) {

		SlotDevice selectedSlot = null;
		for (SlotDevice slotDevice : mediator.getPage().getSlotDevices()) {
			if (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {

				mediator.getPage().setSelectedSlot(slotDevice);
				mediator.getPage().getSelectedSlots().add(slotDevice);
				// mediator.startMoveState();
				return;
			} else if (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {
				mediator.getPage().setSelectedSlot(slotDevice);
				mediator.getPage().getSelectedSlots().add(slotDevice);
				return;
			}

		}
		mediator.getPage().setSelectedSlot(null);
		mediator.getPage().getSelectedSlots().clear();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mediator.startLassoState();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
