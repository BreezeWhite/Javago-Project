package main;
import java.util.ArrayList;
import java.util.List;

import battles.Battle;
import battles.TestBattle;
import entities.characters.Player;
import events.Event;
import events.EventListener;
import graphics.Screen;
import graphics.layers.Layer;
import inputs.Keyboard;
import inputs.Mouse;
import mathematics.Vector2d;
import mathematics.Vector2i;
import network.Client;
import server.Server;

// �o�����O�]�t main ��k�A�o�̪��{���X���n�g�Ӧh�H�O���D���O��²��C
public class JavaGo implements Runnable, EventListener {

	// �Ψ���ܦb�C�����������D�C�C
	public static final String TITLE = "JavaGo Project";

	public static void main(String[] args) {
		JavaGo javaGo = new JavaGo();
		javaGo.start();
	}

	public JavaGo() {
		// ���զ��A���M�Τ�ݪ��{���X�C
		Server server = new Server(37855 /* �H�K�D���� */);
		server.start();
		Client client = new Client("localhost", 37855);
		client.connect();
		
		// ��l�ƹC�������C
		screen = new Screen(defaultScreenWidth, defaultScreenHeight, 2);
		
		// ��l����L�ƥ��ť���M��⥦�[�Jscreen�C
		keyboard = new Keyboard();
		screen.addKeyListener(keyboard);

		// ��l�Ʒƹ��ƥ��ť���M��⥦�[�Jscreen�C
		mouse = new Mouse(this);
		screen.addMouseListener(mouse);
		screen.addMouseMotionListener(mouse);

		// ��l�ƲĤ@�šC�]�H�ᤣ�|�����}�l�]�C���A�|���}�ϥΪ̤����C�^
		battle = new TestBattle("/textures/battles/test_level.png");
		
		// ��o�O�q�������a�[�J�Ĥ@�šC�]Vector2d �O�y�����O�C�^
		battle.add(new Player(battle, new Vector2d(1100, 900), keyboard));

		// ��l�ƪ��a�C�]Client player: �Τ᪱�a�ݡC�^
		player = battle.getClientPlayer();

		// Layer ���h�����O�C�e���W�����P��Layers�A
		// �ӥB�C�@��Layer�t�d�B�z�ƹ��M��L�ƥ�C
		// Layer ���\��B�z�e���W���P���\��A�p���B�C���e�������C
		// ������n���h���O�H�]���A�I�����h���ɡA���藍�Ʊ�Q�C���h�������g�����O�C
		addLayer(battle);

	}
	
	// �b�e���̤W���[�W�s���@�h�C
	public void addLayer(Layer layer) {
		layers.add(layer);
	}

	// �q�̤W�������@�h�}�l�A��ƥ�Ǩ�Ҧ����h���C
	@Override
	public void onEvent(Event event) {
		for(int i = layers.size() - 1; i >= 0; --i) {
			layers.get(i).onEvent(event);
		}
	}

	// �⪱�a�]���ù����ߪ���m�C
	// �s�Ҧ����h���ǩҦ��]�t�i�e�������screen�h�A
	// ���U�ӡAscreen�|��Ҧ��i�e���󪺹�����ƽƻs�쥦������ pixelMap[]�C
	// �̫�A�H screen.render() �s screen �� pixelMap[] �̪���Ƶe��ù��W�C
	public void render() {
		battle.setOffset(getPlayerCentricOffset());
		for(int i = 0; i < layers.size(); ++i) {
			layers.get(i).render(screen);
		}
		screen.render();
	}

	// �� javaGo.start() �I�s thread.start() �ɡA run() �N�|�Q�I�s�F�C
	@Override
	public void run() {
		long prevTimeNS = System.nanoTime(); // �{�b���ɶ��]�`��^�C
		long prevTimeMS = System.currentTimeMillis(); // �{�b���ɶ��]�@��^�C
		final double rate = 1000000000.0 / 60.0; // �@�����Q�����@�C
		double delta = 0; // �ɶ����w����]�ܤơ^�C
		int numFrames = 0; // �V���p�ƾ��C
		int numTicks = 0; // tick() �I�s���ƪ��p�ƾ��C
		while (executing) {
			long timeNS = System.nanoTime(); // �{�b���ɶ��]�`��^�C
			delta += (timeNS - prevTimeNS) / rate;
			prevTimeNS = timeNS;
			while (delta > 1.0) {
				tick();
				++numTicks;
				--delta;
			}
			render();
			++numFrames;
			if(System.currentTimeMillis() - prevTimeMS > 1000) {
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
		thread.start(); // �o�Ӥ�k�|�I�s�����O�� run() ��k�C;
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
	private static final int defaultScreenWidth = 500, defaultScreenHeight = (int)((double)defaultScreenWidth * 9.0 / 16.0);
	private Keyboard keyboard;
	private List<Layer> layers = new ArrayList<Layer>();
	private Mouse mouse;
	private Player player;
	private Screen screen;
	private Thread thread;

	private Vector2i getPlayerCentricOffset() {
		return new Vector2i(player.getCoordinates().add(new Vector2d(player.getWidth() >> 1, player.getHeight() >> 1)).subtract(screen.getDimensionsD().multiply(0.5)));
	}

	private void tick() {
		for(int i = 0; i < layers.size(); ++i) {
			layers.get(i).update();
		}
	}

}
