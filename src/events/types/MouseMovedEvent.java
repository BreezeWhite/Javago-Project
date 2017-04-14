package events.types;

import events.Event;

public class MouseMovedEvent extends Event {

	public MouseMovedEvent(int x, int y, boolean dragged) {
		super(Type.MOUSE_MOVED);
		this.x = x;
		this.y = y;
		this.dragged = dragged;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isDragged() {
		return dragged;
	}
	
	private int x, y;
	private boolean dragged;

}
