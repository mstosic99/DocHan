package state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddSlotCommand;
import graf.elements.RectangleElement;
import gui.PageView;
import model.workspace.Page;

public class RectangleState extends State {

	private PageView mediator;

	public RectangleState(PageView md) {
		mediator = md;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			Point position = e.getPoint();
			Paint fill = new Color(255, 255, 255);

			RectangleElement rectangleElement = new RectangleElement(fill, new BasicStroke(3f),
					"rectangle" + mediator.getPage().getSlotDevices().size(), new Dimension(60, 30), position, 0.0);
			mediator.getPage().getCommandManager().addCommand(new AddSlotCommand(mediator.getPage(), rectangleElement));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
