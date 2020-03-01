package painters;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import graf.elements.RectangleElement;
import graf.elements.SlotElement;

public class RectanglePainter extends DevicePainter {

	private RectangleElement rectangleElement;

	public RectanglePainter(SlotElement slotElement) {
		super(slotElement);
		rectangleElement = (RectangleElement) slotElement;
		reDraw();

	}

	public void reDraw() {
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(rectangleElement.getPositionOFSlot().getX(), rectangleElement.getPositionOFSlot().getY());
		((GeneralPath) shape).lineTo(rectangleElement.getPositionOFSlot().getX() + rectangleElement.getDimension().width,
				rectangleElement.getPositionOFSlot().getY());
		((GeneralPath) shape).lineTo(rectangleElement.getPositionOFSlot().getX() + rectangleElement.getDimension().width,
				rectangleElement.getPositionOFSlot().getY() + rectangleElement.getDimension().height);
		((GeneralPath) shape).lineTo(rectangleElement.getPositionOFSlot().getX(),
				rectangleElement.getPositionOFSlot().getY() + rectangleElement.getDimension().height);
		((GeneralPath) shape).closePath();
		oldShape = shape;

		Point figureCenter = new Point();
		AffineTransform af = AffineTransform.getRotateInstance(rectangleElement.getAngleOFSlot(), figureCenter.getX(),
				figureCenter.getY());
		shape = af.createTransformedShape(shape);
	}

}
