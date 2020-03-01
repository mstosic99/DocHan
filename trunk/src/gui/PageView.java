package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import actions.ActionType;
import actions.ResizeEnum;

import graf.elements.SlotDevice;
import model.workspace.Document;
import model.workspace.Page;
import model.workspace.Project;
import painters.PainterHandle;
import painters.SlotPainter;
import state.ResizeState;
import state.StateManager;
import model.workspace.*;

@SuppressWarnings("serial")
public class PageView extends AbstractPageView {

	private StateManager stateManager;
	private Point lastPosition = null;

	PainterHandle handle;
	Rectangle2D selectionRectangle;

	public PageView(DocumentView dv, Page page) {
		super(dv, page);

		selectionRectangle = new Rectangle2D.Double();
		stateManager = new StateManager(this);

		Project p = (Project) page.getParent().getParent();
		Document d = (Document) this.page.getParent();
		lbl = new JLabel(p.getName() + " - " + d.getName() + " - " + this.page.getName());
		add(lbl, BorderLayout.NORTH);

		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));

		this.addMouseListener(new MouseController(this));
		this.addMouseMotionListener(new MouseMotionController(this));
		revalidate();
		handle = new PainterHandle();
	}

	public PainterHandle getHandle() {
		return handle;
	}

	public void setHandle(PainterHandle handle) {
		this.handle = handle;
	}

	@Override
	public void update(ActionType at, Object notification) {
		if (at.equals(ActionType.RENAME_PAGE)) {

			Page page = (Page) notification;
			Document document = (Document) page.getParent();
			Project project = (Project) document.getParent();
			lbl.setText(project.getName() + " - " + document.getName() + " - " + this.getPage().getName());
		} else if (at.equals(ActionType.REMOVE_PAGE)) {
			Page page = (Page) notification;
			Document document = (Document) page.getParent();
			Project project = (Project) document.getParent();
			ProjectView projectView = null;
			DocumentView documentView = null;
			PageView pageView = null;

			for (ProjectView pv : MainFrame.getInstance().getDesktop().getProjectViews()) {
				if (pv.getProject() == project) {
					projectView = pv;
				}
			}

			for (DocumentView dv : projectView.getDocumentViews()) {
				if (dv.getDocument() == document) {
					documentView = dv;
				}
			}

			for (PageView pv : documentView.getPageViews()) {
				if (pv.getPage() == page) {
					pageView = pv;
				}
			}
			documentView.removePageView(pageView);
		} else if (notification instanceof SlotDevice && at == ActionType.DRAW) {
			repaint();
		}
		MainFrame.getInstance().revalidate();
		MainFrame.getInstance().repaint();
	}

	protected void paintComponent(Graphics g) {
		handle.setG2d((Graphics2D) g);

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (page.getSelectedSlot() != null) {
			page.getSlotDevices().remove(page.getSelectedSlot());
			page.getSlotDevices().add(page.getSelectedSlot());
		}

		for (SlotDevice sl : page.getSlotDevices()) {
			SlotPainter slPainter = sl.getSlotPainter();
			slPainter.paint(g2, sl);

		}

		for (SlotDevice s1 : page.getSlotDevices()) {
			SlotPainter slPainter = s1.getSlotPainter();
			s1.getSlotPainter().reDraw();
			g2.setPaint(Color.BLACK);
			g2.setStroke(s1.getStroke());
			g2.draw(slPainter.getShape());
			g2.setPaint(s1.getPaint());
			g2.fill(slPainter.getShape());

		}

		if (page.getSelectedSlot() != null && stateManager.getCurrentState() instanceof ResizeState) {
			for (SlotDevice slotDevice : page.getSelectedSlots()) {
				handle.setSlotDevice(page.getSelectedSlot());
				handle.paintSlotHandles();
			}

		}
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f,
				new float[] { (float) 2, (float) 3 }, 0));
		g2.draw(selectionRectangle);
	}

	private class MouseMotionController extends MouseMotionAdapter {
		private PageView pw;

		public MouseMotionController(PageView p) {
			pw = p;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (page.getSelectedSlot() == null) {
				return;
			}
			Cursor cursor = handle.setMouseCursor((Point2D) e.getPoint());
			if (cursor != null)
				pw.setCursor(cursor);
			else
				pw.setCursor(Cursor.getDefaultCursor());

			if (!page.getSelectedSlot().getSlotPainter().iselementAt(e.getPoint()))
				pw.setCursor(Cursor.getDefaultCursor());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			ResizeEnum h = handle.getHandleForPoint(page.getSelectedSlot(), e.getPoint());
			if (h != null) {

				stateManager.getCurrentState().setResizeEnum(h);
				stateManager.getCurrentState().mouseDragged(e);
				repaint();

			}

			stateManager.getCurrentState().mouseDragged(e);
			repaint();

		}

	}

	private class MouseController extends MouseAdapter {
		private PageView pageView;

		public MouseController(PageView p) {
			pageView = p;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			lastPosition = e.getPoint();
			documentView.setFocusPageView(pageView);
			if (stateManager == null || stateManager.getCurrentState() == null)
				return;
			if (stateManager.getCurrentState() instanceof ResizeState) {
				stateManager.getCurrentState().setG(pageView.getGraphics());

			}
			stateManager.getCurrentState().mousePressed(e);
			pageView.repaint();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			stateManager.getCurrentState().mouseReleased(e);
			pageView.repaint();
		}

	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public void startRectangleState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setRectangleState();
	}

	public void startTriangleState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setTriangleState();
	}

	public void startCircleState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setCircleState();
	}

	public void startSelectState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setSelectState();
	}

	public void startResizeState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setResizeState();

	}

	public void startRotateState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setRotateState();
	}

	public void startLassoState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setLassoState();
	}

	public void startMoveState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setMoveState();
	}

	public void startDeleteSlotState() {
		if (stateManager == null) {
			stateManager = new StateManager(this);
		}
		stateManager.setDeleteState();
	}

	public Point getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Rectangle2D getSelectionRectangle() {
		return selectionRectangle;
	}

	public void setSelectionRectangle(Rectangle2D selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}

	public void paste() {
		Transferable clipBoardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		if (clipBoardContent instanceof DocumentElementSelection) {
			try {

				SlotDevice device = null;
				ArrayList<SlotDevice> tmpList = (ArrayList<SlotDevice>) clipBoardContent
						.getTransferData(PageElementSelection.elementFlavor);

				int i = 0;
				while (i < tmpList.size()) {
					device = (SlotDevice) tmpList.get(i).clone();
					Point newLocation = (Point) device.getPositionOFSlot().clone();
					newLocation.setLocation(lastPosition.getX() + device.getDimension().width * i,
							lastPosition.getY() + device.getDimension().height * i);
					device.setPositionOFSlot(newLocation);
					page.addSlotDevice(device);

					i++;
				}

			} catch (Exception ex) {
				ex.printStackTrace();

			}

		}
	}

}
