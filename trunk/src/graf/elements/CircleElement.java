package graf.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import java.awt.BasicStroke;
import painters.CirclePainter;

public class CircleElement extends SlotDevice {

	// konstruktor koji je nasledjen
	public CircleElement(Paint paint, Stroke stroke, String name, Dimension dimension, Point position, double angle) {
		super(paint, stroke, name, dimension, position, angle);
		slotPainter = new CirclePainter(this);
	}

//konstruktor za iscrtavanje novog
	public CircleElement(CircleElement circle) {
		super(circle);
		slotPainter = new CirclePainter(this);
	}

	// pravimo klon elementa, tako sto napravim nov
	public SlotElement clone() {
		return new CircleElement(this);

	}
}
