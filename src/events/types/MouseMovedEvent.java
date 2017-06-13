package events.types;

import java.io.Serializable;

import events.Event;

public class MouseMovedEvent extends Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1512607948462142328L;
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
