package painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import actions.ResizeEnum;
import graf.elements.SlotDevice;

public class PainterHandle {
	private SlotDevice selectedSlot = null;
	private Graphics2D g2d;
	private static final int handleSize = 7;
	private Rectangle r;

	public PainterHandle() {
	}

	public static int getHandlesize() {
		return handleSize;
	}

	public SlotDevice getSlotDevice() {
		return selectedSlot;
	}

	public void setSlotDevice(SlotDevice selectedSlot) {
		this.selectedSlot = selectedSlot;
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size, ResizeEnum handlePosition) {
		double x = 0, y = 0;

		// Doreivanje y koordinate

		// Ako su gornji hendlovi
		if (handlePosition == ResizeEnum.NORTHWEST || handlePosition == ResizeEnum.NORTH
				|| handlePosition == ResizeEnum.NORTHEAST) {
			y = topLeft.getY();
		}
		// Ako su centralni po y osi
		if (handlePosition == ResizeEnum.EAST || handlePosition == ResizeEnum.WEST) {
			y = topLeft.getY() + size.getHeight() / 2;
		}
		// Ako su donji
		if (handlePosition == ResizeEnum.SOUTHWEST || handlePosition == ResizeEnum.SOUTH
				|| handlePosition == ResizeEnum.SOUTHEAST) {
			y = topLeft.getY() + size.getHeight();
		}

		// Odreivanje x koordinate

		// Ako su levi
		if (handlePosition == ResizeEnum.NORTHWEST || handlePosition == ResizeEnum.WEST
				|| handlePosition == ResizeEnum.SOUTHWEST) {
			x = topLeft.getX();
		}
		// ako su centralni po x osi
		if (handlePosition == ResizeEnum.NORTH || handlePosition == ResizeEnum.SOUTH) {
			x = topLeft.getX() + size.getWidth() / 2;
		}
		// ako su desni
		if (handlePosition == ResizeEnum.NORTHEAST || handlePosition == ResizeEnum.EAST
				|| handlePosition == ResizeEnum.SOUTHEAST) {
			x = topLeft.getX() + size.getWidth();
		}

		return new Point2D.Double(x, y);
	}

	public Cursor setMouseCursor(Point2D point) {

		ResizeEnum handle = getHandleForPoint(selectedSlot, point);
		Cursor cursor = null;

		if (handle != null) {

			switch (handle) {
			case NORTH:
				cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
				break;
			case SOUTH:
				cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
				break;
			case EAST:
				cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
				break;
			case WEST:
				cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
				break;
			case SOUTHEAST:
				cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case NORTHWEST:
				cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case SOUTHWEST:
				cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case NORTHEAST:
				cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				break;
			}

		}
		return cursor;
	}

	public ResizeEnum getHandleForPoint(SlotDevice element, Point2D point) {
		for (ResizeEnum h : ResizeEnum.values()) {
			if (isPointInHandle(element, point, h)) {
				return h;
			}
		}
		return null;
	}

	private boolean isPointInHandle(SlotDevice element, Point2D point, ResizeEnum handle) {
		if (element instanceof SlotDevice) {
			SlotDevice device = (SlotDevice) element;
			Point2D handleCenter = getHandlePoint(device.getPositionOFSlot(), device.getDimension(), handle);
			return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize / 2)
					&& (Math.abs(point.getY() - handleCenter.getY()) <= (double) handleSize / 2));
		} else
			return false;
	}

	public void paintSlotHandles() {
		if (g2d == null) {
			return;
		}
		g2d.setStroke(
				new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f, new float[] { 3f, 6f }, 0));
		g2d.setPaint(Color.RED);
		g2d.setBackground(Color.BLACK);
		g2d.drawRect((int) selectedSlot.getPositionOFSlot().getX(), (int) selectedSlot.getPositionOFSlot().getY(),
				(int) selectedSlot.getDimension().getWidth(), (int) selectedSlot.getDimension().getHeight());
		for (ResizeEnum h : ResizeEnum.values()) {
			paintSelectionHandle(g2d, getHandlePoint(selectedSlot.getPositionOFSlot(), selectedSlot.getDimension(), h));

		}

	}

	private void paintSelectionHandle(Graphics2D g2, Point2D position) {
		int size = 7;
		g2.fill(new Rectangle((int) position.getX() - size / 2, (int) position.getY() - size / 2, size, size));
	}
}
