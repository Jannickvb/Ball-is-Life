package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

import model.tiles.Tile;
import model.tiles.TileMap;
import control.GameController;
import control.GameStateManager;
import control.handler.MapHandler;

public class MainMap extends Entity implements ActionListener{

	private GameController gameControl;
	private GameStateManager gsm;
	private Player player;
    private TileMap map,collisionmap;
 
	// PARTICLE FIELDS
	private ArrayList<Particle> particles = new ArrayList<Particle>(1000);
	private Point drag;
	private Random rand = new Random();
	private Timer particleTimer;
	// PARTICLE FIELDS
	
	public MainMap(GameController gameControl, Point2D position) {
		super(gameControl, position);
		this.gameControl = gameControl;
		this.gsm = gameControl.getGameStateManager();
		this.map = new TileMap(MapHandler.readLevelFile("res/maps/lvlmap.txt"),false);
		this.collisionmap = new TileMap(MapHandler.readLevelFile("res/maps/collisionmap.txt"),true);
		this.player = new Player(gameControl,collisionmap,new Point2D.Double(300,300));
		particleTimer = new Timer(1000 / 20, this);
		particleTimer.start();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		  for(Tile[] tileX: map.getTileMap())
	        {
	            for(Tile tileY: tileX)
	            {
	                tileY.draw(g);
	            }
	        }
	        
			for (Particle particle : particles) {
				particle.paintComponent(g);
			}
			
			player.draw(g);
	}

	@Override
	public void update() {
		updateParticles();
		Iterator<Particle> i = particles.iterator();
		while (i.hasNext()) {
			Particle p = i.next();
			if (p.getLife() == 100) {
				i.remove();
			}
		}
		player.update();
	}
	public void updateParticles() {
		drag = new Point((int) player.getPlayerTransform().getTranslateX(), (int) player.getPlayerTransform().getTranslateY());

		if (player.isMoving()) {
			addParticle(drag);
			addParticle(drag);
			addParticle(drag);
			addParticle(drag);
		}
		for (Particle particle : particles) {
			particle.update();
		}
		
	}

	public void addParticle(Point2D startDrag) {
		int size = (int) (rand.nextInt(15) * 1.1);
		particles.add(new Particle((int)player.getPlayerTransform().getTranslateX(), (int)player.getPlayerTransform().getTranslateY(), size));
	}
	
//	public boolean checkParticleCollision(Particle p){
//		for(Tile[] row: map.getTileMap())
//			for(Tile column:row){
//				return column.getRect().contains(new Point2D.Double(p.getX(),p.getY()));
//			}
//		return false;
//	}
	@Override
	public void keyPressed(int e) {
		player.keyPressed(e);
		
	}

	@Override
	public void keyReleased(int e) {
		player.keyReleased(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Particle particle : particles) {
			particle.update();
		}
		Iterator<Particle> i = particles.iterator();
		while (i.hasNext()) {
			Particle p = i.next();
			if (p.getLife() == 100) {
				i.remove();
			}
		}
	}

}
