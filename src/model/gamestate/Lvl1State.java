package model.gamestate;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameController;
import control.GameStateManager;

public class Lvl1State extends GameState{


	private GameController gameControl;
	private GameStateManager gsm;
	
	public Lvl1State(GameController gameControl, GameStateManager gsm) {
		super(gameControl, gsm);
		this.gameControl = gameControl;
		this.gsm = gsm;
	}


	@Override
	public void draw(Graphics2D g) {
		g.drawRect(0, 0, 200, 20);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Level 1");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
