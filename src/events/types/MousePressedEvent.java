package events.types;

import java.io.Serializable;

import events.Event;

public class MousePressedEvent extends MouseButtonEvent  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -518842314791353858L;

	public MousePressedEvent(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_PRESSED);
	}
	
}
