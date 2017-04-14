package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mathematics.Vector2i;
import tiles.Tile;

public class SpriteSheet {

	public static final SpriteSheet grassAndStonePath = new SpriteSheet(new SpriteSheet("/textures/sheets/forest.png"), new Vector2i(0, 0), new Vector2i(21, 13), new Vector2i(Tile.WIDTH, Tile.HEIGHT));
	public static final Vector2i warriorDimensions = new Vector2i(72, 72);
	public static final SpriteSheet warrior = new SpriteSheet(new SpriteSheet("/textures/sheets/players/warrior.png"), new Vector2i(0, 0), new Vector2i(10, 12), warriorDimensions);
	public static final SpriteSheet warriorUp = new SpriteSheet(warrior, new Vector2i(0, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorUpRight = new SpriteSheet(warrior, new Vector2i(1, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorRight = new SpriteSheet(warrior, new Vector2i(2, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorDownRight = new SpriteSheet(warrior, new Vector2i(3, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorDown = new SpriteSheet(warrior, new Vector2i(4, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorDownLeft = new SpriteSheet(warrior, new Vector2i(6, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorLeft = new SpriteSheet(warrior, new Vector2i(7, 0), new Vector2i(1, 5), warriorDimensions);
	public static final SpriteSheet warriorUpLeft = new SpriteSheet(warrior, new Vector2i(8, 0), new Vector2i(1, 5), warriorDimensions);

	public SpriteSheet(SpriteSheet spriteSheet, Vector2i offset, Vector2i spriteSheetDimensions,
			Vector2i spriteDimensions) {
		final int spriteWidth = spriteDimensions.getX();
		final int spriteHeight = spriteDimensions.getY();
		final int xOffset = offset.getX() * spriteWidth;
		final int yOffset = offset.getY() * spriteHeight;
		WIDTH = spriteSheetDimensions.getX() * spriteWidth;
		HEIGHT = spriteSheetDimensions.getY() * spriteHeight;
		pixelMap = new int[WIDTH * HEIGHT];
		for (int y = 0; y < HEIGHT; ++y) {
			final int yAbsolute = yOffset + y;
			for (int x = 0; x < WIDTH; ++x) {
				final int xAbsolute = xOffset + x;
				pixelMap[y * WIDTH + x] = spriteSheet.pixelMap[yAbsolute * spriteSheet.WIDTH + xAbsolute];
			}
		}
		int frame = 0;
		sprites = new Sprite[spriteSheetDimensions.getX() * spriteSheetDimensions.getY()];
		for (int spriteSheetY = 0; spriteSheetY < spriteSheetDimensions.getY(); ++spriteSheetY) {
			for (int spriteSheetX = 0; spriteSheetX < spriteSheetDimensions.getX(); ++spriteSheetX) {
				int[] spritePixelMap = new int[spriteWidth * spriteHeight];
				for (int spriteY = 0; spriteY < spriteHeight; ++spriteY) {
					for (int spriteX = 0; spriteX < spriteWidth; ++spriteX) {
						spritePixelMap[spriteY * spriteWidth
								+ spriteX] = pixelMap[(spriteSheetY * spriteHeight + spriteY) * WIDTH
										+ (spriteSheetX * spriteWidth + spriteX)];
					}
				}
				sprites[frame++] = new Sprite(spriteDimensions, spritePixelMap);
			}
		}
	}

	public SpriteSheet(String path) {
		int width = 0, height = 0;
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			height = image.getHeight();
			width = image.getWidth();
			pixelMap = new int[width * height];
			image.getRGB(0, 0, width, height, pixelMap, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HEIGHT = height;
		WIDTH = width;
		sprites = null;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private final int HEIGHT, WIDTH;
	private int[] pixelMap;
	private final Sprite[] sprites;

}
