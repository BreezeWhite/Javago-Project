package entities.characters.players;

import battles.Battle;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class GayVampire extends Player {

	public GayVampire(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireUp, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireUpRight, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireRight, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireDownRight, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireDown, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireDownLeft, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireLeft, 6),
						new AnimatedSprite(SpriteSheet.gayVampireDimensions, SpriteSheet.gayVampireUpLeft, 6)));
	}

}
