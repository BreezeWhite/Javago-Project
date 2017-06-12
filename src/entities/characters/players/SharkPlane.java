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
		speed = 5;
	}
	
	@Override
	public void update() {
		if(!usingAbility) {
			if(keyboard.rPressed() && timeToNextShot <= 0) { // Machine gun.
				Projectile projectile = new Projectile(battle, new Vector2d(x, y), SpriteSheet.projectiles.getSprites()[1], Math.toRadians(90), 5, 5000, 5);
				battle.add(projectile);
				timeToNextShot = reloadTime;
			}
			else if(keyboard.ePressed()) { // Missile.
			}
			else if(keyboard.wPressed()) { // Homing missile.
			}
			else if(keyboard.qPressed()) { // Kamikaze.
			}
		}
		super.update();
	}

}
