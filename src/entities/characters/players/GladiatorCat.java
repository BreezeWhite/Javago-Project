package entities.characters.players;

import java.io.Serializable;

import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.KeyboardServerCopy;
import mathematics.Vector2d;

public class GladiatorCat extends Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2234673269171418932L;
	public GladiatorCat(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatUp, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatUpRight, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatRight, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatDownRight, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatDown, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatDownLeft, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatLeft, 4),
						new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatUpLeft, 4)));
		defaultFrame = 1;
	}
	
	@Override
	public void update() {
		if(speedBoostStart > 0) {
			if(System.currentTimeMillis() - speedBoostStart > SPEED_BOOST_DURATION) {
				speed = oldSpeed;
				speedBoostStart = 0;
			}
		}
		else if(!usingAbility) {
			if(keyboard.rPressed()) { // Pounce.
				abilityAnimation = new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatPounceDown, 4);
				abilityAnimationRepetitions = 1;
				abilityDeltaY = speed;
				usingAbility = true;
			}
			else if(keyboard.ePressed()) { // Purr.
				abilityAnimation = new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatLickDownRight, 4);
				abilityAnimation.setFrameRate(15);
				abilityAnimationRepetitions = 5;
				abilityDeltaY = 0;
				usingAbility = true;
			}
			else if(keyboard.wPressed()) { // Sleep.
				abilityAnimation = new AnimatedSprite(SpriteSheet.gladiatorCatDimensions, SpriteSheet.gladiatorCatSleepDownLeft, 3);
				abilityAnimation.setFrameRate(30);
				abilityAnimationRepetitions = 15;
				abilityDeltaY = 0;
				usingAbility = true;
			}
			else if(keyboard.qPressed()) { // Flee.
				oldSpeed = speed;
				speed *= 5;
				speedBoostStart = System.currentTimeMillis();
			}
		}
		super.update();
	}

	private long speedBoostStart = 0;
	private final long SPEED_BOOST_DURATION = 1500;
	private double oldSpeed = 0;

}
