package entities.characters.players;

import battles.Battle;
import entities.Projectile;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class SharkPlane extends Player {

	public SharkPlane(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
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
					Projectile projectile = new Projectile(battle, new Vector2d(x + 24, y + 24),
							SpriteSheet.miscellaneousProjectiles.getSprites()[1], direction.getDirectionAngleRadians(),
							speed + 5, 300, 5);
					battle.add(projectile);
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Missile.
					Projectile projectile = new Projectile(battle, new Vector2d(x + 24, y + 24),
							SpriteSheet.redMissile.getSprites()[direction.getIndex()],
							direction.getDirectionAngleRadians(), speed + 5, 300, 5);
					battle.add(projectile);
					timeToNextShot = MISSILE_RELOAD_TIME;
				} else if (keyboard.wPressed()) { // Homing missile.
					Projectile projectile = new Projectile(battle, new Vector2d(x + 24, y + 24),
							SpriteSheet.blueMissile.getSprites()[direction.getIndex()],
							direction.getDirectionAngleRadians(), speed + 5, 300, 5);
					battle.add(projectile);
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
