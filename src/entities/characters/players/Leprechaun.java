package entities.characters.players;

import java.io.Serializable;

import battles.Battle;
import entities.Projectile;
import entities.characters.Minion;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import inputs.KeyboardServerCopy;
import main.JavaGo;
import mathematics.Vector2d;

public class Leprechaun extends Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5342844826948025948L;

	public Leprechaun(Vector2d position, KeyboardServerCopy keyboard) {
		super(position, keyboard,
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
					Projectile projectile = new Projectile(new Vector2d(x + 10, y + 16),
							SpriteSheet.miscellaneousProjectiles.getSprites()[2], direction.getDirectionAngleRadians(),
							speed + 5, 300, 5);
					JavaGo.getBattle().add(projectile);
					timeToNextShot = reloadTime;
				} else if (keyboard.ePressed()) { // Luck o' the Irish.
					timeToNextShot = reloadTime;
				} else if (keyboard.wPressed()) { // Riverdance.
					timeToNextShot = reloadTime;
				} else if (keyboard.qPressed()) { // Summon minions.
					Battle battle = JavaGo.getBattle();
					for(int i = 0; i < NUM_MINIONS / 3; ++i) {
						for(int j = 0; j < NUM_MINIONS / 6; ++j) {
							battle.add(new Minion(new Vector2d(x + (i - 2.7) * 75, y + (j - 1) * 75)));
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
