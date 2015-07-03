package model.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import model.tiles.Background;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class MenuState extends GameState{
	
	private GameController gameControl;
	private String[] menuItems = {"Start","Highscore","Exit"};
	private int menuIndex;
	private int stringWidth;
	private int spacing = 100;
	private Background bg;
	private Font font;
	public MenuState(GameController gameControl) {
		super(gameControl);
		this.menuIndex = 0;
		this.gameControl = gameControl;
		bg = new Background(gameControl,ImageHandler.getImage(ImageType.background));
		font = new Font("Dialog",Font.BOLD,34);
	}

	@Override
	public void draw(Graphics2D g2) {
		bg.draw(g2);
		AffineTransform tx = new AffineTransform();
		tx.translate(gameControl.getWidth()/2, gameControl.getHeight()/2);
		g2.transform(tx);
		drawMenuItems(g2);
	}
	
	public void drawMenuItems(Graphics2D g2){
		g2.translate(0, -menuItems.length *(spacing/2));
		g2.setFont(font);
		for(int i = 0; i < menuItems.length ; i++)
		{
			if(i == menuIndex){
				g2.setColor(Color.RED);
				font.deriveFont(24f);
				stringWidth = g2.getFontMetrics().stringWidth(menuItems[i]);
				g2.drawString(menuItems[i], -stringWidth/2, (i*((spacing/6) * 5)+((spacing/6)*5)));
			}else{
				g2.setColor(Color.WHITE);
				font.deriveFont(18f);
				stringWidth = g2.getFontMetrics().stringWidth(menuItems[i]);
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
