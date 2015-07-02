package model.tiles;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import control.handler.ImageHandler;
import control.handler.ImageHandler.ImageType;

public class Tile {

	private boolean isSolid;
	private int x, y, id;
	public static final int size = 32;
	private BufferedImage tile;
	private BufferedImage tilemap;
	private Rectangle2D rect;
	
	public Tile(boolean isSolid, int x, int y, int id) {
		this.isSolid = isSolid;
		this.x = x;
		this.y = y;
		this.id = id;
		 tilemap = ImageHandler.getImage(ImageType.tileset);
		id--;
		if (id > 0)
			tile = tilemap.getSubimage((id * size) % tilemap.getWidth(),
					((((id * size) / tilemap.getWidth()) * size)), size, size);
		if(isSolid)
		{
			rect = new Rectangle2D.Double(x, y, size, size);
		}
		tilemap = null;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isSolid(){
		return isSolid;
	}
	
	public Rectangle2D getRect() {
		return rect;
	}
	
	public void draw(Graphics2D g2) {
		if (tile != null)
		{
			g2.drawImage(tile, null, x, y);
			if(isSolid)
				g2.draw(rect);
		}
	}
}
