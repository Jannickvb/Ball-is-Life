package model.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import model.MainMap;
import model.entity.HUD;
import model.tiles.Background;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class PlayState extends GameState{

	private MainMap mainmap;
	private Background background;
	private HUD hud;
	private boolean flash;
	private int frame;
	private Rectangle2D rect;
	private AffineTransform old,tx;
	private float alpha;
	public PlayState(GameController gameControl) {
		super(gameControl);
		init();
	}

	@Override
	public void draw(Graphics2D g) {
		background.draw(g);
		g.setTransform(tx);
		hud.draw(g);
	    mainmap.draw(g);
	    g.setTransform(old);
	    g.setColor(new Color(1f,1f,1f,alpha));
	    g.fill(rect);
	    g.draw(rect);
	}

	@Override
	public void update() {
		mainmap.update();
		hud.update();
		if(flash)
		{
			if(alpha<1.0f)
			{
				alpha+=0.2f;
			}
		}
		if(alpha > 0.8f)
		{
			flash = false;
			alpha = 0;
		}
	}	
	
	@Override
	public void keyPressed(int e) {
		mainmap.keyPressed(e);
		if(e == KeyEvent.VK_SPACE && mainmap.nukeReady()){
			System.out.println("NUKED!");
			flash = true;
			mainmap.toggleOffNuke();
			mainmap.destroyAllEnemies();
		}
	}

	@Override
	public void keyReleased(int e) {
		mainmap.keyReleased(e);		
	}

	@Override
	public void init() {
		mainmap = new MainMap(gameControl,new Point2D.Double(0, 0));
		background = new Background(gameControl,gameControl.getImgHandler().getScaledImage(ImageHandler.getImage(ImageType.background)));
		hud = new HUD(gameControl, new Point2D.Double(-200, 100),mainmap);
		tx = new AffineTransform();
		old = new AffineTransform();
		tx.translate((((gameControl.getWidth()/2)-400)), 0);
		flash = false;
		frame = 0;
		alpha = 0.0f;
		rect = new Rectangle2D.Double(0,0,gameControl.getWidth(),gameControl.getHeight());
	}

}
