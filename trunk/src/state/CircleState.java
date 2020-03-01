package state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddSlotCommand;
import graf.elements.CircleElement;
import gui.PageView;
import model.workspace.Page;

public class CircleState extends State {

	private PageView mediator;

	public CircleState(PageView md) {
		mediator = md;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point pos = e.getPoint();
			Paint fill = new Color(200, 200, 200);

			CircleElement circleElem = new CircleElement(fill, new BasicStroke(2f),
					"circle" + mediator.getPage().getSlotDevices().size(), new Dimension(60, 60), pos, 0.0);
			// mediator.getPage().addSlotDevice(circleElem);
			mediator.getPage().getCommandManager().addCommand(new AddSlotCommand(mediator.getPage(), circleElem));
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
