package entities.characters.players;

import battles.Battle;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class FatNerd extends Player {

	public FatNerd(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUp, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUpRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDownRight, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDown, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdDownLeft, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdLeft, 3),
						new AnimatedSprite(SpriteSheet.fatNerdDimensions, SpriteSheet.fatNerdUpLeft, 3)));
	}

}
