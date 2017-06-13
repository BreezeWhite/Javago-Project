package graphics;

import java.io.Serializable;

import entities.Direction;

public class AnimatedSpriteSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3521052758018396316L;

	public AnimatedSpriteSet(AnimatedSprite up, AnimatedSprite upRight, AnimatedSprite right, AnimatedSprite downRight,
			AnimatedSprite down, AnimatedSprite downLeft, AnimatedSprite left, AnimatedSprite upLeft) {
		this.up = up;
		this.upRight = upRight;
		this.right = right;
		this.downRight = downRight;
		this.down = down;
		this.downLeft = downLeft;
		this.left = left;
		this.upLeft = upLeft;
		current = down;
	}
	
	public Sprite getSprite() {
		return current.getSprite();
	}
	
	public void setDirection(Direction direction) {
		if (direction == Direction.UP) {
			current = up;
		} else if (direction == Direction.UP_RIGHT) {
			current = upRight;
		} else if (direction == Direction.RIGHT) {
			current = right;
		} else if (direction == Direction.DOWN_RIGHT) {
			current = downRight;
		} else if (direction == Direction.DOWN) {
			current = down;
		} else if (direction == Direction.DOWN_LEFT) {
			current = downLeft;
		} else if (direction == Direction.LEFT) {
			current = left;
		} else if (direction == Direction.UP_LEFT) {
			current = upLeft;
		}
	}
	
	public void setFrame(int index) {
		current.setFrame(index);
	}
	
	public void update() {
		current.update();
	}
	
	private AnimatedSprite up;
	private AnimatedSprite upRight;
	private AnimatedSprite right;
	private AnimatedSprite downRight;
	private AnimatedSprite down;
	private AnimatedSprite downLeft;
	private AnimatedSprite left;
	private AnimatedSprite upLeft;
	
	private AnimatedSprite current;

	public int getFrame() {
		return current.getFrame();
	}

}
