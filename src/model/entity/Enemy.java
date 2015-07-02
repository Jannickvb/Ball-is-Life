package model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import model.Animation;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class Enemy extends Entity {

	private Point2D position, target;
	private double speed, rotation;
	private BufferedImage image;
	private Animation animation;
	private Rectangle2D rect;
	private AffineTransform tx;
	public Enemy(GameController gameControl, Point2D position) {
		
		super(gameControl, position);
		this.position = position;
		this.speed = 3 + Math.random() * 5;
		this.target = new Point2D.Double(position.getX(),
				position.getY()-gameControl.getHeight());
		this.rotation = -90;
		this.animation = new Animation(ImageHandler.getImage(ImageType.enemy),32,32,50);
		this.image = animation.getCurrentImage();
		
	}

	@Override
	public void init() {

	}

	@Override
	public void draw(Graphics2D g2) {
		tx = new AffineTransform();
		tx.translate(position.getX()-16, position.getY()-16);
		tx.rotate(rotation, 32, 32);
		g2.drawImage(image, tx ,null);
	}

	@Override
	public void update() {
		Point2D difference = new Point2D.Double(target.getX() - position.getX(),
												target.getY() - position.getY());

		double newRotation = Math.atan2(difference.getY(), difference.getX());
		double rotDifference = rotation - newRotation;
		while (rotDifference > Math.PI)
			rotDifference -= 2 * Math.PI;
		while (rotDifference < -Math.PI)
			rotDifference += 2 * Math.PI;

		if (Math.abs(rotDifference) < 0.1)
			rotation = newRotation;
		else if (rotDifference < 0)
			rotation += 0.1;
		else if (rotDifference > 0)
			rotation -= 0.1;
		
		position = new Point2D.Double(position.getX() + speed* Math.cos(rotation),
									  position.getY() + speed * Math.sin(rotation));
		
		animation.update();
		image = animation.getCurrentImage();
		if(position.getY()>416 && position.getY()<448){
			speed = 1;
		}
		if(!(target.getX()>320 && target.getX()<480) && position.getY() < 416)
		{
			target.setLocation((Math.random()*160)+320, 64);
			speed = 2 + (Math.random()*5);
		}
		rect = new Rectangle2D.Double(position.getX()-16, position.getY()+16, 32, 32);
	}

	public Rectangle2D getRect() {
		return rect;
	}
	
	public AffineTransform getTransform() {
		return tx;
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
