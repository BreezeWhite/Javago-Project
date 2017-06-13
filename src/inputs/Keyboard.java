package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import main.JavaGo;
import network.Client;

public class Keyboard extends KeyboardServerCopy implements KeyListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8443362919547855603L;
	private int playerIndex;

	public Keyboard(Client client, int playerIndex) {
		this.client = client;
		this.playerIndex = playerIndex;
	}

	private Client client;

	@Override
	public void keyPressed(KeyEvent e) {
		KeyPress keyPress = new KeyPress();
		keyPress.keyIndex = e.getKeyCode();
		keyPress.keyPressed = true;
		keys[keyPress.keyIndex] = keyPress.keyPressed;
		if (!JavaGo.IS_SERVER) {
			keyPress.playerIndex = playerIndex;
			client.send(keyPress.serialise());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyPress keyPress = new KeyPress();
		keyPress.keyIndex = e.getKeyCode();
		keyPress.keyPressed = false;
		keys[keyPress.keyIndex] = keyPress.keyPressed;
		if (!JavaGo.IS_SERVER) {
			keyPress.playerIndex = playerIndex;
			client.send(keyPress.serialise());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
