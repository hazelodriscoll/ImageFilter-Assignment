package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class SaveFile {

	public SaveFile(BufferedImage image) {
		System.out.println("The image has been filtered.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Note: Image file will be saved in current directory");
		System.out.println("<Enter name you wish to save file as:>");

		try {
			String outputFilename = (reader).readLine(); // Uses BufferedReader to go through output file and saves as
															// new image
			ImageIO.write(image, "PNG", new File(outputFilename + ".png"));

			System.out.println(ConsoleColour.GREEN);
			System.out.println("File saved as " + outputFilename + ".png"); // Saves file under chosen name from user
																			// input
			System.out.println(ConsoleColour.RESET);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("[ERROR SAVING FILE]");
		}
	}
}
