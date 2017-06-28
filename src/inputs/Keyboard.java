package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65535];

	public boolean downPressed() {
		return keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
	}

	public static Keyboard getInstance() {
		if(theKeyboard == null) {
			theKeyboard = new Keyboard();
		}
		return theKeyboard;
	}

	public boolean leftPressed() {
		return keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
	}

	public boolean rightPressed() {
		return keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	public void update() {
	}

	public boolean upPressed() {
		return keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private static Keyboard theKeyboard;

}
