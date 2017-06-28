package entities.characters.players;

import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.KeyboardServerCopy;
import mathematics.Vector2d;

public class FatNerd extends Player {

	public FatNerd(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUp, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUpRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDownRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDown, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDownLeft, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdLeft, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUpLeft, 3)));
		defaultFrame = 0;
		reloadTime = 100;
		speed = 2;
	}

	@Override
	public void update() {
		if (!usingAbility) {
			if (timeToNextShot <= 0) {
				if (keyboard.rPressed()) { // Duck.
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Helper.
					timeToNextShot = reloadTime;
				} else if (keyboard.wPressed()) { // DoS attack.
					timeToNextShot = reloadTime;
				} else if (keyboard.qPressed()) { // Cyber warfare.
					timeToNextShot = reloadTime;
				}
			}
		}
		super.update();
	}

}
