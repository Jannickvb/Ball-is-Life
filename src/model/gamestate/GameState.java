package model.gamestate;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameController;
import control.GameStateManager;

public abstract class GameState {
	protected GameController gameControl;
	protected GameStateManager gsm;
	public GameState(GameController gameControl, GameStateManager gsm){
		this.gameControl = gameControl;
		this.gsm = gsm;
	}
	public abstract void draw(Graphics2D g);
	public abstract void update();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
}
