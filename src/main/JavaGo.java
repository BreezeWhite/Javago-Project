package main;

import java.util.ArrayList;
import java.util.List;

import battles.Battle;
import battles.TestBattle;
import entities.characters.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import graphics.UIManager;
import graphics.layers.Layer;
import graphics.layers.World;
import inputs.Keyboard;
import inputs.Mouse;
import mathematics.Vector2d;
import mathematics.Vector2i;

public class JavaGo implements Runnable, EventListener {

	public static final String TITLE = "JavaGo Project";

	public static void main(String[] args) {
		JavaGo javaGo = new JavaGo();
		javaGo.start();
	}

	public JavaGo() {
		screen = new Screen(defaultScreenWidth, defaultScreenHeight, 2);
		keyboard = new Keyboard();
		screen.addKeyListener(keyboard);
		mouse = new Mouse(this);
		screen.addMouseListener(mouse);
		screen.addMouseMotionListener(mouse);
		battle = new TestBattle("/textures/battles/test_level.png");
		battle.add(new Player(battle, new Vector2d(1100, 900), keyboard));
		player = battle.getClientPlayer(); // Should be the player we just
											// added.
		addLayer(battle);

	}

	public void addLayer(Layer layer) {
		layers.add(layer);
	}

	public void clearLayers() {
		layers.clear();
	}

	@Override
	public void onEvent(Event event) {
		for (int i = layers.size() - 1; i >= 0; --i) {
			layers.get(i).onEvent(event);
		}
	}

	public void render() {
		battle.setOffset(getPlayerCentricOffset());
		for (int i = 0; i < layers.size(); ++i) {
			layers.get(i).render(screen);
		}
		screen.render();
	}

	@Override
	public void run() {
		long prevTimeNS = System.nanoTime();
		long prevTimeMS = System.currentTimeMillis();
		final double rate = 1000000000.0 / 60.0;
		double delta = 0;
		int numFrames = 0;
		int numTicks = 0;
		while (executing) {
			// We would like UIManager to be able to update JavaGo (this), with
			// the capability to display multiple menus simultaneously.

			// UIManager should be able to switch among menus by manipulating
			// JavaGo's layers (i.e., calling javaGo.clearLayers() and
			// javaGo.addLayer()).

			// JavaGo (this) must know when to start gameplay. How can UIManager
			// tell JavaGo when to start gameplay?
			UIManager uiManager = new UIManager(this);
			while(uiManager.isOpen()) {
				render();
			}
			long timeNS = System.nanoTime();
			delta += (timeNS - prevTimeNS) / rate;
			prevTimeNS = timeNS;
			while (delta > 1.0) {
				tick();
				++numTicks;
				--delta;
			}
			render();
			++numFrames;
			if (System.currentTimeMillis() - prevTimeMS > 1000) {
				prevTimeMS += 1000;
				screen.setTitle(TITLE + " | " + numTicks + " ups, " + numFrames + " fps");
				numFrames = numTicks = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		executing = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		executing = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean executing = false;
	private Battle battle;
	private static final int defaultScreenWidth = 500,
			defaultScreenHeight = (int) ((double) defaultScreenWidth * 9.0 / 16.0);
	private Keyboard keyboard;
	private List<Layer> layers = new ArrayList<Layer>();
	private Mouse mouse;
	private Player player;
	private Screen screen;
	private Thread thread;

	private Vector2i getPlayerCentricOffset() {
		return new Vector2i(player.getCoordinates().add(new Vector2d(player.getWidth() >> 1, player.getHeight() >> 1))
				.subtract(screen.getDimensionsD().multiply(0.5)));
	}

	private void tick() {
		for (int i = 0; i < layers.size(); ++i) {
			layers.get(i).update();
		}
	}

}
