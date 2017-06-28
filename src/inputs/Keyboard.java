package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import network.Client;

public class Keyboard extends KeyboardServerCopy implements KeyListener {

	public static Keyboard getInstance() {
		if (keyboard == null) {
			keyboard = new Keyboard();
		}
		return keyboard;
	}

	public static void initKeyboard(Client client, int playerIndex) {
		Keyboard.client = client;
		Keyboard.playerIndex = playerIndex;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyPress keyPress = new KeyPress();
		keyPress.keyIndex = e.getKeyCode();
		keyPress.keyPressed = true;
		keys[keyPress.keyIndex] = keyPress.keyPressed;
		if (client != null) {
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
		if (client != null) {
			keyPress.playerIndex = playerIndex;
			client.send(keyPress.serialise());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private static Client client;
	private static Keyboard keyboard;
	private static int playerIndex;

	private Keyboard() {
	}

}
