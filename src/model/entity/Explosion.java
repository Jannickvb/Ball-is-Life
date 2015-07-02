package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class Explosion extends Entity{

	private Animation animation;
	private BufferedImage image;
	private Point2D position;
	private int x,y;
	
	public Explosion(GameController gameControl, Point2D position) {
		super(gameControl, position);
		animation = new Animation(ImageHandler.getImage(ImageType.explosion),64,64,25);
		image = animation.getCurrentImage();
		this.position = position;
		x = (int)(position.getX()-16);
		y = (int)(position.getY()-16);
		
	}

	public boolean hasPlayedOnce() {
		return animation.hasPlayedOnce();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image,x,y,null);
	}

	@Override
	public void update() {
		animation.update();
		image = animation.getCurrentImage();
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
