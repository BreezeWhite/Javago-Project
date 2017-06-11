package entities.characters.players;

import battles.Battle;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class Viking extends Player {

	public Viking(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUp, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUpRight, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorRight, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDownRight, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDown, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDownLeft, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorLeft, 5),
						new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUpLeft, 5)));
	}

}
