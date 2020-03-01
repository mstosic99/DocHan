package observer;

import actions.ActionType;

public interface IPublisher {
	void addSubscriber(ISubscriber subscriber);

	void removeSubscriber(ISubscriber subscriber);

	void notifySubscribers(ActionType at, Object notification);
}
