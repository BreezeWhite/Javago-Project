package entities.characters.players;

import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.KeyboardServerCopy;
import mathematics.Vector2d;

public class Vampire extends Player {

	public Vampire(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireUp, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireUpRight, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireRight, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireDownRight, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireDown, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireDownLeft, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireLeft, 6),
						new AnimatedSprite(SpriteSheet.vampireDimensions, SpriteSheet.vampireUpLeft, 6)));
		defaultFrame = 0;
		reloadTime = 100;
		speed = 2;
	}

	@Override
	public void update() {
		if (!usingAbility) {
			if (timeToNextShot <= 0) {
				if (keyboard.rPressed()) { // Suck.
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Make fun of outfit.
					timeToNextShot = reloadTime;
				} else if (keyboard.wPressed()) { // Shine.
					timeToNextShot = reloadTime;
				} else if (keyboard.qPressed()) { // Rainbow bat.
					timeToNextShot = reloadTime;
				}
			}
		}
		super.update();
	}
}