package inputs;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class KeyboardServerCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8443362919547855603L;
	
	public KeyboardServerCopy() {
	}
	
	public boolean[] keys = new boolean[65535];
	
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

}
