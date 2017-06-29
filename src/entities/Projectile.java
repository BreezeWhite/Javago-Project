package entities;

import graphics.SpriteSheet;
import mathematics.Vector2d;

public class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected int damage, range;
	protected double nx, ny;
	protected double speed;
	private int spriteSheetIndex, spriteIndex;

	public Projectile(Vector2d position, int spriteSheetIndex, int spriteIndex, double angle, double speed, int range,
			int damage) {
		super(position, SpriteSheet.projectiles.get(spriteSheetIndex).getSprites()[spriteIndex]);
		this.spriteSheetIndex = spriteSheetIndex;
		this.spriteIndex = spriteIndex;
		xOrigin = position.getX();
		yOrigin = position.getY();
		this.angle = angle;
		this.range = range;
		this.speed = speed;
		this.damage = damage;
		nx = this.speed * Math.cos(angle);
		ny = this.speed * Math.sin(angle);
	}

	public double getAngle() {
		return angle;
	}

	public int getRange() {
		return range;
	}

	public int getDamage() {
		return damage;
	}

	public double getSpeed() {
		return speed;
	}

	public int getSpriteSheetIndex() {
		return spriteSheetIndex;
	}

	public int getSpriteIndex() {
		return spriteIndex;
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
