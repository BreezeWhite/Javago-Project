package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import main.JavaGo;
import network.Client;

public class Keyboard implements KeyListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8443362919547855603L;
	private int playerIndex;

	public Keyboard(Client client, int playerIndex) {
		this.client = client;
		this.playerIndex = playerIndex;
	}

	private boolean[] keys = new boolean[65535];
	private Client client;

	public boolean downPressed() {
		return keys[KeyEvent.VK_DOWN];
	}

	public boolean ePressed() {
		return keys[KeyEvent.VK_E];
	}

	public boolean leftPressed() {
		return keys[KeyEvent.VK_LEFT];
	}

	public boolean qPressed() {
		return keys[KeyEvent.VK_Q];
	}

	public boolean rightPressed() {
		return keys[KeyEvent.VK_RIGHT];
	}

	public boolean rPressed() {
		return keys[KeyEvent.VK_R];
	}

	public boolean wPressed() {
		return keys[KeyEvent.VK_W];
	}

	public void update() {
	}

	public boolean upPressed() {
		return keys[KeyEvent.VK_UP];
	}

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
		keys[e.getKeyCode()] = keyPress.keyPressed;
		if (!JavaGo.IS_SERVER) {
			keyPress.playerIndex = playerIndex;
			client.send(keyPress.serialise());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
