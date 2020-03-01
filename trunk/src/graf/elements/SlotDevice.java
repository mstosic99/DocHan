package graf.elements;

import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class SlotDevice extends SlotElement {

	protected Point positionOFSlot;
	protected double angleOFSlot;
	protected Dimension dimension;

	public SlotDevice(Paint paint, Stroke stroke, String name, Dimension dimension, Point position, double angle) {
		super(paint, stroke, name);
		this.dimension = dimension;
		this.positionOFSlot = position;
		this.angleOFSlot = angle;
	}

	public SlotDevice(SlotDevice device) {
		super(device);
		this.dimension = device.getDimension();
		this.positionOFSlot = device.getPositionOFSlot();
		this.angleOFSlot = device.getAngleOFSlot();
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Point getPositionOFSlot() {
		return positionOFSlot;
	}

	public void setPositionOFSlot(Point position) {
		this.positionOFSlot = position;
	}

	public Double getAngleOFSlot() {
		return angleOFSlot;
	}

	public void setAngleOFSlot(Double angle) {
		this.angleOFSlot = angle;
	}

	@Override
	public SlotElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
