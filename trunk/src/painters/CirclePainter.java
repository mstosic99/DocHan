package painters;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import graf.elements.CircleElement;
import graf.elements.SlotElement;

public class CirclePainter extends DevicePainter {

	private CircleElement circEl;

	public CirclePainter(SlotElement slotElement) {
		super(slotElement);
		circEl = (CircleElement) slotElement;
		reDraw();

	}

	@Override
	public void reDraw() {
		shape = new GeneralPath();

		((GeneralPath) shape).moveTo(circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth() / 2,
				circEl.getPositionOFSlot().getY());

		((GeneralPath) shape).quadTo(circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth(),
				circEl.getPositionOFSlot().getY(), circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth(),
				circEl.getPositionOFSlot().getY() + circEl.getDimension().getHeight() / 2);

		((GeneralPath) shape).quadTo(circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth(),
				circEl.getPositionOFSlot().getY() + circEl.getDimension().getHeight(),
				circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth() / 2,
				circEl.getPositionOFSlot().getY() + circEl.getDimension().getHeight());

		((GeneralPath) shape).quadTo(circEl.getPositionOFSlot().getX(),
				circEl.getPositionOFSlot().getY() + circEl.getDimension().getHeight(),
				circEl.getPositionOFSlot().getX(),
				circEl.getPositionOFSlot().getY() + circEl.getDimension().getHeight() / 2);

		((GeneralPath) shape).quadTo(circEl.getPositionOFSlot().getX(), circEl.getPositionOFSlot().getY(),
				circEl.getPositionOFSlot().getX() + circEl.getDimension().getWidth() / 2,
				circEl.getPositionOFSlot().getY());
		oldShape = shape;

		Point figureCenter = new Point();
		AffineTransform af = AffineTransform.getRotateInstance(circEl.getAngleOFSlot(), figureCenter.x, figureCenter.y);
		shape = af.createTransformedShape(shape);
	}

}
