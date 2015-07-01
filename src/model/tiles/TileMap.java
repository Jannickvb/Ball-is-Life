package model.tiles;

public class TileMap {
	
	private int[][] tilemap;
	private Tile[][] tiles;
	public int x,y;
	public boolean collisionMap;
	
	public TileMap(int[][] map, boolean collisionMap) {
		this.tilemap = map;
		this.y = tilemap[0].length;
		this.x = tilemap.length;
		this.collisionMap = collisionMap;
		tiles = new Tile[x][y];
		System.out.println("x: "+ x + " y: " + y);
		loadTiles(x, y);
	}
	
	private void loadTiles(int x, int y)
	{
		for (int i =0; i< y; i++)
		{
			for(int k=0 ; k<x; k++)
			{
				if(collisionMap && !isEmpty(tilemap[k][i]))
				{
					tiles[k][i] = new Tile(true,i*Tile.size,k*Tile.size,tilemap[k][i]);
				}
				else
				{
					tiles[k][i] = new Tile(false,i*Tile.size,k*Tile.size,tilemap[k][i]);
				}
			}
		}
	}
	
	private boolean isEmpty(int i){
		return i>1;
	}
	
	public boolean isCollisionMap(){
		return collisionMap;
	}
	
	public Tile[][] getTileMap()
	{
		return tiles;
	}

}
