package entities.characters.players;

import battles.Battle;
import entities.Projectile;
import entities.characters.Minion;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public class Leprechaun extends Player {

	public Leprechaun(Battle battle, Vector2d position, Keyboard keyboard) {
		super(battle, position, keyboard,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUp, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUpRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDownRight, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDown, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunDownLeft, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunLeft, 4),
						new AnimatedSprite(SpriteSheet.leprechaunDimensions, SpriteSheet.leprechaunUpLeft, 4)));
		defaultFrame = 0;
		reloadTime = 100;
		speed = 2;
	}

	@Override
	public void update() {
		if (!usingAbility) {
			if (timeToNextShot <= 0) {
				if (keyboard.rPressed()) { // Four-leaf clover.
					Projectile projectile = new Projectile(battle, new Vector2d(x + 10, y + 16),
							SpriteSheet.miscellaneousProjectiles.getSprites()[2], direction.getDirectionAngleRadians(),
							speed + 5, 300, 5);
					battle.add(projectile);
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Luck o' the Irish.
					timeToNextShot = reloadTime;
				} else if (keyboard.wPressed()) { // Riverdance.
					timeToNextShot = reloadTime;
				} else if (keyboard.qPressed()) { // Summon minions.
					for(int i = 0; i < NUM_MINIONS / 3; ++i) {
						for(int j = 0; j < NUM_MINIONS / 6; ++j) {
							battle.add(new Minion(battle, new Vector2d(x + (i - 2.7) * 75, y + (j - 1) * 75)));
						}
					}
					timeToNextShot = reloadTime;
				}
			}
		}
		super.update();
	}
	
	private final int NUM_MINIONS = 18;

}
