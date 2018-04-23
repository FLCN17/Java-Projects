import java.io.FileNotFoundException;
import java.util.Scanner; //surely I can piggyback from GameMasters input right?

public class Menu {
	
	static Scanner keyboard = new Scanner(System.in);
	
	public Menu() {
		
	}
	
	public void mainMenu() throws FileNotFoundException {
		//String[] gamesAvailable = {"Rock, Paper, Scissors","Master-Mind","SUPERZork","Dots and Crosses","TicTacToe","Connect Four","Battleship","CodeMaster","MagicMaster","MathMaster"};
		String[] gamesAvailable = {"Rock, Paper, Scissors","Master-Mind","SUPERZork","Die-Blackjack","Trivia Master"};
		boolean badinput = true;
		int menuChoice;
		
		System.out.printf("There are %d games available:\n", gamesAvailable.length);
		for(int i = 0; i < gamesAvailable.length; i++) 
			System.out.printf("%d. %s\n", i+1, gamesAvailable[i]);
		System.out.print("Which game would you like to play: ");
		do {
			menuChoice = keyboard.nextInt();
			keyboard.nextLine();
			if(--menuChoice >= 0 || menuChoice < gamesAvailable.length)
				badinput = false;
			else
				System.out.printf("Not a valid option, please choose 1 - %d", gamesAvailable.length-1);
		} while (badinput);
		switch(menuChoice) {
		case 0://RPS
			rockPaperScissorsMenu();
			break;
		case 1://MASTERMIND
			masterMindMenu();
			break;
		case 2://ZORK
			zorkMenu();
			break;
		case 3://Dice
			diceMenu();
			break;
		case 4: //trivia-master
			triviaMenu();
			break;
		//add more as games are added
		}
	}
	
	static boolean continueGame;
	static String continuePlaying = "";
	
	private void rockPaperScissorsMenu() {
		int rounds = 3, difficulty = 1;
		String sameSettings;
		boolean skip = false;
		
		System.out.println("\nWelcome to \n\tRock Paper Scissors\nThe classic game where\nrock beats scissors, scissors beat paper,\nand paper beats rock. There are three available\nmodes of difficulty to choose from, as well as the number of rounds you'd like to play.");
		do {
			if (continueGame) {
				System.out.println("Would you like to use the same settings?");
				sameSettings = keyboard.nextLine();
				if(sameSettings.toUpperCase().charAt(0) == 'Y')
					skip = true;
			}
			if (!skip) {
				System.out.println("Please enter the number of rounds: ");
				do {
					rounds = keyboard.nextInt();
					keyboard.nextLine();
				} while (rounds < 0);
				System.out.println("Please enter the difficulty:\n1. Easy\n2. Normal\n3. Hard\n");
				do {
					difficulty = keyboard.nextInt();
					keyboard.nextLine();
				} while (difficulty < 1 || difficulty > 3);
			}
			
			RockPaperScissors.Game(rounds, difficulty);
			
			System.out.println("Would you like to play again?");
			continuePlaying = keyboard.nextLine();
			if(continuePlaying.toUpperCase().charAt(0) != 'Y')
				continueGame = false;
			else
				continueGame = true;
		} while(continueGame);
	}
	
	private void masterMindMenu() throws FileNotFoundException {
		MasterMind.mainMenu(); //pull gamemenu logic out from MasterMind into here
	}
	
	private void zorkMenu() {
		
	}
	
	private void diceMenu() {
		int die = 2, sides = 6;
		String sameSettings, name = "";
		boolean skip = false;
		DiceGame game = new DiceGame();
		System.out.println("\n\tD I C E G A M E S\n\nThis is a take on the game of Blackjack,\nwhere the goal is to roll up to\nand exactly at the bust value. Any further \nand the player loses. The bust value shifts\nbased on number of dice and number of sides.");
		System.out.println("Please enter your name: ");
		name = keyboard.nextLine();
		do {
			if (continueGame) {
				skip = false;
				System.out.println("Would you like to use the same settings?");
				sameSettings = keyboard.nextLine();
				if(sameSettings.toUpperCase().charAt(0) == 'Y')
					skip = true;
					
			}
			if (!skip) {
				
				do {
					System.out.println("Please enter the number of die: ");
					die = keyboard.nextInt(); keyboard.nextLine();
				} while (die < 0);
				do {
					System.out.println("Please enter the number of sides: ");
					sides = keyboard.nextInt();	keyboard.nextLine();
				} while (sides < 0);
			}
			
			game.dicePlay(name, die, sides);
			
			continueGame = true;
			System.out.println("\nWould you like to play again?");
			continuePlaying = keyboard.nextLine();
			if(continuePlaying.toUpperCase().charAt(0) != 'Y')
				continueGame = false;
		} while(continueGame);
	}
	
	private void triviaMenu() {
		String sameSettings, name = "";
		boolean skip = false;
		TriviaQuiz game = new TriviaQuiz();
		System.out.println("\n\tTrivia Master\n\nThis is a trivia game");
		
		do {
			if (continueGame) {
				System.out.println("Would you like to use the same settings?");
				sameSettings = keyboard.nextLine();
				if(sameSettings.toUpperCase().charAt(0) == 'Y')
					skip = true;
			}
			if (!skip) {
				System.out.println("Please enter your name: ");
				name = keyboard.nextLine();
				
			}
			
			game.game();
			
			System.out.println("\nWould you like to play again?");
			continuePlaying = keyboard.nextLine();
			if(continuePlaying.toUpperCase().charAt(0) != 'Y')
				continueGame = false;
			else
				continueGame = true;
		} while(continueGame);
	}
}
