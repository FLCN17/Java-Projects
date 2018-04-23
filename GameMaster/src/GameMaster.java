import java.io.FileNotFoundException;
import java.util.Scanner;

/** GAME MASTER - 
 * @author cory cothrum
 * 4/7/2018
 * Game Master is a collection of programs I have created or planned over the years, 
 * unified in one location, ideally with a nice UI
 * 
 * 
 */
public class GameMaster {

	public static void main(String[] args) throws FileNotFoundException {
		double version = 0.03;
		boolean continueGame = true;
		String title = "GAME MASTER", continuePlaying = "";
		Scanner keyboard = new Scanner(System.in);
		Menu gameMenu = new Menu();
		System.out.printf("Welcome to\n=======%s=======\nversion %.2f\n\n This is a collection of fun and easy games to play,\nas well as unobfuscated code to examine in order\nto gain better understanding of programming, and\nallow users to modify or create their own games!\n\n", title, version);
		do {
			gameMenu.mainMenu();
			System.out.println("Would you like to play another game?");
			continuePlaying = keyboard.nextLine();
			if(continuePlaying.toUpperCase().charAt(0) != 'Y')
				continueGame = false;
		} while(continueGame);
		keyboard.close();
	}

}
