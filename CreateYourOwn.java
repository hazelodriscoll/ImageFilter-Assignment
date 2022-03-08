package ie.gmit.dip;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.System.out;


public class CreateYourOwn {
		public static String filename = "./in.png";
		 //Accesses image received and saved from ImageWriter
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		public CreateYourOwn() throws IOException{
			
			System.out.println(ConsoleColour.CYAN_UNDERLINED);
			out.print("[Please enter your chosen Kernel order]");
			System.out.println(ConsoleColour.RESET);
			
			out.println("\nE.g.: For 3x3 Matrix: Enter '3'");
			int order = Integer.parseInt(reader.readLine());
			
			float[][] kernel = new float[order][order];
			float sum_of_elements = 0.0f;
			float mult_factor = 1.0f;
			float bias = 0f;
			
			System.out.println(ConsoleColour.BLUE_BOLD);
			out.print("\nEnter kernel values starting from top left to right:");
			System.out.println(ConsoleColour.RESET);
			
			// Getting the Kernel Matrix as input from the user
			for(int i=0; i < order; i++)
			for(int j=0; j < order; j++)
			{	
				out.print(i+","+j+":");
				kernel[i][j] = Float.parseFloat(reader.readLine());
			}
			System.out.println(ConsoleColour.BLUE_BOLD);
			out.println("\nThe Kernel Matrix is:\n");
			System.out.println(ConsoleColour.RESET);
			
			for(int i=0; i < order; i++)
			{
				for(int j=0; j < order; j++)
				{
					out.print("\t"+kernel[i][j]);
					sum_of_elements += kernel[i][j];
				}
				out.println();
			}
			
			out.println("\nThe sum of matrix elements is: "+sum_of_elements);
			
			// mult_factor is the value with which each computed sum is multiplied
			
			System.out.println(ConsoleColour.BLUE_BOLD);
			out.print("\nMultiplication Factor: ");
			System.out.println(ConsoleColour.RESET);
			out.print("\n(Hint: Multipication Factor of 1 will give no change to Matrix input)\n");
			mult_factor = Float.parseFloat(reader.readLine());
			
			// Bias can be added to increase brightness of the image 
			
			System.out.println(ConsoleColour.BLUE_BOLD);
			out.print("Bias: ");
			System.out.println(ConsoleColour.RESET);
			out.print("\n(Hint: Bias added to increase brightness of image)");
			out.print("\n(Hint: If sum of elements is 1: Enter 0 for no change in brightness)\n");
			bias = Float.parseFloat(reader.readLine());
			
			BufferedImage input, output;
			
			input = ImageIO.read(new File(filename));
			int width = input.getWidth();
			int height = input.getHeight();
			float red = 0f,green = 0f,blue = 0f;
			
			output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
			// Set type to ARGB to avoid Exception of Unknown Image Type 0
			
			System.out.println(ConsoleColour.YELLOW);
			out.println("[*] Rendering the image...");
			System.out.println(ConsoleColour.RESET);
			
			for(int x = 0; x < width; x++){
				for(int y = 0; y < height; y++){
					
					for(int i = 0;i < order; i++){
						for(int j = 0; j < order; j++){
							// Calculating X and Y coordinates of the pixel to be multiplied with current kernel element
							// In case of edges of image the '% WIDTH' wraps the image and the pixel from opposite edge is used
							int imageX = (x - order / 2 + i + width) % width; 
							int imageY = (y - order / 2 + j + height) % height; 
							
							int RGB = input.getRGB(imageX,imageY);
							int R = (RGB >> 16) & 0xff; // Red Value
							int G = (RGB >> 8) & 0xff;	// Green Value
							int B = (RGB) & 0xff;		// Blue Value
							
							// The RGB is multiplied with current kernel element and added on to the variables red, blue and green
							red += (R*kernel[i][j]);
							green += (G*kernel[i][j]);
							blue += (B*kernel[i][j]);
						}
					}
					int outR, outG, outB;
					// The value is truncated to 0 and 255 if it goes beyond
					outR = Math.min(Math.max((int)(red*mult_factor),0),255);
					outG = Math.min(Math.max((int)(green*mult_factor),0),255);
					outB = Math.min(Math.max((int)(blue*mult_factor),0),255);
					// Pixel is written to output image
					output.setRGB(x,y,new Color(outR,outG,outB).getRGB());
				}
			}
			new SaveFile(output);
		}
}
/*Referenced http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1 Of Bits and Pieces—
Kernel Image Processing : Image Filters (with Java Code)*/
		