package events;

public abstract class Event {
	
	public enum Type {
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_MOVED,
		MENU_CHANGE
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
