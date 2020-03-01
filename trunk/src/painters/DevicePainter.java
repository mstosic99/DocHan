package painters;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import graf.elements.SlotElement;

/**
 * DevicePainter je zadužen za crtanje uredjaja kao i za detekciju pogotka za
 * sta koristi Shape.
 */
public class DevicePainter extends SlotPainter implements Serializable {

	public DevicePainter(SlotElement slotElement) {
		super(slotElement);
	}

	@Override
	public boolean iselementAt(Point pos) {
		return getShape().contains(pos);
	}

	@Override
	public void paint(Graphics2D g, SlotElement slotElement) {

		g.setPaint(Color.BLACK);
		

		g.setStroke(slotElement.getStroke());
		g.draw(getShape());
		g.setPaint(slotElement.getPaint());

		g.fill(getShape());

	}

	public void reDraw() {
		// TODO
	}

}
