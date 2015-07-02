package model.entity;

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
	
	public Enemy(GameController gameControl, Point2D position) {
		super(gameControl, position);
		this.position = position;
		this.speed = 1 + Math.random() * 10;
		this.target = new Point2D.Double(gameControl.getWidth() / 2,
				gameControl.getHeight() / 2 - 100);
		
		this.animation = new Animation(ImageHandler.getImage(ImageType.player),32,32,25);
		this.image = animation.getCurrentImage();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform tx = new AffineTransform();
		tx.translate(position.getX()-32, position.getY()-32);
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
