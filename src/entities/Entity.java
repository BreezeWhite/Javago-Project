package entities;

import java.util.Random;

import battles.Battle;
import graphics.Screen;
import graphics.Sprite;
import mathematics.Vector2d;
import mathematics.Vector2i;

public abstract class Entity {
	
	protected Entity(Battle battle, Vector2d position, Sprite sprite) {
		x = position.getX();
		y = position.getY();
		this.sprite = sprite;
		this.battle = battle;
	}

	public Vector2d getCoordinates() {
		return new Vector2d(x, y);
	}
	
	public int getHeight() {
		return sprite.getHeight();
	}
	
	public int getWidth() {
		return sprite.getWidth();
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void remove() {
		removed = true;
	}
	
	public void render(Screen screen) {
		screen.render(sprite, new Vector2i((int)x, (int)y), false);
	}
	
	public abstract void update();

	protected Battle battle;
	protected final Random random = new Random();
	protected Sprite sprite;
	protected double x, y;

	private boolean removed = false;

}
