package entities.characters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.Projectile;
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
		update.usingAbility = usingAbility;
		update.speed = speed;
		if(usingAbility) {
			update.abilityAnimationIndex = abilityAnimationIndex;
			update.frame = abilityAnimations.get(abilityAnimationIndex).getFrame();
		}
		else {
			update.frame = animatedSpriteSet.getFrame();
		}
		return update;
	}
	
	@Override
	public void processUpdate(Update update) {
		super.processUpdate(update);
		sprite = animatedSpriteSet.getSprite();
		speed = update.speed;
		usingAbility = update.usingAbility;
		if(usingAbility) {
			abilityAnimationIndex = update.abilityAnimationIndex;
			abilityAnimations.get(abilityAnimationIndex).setFrame(update.frame);
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
			sprite = abilityAnimations.get(abilityAnimationIndex).getSprite();
			move(abilityDeltaX, abilityDeltaY);
			if (abilityAnimations.get(abilityAnimationIndex).update() >= abilityAnimationRepetitions) {
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
	protected int abilityAnimationIndex = 0;
	protected List<AnimatedSprite> abilityAnimations = new ArrayList<AnimatedSprite>();
	protected int projectileIndex = -1;
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
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
