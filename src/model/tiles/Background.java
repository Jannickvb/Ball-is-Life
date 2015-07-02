package model.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import control.GameController;

public class Background {
	
	private GameController gameControl;
	private Rectangle2D rect;
	public Background(GameController gameControl)
	{
		this.gameControl = gameControl;
		rect = new Rectangle2D.Double(0, 0, 1920,1080);
	}
	
	public void draw(Graphics2D g2){
		g2.setPaint(Color.black);
		g2.fill(rect);
		g2.draw(rect);
	}
}
