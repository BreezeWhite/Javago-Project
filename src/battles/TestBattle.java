package battles;

import entities.characters.players.GladiatorCat;
import inputs.Keyboard;
import mathematics.Vector2d;

public class TestBattle extends Battle {

	public TestBattle(String path) {
		loadTiles(path);

		// 把這臺電腦的玩家加入第一級。（Vector2d 是座標類別。）
		add(new GladiatorCat(new Vector2d(1100, 900), Keyboard.getInstance()));
	}

}
