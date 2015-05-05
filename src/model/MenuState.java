package model;

import java.awt.Graphics2D;

import control.GameController;

public class MenuState extends GameState{
	
	private GameController gameControl;
	
	public MenuState(GameController gameControl) {
		super(gameControl);
		this.gameControl = gameControl;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawRect(0, 0, gameControl.width, gameControl.height);
	}
	@Override
	public void update() {

	}
}
