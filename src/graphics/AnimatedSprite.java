package graphics;

import java.io.Serializable;

import mathematics.Vector2i;

public class AnimatedSprite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4672645496995301586L;

	public AnimatedSprite(Vector2i dimensions, SpriteSheet spriteSheet, int length) {
		this.length = length;
		this.spriteSheet = spriteSheet;
		sprite = spriteSheet.getSprites()[(frame %= length)];
		if (length > spriteSheet.getSprites().length) {
			System.err.println("Error! Length of animation exceeds sprite sheet's length.");
			length = spriteSheet.getSprites().length;
		}
	}

	public int getFrame() {
		return frame;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrame(int index) {
		if (index >= spriteSheet.getSprites().length) {
			System.err.println("Index out of bounds in " + this);
		}
		sprite = spriteSheet.getSprites()[index];
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}

	public int update() {
		if ((time = (time + 1) % frameRate) == 0) {
			frame = (frame + 1) % length;
			sprite = spriteSheet.getSprites()[frame];
			if (frame == 0) {
				++counter;
			}
		}
		return counter;
	}

	private int counter = 0;
	private int frame = 0;
	private int frameRate = 6;
	private int length = -1;
	private Sprite sprite;
	private SpriteSheet spriteSheet;
	private int time = 0;

}
