package battles;

import java.io.Serializable;

public class TestBattle extends Battle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222870703486012634L;

	public TestBattle(String path) {
		loadTiles(path);
	}

}
