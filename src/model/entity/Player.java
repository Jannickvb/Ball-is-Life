package model.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import model.Animation;
import model.tiles.Tile;
import model.tiles.TileMap;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class Player extends Entity {

	private int x, y;
	public final int MAXSPEED = 15;
	private boolean up, down, left, right;
	private AffineTransform tx = new AffineTransform();
	private Animation animation;
	private BufferedImage sprite;
	private TileMap tilemap;
	public Player(GameController gameControl,TileMap tilemap, Point2D position) {
		super(gameControl, position);
		tx.translate(position.getX(), position.getY());
		position.setLocation(0, 0);
		x = 0;
		y = 0;
		animation = new Animation(ImageHandler.getImage(ImageType.player), 32, 32, 20);
		sprite = animation.getCurrentImage();
		this.tilemap = tilemap;
	}

	@Override
	public void init() {

	}

	@Override
	public void draw(Graphics2D g2) {
//		AffineTransform old = g2.getTransform();
		g2.drawImage(sprite, (int)(-sprite.getWidth()/2 + tx.getTranslateX()),(int)(-sprite.getHeight()/2 + tx.getTranslateY()),null);
		g2.setTransform(tx);
//		g2.setTransform(old);
//		g2.setStroke(new BasicStroke(4));
//		g2.drawLine((int)tx.getTranslateX(), (int)tx.getTranslateY(), (int)tx.getTranslateX(), (int)tx.getTranslateY());
	}

	@Override
	public void update() {
		for(Tile[] row: tilemap.getTileMap()){
			for(Tile column:row){
				if(column.isSolid())
					collision(column.getRect());
			}
		}
		setPosition();
		animation.update();
		sprite = animation.getCurrentImage();
	}

	@Override
	public void keyPressed(int e) {
		if (e == KeyEvent.VK_UP)
			up = true;
		if (e == KeyEvent.VK_DOWN)
			down = true;
		if (e == KeyEvent.VK_LEFT)
			left = true;
		if (e == KeyEvent.VK_RIGHT)
			right = true;
	}

	@Override
	public void keyReleased(int e) {
		if (e == KeyEvent.VK_UP)
			up = false;
		if (e == KeyEvent.VK_DOWN)
			down = false;
		if (e == KeyEvent.VK_LEFT)
			left = false;
		if (e == KeyEvent.VK_RIGHT)
			right = false;
	}

	public void setPosition() {

		// VERTICAL MOVEMENT
		if (y <= MAXSPEED) {
			if (up)
				y -= 2;
			if (down)
				y += 2;
			if (!up && !down) {
				if (y > 0)
					y -= 1;
				if (y < 0)
					y += 1;
			}
		}
		if (y < -MAXSPEED)
			y = -MAXSPEED;
		if (y > MAXSPEED)
			y = MAXSPEED;
		// HORIZONTAL MOVEMENT
		if (x <= MAXSPEED) {
			if (left)
				x -= 2;
			if (right)
				x += 2;
			if (!left && !right) {
				if (x > 0)
					x -= 1;
				if (x < 0)
					x += 1;
			}
		}

		if (x < -MAXSPEED)
			x = -MAXSPEED;
		if (x > MAXSPEED)
			x = MAXSPEED;
		tx.translate(x, y);

	}
	
	public AffineTransform getPlayerTransform() {
		return tx;
	}

	public boolean collision(Rectangle2D r) {
		double dX = tx.getTranslateX();
		double dY = tx.getTranslateY();
		boolean north = r.contains(new Point2D.Double(dX, dY-16)),
				south = r.contains(new Point2D.Double(dX, dY+16)),
				east = r.contains(new Point2D.Double(dX+16, dY)),
				west = r.contains(new Point2D.Double(dX-16, dY));
		if(north)
		{
			up = false;
			y = -y;
			return true;
		}
		if(east)
		{
			right = false;
			x = -x;
			return true;
		}
		if(south)
		{
			down = false;
			y=-y;
			return true;
		}
		if(west)
		{
			left = false;
			x=-x;
			return true;
		}
		/////////////////
		if(north && west)
		{
			left = false;
			up = false;
			x=-x;
			y=-y;
			return true;
		}
		if(north && east)
		{
			right = false;
			up = false;
			x=-x;
			y=-y;
			return true;
		}
		if(south && west)
		{
			left = false;
			down = false;
			x=-x;
			y=-y;
			return true;
		}
		if(south && east)
		{
			right = false;
			down = false;
			x=-x;
			y=-y;
			return true;
		}
		return false;
	}
	
	public boolean isMoving(){
		return (up||left||down||right);
	}	
}
