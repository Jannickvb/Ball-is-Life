package model.gamestate;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.IOException;

import view.GameFrame;
import model.Player;
import model.tiles.Tile;
import model.tiles.TileMap;
import control.GameController;
import control.GameStateManager;
import control.handler.MapHandler;

public class Lvl1State extends GameState{


	private GameController gameControl;
	private GameStateManager gsm;
	private Player player;
    private TileMap map;

	
	public Lvl1State(GameController gameControl) {
		super(gameControl);
		this.gameControl = gameControl;
		this.gsm = gameControl.getGameStateManager();
		this.map = new TileMap(MapHandler.readLevelFile("res/maps/test.txt"),false);
		this.player = new Player(gameControl,new Point2D.Double(200,200));
	}


	@Override
	public void draw(Graphics2D g) {
		g.drawRect(0, 0, 200, 20);
        for(Tile[] tileX: map.getTileMap())
        {
            for(Tile tileY: tileX)
            {
                tileY.draw(g);
            }
        }

		player.draw(g);
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void keyPressed(int e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(int e) {
		player.keyReleased(e);		
	}
	
}
