package model;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.GameController;

public class ScorePopup extends JDialog {
	private Score score;

	public ScorePopup(Score score,GameController gameControl) {
		Score sc = score;
		setTitle("ENTER NAME");
		setSize(300, 300);
		((JPanel) this.getContentPane()).setBorder(new EmptyBorder(10, 10,
				10, 10));

		JPanel info = new JPanel(new GridLayout(2, 2));

		JTextField name = new JTextField("");
		JLabel label;

		info.add(label = new JLabel("Naam:"));
		label.setBorder(new EmptyBorder(0, 0, 0, 20));
		info.add(name);
		this.add(info, BorderLayout.NORTH);

		JPanel buttons = new JPanel(new GridLayout(1, 0));
		JButton ok = new JButton("OK");
		buttons.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!name.getText().equals(""))
				{
					score.setName(name.getText());
					gameControl.getScoreHandler().addScore(score);
					gameControl.getScoreHandler().writeScores("scores.txt", gameControl.getScoreHandler().getScores());
					gameControl.getScoreHandler().loadScores();
					gameControl.getGameStateManager().select(1);
					ScorePopup.this.dispose();
				}else{
					score.setName("PLAYER");
					gameControl.getScoreHandler().addScore(score);
					gameControl.getScoreHandler().writeScores("scores.txt", gameControl.getScoreHandler().getScores());
					gameControl.getScoreHandler().loadScores();
					gameControl.getGameStateManager().select(1);
					ScorePopup.this.dispose();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				if(score.getName().equals("")){
					score.setName("PLAYER");
					gameControl.getScoreHandler().addScore(score);
					gameControl.getScoreHandler().writeScores("scores.txt", gameControl.getScoreHandler().getScores());
					gameControl.getScoreHandler().loadScores();
					gameControl.getGameStateManager().select(1);
					ScorePopup.this.dispose();
				}else{
					gameControl.getScoreHandler().addScore(score);
					gameControl.getScoreHandler().writeScores("scores.txt", gameControl.getScoreHandler().getScores());
					gameControl.getScoreHandler().loadScores();
					gameControl.getGameStateManager().select(1);
					ScorePopup.this.dispose();
				}
			}
			
		});
		buttons.setBorder(new EmptyBorder(10, 0, 10, 0));
		this.add(buttons, BorderLayout.SOUTH);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}