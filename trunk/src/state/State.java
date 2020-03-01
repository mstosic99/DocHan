package state;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import actions.ResizeEnum;
import gui.PageView;

public abstract class State implements Serializable {

	protected Graphics g = null;
	protected ResizeEnum resizeEnum;
	protected PageView mediator;

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public ResizeEnum getResizeEnum() {
		return resizeEnum;
	}

	public void setResizeEnum(ResizeEnum resizeEnum) {
		this.resizeEnum = resizeEnum;
	}

}
