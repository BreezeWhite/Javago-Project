package graphics.layers;

import java.io.Serializable;

import events.Event;
import events.EventListener;
import graphics.Screen;

public class Layer implements EventListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213724754776908369L;

	@Override
	public void onEvent(Event event) {
	}
	
	public void render(Screen screen) {
	}
	
	public void update() {
	}

}
