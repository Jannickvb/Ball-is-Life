package control.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Score;

public class ScoreHandler {

	private List<Score> highscores;
	
	public ScoreHandler(){
		highscores = new ArrayList<>();
		loadScores();
		writeScores("scores.txt", (ArrayList<Score>) highscores);
	}
	
	public void loadScores(){
		ArrayList<Score> scores = (ArrayList<Score>) readScoreFile("scores.txt");
		if(!scores.isEmpty()){
			highscores = scores;
			Collections.sort(highscores);
			System.out.println(highscores);
		}
	}
	
	public void addScore(Score score){
		highscores.add(score);
	}
	
	public ArrayList<Score> getScores(){
		return (ArrayList<Score>) highscores;
	}
	
	public List<Score> readScoreFile(String file){
		List<Score> scores = new ArrayList<>();
		BufferedReader br = null;
		try{
			String text;
			br = new BufferedReader(new FileReader(file));
			while ((text = br.readLine()) != null){
				String[] values = text.split(",");
				String name = values[0];
				int score = Integer.parseInt(values[1]);
				scores.add(new Score(name,score));
			}
		}catch(IOException e){
			System.out.println("FILE IS NOT ACCEPTED");
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("CANT READ FILE");
			}
		}
		
		return scores;
	}
	
	public void writeScores(String fileName, ArrayList<Score> s){
		List<Score> scores = s;
		  PrintWriter writer = null;
	        try {
	            writer = new PrintWriter(new FileWriter(fileName));
	            for (Score score : scores)
	            {
	                writer.println(score);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	}
	
	
	
}
