package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rotate180 {

	public File input = new File("./in.png");

	public Rotate180() {
		rotate180(input);
	}

	public static void rotate180(File input) {
		try {
			BufferedImage image = ImageIO.read(input);

			int width = image.getWidth();
			int height = image.getHeight();

			BufferedImage rotated = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
			// Set type to ARGB to avoid Exception of Unknown Image Type 0
		
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					rotated.setRGB((width - 1) - x, (height - 1) - y, image.getRGB(x, y));
				}
			}
			System.out.println(ConsoleColour.BLUE_BOLD);
			System.out.println("[Image Rotated 180]");
			System.out.println(ConsoleColour.RESET);

			new SaveFile(rotated);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
/*Referenced https://www.youtube.com/watch?v=RLHG1dR3TsI
Zoran Davidovic, Java Tutorial - Image rotation, Oct 17, 2017*/