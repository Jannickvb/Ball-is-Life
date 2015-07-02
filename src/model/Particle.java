package model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Particle {

	private int x, y, speedx, speedy, size, life;
	private float alpha;
	private Color color;

	public Particle(int x, int y, int size, boolean isPlayer) {
		this.x = x;
		this.y = y;
		int random = (int) (Math.random() * 4);
		int speed; 
		if(isPlayer)
			speed = 3;
		else
			speed = 2;
		
		if (random == 0) {
			this.speedx = (int) (Math.random() * -speed);
			this.speedy = (int) (Math.random() * speed);
		} else if (random == 1) {
			this.speedx = (int) (Math.random() * speed);
			this.speedy = (int) (Math.random() * -3);
		} else if (random == 2) {
			this.speedx = (int) (Math.random() * speed);
			this.speedy = (int) (Math.random() * speed);
		} else {
			this.speedx = (int) (Math.random() * -speed);
			this.speedy = (int) (Math.random() * -speed);
		}
		
		this.size = size;
		this.alpha = 1f;
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(isPlayer)
		{
			switch (i) {
			case 0:
				this.color = new Color(255,102,79);
				break;
			case 1:
				this.color = new Color(255,129,109);
				break;
			case 2:
				this.color = new Color(255,157,139);
				break;
			}
		}else
		{
			switch (i) {
			case 0:
				this.color = new Color(79,102,255);
				break;
			case 1:
				this.color = new Color(109,129,255);
				break;
			case 2:
				this.color = new Color(139,157,255);
				break;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDX() {
		return speedx;
	}

	public int getDY() {
		return speedy;
	}

	public boolean update() {
		x += -speedx;
		y += -speedy;
		
		life++;
		alpha = alpha - 0.04f;
		if (alpha == 0.1f) {
			alpha = 0.0f;
			return true;
		}
		return false;
	}

	public float getAlpha() {
		return alpha;
	}

	public int getLife() {
		return life;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		if (alpha > 0.0f) {
			g2.setComposite(makeComposite(alpha));
			g2.setPaint(color);
		} else {
			g2.setPaint(new Color(1, 0, 0, 0.0f));
		}
		g2.fillOval(x - (size / 2), y - (size / 2), size, size);
		g2.dispose();
	}
}