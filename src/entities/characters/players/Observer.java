package entities.characters.players;

import battles.Battle;
import graphics.Sprite;
import inputs.Keyboard;
import mathematics.Vector2d;

public class Observer extends Player {

	public Observer(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard, Sprite.getInvisible());
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
