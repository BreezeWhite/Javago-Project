package graphics;

import mathematics.Vector2i;

public class AnimatedSprite {

	public AnimatedSprite(Vector2i dimensions, SpriteSheet spriteSheet, int length) {
		this.length = length;
		this.spriteSheet = spriteSheet;
		sprite = spriteSheet.getSprites()[(frame %= length)];
		if(length > spriteSheet.getSprites().length) {
			System.err.println("Error! Length of animation exceeds sprite sheet's length.");
			length = spriteSheet.getSprites().length;
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrame(int index) {
		if(index >= spriteSheet.getSprites().length) {
			System.err.println("Index out of bounds in " + this);
		}
		sprite = spriteSheet.getSprites()[index];
	}
	
	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}
	
	public void update() {
		if((time = (time + 1) % frameRate) == 0) {
			frame = (frame + 1) % length;
			sprite = spriteSheet.getSprites()[frame];
		}
	}

	private int frame = 0;
	private int frameRate = 6;
	private int length = -1;
	private Sprite sprite;
	private SpriteSheet spriteSheet;
	private int time = 0;

}
