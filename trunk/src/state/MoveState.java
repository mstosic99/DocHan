package state;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import graf.elements.SlotDevice;
import gui.PageView;

public class MoveState extends State {

	private Point2D startPoint = null;

	public MoveState(PageView page) {
		mediator = page;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
