package painters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import graf.elements.SlotElement;

public abstract class SlotPainter implements Serializable {

	protected Shape shape;
	protected Shape oldShape;

	public SlotPainter(SlotElement slotElement) {
	}

	public abstract void paint(Graphics2D g, SlotElement slotElement);

	public abstract boolean iselementAt(Point pos);

	public abstract void reDraw();

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Shape getOldShape() {
		return oldShape;
	}

	public void setOldShape(Shape oldShape) {
		this.oldShape = oldShape;
	}

}
