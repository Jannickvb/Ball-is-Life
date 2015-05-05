package model;

import java.awt.Graphics2D;

import control.GameController;

public abstract class GameState {
	protected GameController gameControl;
	public GameState(GameController gameControl){
		this.gameControl = gameControl;
	}
	public abstract void draw(Graphics2D g);
	public abstract void update();
}
