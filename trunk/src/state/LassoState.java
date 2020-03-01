package state;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import gui.PageView;

public class LassoState extends State {

	Rectangle2D rect = new Rectangle2D.Double();

	public LassoState(PageView page) {
		super();
		mediator = page;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		Point mousePos = e.getPoint();

		double width = mousePos.getX() - mediator.getLastPosition().getX();
		double height = mousePos.getY() - mediator.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(mousePos.getX(), mousePos.getY(), Math.abs(width), Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(mousePos.getX(), mediator.getLastPosition().getY(), Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(mediator.getLastPosition().getX(), mousePos.getY(), Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(mediator.getLastPosition().getX(), mediator.getLastPosition().getY(), Math.abs(width),
					Math.abs(height));
		}

		mediator.setSelectionRectangle(rect);
		mediator.getPage().selectElements(rect);
		mediator.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mediator.setSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
		mediator.repaint();
		mediator.startSelectState();
	}

}
