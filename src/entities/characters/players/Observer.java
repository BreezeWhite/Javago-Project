package entities.characters.players;

import java.io.Serializable;

import graphics.Sprite;
import inputs.Keyboard;
import mathematics.Vector2d;

public class Observer extends Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5417554807413499448L;

	public Observer(Vector2d position, Keyboard keyboard) {
		super(position, keyboard, Sprite.getInvisible());
	}
	
	@Override
	public void update() {
		double deltaX = 0, deltaY = 0;
		if (keyboard.upPressed()) {
			deltaY -= speed;
		}
		if (keyboard.leftPressed()) {
			deltaX -= speed;
		}
		if (keyboard.downPressed()) {
			deltaY += speed;
		}
		if (keyboard.rightPressed()) {
			deltaX += speed;
		}
		if (deltaX != 0 || deltaY != 0) {
			move(deltaX, deltaY);
		}
	}

}
