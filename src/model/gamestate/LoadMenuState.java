package model.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import control.GameController;
import control.GameStateManager;

public class LoadMenuState extends GameState{
	
	private GameStateManager gsm;
	private int menuIndex,stringWidth;
	private int bHeight = 150,bWidth = 150;
	private String[] saves = {"Empty","Empty","Empty"};
	public LoadMenuState(GameController gameControl, GameStateManager gsm){
		super(gameControl,gsm);
		this.gsm = gsm;
	}
	
	@Override
	public void draw(Graphics2D g2){
		AffineTransform tx = new AffineTransform();
		tx.translate(gameControl.getWidth()/2, gameControl.getHeight()/2);
		g2.transform(tx);
		drawLoadStates(g2);
		g2.setColor(Color.BLACK);
		g2.drawRect(gameControl.getWidth()/2 - 51, gameControl.getHeight()/2 - 50, 50, 50);
		g2.drawString("ESC", gameControl.getWidth()/2 - 35, gameControl.getHeight()/2-20);
	}
	
	public void drawLoadStates(Graphics2D g2){
		for(int i = 0; i < saves.length ; i++)
		{
			stringWidth = g2.getFontMetrics().stringWidth(saves[i]);
			if(i == menuIndex){
				g2.setColor(Color.RED);
				g2.drawString(saves[i], (225*i-(bWidth*2))+(bWidth/2)-(stringWidth/2), 40);
				g2.drawRect(225*i-(bWidth*2), -(bHeight/2), bWidth, bHeight);
			}else{
				g2.setColor(Color.BLACK);
				g2.drawString(saves[i], (225*i-(bWidth*2))+(bWidth/2)-(stringWidth/2), 40);
				g2.drawRect(225*i-(bWidth*2), -(bHeight/2), bWidth, bHeight);
			}
		}
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			gsm.select(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			menuIndex--;
			if(menuIndex == -1) {
				menuIndex = saves.length - 1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			menuIndex++;
			if(menuIndex == saves.length) {
				menuIndex = 0;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}
}
