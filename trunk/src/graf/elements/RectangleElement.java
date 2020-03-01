package graf.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import java.awt.BasicStroke;
import painters.RectanglePainter;

public class RectangleElement extends SlotDevice {

	// konstruktor koji je nasledjen
	public RectangleElement(Paint paint, Stroke stroke, String name, Dimension dimension, Point position,
			double angle) {
		super(paint, stroke, name, dimension, position, angle);
		slotPainter = new RectanglePainter(this);

	}
	//konstruktor za iscrtavanje novog
	public RectangleElement(RectangleElement rectangleElement) {
		super(rectangleElement);
		slotPainter = new RectanglePainter(this);

	}

	public RectangleElement clone() {
		return new RectangleElement(this);
	}
}
