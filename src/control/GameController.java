package control;

import java.awt.Toolkit;

import control.handler.ImageHandler;
import control.handler.ScoreHandler;
import view.GameFrame;

public class GameController {
	
	private GameFrame frame;
	private GameStateManager gsm;
	private ImageHandler imgHandler;
	private ScoreHandler scoreHandler;
	
	public GameController(GameFrame frame){
		this.frame = frame;
		this.imgHandler = new ImageHandler(this);
		this.scoreHandler = new ScoreHandler();
		this.gsm = new GameStateManager(this);

	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}

	public ImageHandler getImgHandler() {
		return imgHandler;
	}
	
	public ScoreHandler getScoreHandler(){
		return scoreHandler;
	}
	
	public int getHeight(){
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	public int getWidth(){
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}
}
