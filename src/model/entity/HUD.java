package model.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.MainMap;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class HUD extends Entity{

	private BufferedImage image;
	private int x,y,score,health;
	private AffineTransform tx;
	private Rectangle2D healthBar;
	private Font font;
	private Color color;
	private MainMap map;
	private String nukeStatus;
	public HUD(GameController gameControl, Point2D position, MainMap map) {
		super(gameControl, position);
		this.map = map;
		image = ImageHandler.getImage(ImageType.hud);
		tx = new AffineTransform();
		tx.translate(position.getX(), position.getY());
		x = (int)position.getX();
		y = (int)position.getY();
		score = 0;
		font = new Font("Dialog", Font.BOLD, 14);
		health = (int) (map.getTrophy().getHealth()*0.55);
		healthBar = new Rectangle2D.Double(x+65, y+48, health, 12);
		color = new Color(229,36,32);
		nukeStatus = "LOADING";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image,tx,null);
		g2.setColor(Color.white);
		g2.setFont(font);
		g2.drawString("score: " + score, x+20, y+20);
		g2.setColor(color);
		g2.fill(healthBar);
		g2.draw(healthBar);
		g2.drawString(nukeStatus, x - (g2.getFontMetrics().stringWidth(nukeStatus)/2)+81, y+138);
	}
	
	@Override
	public void update() {
		if(map.nukeReady())
			nukeStatus = "READY";
		else
			nukeStatus = "LOADING";
		score = map.getTrophy().getScore();
		health = (int) (map.getTrophy().getHealth()*0.55);
		healthBar = new Rectangle2D.Double(x+65, y+48, health, 12);
	}

	@Override
	public void keyPressed(int e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int e) {
		// TODO Auto-generated method stub
		
	}

}
