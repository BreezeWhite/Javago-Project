package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mathematics.Vector2i;
import tiles.Tile;

public class SpriteSheet {

	public static final SpriteSheet faerieIsland = new SpriteSheet(new SpriteSheet("/textures/sheets/islands/faerie_island.png"), new Vector2i(0, 0), new Vector2i(23, 29), new Vector2i(Tile.WIDTH, Tile.HEIGHT));

	public static final SpriteSheet projectiles = new SpriteSheet(new SpriteSheet("/textures/sheets/projectiles/projectiles.png"), new Vector2i(0, 0), new Vector2i(3, 3), new Vector2i(16, 16));

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

	public static final Vector2i gladiatorCatDimensions = new Vector2i(48, 48);
	public static final SpriteSheet gladiatorCat = new SpriteSheet(new SpriteSheet("/textures/sheets/players/gladiator_cat.png"), new Vector2i(0, 0), new Vector2i(12, 8), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatUp = new SpriteSheet(gladiatorCat, new Vector2i(0, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatUpRight = new SpriteSheet(gladiatorCat, new Vector2i(10, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatRight = new SpriteSheet(gladiatorCat, new Vector2i(9, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatDownRight = new SpriteSheet(gladiatorCat, new Vector2i(8, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatDown = new SpriteSheet(gladiatorCat, new Vector2i(4, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatDownLeft = new SpriteSheet(gladiatorCat, new Vector2i(3, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatLeft = new SpriteSheet(gladiatorCat, new Vector2i(2, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatUpLeft = new SpriteSheet(gladiatorCat, new Vector2i(1, 4), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatPounceDown = new SpriteSheet(gladiatorCat, new Vector2i(3, 0), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatLickDownRight = new SpriteSheet(gladiatorCat, new Vector2i(9, 0), new Vector2i(1, 4), gladiatorCatDimensions);
	public static final SpriteSheet gladiatorCatSleepDownLeft = new SpriteSheet(gladiatorCat, new Vector2i(1, 1), new Vector2i(1, 3), gladiatorCatDimensions);

	public static final Vector2i sharkPlaneDimensions = new Vector2i(64, 64);
	public static final SpriteSheet sharkPlane = new SpriteSheet(new SpriteSheet("/textures/sheets/players/shark_plane.png"), new Vector2i(0, 0), new Vector2i(3, 8), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneUp = new SpriteSheet(sharkPlane, new Vector2i(0, 0), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneUpRight = new SpriteSheet(sharkPlane, new Vector2i(0, 1), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneRight = new SpriteSheet(sharkPlane, new Vector2i(0, 2), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneDownRight = new SpriteSheet(sharkPlane, new Vector2i(0, 3), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneDown = new SpriteSheet(sharkPlane, new Vector2i(0, 7), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneDownLeft = new SpriteSheet(sharkPlane, new Vector2i(0, 6), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneLeft = new SpriteSheet(sharkPlane, new Vector2i(0, 5), new Vector2i(3, 1), sharkPlaneDimensions);
	public static final SpriteSheet sharkPlaneUpLeft = new SpriteSheet(sharkPlane, new Vector2i(0, 4), new Vector2i(3, 1), sharkPlaneDimensions);

	public static final Vector2i leprechaunDimensions = new Vector2i(32, 48);
	public static final SpriteSheet leprechaun = new SpriteSheet(new SpriteSheet("/textures/sheets/players/leprechaun.png"), new Vector2i(0, 0), new Vector2i(4, 4), leprechaunDimensions);
	public static final SpriteSheet leprechaunUp = new SpriteSheet(leprechaun, new Vector2i(0, 3), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunUpRight = new SpriteSheet(leprechaun, new Vector2i(0, 3), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunRight = new SpriteSheet(leprechaun, new Vector2i(0, 2), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunDownRight = new SpriteSheet(leprechaun, new Vector2i(0, 2), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunDown = new SpriteSheet(leprechaun, new Vector2i(0, 0), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunDownLeft = new SpriteSheet(leprechaun, new Vector2i(0, 1), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunLeft = new SpriteSheet(leprechaun, new Vector2i(0, 1), new Vector2i(4, 1), leprechaunDimensions);
	public static final SpriteSheet leprechaunUpLeft = new SpriteSheet(leprechaun, new Vector2i(0, 3), new Vector2i(4, 1), leprechaunDimensions);

	public static final Vector2i fatNerdDimensions = new Vector2i(67, 91);
	public static final SpriteSheet fatNerd = new SpriteSheet(new SpriteSheet("/textures/sheets/players/fat_nerd.png"), new Vector2i(0, 0), new Vector2i(3, 4), fatNerdDimensions);
	public static final SpriteSheet fatNerdUp = new SpriteSheet(fatNerd, new Vector2i(0, 0), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdUpRight = new SpriteSheet(fatNerd, new Vector2i(0, 1), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdRight = new SpriteSheet(fatNerd, new Vector2i(0, 1), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdDownRight = new SpriteSheet(fatNerd, new Vector2i(0, 1), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdDown = new SpriteSheet(fatNerd, new Vector2i(0, 2), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdDownLeft = new SpriteSheet(fatNerd, new Vector2i(0, 3), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdLeft = new SpriteSheet(fatNerd, new Vector2i(0, 3), new Vector2i(3, 1), fatNerdDimensions);
	public static final SpriteSheet fatNerdUpLeft = new SpriteSheet(fatNerd, new Vector2i(0, 3), new Vector2i(3, 1), fatNerdDimensions);

	public static final Vector2i gayVampireDimensions = new Vector2i(32, 32);
	public static final SpriteSheet gayVampire = new SpriteSheet(new SpriteSheet("/textures/sheets/players/gay_vampire.png"), new Vector2i(0, 0), new Vector2i(6, 8), gayVampireDimensions);
	public static final SpriteSheet gayVampireUp = new SpriteSheet(gayVampire, new Vector2i(0, 3), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireUpRight = new SpriteSheet(gayVampire, new Vector2i(0, 7), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireRight = new SpriteSheet(gayVampire, new Vector2i(0, 2), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireDownRight = new SpriteSheet(gayVampire, new Vector2i(0, 5), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireDown = new SpriteSheet(gayVampire, new Vector2i(0, 0), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireDownLeft = new SpriteSheet(gayVampire, new Vector2i(0, 4), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireLeft = new SpriteSheet(gayVampire, new Vector2i(0, 1), new Vector2i(6, 1), gayVampireDimensions);
	public static final SpriteSheet gayVampireUpLeft = new SpriteSheet(gayVampire, new Vector2i(0, 6), new Vector2i(6, 1), gayVampireDimensions);

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
