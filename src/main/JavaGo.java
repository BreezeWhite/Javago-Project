package main;

import java.util.ArrayList;
import java.util.List;
import battles.Battle;
import battles.TestBattle;
import entities.characters.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import ui.MyButton.MyButton;
import ui.UIManager;
import ui.UIInterface.*;
import graphics.layers.Layer;
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
		battle.add(new Player(battle, new Vector2d(1100, 900), 25, keyboard));
		player = battle.getClientPlayer(); // Should be the player we just
											// added.
		
		//UIManager will handle all the UI screen
		//and will not end until entering the battle
		uiManager = new UIManager();
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
		uiManager.Start(new UI_World());
		MyButton b = new MyButton("Blah", null);
		while (executing) {
			/*The part of handling battle*/
			long timeNS = System.nanoTime();
			delta += (timeNS - prevTimeNS) / rate;
			prevTimeNS = timeNS;
			while (delta > 1.0) {
				tick();
				++numTicks;
				--delta;
			}
			render();
			screen.render(b);
			++numFrames;
			if (System.currentTimeMillis() - prevTimeMS > 1000) {
				prevTimeMS += 1000;
				screen.setTitle(TITLE + " | " + numTicks + " ups, " + numFrames + " fps");
				numFrames = numTicks = 0;
			}
			if(player.getHP() <= 0) {
				executing = false;
			}
		}
		//戰鬥結束後，顯示獎勵畫面，然後進入下一次的UI畫面循環
		uiManager.Start(new BattleEnd(uiManager,IslandName.ElfinIsland));
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
	private UIManager uiManager;

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
