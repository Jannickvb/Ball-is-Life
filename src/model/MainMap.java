package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import model.entity.Enemy;
import model.entity.Entity;
import model.entity.Explosion;
import model.entity.Player;
import model.entity.Trophy;
import model.tiles.Tile;
import model.tiles.TileMap;
import control.GameController;
import control.GameStateManager;
import control.handler.MapHandler;

public class MainMap extends Entity implements ActionListener {

	private GameController gameControl;
	private GameStateManager gsm;
	private Player player;
	private TileMap map, collisionmap;
	private List<Enemy> enemies;
	private List<Explosion> explosions;
	private Trophy trophy;
	private int nukeCounter;
	private boolean nukeReady,popup;
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
		this.map = new TileMap(MapHandler.readLevelFile("res/maps/lvlmap.txt"),
				false);
		this.collisionmap = new TileMap(
				MapHandler.readLevelFile("res/maps/collisionmap.txt"), true);
		this.player = new Player(gameControl, collisionmap, new Point2D.Double(
				300, 300));
		particleTimer = new Timer(1000 / 20, this);
		particleTimer.start();

		enemies = new LinkedList<>();
		explosions = new LinkedList<>();
		trophy = new Trophy(gameControl,new Point2D.Double(388, 32));
		nukeReady = false;
		popup = false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		for (Tile[] tileX : map.getTileMap()) {
			for (Tile tileY : tileX) {
				tileY.draw(g);
			}
		}

		for (Particle particle : particles) {
			particle.paintComponent(g);
		}
		trophy.draw(g);
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
		for (Explosion expl : explosions) {
			expl.draw(g);
		}
		player.draw(g);
	}

	@Override
	public void update() {
		if(!trophy.isGameOver())
			{
			nukeCounter++;
			if(nukeCounter > 300){
				nukeReady = true;
			}
			updateParticles();
			Iterator<Particle> i = particles.iterator();
			while (i.hasNext()) {
				Particle p = i.next();
				if (p.getLife() == 100) {
					i.remove();
				}
			}
			if (Math.floor(Math.random() * 60) == 3) {
				enemies.add(new Enemy(gameControl, new Point2D.Double((Math
						.random() * 700) + 50, gameControl.getHeight() + 64)));
			}
			for (Enemy enemy : enemies) {
				enemy.update();
			}
			for (Explosion expl : explosions) {
				expl.update();
			}
			trophy.update();
			collidingWithEnemy();
			removeExplosions();
			player.update();
		}else if(trophy.isGameOver() && !popup){
			new ScorePopup(new Score("",trophy.getScore()),gameControl);
			popup = true;
		}
	}

	public void destroyAllEnemies() {
		Iterator<Enemy> it = enemies.iterator();
		while(it.hasNext())
		{
			Enemy enemy = it.next();
			trophy.raiseScore();
			explosions.add(new Explosion(gameControl, new Point2D.Double(
					enemy.getRect().getX(), enemy.getRect().getY())));
			it.remove();
		}
	}
	
	public void collidingWithEnemy() {
		Iterator<Enemy> colPl = enemies.iterator();
		while (colPl.hasNext())
		{
			Enemy enemy = colPl.next();
			if (player.collision(enemy.getRect())) 
			{
				// System.out.println("" + enemy.getPosition());
				trophy.raiseScore();
				explosions.add(new Explosion(gameControl, new Point2D.Double(
						enemy.getRect().getX(), enemy.getRect().getY())));
				colPl.remove();
			}
		}
		Iterator<Enemy> colTr = enemies.iterator();
		while(colTr.hasNext())
		{
			Enemy enemy = colTr.next();
			if(enemy.getRect().getY()<96)
			{
				trophy.lowerHealth();
				trophy.setAnimation(true);
				colTr.remove();
			}
		}
		
	}

	public void removeExplosions() {
		Iterator<Explosion> it = explosions.iterator();
		while (it.hasNext())
		{
			Explosion explosion = it.next();
			if (explosion.hasPlayedOnce())
			{
				it.remove();
			}
		}
	}

	public void updateParticles() {
		drag = new Point((int) player.getPlayerTransform().getTranslateX(),
				(int) player.getPlayerTransform().getTranslateY());

		if (player.isMoving()) {
			addParticle(drag,player);
			addParticle(drag,player);
			addParticle(drag,player);
			addParticle(drag,player);
		}
		
		for(Enemy enemy: enemies) {
			drag = new Point((int) enemy.getRect().getX(),
					(int) enemy.getRect().getY());
			addParticle(drag,enemy);
			addParticle(drag,enemy);
			addParticle(drag,enemy);
		}
		
		for (Particle particle : particles) {
			particle.update();
		}

	}

	public void addParticle(Point2D startDrag,Entity entity) {
		int size = (int) (rand.nextInt(15) * 1.1);
		if(entity instanceof Player)
		{
			particles.add(new Particle((int) player.getPlayerTransform()
					.getTranslateX(), (int) player.getPlayerTransform()
					.getTranslateY(), size,true));
		}
		if(entity instanceof Enemy)
		{
			Enemy enemy = (Enemy) entity;
			particles.add(new Particle((int) enemy.getTransform().getTranslateX()+16,
					(int) enemy.getTransform().getTranslateY()-16,
					size-2,false));
		}
	}

	public boolean nukeReady(){
		return nukeReady;
	}
	
	public void toggleOffNuke()
	{
		nukeCounter = 0;
		this.nukeReady = false;
	}
	
	// public boolean checkParticleCollision(Particle p){
	// for(Tile[] row: map.getTileMap())
	// for(Tile column:row){
	// return column.getRect().contains(new Point2D.Double(p.getX(),p.getY()));
	// }
	// return false;
	// }
	
	public Trophy getTrophy() {
		return trophy;
	}
	
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
