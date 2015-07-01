package control;

import view.GameFrame;

public class GameController {
	
	private GameFrame frame;
	private GameStateManager gsm;
	private ImageHandler imgHandler;

	public GameController(GameFrame frame){
		this.frame = frame;
		this.gsm = new GameStateManager(this);
		this.imgHandler = new ImageHandler(this);
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
