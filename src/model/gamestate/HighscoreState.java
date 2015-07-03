package model.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import model.Score;
import model.tiles.Background;
import control.GameController;
import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class HighscoreState extends GameState{

	private List<Score> scores;
	private int midX;
	private Font font;
	private Background bg;
	
	public HighscoreState(GameController gameControl) {
		super(gameControl);
		scores = gameControl.getScoreHandler().getScores();
		midX = gameControl.getWidth()/2;
		font = new Font("Dialog",Font.BOLD,40);
		bg = new Background(gameControl, ImageHandler.getImage(ImageType.background));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(font);
		bg.draw(g);
		g.setColor(Color.white);
		g.drawString("HIGHSCORES", midX - (g.getFontMetrics().stringWidth("HIGHSCORES")/2), 100);
		font.deriveFont(24f);
		for(int i = 0;i<scores.size();i++){
			String s = scores.get(i).getName() + "   " + scores.get(i).getScore();
			g.drawString(s,midX - (g.getFontMetrics().stringWidth(s)/2), (i*55)+200);
		}
	}

	@Override
	public void update() {}

	@Override
	public void keyPressed(int e) {
		if(e == KeyEvent.VK_ENTER){
			gameControl.getGameStateManager().select(0);
		}
	}

	@Override
	public void keyReleased(int e) {}

	@Override
	public void init() {
		scores = gameControl.getScoreHandler().getScores();
	}

	
	
}
