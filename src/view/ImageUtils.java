package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {	
	
	/**
	 * Takes a String representing a suit and returns the corresponding image in the img folder
	 * @param s String representing suit
	 * @return corresponding image, or null if not found
	 */
	
	public static Image getSuitImage(String s) {
		BufferedImage image;
		final String EXTENSION = ".png";
		
		String suit = s.toLowerCase();
		
		try {
			image = ImageIO.read(new File(String.format("img%s%s%s", File.separator, suit, EXTENSION)));
		} catch (IOException ioe) {
			image = null;
		}
		
		return image;
	}
}
