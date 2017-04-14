package events;

import events.Event;

public interface EventHandler {
	
	public boolean onEvent(Event event);

}
