package state;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import graf.elements.SlotDevice;
import gui.PageView;

public class DeleteSlotState extends State {

	public DeleteSlotState(PageView page) {
		mediator = page;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ArrayList<SlotDevice> slotDevices = mediator.getPage().getSelectedSlots();
		if (slotDevices.isEmpty()) {
			return;
		}
		mediator.getPage().getSlotDevices().removeAll(slotDevices);
		mediator.getPage().getSelectedSlots().clear();
		mediator.repaint();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
