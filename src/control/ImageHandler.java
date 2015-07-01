package control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import view.GameFrame;

public class ImageHandler {

	public ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private Scalr.Mode mode;
	private GameController gc;
	
	public ImageHandler(GameController gc) {
		this.gc = gc;
		try {
			addImage("", "test.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public enum ImageType{
	 test;
	}

	public void addImage(String folderName, String fileName)
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

	public BufferedImage getImage(ImageType img) {
		return images.get(img.ordinal());
	}

	public BufferedImage getScaledImage(BufferedImage image) {
		getMode(image);
		int targetSize;
		if (mode == Scalr.Mode.FIT_TO_HEIGHT)
		{
			System.out.println(targetSize = gc.getHeight());
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