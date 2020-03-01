package state;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Enumeration;

import command.RotateCommand;
import graf.elements.SlotDevice;
import gui.PageView;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class RotateState extends State {

	private PageView page;

	private SlotDevice selectedSlot;
	private double oldAngle;
	private double newAngle;

	public RotateState(PageView p) {
		super();
		mediator = p;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
		 * SlotDevice selectedSlot = null; for (SlotDevice slotDevice :
		 * page.getSlotDevices()) { if
		 * (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {
		 * page.setSelectedSlot(slotDevice); return; } } page.setSelectedSlot(null);
		 */

		selectedSlot = null;
		oldAngle = 0.0;

		for (SlotDevice slotDevice : mediator.getPage().getSlotDevices()) {
			if (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {
				selectedSlot = slotDevice;
				mediator.getPage().setSelectedSlot(slotDevice);

				if (!mediator.getPage().getSelectedSlots().contains(slotDevice)) {
					mediator.getPage().getSelectedSlots().add(slotDevice);
					oldAngle = newAngle = slotDevice.getAngleOFSlot();

				}
				return;
			}
		}
		mediator.getPage().setSelectedSlot(null);
		mediator.getPage().getSelectedSlots().clear();

	}

	private double calculateAngle(Point slotCenter, Point mousePoint) {

		double solution = 0.0;
		// funkcija atan2 je fja arcttg i vraca ugao u intervalu od 0 do 2PI
		// uzmemo koordinatu misa- koordinatu centra
		solution = Math.atan2(mousePoint.y - slotCenter.y, mousePoint.x - slotCenter.x);
		return solution;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/*
		 * Point pos = e.getPoint(); if (page.getSelectedSlot() != null) { Point
		 * figureCenter = new Point( page.getSelectedSlot().getPosition().x +
		 * page.getSelectedSlot().getDimension().width / 2,
		 * page.getSelectedSlot().getPosition().y +
		 * page.getSelectedSlot().getDimension().height / 2);
		 * 
		 * Shape slotShape = page.getSelectedSlot().getSlotPainter().getOldShape();
		 * double angle = calculateAngle(figureCenter, pos);
		 * 
		 * AffineTransform af = AffineTransform.getRotateInstance(angle, figureCenter.x,
		 * figureCenter.y);
		 * 
		 * page.getSelectedSlot().setAngle(angle);
		 * page.getSelectedSlot().getSlotPainter().setShape(af.createTransformedShape(
		 * slotShape));
		 * 
		 * }
		 */
		Point pos = e.getPoint();
		if (mediator.getPage().getSelectedSlot() != null) {
			Point figureCenter = new Point(
					mediator.getPage().getSelectedSlot().getPositionOFSlot().x
							+ mediator.getPage().getSelectedSlot().getDimension().width / 2,
					mediator.getPage().getSelectedSlot().getPositionOFSlot().y
							+ mediator.getPage().getSelectedSlot().getDimension().height / 2);

			Shape slotShape = mediator.getPage().getSelectedSlot().getSlotPainter().getOldShape();
			newAngle = calculateAngle(figureCenter, pos);
			AffineTransform af = AffineTransform.getRotateInstance(newAngle, figureCenter.x, figureCenter.y);

			mediator.getPage().getSelectedSlot().setAngleOFSlot(newAngle);
			mediator.getPage().getSelectedSlot().getSlotPainter().setShape(af.createTransformedShape(slotShape));

			for (SlotDevice slot : mediator.getPage().getSelectedSlots()) {
				if (slot != mediator.getPage().getSelectedSlot()) {
					Point slotCenter = new Point(slot.getPositionOFSlot().x + slot.getDimension().width / 2,
							slot.getPositionOFSlot().y + slot.getDimension().height / 2);
					Shape slotShape2 = slot.getSlotPainter().getOldShape();

					af = AffineTransform.getRotateInstance(newAngle, slotCenter.x, slotCenter.y);

					slot.setAngleOFSlot(newAngle);
					slot.getSlotPainter().setShape(af.createTransformedShape(slotShape2));

				}

			}
			Document d = (Document) mediator.getPage().getParent();
			Project pp = (Project) d.getParent();
			pp.updatePerformed(null);
			Workspace w = (Workspace) pp.getParent();
			w.updatePerformed(null);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (selectedSlot == null) {
			return;
		}
		if (oldAngle == newAngle) {
			return;
		}
		mediator.getPage().getCommandManager().addCommand(new RotateCommand(mediator.getPage(), selectedSlot,
				mediator.getPage().getSelectedSlots(), oldAngle, newAngle));
	}

}
