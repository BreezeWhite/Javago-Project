package entities.characters;

import battles.Battle;
import entities.Entity;
import graphics.Sprite;
import mathematics.Vector2d;
import tiles.Tile;

public abstract class Character extends Entity {

	protected enum Direction {
		UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
	}

	protected Direction direction;

	protected Character(Battle battle, Vector2d position, Sprite sprite) {
		super(battle, position, sprite);
	}

	public void move(double deltaX, double deltaY) {
		if (deltaX > 0) {
			if (deltaY > 0) {
				direction = Direction.DOWN_RIGHT;
			} else if (deltaY < 0) {
				direction = Direction.UP_RIGHT;
			} else {
				direction = Direction.RIGHT;
			}
		} else if (deltaX < 0) {
			if (deltaY > 0) {
				direction = Direction.DOWN_LEFT;
			} else if (deltaY < 0) {
				direction = Direction.UP_LEFT;
			} else {
				direction = Direction.LEFT;
			}
		} else if (deltaY > 0) {
			direction = Direction.DOWN;
		} else if (deltaY < 0) {
			direction = Direction.UP;
		}
		while (deltaX != 0) {
			if (Math.abs(deltaX) > 1) {
				if (!collision(sign(deltaX), deltaY)) {
					this.x += sign(deltaX);
				}
				deltaX -= sign(deltaX);
			} else {
				if (!collision(sign(deltaX), deltaY)) {
					this.x += deltaX;
				}
				deltaX = 0;
			}
		}
		while (deltaY != 0) {
			if (Math.abs(deltaY) > 1) {
				if (!collision(deltaX, sign(deltaY))) {
					this.y += sign(deltaY);
				}
				deltaY -= sign(deltaY);
			} else {
				if (!collision(deltaX, sign(deltaY))) {
					this.y += deltaY;
				}
				deltaY = 0;
			}
		}
	}

	// TODO Collisions is broken.
	// What if only part of sprite should be collidable?
	// What if sprite has a lot of whitespace?
	private boolean collision(double deltaX, double deltaY) {
		for (int i = 0; i < 4; ++i) {
			double xt = ((x + deltaX) - i % 2 * sprite.getWidth()) / Tile.WIDTH;
			double yt = ((y + deltaY) - i / 2 * sprite.getHeight()) / Tile.HEIGHT;
			int xi = (i % 2 == 0) ? (int) Math.floor(xt) : (int) Math.ceil(xt);
			int yi = (i / 2 == 0) ? (int) Math.floor(yt) : (int) Math.ceil(yt);
			if (battle.getTile(xi, yi).isSolid()) {
				return true;
			}
		}
		return false;
	}

	private int sign(double value) {
		return (value < 0) ? -1 : 1;
	}

	@Override
	public abstract void update();

	protected int reloadTime = 0;
	protected double speed = 2;
	protected boolean walking = false;

}
