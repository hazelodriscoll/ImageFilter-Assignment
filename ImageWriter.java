package ie.gmit.dip;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageWriter {

	private static Scanner scanner = null;

	public ImageWriter() throws Exception {
		scanner = new Scanner(System.in);

		initImageMenu();

		try {
			String userPath = scanner.next();

			// The following takes in the user input of a filename thru a scanner and the
			// method getFile checks that it exists.
			File f = getFile(userPath);
			BufferedImage image = ImageIO.read(f); // Returns a buffered image from given file
			System.out.println("Image has been recieved."); // Prints data on image entered
			ImageIO.write(image, "png", new File("./in.png")); // Saves the file as in.png in the current directory.

		} catch (IOException e) {
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR HAS OCCURED]");
			System.out.println(ConsoleColour.RESET);
			e.printStackTrace();// If an exception occurs, print out the stack trace to see where it has gone
								// wrong.
			System.out.println("Please try again");
		}
	}

	private void initImageMenu() {
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("[Please enter full filepath of image file you wish to filter]");
		System.out.println(ConsoleColour.RESET);
	}

	private File getFile(String userPath) { // Checks that file name entered by user exists or is correct and if so,
											// returns that file.
		File f = new File(userPath);

		while (!f.exists()) {// Added a loop so that it keeps taking input until correct file name is put in
			System.out.println(ConsoleColour.RED);
			System.out.println("Invalid file name! Please try again: ");
			System.out.println(ConsoleColour.RESET);
			String path = scanner.next();
			f = new File(path);
		}
		return f;
	}
}
