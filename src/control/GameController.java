package control;

import view.GameFrame;

public class GameController {
	public int width;
	public int height;
	public GameController(GameFrame frame){
		this.width = frame.getWidth();
		this.height = frame.getHeight();
	}
	
}
