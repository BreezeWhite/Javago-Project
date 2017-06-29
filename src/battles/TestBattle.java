package battles;

import entities.characters.Viking;
import entities.characters.players.FatNerd;
import entities.characters.players.GladiatorCat;
import entities.characters.players.Leprechaun;
import entities.characters.players.SharkPlane;
import inputs.KeyboardServerCopy;
import mathematics.Vector2d;

public class TestBattle extends Battle {

	public TestBattle(String path) {
		loadTiles(path);

		// 把這臺電腦的玩家加入第一級。（Vector2d 是座標類別。）
		final int PLAYER_X = 1100, PLAYER_Y = 900;
		add(new GladiatorCat(new Vector2d(PLAYER_X - 200, PLAYER_Y), KeyboardServerCopy.serverKeyboards.get(0)));
		add(new SharkPlane(new Vector2d(PLAYER_X - 100, PLAYER_Y), KeyboardServerCopy.serverKeyboards.get(1)));
		add(new Leprechaun(new Vector2d(PLAYER_X, PLAYER_Y), KeyboardServerCopy.serverKeyboards.get(2)));
		add(new FatNerd(new Vector2d(PLAYER_X + 100, PLAYER_Y), KeyboardServerCopy.serverKeyboards.get(3)));
		add(new Viking(new Vector2d(PLAYER_X, PLAYER_Y - 100)));
	}

}
