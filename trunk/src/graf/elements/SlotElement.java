package graf.elements;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import actions.save.SerializableStrokeAdapter;
import painters.SlotPainter;

public abstract class SlotElement implements Serializable {

	protected Paint paint;
	protected SerializableStrokeAdapter stroke;

	protected String name;
	protected String description;

	protected SlotPainter slotPainter;

	public abstract SlotElement clone();

	public SlotElement(Paint paint, Stroke stroke, String name) {
		super();
		this.paint = paint;
		setStroke(stroke);
		this.name = name;
	}

//konstruktor kao u paketu dsw state
	public SlotElement(SlotElement sl) {
		super();
		this.paint = sl.getPaint();
		this.stroke = sl.getStroke();
		this.name = sl.getName();
		this.slotPainter = sl.getSlotPainter();
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public SerializableStrokeAdapter getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SlotPainter getSlotPainter() {
		return slotPainter;
	}

	public void setSlotPainter(SlotPainter slotPainter) {
		this.slotPainter = slotPainter;
	}

}
