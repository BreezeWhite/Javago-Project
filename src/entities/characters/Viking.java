package entities.characters;

import java.io.Serializable;

import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import mathematics.Vector2d;

public class Viking extends Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5619303498906885253L;

	public Viking(Vector2d position) {
		super(position,
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
