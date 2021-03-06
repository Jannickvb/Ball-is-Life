package control.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapHandler {

	public static int[][] readLevelFile(String file)
	{
		int readMapWidth, readMapHeight;
		int[][] map = null;
		BufferedReader br = null;
		
		try {
		    br = new BufferedReader(new FileReader(file));
		    readMapWidth = Integer.parseInt(br.readLine());
		    readMapHeight = Integer.parseInt(br.readLine());
		    map = new int[readMapHeight][readMapWidth];
		    int row = 0;
		    String text = null;
		    while ((text = br.readLine()) != null){
		    	if(row < readMapHeight )
		    	{
			        String[] tileValues = text.split(",");
			        for(int col = 0; col < readMapWidth; col++){
	//		        	System.out.println("col: "+map.length);
			            map[row][col] = Integer.parseInt(tileValues[col]);  
			        }
			        row++;
		    	}
		    }
		    // Zero index rows...
		    if (row < readMapHeight - 1) {
		        throw new IOException("Expected title height does not match actual row height");
		    }  
		    return map;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
		        br.close();
		    } catch (Exception exp) {
		    }
		}
		return map;
	}
	
		
}
