package events.types;

import java.io.Serializable;

import events.Event;

public class MouseReleasedEvent extends MouseButtonEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4447389286508119059L;

	public MouseReleasedEvent(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_RELEASED);
	}
	
}
