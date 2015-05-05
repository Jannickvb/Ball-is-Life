package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import control.GameController;
import control.GameStateManager;

public class GameFrame extends JFrame{
	public GameFrame(){
		setPreferredSize(new Dimension(800,600));
		setMinimumSize(new Dimension(600,400));
		setContentPane(new GamePanel(new GameStateManager(new GameController(this))));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
