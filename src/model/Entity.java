package model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import control.GameController;

public abstract class Entity {
	
	protected Point2D position;
	protected GameController gameControl;
	public Entity(GameController gameControl, Point2D position){
		this.position = position;
		this.gameControl = gameControl;
	}
	
	public Point2D getPosition(){return position;}
	
	public abstract void init();
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract void keyPressed(int e);
	public abstract void keyReleased(int e);
}
