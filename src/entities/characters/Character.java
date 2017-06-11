package entities.characters;

import battles.Battle;
import entities.Entity;
import graphics.Sprite;
import mathematics.Vector2d;

public abstract class Character extends Entity {

	protected Character(Battle battle, Vector2d position, Sprite sprite) {
		super(battle, position, sprite);
	}

	@Override
	public abstract void update();

	protected int hp = 100;
	protected int reloadTime = 0;
	protected double speed = 2;
	protected boolean walking = false;

}
