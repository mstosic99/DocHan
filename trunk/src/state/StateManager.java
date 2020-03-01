package state;

import state.State;

import java.io.Serializable;

import gui.PageView;
import model.workspace.Page;

public class StateManager implements Serializable {

	private State currentState = null;

	private CircleState circleState;
	private RectangleState rectangleState;
	private TriangleState triangleState;

	private RotateState rotateState;
	private ResizeState resizeState;

	private LassoState lassoState;
	private SelectState selectState;
	private MoveState moveState;

	private DeleteSlotState deleteSlotState;

	public StateManager(PageView mediator) {
		circleState = new CircleState(mediator);
		rectangleState = new RectangleState(mediator);
		triangleState = new TriangleState(mediator);

		rotateState = new RotateState(mediator);
		resizeState = new ResizeState(mediator);

		lassoState = new LassoState(mediator);
		selectState = new SelectState(mediator);
		moveState = new MoveState(mediator);

		deleteSlotState = new DeleteSlotState(mediator);
		currentState = selectState;

	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public CircleState getCircleState() {
		return circleState;
	}

	public void setCircleState() {
		currentState = circleState;
	}

	public RectangleState getRectangleState() {
		return rectangleState;
	}

	public void setRectangleState() {
		currentState = rectangleState;
	}

	public TriangleState getTriangleState() {
		return triangleState;
	}

	public void setTriangleState() {
		currentState = triangleState;
	}

	public RotateState getRotateState() {
		return rotateState;
	}

	public void setRotateState() {
		currentState = rotateState;
	}

	public ResizeState getResizeState() {
		return resizeState;
	}

	public void setResizeState() {
		currentState = resizeState;
	}

	public LassoState getLassoState() {
		return lassoState;
	}

	public void setLassoState() {
		currentState = lassoState;
	}

	public SelectState getSelectState() {
		return selectState;
	}

	public void setSelectState() {
		currentState = selectState;
	}

	public MoveState getMoveState() {
		return moveState;
	}

	public void setMoveState() {
		currentState = moveState;
	}

	public DeleteSlotState getDeleteState() {
		return deleteSlotState;
	}

	public void setDeleteState() {
		currentState = deleteSlotState;
	}

}