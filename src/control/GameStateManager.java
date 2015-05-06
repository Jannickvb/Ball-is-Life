package control;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.gamestate.GameState;
import model.gamestate.HighscoreState;
import model.gamestate.LoadMenuState;
import model.gamestate.Lvl1State;
import model.gamestate.MenuState;

public class GameStateManager {
	
	private ArrayList<GameState> states = new ArrayList<GameState>();
	private GameController gameControl;
	
	public GameState currentState;
	public int index;
	public GameStateManager(GameController gameControl){
		this.gameControl = gameControl;
		states.add(new MenuState(gameControl,this));
		states.add(new LoadMenuState(gameControl,this));
		states.add(new HighscoreState(gameControl,this));
		states.add(new Lvl1State(gameControl,this));
		
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
		currentState = states.get(i);
	}
	
	public void keyPressed(KeyEvent e){
		currentState.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		currentState.keyReleased(e);
	}
}
