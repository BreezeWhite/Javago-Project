package battles;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.characters.players.Observer;
import entities.characters.players.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import mathematics.Vector2d;
import mathematics.Vector2i;
import settings.Settings;
import tiles.Tile;

public abstract class Battle implements EventListener {

	public void add(Entity entity) {
		if (entity instanceof Player) {
			lists.players.add((Player) entity);
		} else {
			lists.entities.add(entity);
		}
	}

	public byte[] exportEntities() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(lists.players.get(0));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] data = bos.toByteArray();
			return data;
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, new NodeComparator());
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				// Reconstruct path.
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear(); // Necessary?
				closedList.clear(); // Necessary?
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; ++i) {
				if (i == 4) {
					continue;
				}
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xDirection = (i % 3) - 1;
				int yDirection = (i / 3) - 1;
				Tile at = getTile(x + xDirection, y + yDirection);
				if (at.isSolid()) {
					continue;
				}
				Vector2i a = new Vector2i(x + xDirection, y + yDirection);
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95); // Either
																								// 1
																								// or
																								// sqrt(2).
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) {
					continue;
				}
				if (!vecInList(openList, a) || gCost < node.gCost) {
					openList.add(node);
				}
			}
		}
		closedList.clear(); // Necessary?
		return null;
	}

	private double getDistance(Vector2i a, Vector2i b) {
		double dx = a.getX() - b.getX();
		double dy = a.getY() - b.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	public Player getClientPlayer() {
		return lists.players.get(0);
	}

	public List<Entity> getEntities() {
		return lists.entities;
	}

	public List<Player> getPlayers() {
		return lists.players;
	}

	public List<Entity> getEntities(Entity entity, double radius) {
		List<Entity> result = new ArrayList<Entity>();
		final Vector2d coordinates = entity.getCoordinates();
		final double relRadius = radius * radius;
		for (int i = 0; i < lists.entities.size(); ++i) {
			Entity currentEntity = lists.entities.get(i);
			if (Vector2d.getRelDistance(coordinates, currentEntity.getCoordinates()) < relRadius) {
				result.add(currentEntity);
			}
		}
		return result;
	}

	public void importEntities(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInput in = null;
		try {
			try {
				in = new ObjectInputStream(bis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Object obj = in.readObject();
				if (obj instanceof EntityContainer) {
					lists = (EntityContainer) obj;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
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
		for (int i = 0; i < lists.entities.size(); ++i) {
			lists.entities.get(i).render(screen);
		}
		for (int i = 0; i < lists.players.size(); ++i) {
			lists.players.get(i).render(screen);
		}
	}

	public void setOffset(Vector2i offset) {
		this.offset = new Vector2i(offset);
	}

	public void update() {
		if (Settings.isServer()) {
			for (int i = 0; i < lists.players.size(); ++i) {
				lists.players.get(i).update();
			}
			for (int i = 0; i < lists.entities.size(); ++i) {
				lists.entities.get(i).update();
			}
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

	private EntityContainer lists = new EntityContainer();
	Vector2i offset = new Vector2i();

	private void remove() {
		for (int i = 0; i < lists.entities.size(); ++i) {
			if (lists.entities.get(i).isRemoved()) {
				lists.entities.remove(i);
			}
		}
		for (int i = 0; i < lists.players.size(); ++i) {
			if (lists.players.get(i).isRemoved()) {
				lists.players.set(i,
						new Observer(lists.players.get(i).getCoordinates(), lists.players.get(i).getKeyboard()));
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

	private static class NodeComparator implements Comparator<Node>, Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1809872804838930284L;

		@Override
		public int compare(Node a, Node b) {
			if (b.fCost < a.fCost) {
				return +1;
			}
			if (b.fCost > a.fCost) {
				return -1;
			}
			return 0;
		}
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).tile.equals(vector)) {
				return true;
			}
		}
		return false;
	}

}
