package graf.elements;

import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

import painters.SlotPainter;
import painters.TrainglePainter;

public class TriangleElement extends SlotDevice {

	// konstruktor koji je nasledjen
	public TriangleElement(Paint paint, Stroke stroke, String name, Dimension dimension, Point position, double angle) {
		super(paint, stroke, name, dimension, position, angle);
		slotPainter = new TrainglePainter(this);
	}

	// konstruktor za iscrtavanje novog
	public TriangleElement(TriangleElement triangle) {
		super(triangle);
		slotPainter = new TrainglePainter(this);
	}

	// pravimo klon elementa, tako sto napravim nov
	public SlotElement clone() {
		return new TriangleElement(this);
	}
}
