package entities.characters.players;

import battles.Battle;
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
			if(keyboard.rPressed()) { // Machine gun.
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
