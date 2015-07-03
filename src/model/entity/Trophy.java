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
	private int x,y,health,score;
	private boolean gameover;
	public Trophy(GameController gameControl, Point2D position) {
		super(gameControl, position);
		regular = new Animation(ImageHandler.getImage(ImageType.trophy), 24, 30, 150);
		hit = new Animation(ImageHandler.getImage(ImageType.trophyHit), 24, 30, 25);
		current = regular;
		image = current.getCurrentImage();
		x = (int) position.getX();
		y = (int) position.getY();
		health = 100;
		score = 0;
		gameover = false;
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
		if(health<=0){
			gameover = true;
		}
	}

	public boolean isGameOver(){
		return gameover;
	}
	
	public int getScore(){
		return score;
	}
	
	public void raiseScore(){
		this.score += 50;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void setAnimation(boolean isHit) {
		if(isHit)
			current = hit;
		else
			current = regular;
		current.setFrame(0);
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void lowerHealth(){
		this.health -= 5;
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
