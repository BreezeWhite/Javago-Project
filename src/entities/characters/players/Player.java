package entities.characters.players;

import battles.Battle;
import entities.characters.Character;
import events.Event;
import events.EventDespatcher;
import events.EventListener;
import events.types.MousePressedEvent;
import events.types.MouseReleasedEvent;
import graphics.AnimatedSpriteSet;
import graphics.Sprite;
import inputs.Keyboard;
import mathematics.Vector2d;

public abstract class Player extends Character implements EventListener {

	public Player(Battle battle, Vector2d position, Keyboard keyboard, AnimatedSpriteSet animatedSpriteSet) {
		super(battle, position, Sprite.getInvisible(), animatedSpriteSet);
		this.keyboard = keyboard;
	}

	// Player with static sprite.
	public Player(Battle battle, Vector2d position, Keyboard keyboard, Sprite sprite) {
		super(battle, position, sprite, null);
		this.keyboard = keyboard;
	}

	public Keyboard getKeyboard() {
		return keyboard;
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
		deltaX = 0;
		deltaY = 0;
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
		super.update();
	}

	protected Keyboard keyboard;

}
