package state;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import actions.ResizeEnum;
import command.ResizeCommand;
import graf.elements.SlotDevice;
import gui.PageView;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import model.workspace.Workspace;

public class ResizeState extends State {

	private Dimension oldDim = new Dimension(0, 0);
	private Point oldPoint = new Point(0, 0);
	private Dimension newDim = new Dimension(0, 0);
	private Point newPoint = new Point(0, 0);
	private SlotDevice selectedSlot = null;
	private ArrayList<Dimension> oldSlotDims = new ArrayList<>();
	private ArrayList<Point> oldSlotPos = new ArrayList<>();

	Page page;

	public ResizeState(PageView pageView) {
		super();
		mediator = pageView;
		this.page = pageView.getPage();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
		 * SlotDevice selectedSlot = null; for (SlotDevice slotDevice :
		 * page.getSlotDevices()) { if
		 * (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {
		 * page.setSelectedSlot(slotDevice); return; } } page.setSelectedSlot(null);
		 */

		selectedSlot = null;
		oldSlotDims.clear();
		oldSlotPos.clear();
		for (SlotDevice sld : mediator.getPage().getSelectedSlots()) {
			oldSlotDims.add(sld.getDimension());
			oldSlotPos.add(sld.getPositionOFSlot());
		}
		for (SlotDevice slotDevice : mediator.getPage().getSlotDevices()) {
			if (slotDevice.getSlotPainter().iselementAt(e.getPoint())) {
				selectedSlot = slotDevice;
				newPoint = oldPoint = selectedSlot.getPositionOFSlot();
				newDim = oldDim = selectedSlot.getDimension();
				mediator.getPage().setSelectedSlot(slotDevice);
				if (!mediator.getPage().getSelectedSlots().contains(slotDevice)) {
					mediator.getPage().getSelectedSlots().add(slotDevice);
					oldSlotDims.add(slotDevice.getDimension());
					oldSlotPos.add(slotDevice.getPositionOFSlot());
				}
				return;
			}
		}
		mediator.getPage().setSelectedSlot(null);
		mediator.getPage().getSelectedSlots().clear();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		SlotDevice selectedSlot = page.getSelectedSlot();
		if (resizeEnum == null)
			return;
		updateSelectedFigure(resizeEnum, e.getPoint(), selectedSlot);

	}

	public void updateSelectedFigure(ResizeEnum resizeEnum, Point p, SlotDevice selectSlot) {
		/*
		 * page.getSlotDevices().remove(selectSlot); Dimension oldDimension =
		 * selectSlot.getDimension(); Point oldPoint = selectSlot.getPosition();
		 */

		mediator.getPage().getSlotDevices().remove(selectSlot);

		switch (resizeEnum) {

		case NORTH:
			/*
			 * selectSlot.setPosition(new Point(oldPoint.x, p.y)); // ako povlacim od gore,
			 * x kordinata je ista, a y se // menja // konstrukrot dimension kao prvi
			 * parametar prima prvo sirinu pa onda visinu, // dakle posto "vucemo" ka gore
			 * // sirina ostaje ista a visina se menja tako sta uzmem kooridnatu staretacke
			 * // minus koordinata trenurno i plus stara dimenzija
			 * selectSlot.setDimension(new Dimension(oldDimension.width, oldPoint.y - p.y +
			 * oldDimension.height));
			 */

			newPoint = new Point(oldPoint.x, p.y);
			newDim = new Dimension(oldDim.width, (oldPoint.y - p.y) + oldDim.height);
			break;

		// kada hvatamo gornji levi ugao menjaju se i visina i sirina, ista formula koa
		// i gore dakle stara tacka minus nova +stara dimeznija
		case NORTHWEST:
			/*
			 * selectSlot.setPosition(p); selectSlot.setDimension( new Dimension(oldPoint.x
			 * - p.x + oldDimension.width, oldPoint.y - p.y + oldDimension.height));
			 */
			newPoint = p;
			newDim = new Dimension(oldPoint.x - p.x + oldDim.width, (oldPoint.y - p.y) + oldDim.height);
			break;
		case NORTHEAST:
			/*
			 * selectSlot.setPosition(new Point(oldPoint.x, p.y));
			 * selectSlot.setDimension(new Dimension(p.x - oldPoint.x, oldPoint.y - p.y +
			 * oldDimension.height));
			 */

			newPoint = new Point(oldPoint.x, p.y);
			newDim = new Dimension((p.x - oldPoint.x), (oldPoint.y - p.y) + oldDim.height);
			break;
		// kada vucemo desno, menja se sirina a visina ne
		case EAST:
			/*
			 * selectSlot.setPosition(oldPoint); selectSlot.setDimension(new Dimension(p.x -
			 * oldPoint.x, oldDimension.height));
			 */
			newPoint = oldPoint;
			newDim = new Dimension((p.x - oldPoint.x), oldDim.height);
			break;
		case WEST:
			/*
			 * selectSlot.setPosition(new Point(p.x, oldPoint.y));
			 * selectSlot.setDimension(new Dimension(oldPoint.x - p.x + oldDimension.width,
			 * oldDimension.height));
			 */
			newPoint = new Point(p.x, oldPoint.y);
			newDim = new Dimension(oldPoint.x - p.x + oldDim.width, oldDim.height);
			break;
		case SOUTHWEST:
			/*
			 * selectSlot.setPosition(new Point(p.x, oldPoint.y));
			 * selectSlot.setDimension(new Dimension(oldPoint.x - p.x + oldDimension.width,
			 * p.y - oldPoint.y));
			 */
			newPoint = new Point(p.x, oldPoint.y);
			newDim = new Dimension(oldPoint.x - p.x + oldDim.width, p.y - oldPoint.y);
			break;
		case SOUTH:
			/*
			 * selectSlot.setPosition(oldPoint); selectSlot.setDimension(new
			 * Dimension(oldDimension.width, p.y - oldPoint.y));
			 */
			newPoint = oldPoint;
			newDim = new Dimension(oldDim.width, p.y - oldPoint.y);
			break;
		case SOUTHEAST:
			/*
			 * selectSlot.setPosition(oldPoint); selectSlot.setDimension(new Dimension((p.x
			 * - oldPoint.x), p.y - oldPoint.y));
			 */
			newPoint = oldPoint;
			newDim = new Dimension((p.x - oldPoint.x), p.y - oldPoint.y);
			break;

		}
		if (selectedSlot == null)
			return;

		selectSlot.setDimension(newDim);
		selectSlot.setPositionOFSlot(newPoint);
		selectSlot.getSlotPainter().reDraw();

		mediator.getPage().getSlotDevices().add(selectSlot);

		int i2 = 0;
		for (SlotDevice slot : mediator.getPage().getSelectedSlots()) {
			if (slot != selectSlot) {
				slot.setDimension(new Dimension(oldSlotDims.get(i2).width + newDim.width - oldDim.width,
						oldSlotDims.get(i2).height + newDim.height - oldDim.height));
				slot.setPositionOFSlot(new Point(oldSlotPos.get(i2).x + newPoint.x - oldPoint.x,
						oldSlotPos.get(i2).y + newPoint.y - oldPoint.y));
				slot.getSlotPainter().reDraw();
			}
			i2++;
		}
		Document d = (Document) mediator.getPage().getParent();
		Project prj = (Project) d.getParent();
		prj.updatePerformed(null);
		Workspace w = (Workspace) prj.getParent();
		w.updatePerformed(null);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (selectedSlot == null) {
			return;
		}
		if (newPoint == oldPoint && newDim == oldDim) {
			return;
		}
		mediator.getPage().getCommandManager().addCommand(new ResizeCommand(mediator.getPage(), selectedSlot,
				mediator.getPage().getSelectedSlots(), oldDim, oldPoint, oldSlotDims, oldSlotPos, newDim, newPoint));
	}
}
