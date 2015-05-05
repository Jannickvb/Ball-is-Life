package control;

import java.util.ArrayList;

import model.GameState;
import model.Lvl1State;
import model.MenuState;

public class GameStateManager {
	
	private ArrayList<GameState> states = new ArrayList<GameState>();
	public GameState currentState;
	private GameController gameControl;
	public GameStateManager(GameController gameControl){
		this.gameControl = gameControl;
		states.add(new MenuState(gameControl));
		states.add(new Lvl1State(gameControl));
		currentState = states.get(0);
	}
	public GameState getCurrentState(){
		return currentState;
	}
}
