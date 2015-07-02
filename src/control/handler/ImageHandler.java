package control.handler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import control.GameController;
import view.GameFrame;

public class ImageHandler {

	public static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private Scalr.Mode mode;
	private GameController gc;
	
	public ImageHandler(GameController gc) {
		this.gc = gc;
	}
	static{
		try {
			addImage("tileset", "tileset.png");
			addImage("spritesheets", "player.png");
			addImage("spritesheets","enemy.png");
			addImage("spritesheets","explosion.png");
			addImage("spritesheets","trophy.png");
			addImage("spritesheets","trophy_hit.png");
			addImage("backgrounds","background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public enum ImageType{
		tileset,
		player,
		enemy,
		explosion,
		trophy,
		trophyHit,
		background;
	}

	public static void addImage(String folderName, String fileName)
			throws IOException {
		if (folderName.equals(""))
			images.add(ImageIO.read(GameFrame.class.getResource("/images/"
																+ fileName)));
		else
			images.add(ImageIO.read(GameFrame.class.getResource("/images/"
																+ folderName
																+ "/"
																+ fileName)));
	}

	public static BufferedImage getImage(ImageType img) {
		return images.get(img.ordinal());
	}

	public BufferedImage getScaledImage(BufferedImage image) {
		getMode(image);
		int targetSize;
		if (mode == Scalr.Mode.FIT_TO_HEIGHT)
		{
			targetSize = gc.getHeight();
		}
		else
			targetSize = gc.getWidth();
		image = Scalr.resize(image, mode, targetSize, Scalr.OP_ANTIALIAS);
		return image;
	}

	private Scalr.Mode getMode(BufferedImage image) {
		if (image.getHeight() < image.getWidth()) {
			mode = Scalr.Mode.FIT_TO_WIDTH;
		} else {
			mode = Scalr.Mode.FIT_TO_HEIGHT;
		}
		return mode;
	}
}