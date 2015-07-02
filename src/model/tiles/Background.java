package model.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import control.GameController;

public class Background {
	
	private GameController gameControl;
	private BufferedImage image;
	public Background(GameController gameControl, BufferedImage image)
	{
		this.gameControl = gameControl;
		this.image = image;
	}
	
	public void draw(Graphics2D g2){
		g2.drawImage(image,0,0,null);
	}
}
