package entities.characters.players;

import java.io.Serializable;

import entities.Projectile;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.KeyboardServerCopy;
import main.JavaGo;
import mathematics.Vector2d;

public class SharkPlane extends Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2684957700193700335L;

	public SharkPlane(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneUp, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneUpRight, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneRight, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneDownRight, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneDown, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneDownLeft, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneLeft, 3),
						new AnimatedSprite(SpriteSheet.sharkPlaneDimensions, SpriteSheet.sharkPlaneUpLeft, 3)));
		defaultFrame = 1;
		reloadTime = 30;
		speed = 5;
	}

	@Override
	public void update() {
		if (!usingAbility) {
			if (kamikaze) {
				removed = true;
			} else if (timeToNextShot <= 0) {
				if (keyboard.rPressed()) { // Machine gun.
					Projectile projectile = new Projectile(new Vector2d(x + 24, y + 24),
							SpriteSheet.miscellaneousProjectiles.getSprites()[1], direction.getDirectionAngleRadians(),
							speed + 5, 300, 5);
					JavaGo.getBattle().add(projectile);
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Missile.
					Projectile projectile = new Projectile(new Vector2d(x + 24, y + 24),
							SpriteSheet.redMissile.getSprites()[direction.getIndex()],
							direction.getDirectionAngleRadians(), speed + 5, 300, 5);
					JavaGo.getBattle().add(projectile);
					timeToNextShot = MISSILE_RELOAD_TIME;
				} else if (keyboard.wPressed()) { // Homing missile.
					Projectile projectile = new Projectile(new Vector2d(x + 24, y + 24),
							SpriteSheet.blueMissile.getSprites()[direction.getIndex()],
							direction.getDirectionAngleRadians(), speed + 5, 300, 5);
					JavaGo.getBattle().add(projectile);
					timeToNextShot = MISSILE_RELOAD_TIME;
				}
			}
		}
		if (keyboard.qPressed()) { // Kamikaze.
			abilityAnimation = new AnimatedSprite(SpriteSheet.explosionDimensions, SpriteSheet.explosion, 81);
			abilityAnimation.setFrameRate(4);
			abilityAnimationRepetitions = 1;
			abilityDeltaY = 0;
			usingAbility = true;
			kamikaze = true;
		}
		super.update();
	}

	private boolean kamikaze = false;
	private final int MISSILE_RELOAD_TIME = 75;

}
