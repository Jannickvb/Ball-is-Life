package model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

public class Player extends Entity{

	public int x;
	public int y;
	public int angle;
	public boolean fallling;
	public boolean jumping;
	public boolean ducking;
	public boolean spindash;
	public static final int MAXJUMPHEIGHT = 60;
	public static int GROUNDSPEED = 0;
	public Line2D sensorA,sensorB;
	
	public Player(){
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
