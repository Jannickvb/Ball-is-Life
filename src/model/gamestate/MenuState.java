package model.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import control.GameController;
import control.GameStateManager;

public class MenuState extends GameState{
	
	private GameController gameControl;
	private String[] menuItems = {"Start","Load","Highscore","Exit"};
	private int menuIndex;
	private int stringWidth;
	private int spacing = 100;
	public MenuState(GameController gameControl) {
		super(gameControl);
		this.menuIndex = 0;
		this.gameControl = gameControl;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(gameControl.getWidth()/2, gameControl.getHeight()/2);
		g2.transform(tx);
		drawMenuItems(g2);
	}
	
	public void drawMenuItems(Graphics2D g2){
		g2.translate(0, -menuItems.length *(spacing/2));
		for(int i = 0; i < menuItems.length ; i++)
		{
			stringWidth = g2.getFontMetrics().stringWidth(menuItems[i]);
			if(i == menuIndex){
				g2.setColor(Color.RED);
				g2.drawString(menuItems[i], -stringWidth/2, (i*((spacing/6) * 5)+((spacing/6)*5)));
			}else{
				g2.setColor(Color.BLACK);
				g2.drawString(menuItems[i], -stringWidth/2, (i*((spacing/6) * 5)+((spacing/6)*5)));
			}
		}
	}
	
	@Override
	public void update() {
		
	}

	public void select() {
		if(menuIndex == 0){
			gameControl.getGameStateManager().start();
		}
		else if(menuIndex == menuItems.length-1){
			System.exit(0);
		}
		else{
			gameControl.getGameStateManager().select(menuIndex);
		}
		
	}
	
	@Override
	public void keyPressed(int e) {
		if(e == KeyEvent.VK_ENTER)
			select();
		if(e == KeyEvent.VK_UP)
		{
			menuIndex--;
			if(menuIndex == -1) {
				menuIndex = menuItems.length - 1;
			}
		}
		if(e == KeyEvent.VK_DOWN)
		{
			menuIndex++;
			if(menuIndex == menuItems.length) {
				menuIndex = 0;
			}
		}
	}

	@Override
	public void keyReleased(int e) {
		// TODO Auto-generated method stub
		
	}
}
