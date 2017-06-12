package main;

import battles.Battle;
import battles.TestBattle;
import entities.characters.Viking;
import entities.characters.players.FatNerd;
import entities.characters.players.GladiatorCat;
import entities.characters.players.Leprechaun;
import entities.characters.players.Player;
import entities.characters.players.SharkPlane;
import events.Event;
import events.EventListener;
import graphics.Screen;
import inputs.Keyboard;
import inputs.Mouse;
import mathematics.Vector2d;
import mathematics.Vector2i;
import network.Client;
import server.Server;

// 這個類別包含 main 方法，這裡的程式碼不要寫太多以保持主類別的簡潔。
public class JavaGo implements Runnable, EventListener {

	// 用來顯示在遊戲視窗的標題列。
	public static final String TITLE = "JavaGo Project";
	public static final boolean IS_SERVER = true;

	public static void main(String[] args) {
		JavaGo javaGo = new JavaGo();
		javaGo.start();
	}

	public JavaGo() {
		if (IS_SERVER) {
			// 測試伺服器和用戶端的程式碼。
			Server server = new Server(37855 /* 隨便挑的埠 */);
			server.start();
			Client client = new Client("localhost", 37855);
			client.connect();
		} else {
			Client client = new Client("localhost", 37855);
			client.connect();
		}

		// 初始化鍵盤事件監聽器。
		keyboard = new Keyboard();

		// 初始化第一級。（以後不會直接開始跑遊戲，會先開使用者介面。）
		battle = new TestBattle("/textures/battles/test_level.png");

		// 把這臺電腦的玩家加入第一級。（Vector2d 是座標類別。）
		final int PLAYER_X = 1100, PLAYER_Y = 900;
		battle.add(new GladiatorCat(battle, new Vector2d(PLAYER_X, PLAYER_Y), keyboard));
		battle.add(new Viking(battle, new Vector2d(PLAYER_X, PLAYER_Y - 100)));

		// 初始化玩家。（Client player: 用戶玩家端。）
		player = battle.getClientPlayer();

		// 初始化遊戲視窗。
		screen = new Screen(defaultScreenWidth, defaultScreenHeight, 2);

		// 把鍵盤事件監聽器它加入screen。
		screen.addKeyListener(keyboard);

		// 初始化滑鼠事件監聽器然後把它加入screen。
		mouse = new Mouse(this);
		screen.addMouseListener(mouse);
		screen.addMouseMotionListener(mouse);
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
				screen.setTitle(TITLE + " | " + numTicks + " ups, " + numFrames + " fps");
				numFrames = numTicks = 0;
			}
		}
		stop();
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

	private boolean executing = false;
	private Battle battle;
	private static final int defaultScreenWidth = 500,
			defaultScreenHeight = (int) ((double) defaultScreenWidth * 9.0 / 16.0);
	private Keyboard keyboard;
	private Mouse mouse;
	private Player player;
	private Screen screen;
	private Thread thread;

	private Vector2i getPlayerCentricOffset() {
		return new Vector2i(player.getCoordinates().add(new Vector2d(player.getWidth() >> 1, player.getHeight() >> 1))
				.subtract(screen.getDimensionsD().multiply(0.5)));
	}

	private void tick() {
		if (player.isRemoved()) {
			player = battle.getClientPlayer();
		}
		battle.update();
	}

}
