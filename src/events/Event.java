package events;

import java.io.Serializable;

public abstract class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 842310542408879986L;
	public enum Type {
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_MOVED
	}
	
	public Type getType() {
		return type;
	}
	
	protected Event(Type type) {
		this.type = type;
	}
	
	boolean handled;
	private Type type;

}
