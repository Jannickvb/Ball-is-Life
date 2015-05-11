package model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class Entity {
	public abstract void init();
	public abstract void draw(Graphics2D g2);
	public abstract void update();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
}
