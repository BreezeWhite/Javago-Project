package entities.characters.players;

import battles.Battle;
import entities.characters.Character;
import events.Event;
import events.EventDespatcher;
import events.EventListener;
import events.types.MousePressedEvent;
import events.types.MouseReleasedEvent;
import graphics.AnimatedSprite;
import graphics.AnimatedSpriteSet;
import graphics.Sprite;
import inputs.Keyboard;
import mathematics.Vector2d;

public abstract class Player extends Character implements EventListener {

	public Player(Battle battle, Vector2d position, Keyboard keyboard, AnimatedSpriteSet animatedSpriteSet) {
		super(battle, position, Sprite.getVoid());
		this.keyboard = keyboard;
		this.animatedSpriteSet = animatedSpriteSet;
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
		super.update();
		if(usingAbility) {
			sprite = abilityAnimation.getSprite();
			move(abilityDeltaX, abilityDeltaY);
			if(abilityAnimation.update() >= abilityAnimationRepetitions) {
				usingAbility = false;
			}
		}
		else {
			animatedSpriteSet.setDirection(direction);
			if (walking) {
				animatedSpriteSet.update();
			} else {
				animatedSpriteSet.setFrame(defaultFrame);
			}
			if (timeToNextShot > 0) {
				--timeToNextShot;
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
			sprite = animatedSpriteSet.getSprite();
		}
	}

	private AnimatedSpriteSet animatedSpriteSet;
	protected AnimatedSprite abilityAnimation;
	protected int abilityAnimationRepetitions = 1;
	protected double abilityDeltaX = 0, abilityDeltaY = 0;
	protected int defaultFrame = 0;
	protected Keyboard keyboard;
	protected boolean usingAbility = false;
	private boolean walking = false;

}
