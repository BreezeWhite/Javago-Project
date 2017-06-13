package events.types;

import java.io.Serializable;

import events.Event;

public class MouseButtonEvent extends Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 968132208998873511L;

	public int getButton() {
		return button;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	private int button;
	private int x, y;
	
	protected MouseButtonEvent(int button, int x, int y, Type type) {
		super(type);
		this.button = button;
		this.x = x;
		this.y = y;
	}

}
