package battles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.characters.players.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import mathematics.Vector2d;
import mathematics.Vector2i;
import tiles.Tile;

public abstract class Battle implements EventListener {

	public void add(Entity entity) {
		if (entity instanceof Player) {
			players.add((Player) entity);
		} else {
			entities.add(entity);
		}
	}

	public Player getClientPlayer() {
		return players.get(0);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Entity> getEntities(Entity entity, double radius) {
		List<Entity> result = new ArrayList<Entity>();
		final Vector2d coordinates = entity.getCoordinates();
		final double relRadius = radius * radius;
		for (int i = 0; i < entities.size(); ++i) {
			Entity currentEntity = entities.get(i);
			if (Vector2d.getRelDistance(coordinates, currentEntity.getCoordinates()) < relRadius) {
				result.add(currentEntity);
			}
		}
		return result;
	}
	
	@Override
	public void onEvent(Event event) {
		getClientPlayer().onEvent(event);
	}

	public void render(Screen screen) {
		screen.clear(0xFF000000);
		final int screenWidth = screen.getDimensions().getX();
		final int screenHeight = screen.getDimensions().getY();
		screen.setOffset(offset);
		int xStart = (offset.getX() < 0) ? offset.getX() / Tile.WIDTH - 1 : offset.getX() >> Tile.SIZE;
		int xEnd = (offset.getX() + screenWidth + Tile.WIDTH) >> Tile.SIZE;
		int yStart = (offset.getY() < 0) ? offset.getY() / Tile.HEIGHT - 1 : offset.getY() >> Tile.SIZE;
		int yEnd = (offset.getY() + screenHeight + Tile.HEIGHT) >> Tile.SIZE;
		for (int y = yStart; y < yEnd; ++y) {
			for (int x = xStart; x < xEnd; ++x) {
				getTile(x, y).render(screen, new Vector2i(x, y));
			}
		}
		for (int i = 0; i < entities.size(); ++i) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); ++i) {
			players.get(i).render(screen);
		}
	}
	
	public void setOffset(Vector2i offset) {
		this.offset = new Vector2i(offset);
	}

	public void update() {
		for (int i = 0; i < players.size(); ++i) {
			players.get(i).update();
		}
		for (int i = 0; i < entities.size(); ++i) {
			entities.get(i).update();
		}
		remove();
	}

	protected int[] tiles;
	protected int width, height;

	protected void loadTiles(String path) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Entity> entities = new ArrayList<Entity>();
	Vector2i offset = new Vector2i();
	private List<Player> players = new ArrayList<Player>();

	private void remove() {
		for (int i = 0; i < entities.size(); ++i) {
			if (entities.get(i).isRemoved()) {
				entities.remove(i);
			}
		}
	}

	public Tile getTile(int x, int y) {
		final int tilesIndex = y * width + x;
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return Tile.voidTile;
		}
		switch (tiles[tilesIndex]) {
		case 0xFF00FF00:
			return Tile.grass;
		case 0xFFFFFF00:
			return Tile.grass;
		case 0xFF7F7F00:
			return Tile.grass;
		default:
			return Tile.voidTile;
		}
	}

}
