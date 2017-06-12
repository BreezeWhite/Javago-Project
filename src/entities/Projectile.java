package entities;

import battles.Battle;
import graphics.Sprite;
import mathematics.Vector2d;

public class Projectile extends Entity {
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected double nx, ny;
	protected double speed, range, damage;

	public Projectile(Battle battle, Vector2d position, Sprite sprite, double angle, double speed, int range,
			int damage) {
		super(battle, position, sprite);
		xOrigin = position.getX();
		yOrigin = position.getY();
		this.angle = angle;
		this.range = range;
		this.speed = speed;
		this.damage = damage;
		nx = this.speed * Math.cos(angle);
		ny = this.speed * Math.sin(angle);
	}

	@Override
	public void update() {
		if (!collision((int) nx, (int) ny)) {
			x += nx;
			y += ny;
			if (distance() > range) {
				remove();
			}
		} else {
			// battle.add(new ParticleSpawner((int)x, (int)y, 50, 10, level));
			remove();
		}
	}

	private double distance() {
		final double deltaX = x - xOrigin;
		final double deltaY = y - yOrigin;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
}
