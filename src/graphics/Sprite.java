package graphics;

import mathematics.Vector2i;
import tiles.Tile;

public class Sprite {

	public static Sprite getVoid() {
		return voidSprite;
	}

	public static Sprite getGrassA() {
		return SpriteSheet.grassAndStonePath.getSprites()[21*9 + 3];
	}

	public static Sprite getGrassB() {
		return SpriteSheet.grassAndStonePath.getSprites()[21*9 + 9];
	}

	public static Sprite getPathA() {
		return SpriteSheet.grassAndStonePath.getSprites()[21*3 + 3];
	}
	/*public static Sprite getAstroTurf() {
		return SpriteSheet.grassAndWater.getSprites()[0];
	}
	public static Sprite getTallGrass() {
		return SpriteSheet.grassAndWater.getSprites()[1];
	}
	public static Sprite getBrownishGrass() {
		return SpriteSheet.grassAndWater.getSprites()[2];
	}
	public static Sprite getLakeWater() {
		return SpriteSheet.grassAndWater.getSprites()[3];
	}
	public static Sprite getDryGrass() {
		return SpriteSheet.grassAndWater.getSprites()[4];
	}*/

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
