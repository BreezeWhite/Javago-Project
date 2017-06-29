package entities;

import java.util.Random;

import graphics.Screen;
import graphics.Sprite;
import main.JavaGo;
import mathematics.Vector2d;
import mathematics.Vector2i;
import settings.Settings;
import tiles.Tile;

public abstract class Entity {

	public Direction direction = Direction.DOWN;

	protected Entity(Vector2d position, Sprite sprite) {
		x = position.getX();
		y = position.getY();
		this.sprite = sprite;
		id = Settings.rand.nextInt();
	}
	
	public Update generateUpdate() {
		Update update = new Update();
		update.x = x;
		update.y = y;
		update.direction = direction;
		return update;
	}

	public Vector2d getCoordinates() {
		return new Vector2d(x, y);
	}

	public int getID() {
		return id;
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
	
	public void processUpdate(Update update) {
		x = update.x;
		y = update.y;
		direction = update.direction;
	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {
		screen.render(sprite, new Vector2i((int) x, (int) y), false, sprite.getPixel(0, 0));
	}

	public abstract void update();

	protected final Random random = new Random();
	protected Sprite sprite;
	protected double x, y;

	protected boolean removed = false;

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

	protected boolean collision(double deltaX, double deltaY) {
		for (int i = 0; i < 4; ++i) {
			double xt = ((x + deltaX) - i % 2 * sprite.getWidth()) / Tile.WIDTH;
			double yt = ((y + deltaY) - i / 2 * sprite.getHeight()) / Tile.HEIGHT;
			int xi = (i % 2 == 0) ? (int) Math.floor(xt) : (int) Math.ceil(xt);
			int yi = (i / 2 == 0) ? (int) Math.floor(yt) : (int) Math.ceil(yt);
			if (JavaGo.getInstance().getBattle().getTile(xi, yi).isSolid()) {
				return true;
			}
		}
		return false;
	}
	
	public int id;

	private int sign(double value) {
		return (value < 0) ? -1 : 1;
	}

}
