package control;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.gamestate.GameState;
import model.gamestate.HighscoreState;
import model.gamestate.LoadMenuState;
import model.gamestate.PlayState;
import model.gamestate.MenuState;

public class GameStateManager {
	
	private ArrayList<GameState> states = new ArrayList<GameState>();
	private GameController gameControl;
	
	public GameState currentState;
	public int index;
	public GameStateManager(GameController gameControl){
		this.gameControl = gameControl;
		states.add(new MenuState(gameControl));
		states.add(new LoadMenuState(gameControl));
		states.add(new HighscoreState(gameControl));
		states.add(new PlayState(gameControl));
		
		currentState = states.get(0);
	}
	
	public GameState getCurrentState(){
		return currentState;
	}
	
	public void next(){
		index++;
		if(index == states.size()) {
			index = 0;
		}
	}
	
	public void back(){
		index--;
		if(index == -1) {
			index = states.size() - 1;
		}
	}
	
	public void start(){
		currentState = states.get(3);
	}
	
	public void select(int i){
//		currentState = states.get(i);
		System.out.println(i);
	}
	
	public void keyPressed(int e){
		currentState.keyPressed(e);
	}
	
	public void keyReleased(int e){
		currentState.keyReleased(e);
	}

	public void draw(Graphics2D g2) {
		currentState.draw(g2);
	}
	
	public void update(){
		currentState.update();
	}
}
