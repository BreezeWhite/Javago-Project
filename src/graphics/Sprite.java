package graphics;

import mathematics.Vector2i;
import tiles.Tile;

public class Sprite {

	public static Sprite getVoid() {
		return voidSprite;
	}

	public Sprite(Vector2i dimensions) {
		WIDTH = dimensions.getX();
		HEIGHT = dimensions.getY();
		pixelMap = new int[WIDTH * HEIGHT];
	}

	public Sprite(Vector2i dimensions, int[] pixelMap) {
		this(dimensions);
		for (int i = 0; i < pixelMap.length; ++i) {
			this.pixelMap[i] = pixelMap[i];
		}
	}

	public Sprite(Vector2i dimensions, int colour) {
		this(dimensions);
		for (int i = 0; i < pixelMap.length; ++i) {
			pixelMap[i] = colour;
		}
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getPixel(int x, int y) {
		return pixelMap[y * WIDTH + x];
	}

	public int getWidth() {
		return WIDTH;
	}

	private int[] pixelMap;
	private final int WIDTH, HEIGHT;

	private static final Sprite voidSprite = new Sprite(new Vector2i(Tile.WIDTH, Tile.HEIGHT), 0xFFFFFFFF);

}
