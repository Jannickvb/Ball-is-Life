package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.gamestate.MenuState;

public class MyKeyListener implements KeyListener{

	
	GameStateManager gsm;
	public MyKeyListener(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
