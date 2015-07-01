package model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import control.GameController;

public class Player extends Entity{

	private int x,y;
	public final int MAXSPEED = 14;
	private boolean up,down,left,right;
	AffineTransform tx = new AffineTransform();
	
	public Player(GameController gameControl,Point2D position){
		super(gameControl,position);
		tx.translate(position.getX(), position.getY());
		position.setLocation(0, 0);
		x=0;y=0;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setTransform(tx);
		g2.drawRect((int)position.getX(), (int)position.getY(), 100, 100);
	}

	@Override
	public void update() {
		setPosition();
//		System.out.println("\nx :" + x +"\ny " + y);
	}

	@Override
	public void keyPressed(int e) {
		if(e == KeyEvent.VK_UP)
			up = true;
		if(e == KeyEvent.VK_DOWN)
			down = true;
		if(e == KeyEvent.VK_LEFT)
			left = true;
		if(e == KeyEvent.VK_RIGHT)
			right = true;
	}

	@Override
	public void keyReleased(int e) {
		if(e == KeyEvent.VK_UP)
			up = false;
		if(e == KeyEvent.VK_DOWN)
			down = false;
		if(e == KeyEvent.VK_LEFT)
			left = false;
		if(e == KeyEvent.VK_RIGHT)
			right = false;
	}
	
	public void setPosition() {

		//VERTICAL MOVEMENT
		if(y<=MAXSPEED)
		{
			if(up)
				y-=2;
			if(down)
				y+=2;
			if(!up && !down)
			{
				if(y>0)
					y-=1;
				if(y<0)
					y+=1;
			}
		}
		if(y<-MAXSPEED)y=-MAXSPEED;
		if(y>MAXSPEED)y=MAXSPEED;
		//HORIZONTAL MOVEMENT
		if(x<=MAXSPEED)
		{
			if(left)
				x-=2;
			if(right)
				x+=2;
			if(!left && !right)
			{
				if(x>0)
					x-=1;
				if(x<0)
					x+=1;
			}
		}
		
		if(x<-MAXSPEED)x=-MAXSPEED;
		if(x>MAXSPEED)x=MAXSPEED;
		tx.translate(x, y);

	}

	public Point2D getPosition(){
		return new Point2D.Double(x, y);
	}
	
}
