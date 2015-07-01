package control;

import control.handler.ImageHandler;
import view.GameFrame;

public class GameController {
	
	private GameFrame frame;
	private GameStateManager gsm;
	private ImageHandler imgHandler;
	public GameController(GameFrame frame){
		this.frame = frame;
		this.imgHandler = new ImageHandler(this);
		this.gsm = new GameStateManager(this);
	}
	
	public GameStateManager getGameStateManager() {
		return gsm;
	}

	public ImageHandler getImgHandler() {
		return imgHandler;
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
}
