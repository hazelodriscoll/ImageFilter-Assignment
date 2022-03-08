package ie.gmit.dip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class ImageFilter {
	
	public static String filename = "./in.png";
	private static Scanner scanner = null;
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public ImageFilter() throws IOException {
		scanner = new Scanner(System.in);
		
		listFilters();
		try {
		processFilter();
		} catch (IIOException exception) { //Catch exception where user has forgotten to input image
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR] Must enter image before selecting a filter");
			System.out.println(ConsoleColour.RESET);
		}
	}
	
	private void listFilters() {
		
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("<Please enter the number for the corresponding filter choice>");
		System.out.println(ConsoleColour.RESET);
		System.out.println("[1] Identity");
		System.out.println("[2] Edge Detection 1");
		System.out.println("[3] Edge Detection 2");
		System.out.println("[4] Laplacian");
		System.out.println("[5] Sharpen");
		System.out.println("[6] Vertical Lines");
		System.out.println("[7] Horizontal Lines");
		System.out.println("[8] Diagonal 45 Lines");
		System.out.println("[9] Box Blur");
		System.out.println("[10] Sobel Horizontal");
		System.out.println("[11] Sobel Vertical");
	}

	public static void processFilter() throws IOException {

		File input = new File("./in.png"); // Accesses image received and saved from ImageWriter
		
		BufferedImage image = ImageIO.read(input);
		String userInput = scanner.next();
		int userChoice = Integer.parseInt(userInput);
		
		double[][] filterChoice = null; // Creating variable filterChoice to be used in switch statement.

		switch (userChoice) { // New switch statement to take in user input and process chosen filter
		case 1 -> {
			filterChoice = setKernel(KernelEnum.IDENTITY.getKernel());
			System.out.println("You have chosen the filter 'Identity'.");
		}
		case 2 -> {
			filterChoice = setKernel(KernelEnum.EDGE_DETECTION_1.getKernel());
			System.out.println("You have chosen the filter 'Edge Detection 1'.");
		}
		case 3 -> {
			filterChoice = setKernel(KernelEnum.EDGE_DETECTION_2.getKernel());
			System.out.println("You have chosen the filter 'Edge Detection 2'.");
		}
		case 4 -> {
			filterChoice = setKernel(KernelEnum.LAPLACIAN.getKernel());
			System.out.println("You have chosen the filter 'Laplacian'.");
		}
		case 5 -> {
			filterChoice = setKernel(KernelEnum.SHARPEN.getKernel());
			System.out.println("You have chosen the filter 'Sharpen'.");
		}
		case 6 -> {
			filterChoice = setKernel(KernelEnum.VERTICAL_LINES.getKernel());
			System.out.println("You have chosen the filter 'Vertical Lines'.");
		}
		case 7 -> {
			filterChoice = setKernel(KernelEnum.HORIZONTAL_LINES.getKernel());
			System.out.println("You have chosen the filter 'Horizontal Lines'.");
		}
		case 8 -> {
			filterChoice = setKernel(KernelEnum.DIAGONAL_45_LINES.getKernel());
			System.out.println("You have chosen the filter 'Diagonal 45 Lines'.");
		}
		case 9 -> {
			filterChoice = setKernel(KernelEnum.BOX_BLUR.getKernel());
			System.out.println("You have chosen the filter 'Box Blur'.");
		}
		case 10 -> {
			filterChoice = setKernel(KernelEnum.SOBEL_HORIZONTAL.getKernel());
			System.out.println("You have chosen the filter 'Sobel Horizontal'.");
		}
		case 11 -> {
			filterChoice = setKernel(KernelEnum.SOBEL_VERTICAL.getKernel());
			System.out.println("You have chosen the filter 'Sobel Vertical'.");
		}
		default -> {
			System.out.println(ConsoleColour.RED);
			System.out.println("Invalid Selection: Please choose filter number between [1-11] ");
			System.out.println(ConsoleColour.RESET);
			}
		}//End of switch statement

		double[][] filterArray = filterChoice; // filterChoice will now be referred to as filterArray
		
		try {
		filter(image, filterArray);
		}catch (NullPointerException ex) {
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR] NullPointer Exception: Must enter the corresponding number.");
			System.out.println(ConsoleColour.RESET);
		}
	}

	private static double[][] setKernel(double[][] filterArray) {
		double[][] filterChoice = filterArray;
		return filterChoice;
	}

	public static void filter(BufferedImage image, double[][] filterArray) throws IOException {
		
		BufferedImage input = image;
		int width = input.getWidth();
		int height = input.getHeight();
		
		BufferedImage output = new BufferedImage(width, height, input.getType()); 
		
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				// first 2 loops used to access every pixel in image
				float red = 0f,green = 0f,blue = 0f;
				
				for (int i = 0; i < filterArray.length; i++) { // Used to apply kernel values to image
					for (int j = 0; j < filterArray[0].length; j++) {
					
					
						//Modulus to avoid ArrayIndexOutOfBounds
						int imageX = (row - filterArray.length / 2 + i + width) % width; 
						int imageY = (col - filterArray[0].length / 2 + j + height) % height; 
						
						
						int RGB = input.getRGB(imageX,imageY);
						int R = (RGB >> 16) & 0xff; // Red Value
						int G = (RGB >> 8) & 0xff;	// Green Value
						int B = (RGB) & 0xff;		// Blue Value
						
						// The RGB is multiplied with current kernel element and added on to the variables red, blue and green
						red += (R*filterArray[i][j]);
						green += (G*filterArray[i][j]);
						blue += (B*filterArray[i][j]);
					}
				}
				int outR, outG, outB;
				
				// The value is truncated to 0 and 255 if it goes beyond range
				outR = (int) Math.min(Math.max((red),0),255);
				outG = (int) Math.min(Math.max((green),0),255);
				outB = (int) Math.min(Math.max((blue),0),255);
				// Pixel is written to output image
				try {
				output.setRGB(row,col,new Color(outR,outG,outB).getRGB());
				}catch (Exception e) {
					continue;
				}
			}
		}
		new SaveFile(output);
	}
}
/*Referenced http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1 Of Bits and Pieces—
Kernel Image Processing : Image Filters (with Java Code)*/