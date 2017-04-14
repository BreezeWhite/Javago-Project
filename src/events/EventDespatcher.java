package events;

public class EventDespatcher {
	
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
