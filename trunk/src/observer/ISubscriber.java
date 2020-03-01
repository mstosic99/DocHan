package observer;

import actions.ActionType;

public interface ISubscriber {
	void update(ActionType at, Object notification);
}
