package main;

import java.util.List;

import battles.Battle;
import battles.TestBattle;
import entities.Entity;
import entities.Update;
import entities.characters.players.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import graphics.SpriteSheet;
import gui.IslandSelector;
import gui.Window;
import inputs.Keyboard;
import inputs.KeyboardServerCopy;
import inputs.Mouse;
import mathematics.Vector2d;
import mathematics.Vector2i;
import network.Client;
import server.Server;
import settings.Settings;

// 這個類別包含 main 方法，這裡的程式碼不要寫太多以保持主類別的簡潔。
public class JavaGo implements Runnable, EventListener {

	// 用來顯示在遊戲視窗的標題列。
	public static final String TITLE = "JavaGo Project";

	public static void main(String[] args) {
		JavaGo javaGo = JavaGo.getInstance();
		Mouse.setEventListener(javaGo);
		JavaGo.setScreen(Screen.getInstance());
	}

	public Battle getBattle() {
		return battle;
	}

	public void connect() {
		if (Settings.isServer()) {
			// 測試伺服器和用戶端的程式碼。
			server = new Server();
			server.start();
		} else {
			client = new Client();
			client.connect();
		}
	}

	public static JavaGo getInstance() {
		if (theJavaGo == null) {
			theJavaGo = new JavaGo();
		}
		return theJavaGo;
	}

	private JavaGo() {
		Settings.getInstance();
		SpriteSheet.initSpriteSheetLists();

		connect();

		Keyboard.initKeyboard(client, Settings.getPlayerIndex());
		for (int i = 0; i < Battle.getNumPlayers(); ++i) {
			if (i == Settings.getPlayerIndex()) {
				KeyboardServerCopy.serverKeyboards.add(Keyboard.getInstance());
			} else {
				KeyboardServerCopy.serverKeyboards.add(new KeyboardServerCopy());
			}
		}

		// 初始化第一級。（以後不會直接開始跑遊戲，會先開使用者介面。）
		battle = new TestBattle("/textures/battles/test_level.png");

		// 初始化玩家。（Client player: 用戶玩家端。）
		player = battle.getClientPlayer();

		// 初始化遊戲視窗。
		window = Window.getInstance();
		window.changeTo(IslandSelector.getInstance(), true);
		
		if(!Settings.isServer()) {
			client.start();
		}
	}

	public boolean isExecuting() {
		return executing;
	}

	// 從最上面的那一層開始，把事件傳到所有的層次。
	@Override
	public void onEvent(Event event) {
		battle.onEvent(event);
	}

	// 把玩家設為螢幕中心的位置。
	// 叫所有的層次傳所有包含可畫的物件到screen去，
	// 接下來，screen會把所有可畫物件的像素資料複製到它的成員 pixelMap[]。
	// 最後，以 screen.render() 叫 screen 把 pixelMap[] 裡的資料畫到螢幕上。
	public void render() {
		if (screen == null) {
			return;
		}
		battle.setOffset(getPlayerCentricOffset());
		battle.render(screen);
		screen.render();
	}

	// 當 javaGo.start() 呼叫 thread.start() 時， run() 就會被呼叫了。
	@Override
	public void run() {
		long prevTimeNS = System.nanoTime(); // 現在的時間（奈秒）。
		long prevTimeMS = System.currentTimeMillis(); // 現在的時間（毫秒）。
		final double rate = 1000000000.0 / 60.0; // 一秒的六十分之一。
		double delta = 0; // 時間的德爾塔（變化）。
		int numFrames = 0; // 幀的計數器。
		int numTicks = 0; // tick() 呼叫次數的計數器。
		while (executing) {
			long timeNS = System.nanoTime(); // 現在的時間（奈秒）。
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
				window.setTitle(TITLE + " | " + numTicks + " ups, " + numFrames + " fps");
				numFrames = numTicks = 0;
			}
		}
	}

	public static void setScreen(Screen screen) {
		JavaGo.screen = screen;
	}

	public synchronized void start() {
		executing = true;
		thread = new Thread(this, "Display");
		thread.start(); // 這個方法會呼叫本類別的 run() 方法。;
	}

	public synchronized void stop() {
		executing = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Client client = null;
	private boolean executing = false;
	private Battle battle;
	private static JavaGo theJavaGo;
	private Player player;
	private static Screen screen;
	public Server server = null;
	private Thread thread;
	private Window window;

	private Vector2i getPlayerCentricOffset() {
		return new Vector2i(player.getCoordinates().add(new Vector2d(player.getWidth() >> 1, player.getHeight() >> 1))
				.subtract(screen.getDimensionsD().multiply(0.5)));
	}

	private void tick() {
		if (player.isRemoved()) {
			player = battle.getClientPlayer();
		}
		if (Settings.isServer()) {
			battle.update();
			List<Entity> entities = battle.getEntities();
			for (int i = 0; i < entities.size(); ++i) {
				Update update = entities.get(i).generateUpdate();
				update.index = entities.get(i).getID();
				update.player = false;
				update.newProjectile = false;
				server.sendAll(update.serialise());
			}
			List<Player> players = battle.getPlayers();
			for (int i = 0; i < players.size(); ++i) {
				Update update = players.get(i).generateUpdate();
				update.index = i;
				update.player = true;
				server.sendAll(update.serialise());
			}
		} else {

		}
	}

}
