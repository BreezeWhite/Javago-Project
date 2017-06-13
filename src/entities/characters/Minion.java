package entities.characters;

import java.io.Serializable;
import java.util.List;

import battles.Battle;
import battles.Node;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.SpriteSheet;
import main.JavaGo;
import mathematics.Vector2d;
import mathematics.Vector2i;
import tiles.Tile;

public class Minion extends Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4319235316303050550L;

	public Minion(Vector2d position) {
		super(position,
				new AnimatedSpriteSet(new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionUp, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionUpRight, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionRight, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionDownRight, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionDown, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionDownLeft, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionLeft, 7),
						new AnimatedSprite(SpriteSheet.minionDimensions, SpriteSheet.minionUpLeft, 7)));
	}

	private int time = 0;
	private List<Node> path = null;

	@Override
	public void update() {
		time = ++time % 600000;
		deltaX = 0;
		deltaY = 0;
		Battle battle = JavaGo.getBattle();
		int playerX = (int) battle.getClientPlayer().getCoordinates().getX();
		int playerY = (int) battle.getClientPlayer().getCoordinates().getY();
		Vector2i start = new Vector2i(((int) x) / Tile.WIDTH, ((int) y) / Tile.HEIGHT);
		Vector2i destination = new Vector2i(playerX / Tile.WIDTH, playerY / Tile.HEIGHT);
		if (time % 3 == 0) {
			path = battle.findPath(start, destination);
		}
		if (path != null && path.size() > 0) {
			Vector2i vector = path.get(path.size() - 1).tile;
			if ((int) x < vector.getX() * Tile.WIDTH) {
				++deltaX;
			} else if ((int) x > vector.getX() * Tile.WIDTH) {
				--deltaX;
			}
			if ((int) y < vector.getY() * Tile.HEIGHT) {
				++deltaY;
			} else if ((int) y > vector.getY() * Tile.HEIGHT) {
				--deltaY;
			}
		}
		super.update();
	}

}