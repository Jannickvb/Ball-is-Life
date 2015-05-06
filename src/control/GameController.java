package control;

import view.GameFrame;

public class GameController {
	private GameFrame frame;
	public GameController(GameFrame frame){
		this.frame = frame;
	}
	
	public int getHeight(){
		return frame.getContentPane().getHeight();
	}
	
	public int getWidth(){
		return frame.getContentPane().getWidth();
	}
}
