package entities.characters;

import battles.Battle;
import entities.Entity;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.Sprite;
import mathematics.Vector2d;

public abstract class Character extends Entity {

	protected Character(Battle battle, Vector2d position, Sprite sprite, AnimatedSpriteSet animatedSpriteSet) {
		super(battle, position, sprite);
		this.animatedSpriteSet = animatedSpriteSet;
	}

	@Override
	public void update() {
		if(timeToNextShot > 0) {
			--timeToNextShot;
		}
		if (usingAbility) {
			sprite = abilityAnimation.getSprite();
			move(abilityDeltaX, abilityDeltaY);
			if (abilityAnimation.update() >= abilityAnimationRepetitions) {
				usingAbility = false;
			}
		} else {
			animatedSpriteSet.setDirection(direction);
			if (walking) {
				animatedSpriteSet.update();
			} else {
				animatedSpriteSet.setFrame(defaultFrame);
			}
			if (timeToNextShot > 0) {
				--timeToNextShot;
			}
			if (deltaX != 0 || deltaY != 0) {
				move(deltaX, deltaY);
				if (!walking) {
					walking = true;
				}
			} else {
				walking = false;
			}
			sprite = animatedSpriteSet.getSprite();
		}
	}

	protected AnimatedSpriteSet animatedSpriteSet;
	protected AnimatedSprite abilityAnimation;
	protected int abilityAnimationRepetitions = 1;
	protected double abilityDeltaX = 0, abilityDeltaY = 0;
	protected int defaultFrame = 0;
	protected double deltaX = 0, deltaY = 0;
	protected int hp = 100;
	protected int reloadTime = 20;
	protected double speed = 2;
	protected int timeToNextShot = 0;
	protected boolean usingAbility = false;
	protected boolean walking = false;

}
