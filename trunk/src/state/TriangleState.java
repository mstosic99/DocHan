package state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddSlotCommand;
import graf.elements.*;
import gui.PageView;
import model.workspace.Page;

public class TriangleState extends State {

	public TriangleState(PageView md) {
		mediator = md;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point pos = e.getPoint();
			Paint fill = new Color(200, 200, 200);

			TriangleElement triangleElement = new TriangleElement(fill, new BasicStroke(3f),
					"triangle" + mediator.getPage().getSlotDevices().size(), new Dimension(70, 30), pos, 0.0);
			// mediator.getPage().addSlotDevice(triangleElement);
			mediator.getPage().getCommandManager().addCommand(new AddSlotCommand(mediator.getPage(), triangleElement));
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