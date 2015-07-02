package model.gamestate;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import model.MainMap;
import model.tiles.Background;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class PlayState extends GameState{

	private MainMap mainmap;
	private Background background;
	
	public PlayState(GameController gameControl) {
		super(gameControl);
		mainmap = new MainMap(gameControl,new Point2D.Double(0, 0));
		background = new Background(gameControl,gameControl.getImgHandler().getScaledImage(ImageHandler.getImage(ImageType.background)));
	}

	@Override
	public void draw(Graphics2D g) {
		background.draw(g);
		AffineTransform tx = new AffineTransform();
		tx.translate(gameControl.getWidth()/2-400, gameControl.getHeight()/2-300);
		g.setTransform(tx);
	    mainmap.draw(g);
	}

	@Override
	public void update() {
		mainmap.update();
	}	
	
	@Override
	public void keyPressed(int e) {
		mainmap.keyPressed(e);
	}

	@Override
	public void keyReleased(int e) {
		mainmap.keyReleased(e);		
	}

}
