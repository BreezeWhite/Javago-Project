package battles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.characters.players.Player;

public class EntityContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7231419285748756745L;
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Player> players = new ArrayList<Player>();

}
