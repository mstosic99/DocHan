package painters;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import graf.elements.SlotElement;
import graf.elements.TriangleElement;

public class TrainglePainter extends DevicePainter {

	private TriangleElement traingleElement;

	public TrainglePainter(SlotElement slotElement) {
		super(slotElement);
		traingleElement = (TriangleElement) slotElement;
		reDraw();

	}

	public void reDraw() {
		shape = new GeneralPath();

		((GeneralPath) shape).moveTo(traingleElement.getPositionOFSlot().getX(),
				traingleElement.getPositionOFSlot().getY() + traingleElement.getDimension().height);
		((GeneralPath) shape).lineTo(traingleElement.getPositionOFSlot().getX() + traingleElement.getDimension().width,
				traingleElement.getPositionOFSlot().getY() + traingleElement.getDimension().height);
		((GeneralPath) shape).lineTo(traingleElement.getPositionOFSlot().getX() + traingleElement.getDimension().width / 2,
				traingleElement.getPositionOFSlot().getY());
		((GeneralPath) shape).closePath();

		oldShape = shape;

		Point figureCenter = new Point();
		AffineTransform af = AffineTransform.getRotateInstance(traingleElement.getAngleOFSlot(), figureCenter.getX(),
				figureCenter.getY());
		shape = af.createTransformedShape(shape);
	}

}
