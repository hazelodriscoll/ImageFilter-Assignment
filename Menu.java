package ie.gmit.dip;

import java.util.Scanner;

//Menu Class to initialize list of options and take user input as to which action they want to take.
public class Menu {

	private Scanner scanner = null;
	private boolean keepRunning = true; // Used to give option to shut down the application/menu process

	public Menu() throws Exception {
		scanner = new Scanner(System.in);
		initMenu();

		do {
			processInput();
		} while (keepRunning == true);
	}
	
	private void initMenu() {

		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println(" ___________________________________________________");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics   *");
		System.out.println("*                                                   *");
		System.out.println("*           Image Filtering System V0.1             *");
		System.out.println("*     H.Dip in Science (Software Development)       *");
		System.out.println("*               Hazel O'Driscoll                    *");
		System.out.println("*___________________________________________________*");

		System.out.println("\n[1] Enter Name of Image File"); // Ask user to specify the file to process.
		System.out.println("[2] Select a Filter"); // Lists the set of filters available in the class Kernel.java
		System.out.println("[3] Extra Options");
		System.out.println("[4] Quit"); // Terminate
		System.out.println("\nEnter [1] to upload Image File and then select options [2-4]>");
		System.out.println(ConsoleColour.RESET);
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);

	}
	
	private void processInput() throws Exception {

		try {
			String input = scanner.next();
			int choice = Integer.parseInt(input);

			switch (choice) {
			case 1 -> { // Gives access to the class ImageWriter which will take the name of the file
				new ImageWriter();
				System.out.println("Choose next option from Main Menu.");
			}
			case 2 -> { //Filters input image with filters chosen by user
				new ImageFilter();
				shortMenu();//Brings up simplified menu so user doesn't have to scroll back up
			}
			case 3 -> { //Provides available extra options in new menu
				new Extras(); 
				System.out.println();
				shortMenu();
			}
			case 4 -> { //Closes application
				keepRunning = false;
				System.out.println("Terminating...");
				System.out.println(ConsoleColour.PURPLE_UNDERLINED);
				System.out.println("*[APPLICATION TERMINATED]*");// Option to quit out of application
				System.out.println(ConsoleColour.RESET);
			}
			default -> {// Looks at exceptions if user enters int outside the range [1-4]
				System.out.println(ConsoleColour.RED);
				System.out.println("[ERROR]: <Invalid Number: Please select option using specified numbers [1-4]>");
				System.out.println(ConsoleColour.RESET);
			}
			} // End of switch statement
		} // End of try block
		catch (NumberFormatException e) { //Catches exceptions for invalid character inputs
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR]: <Invalid Character Input> Restarting Initial Menu: Please used numbers specfied for each menu.>");
			System.out.println(ConsoleColour.RESET);
			processInput(); // Resets to original menu and reminds user they need to have the correct input
		}
	} // End of method
	
	private void shortMenu() {
		System.out.print(ConsoleColour.YELLOW);
		System.out.println("<Restarting Menu for additional options>");
		System.out.print(ConsoleColour.RESET);
		System.out.print(ConsoleColour.BLUE_BOLD);
		System.out.println("\n[1] Enter Name of Image File"); // Ask user to specify the file to process.
		System.out.println("[2] Select a Filter"); // Lists the set of filters available in the class Kernel.java
		System.out.println("[3] Extra Options");
		System.out.println("[4] Quit"); // Terminate
		System.out.println("\nEnter [1] to upload/change Image File and/or select options [2-4]>");
		System.out.print(ConsoleColour.RESET);
	}
}