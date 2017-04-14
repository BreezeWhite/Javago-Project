package tiles;

import graphics.Screen;
import graphics.Sprite;
import mathematics.Vector2i;

public class Tile {

	// SIZE is measured in powers of two.
	// E.g., SIZE = 4 means that WIDTH and HEIGHT are both 2^4 pixels.
	public static final int SIZE = 5;
	public static final int WIDTH = 1 << SIZE, HEIGHT = WIDTH;

	public static Tile voidTile = new Tile(Sprite.getVoid(), true);
	public static Tile grassA = new Tile(Sprite.getGrassA(), false);
	public static Tile grassB = new Tile(Sprite.getGrassB(), false);
	public static Tile pathA = new Tile(Sprite.getPathA(), false);

	public Tile(Sprite sprite, boolean solid) {
		this.sprite = sprite;
		this.solid = solid;
	}

	public boolean isSolid() {
		return solid;
	}

	public void render(Screen screen, Vector2i position) {
		position = new Vector2i(position.getX() * WIDTH, position.getY() * HEIGHT);
		screen.render(sprite, position, false);
	}

	private boolean solid;
	private Sprite sprite;

}
