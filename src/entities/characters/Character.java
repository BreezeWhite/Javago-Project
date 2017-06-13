package entities.characters;

import java.io.Serializable;

import entities.Entity;
import entities.Update;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.Sprite;
import mathematics.Vector2d;

public abstract class Character extends Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108415205526717196L;
	protected Character(Vector2d position, AnimatedSpriteSet animatedSpriteSet) {
		super(position, Sprite.getInvisible());
		this.animatedSpriteSet = animatedSpriteSet;
	}
	
	@Override
	public Update generateUpdate() {
		Update update = super.generateUpdate();
		update.frame = animatedSpriteSet.getFrame();
		update.usingAbility = usingAbility;
		return update;
	}
	
	@Override
	public void processUpdate(Update update) {
		super.processUpdate(update);
		sprite = animatedSpriteSet.getSprite();
		usingAbility = update.usingAbility;
		if(usingAbility) {
			abilityAnimation.setFrame(update.frame);
		}
		else {
			animatedSpriteSet.setDirection(direction);
			animatedSpriteSet.setFrame(update.frame);
		}
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
