package entities.characters.players;

import battles.Battle;
import entities.characters.Character;
import events.Event;
import events.EventDespatcher;
import events.EventListener;
import events.types.MousePressedEvent;
import events.types.MouseReleasedEvent;
import graphics.AnimatedSprite;
import graphics.Sprite;
import graphics.SpriteSheet;
import inputs.Keyboard;
import mathematics.Vector2d;

public abstract class Player extends Character implements EventListener {

	public Player(Battle battle, Vector2d position) {
		super(battle, position, Sprite.getVoid());
		keyboard = Keyboard.getInstance();
	}

	@Override
	public void onEvent(Event event) {
		EventDespatcher despatcher = new EventDespatcher(event);
		despatcher.despatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		despatcher.despatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent event) {
		return false;
	}

	public boolean onMouseReleased(MouseReleasedEvent event) {
		return false;
	}

	@Override
	public void update() {
		if (walking) {
			animatedSprite.update();
		} else {
			animatedSprite.setFrame(0);
		}
		if (direction == Direction.UP) {
			animatedSprite = up;
		} else if (direction == Direction.UP_RIGHT) {
			animatedSprite = upRight;
		} else if (direction == Direction.RIGHT) {
			animatedSprite = right;
		} else if (direction == Direction.DOWN_RIGHT) {
			animatedSprite = downRight;
		} else if (direction == Direction.DOWN) {
			animatedSprite = down;
		} else if (direction == Direction.DOWN_LEFT) {
			animatedSprite = downLeft;
		} else if (direction == Direction.LEFT) {
			animatedSprite = left;
		} else if (direction == Direction.UP_LEFT) {
			animatedSprite = upLeft;
		}
		if (reloadTime > 0) {
			--reloadTime;
		}
		double deltaX = 0, deltaY = 0;
		if (keyboard.upPressed()) {
			deltaY -= speed;
		}
		if (keyboard.leftPressed()) {
			deltaX -= speed;
		}
		if (keyboard.downPressed()) {
			deltaY += speed;
		}
		if (keyboard.rightPressed()) {
			deltaX += speed;
		}
		if (deltaX != 0 || deltaY != 0) {
			move(deltaX, deltaY);
			if (!walking) {
				walking = true;
			}
		} else {
			walking = false;
		}
		sprite = animatedSprite.getSprite();
	}

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUp, 5);
	private AnimatedSprite upRight = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUpRight, 5);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorRight, 5);
	private AnimatedSprite downRight = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDownRight, 5);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDown, 5);
	private AnimatedSprite downLeft = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorDownLeft, 5);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorLeft, 5);
	private AnimatedSprite upLeft = new AnimatedSprite(SpriteSheet.warriorDimensions, SpriteSheet.warriorUpLeft, 5);

	private AnimatedSprite animatedSprite = down;

	private Keyboard keyboard;
	private boolean walking = false;

}
