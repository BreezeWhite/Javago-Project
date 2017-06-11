package entities.characters.players;

import battles.Battle;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class Leprechaun extends Player {

	public Leprechaun(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUp, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUpRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDownRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDown, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDownLeft, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunLeft, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUpLeft, 4)));
	}

}
