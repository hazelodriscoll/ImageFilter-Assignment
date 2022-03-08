package ie.gmit.dip;

import java.util.Scanner;

public class Extras {

	private Scanner scanner = null;
	private boolean keepRunning = true;

	public Extras() throws Exception {
		scanner = new Scanner(System.in);
		initMenu();

		do {
			processChoice();
		} while (keepRunning == true);
	}

	public void initMenu() { // Lists Extra Options
		System.out.println();
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("**Extra Options**");
		System.out.println(ConsoleColour.RESET);
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("Select Option [1-3]");
		System.out.println(ConsoleColour.RESET);
		System.out.println("[1] Create-Your-Own Filter Matrix");
		System.out.println("[2] Convert Colour Image to GreyScale");
		System.out.println("[3] Rotate Image 180 Degrees");
		System.out.println("[4] Go Back to Main Menu");

	}

	public void processChoice() throws Exception {
		try {
			String userInput = scanner.next();
			int userChoice = Integer.parseInt(userInput);

			switch (userChoice) {
			case 1 -> {
				System.out.println("[[Create Your Own Filter]]");
				new CreateYourOwn();
				System.out.println();
				System.out.println("<Returning to Extras Menu>");
				initMenu(); // Bring up Menu again so user doesn't have far to scroll for options
			}
			case 2 -> {
				System.out.println("[[Convert Color Image to GreyScale]]");
				new GreyScale();
				System.out.println("<Returning to Extras Menu> Select options [1-4]"); // No need to bring up Menu again
			}
			case 3 -> {
				System.out.println("[[Rotate Image 180]]");
				new Rotate180();
				System.out.println("<Returning to Extras Menu> Select options [1-4]");
			}
			case 4 -> {
				keepRunning = false;
				System.out.println("<<Initialising Main Menu>>"); // Option to quit out of application
			}
			default -> { // Look at exceptions to handle if user enters anything that is not an int
				System.out.println(ConsoleColour.RED);
				System.out.println(
						"\n[ERROR]: <Invalid Number: Restarting Extras Menu: Please select option using specified numbers [1-4]>");
				System.out.println(ConsoleColour.RESET);
				processChoice(); // Resets menu and reminds user they need to have the correct input method
			}
			} // End of switch statement
		} // End of try block
		catch (NumberFormatException e) {
			System.out.println(ConsoleColour.RED);
			System.out.println(
					"\n[ERROR]: <Invalid Character Input> Restarting Extras Menu: Please used characters specfied.>");
			System.out.println(ConsoleColour.RESET);
			processChoice(); // Resets menu and reminds user they need to have the correct input method
		}
	}
}
