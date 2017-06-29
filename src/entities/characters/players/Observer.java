package entities.characters.players;

import graphics.Sprite;
import inputs.KeyboardServerCopy;
import mathematics.Vector2d;

public class Observer extends Player {

	public Observer(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard, Sprite.getInvisible());
		hp = 9999999;
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
