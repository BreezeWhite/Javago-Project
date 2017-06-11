package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65535];
	
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

}
