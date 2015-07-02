package model.entity;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class Trophy extends Entity{

	private Animation current,regular,hit;
	private BufferedImage image;
	private int x,y;
	
	public Trophy(GameController gameControl, Point2D position) {
		super(gameControl, position);
		regular = new Animation(ImageHandler.getImage(ImageType.trophy), 24, 30, 150);
		hit = new Animation(ImageHandler.getImage(ImageType.trophyHit), 24, 30, 25);
		current = regular;
		image = current.getCurrentImage();
		x = (int) position.getX();
		y = (int) position.getY();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image,x, y, null);
	}

	@Override
	public void update() {
		if(current.equals(hit)){
			if(current.hasPlayedOnce())
			{
				hit.setPlayedOnce(false);
				current = regular;
			}
		}
		current.update();
		image = current.getCurrentImage();
	}

	public void setAnimation(boolean isHit) {
		if(isHit)
			current = hit;
		else
			current = regular;
		current.setFrame(0);
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
