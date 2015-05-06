package model;

import java.awt.Graphics2D;

public abstract class MabObject {
	public abstract void init();
	public abstract void draw(Graphics2D g2);
	public abstract void update();
}