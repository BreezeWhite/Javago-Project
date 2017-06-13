package events;

import java.io.Serializable;

public class EventDespatcher implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6102758858918203012L;

	public EventDespatcher(Event event) {
		this.event = event;
	}
	
	public void despatch(Event.Type type, EventHandler eventHandler) {
		if(event.handled) {
			return;
		}
		if(event.getType() == type) {
			event.handled = eventHandler.onEvent(event);
		}
	}

	private Event event;

}
