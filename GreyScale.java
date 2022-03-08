package ie.gmit.dip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

//Converts colored image to greyscale using java.awt.Color import
public class GreyScale {

	public File input = new File("./in.png");

	public GreyScale() {
		toGreyScale(input);
	}

	public static void toGreyScale(File input) {
		try {
			BufferedImage image = ImageIO.read(input);

			int width = image.getWidth();
			int height = image.getHeight();

			for (int y = 0; y < height; y++) { // Looping through image to get and set the colour of each pixel
				for (int x = 0; x < width; x++) {

					Color color = new Color(image.getRGB(x, y));
					int red = (int) (color.getRed() * 0.2126); // Multiplied to get the grey values
					int green = (int) (color.getGreen() * 0.7152);
					int blue = (int) (color.getBlue() * 0.0722);
					int sum = red + green + blue;

					Color shadeOfGrey = new Color(sum, sum, sum);
					image.setRGB(x, y, shadeOfGrey.getRGB());
				}
			}
			System.out.println(ConsoleColour.BLUE_BOLD);
			System.out.println("[Image converted to GreyScale]");
			System.out.println(ConsoleColour.RESET);

			new SaveFile(image);
		} catch (IOException ex) {
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR HAS OCCURED]");
			System.out.println(ConsoleColour.RESET);
			ex.printStackTrace();
		}
	}
}
/*Referenced https://www.youtube.com/watch?v=luBSuon_Y5Q
Zoran Davidovic, Java Tutorial - GrayScale Image Filter, Oct 19, 2017*/