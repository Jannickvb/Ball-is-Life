package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import control.GameStateManager;

public class GamePanel extends JPanel{
	
	GameStateManager gsm;
	Timer paintTimer;
	Timer gameTimer;
	public GamePanel(GameStateManager gsm){
		this.gsm = gsm;
		paintTimer = new Timer(1000/60,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		paintTimer.start();
		gameTimer = new Timer(1000/20,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gsm.currentState.update();
			}
		});
		gameTimer.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		gsm.currentState.draw(g2);
	}
}
