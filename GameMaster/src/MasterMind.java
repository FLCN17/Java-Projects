///MAKE CLASS BASED


import java.io.*;
import java.util.*;

/**@author cory cothrum
 * 3/21/2018
 * MASTERMIND
 * Basic implementation of the popular board game MASTERMIND
 * generates string from color pallete, allows guesses from player
 * gives feedback on # of matches and # of 'misses', or colors out of order.
 * allows multiple games
 * 
 * TODO
 * -score tracking
 * -larger play size - more colors, larger space
 * -make color validation more robust- need to add separate '|| curChoice ==' per color
 * 
 * 3/22/2018
 * Opened up the game size and difficulty/modes, allowing for different games of MASTERMIND
 * Handles game generation per mode and difficulty
 * TODO
 * Fix comparison - currently misses around one or two letters. Problem with avoid doubles.
 */

public class MasterMind {

	static Random rand = new Random();						//declare as static at top just to make it easier to use
	static Scanner keyboard = new Scanner(System.in);  		
	static String[] validColors = {"Red", "Blue", "Yellow", "Green", "Teal", "Orange", "Pink", "White", "Violet", "Lavender", "Maroon", "Cyan"};     //rbygtopwvlmc - 12 colors   //adefhijknqsuxz - leftover characters
	static String[] validWords = new String[4031]; 			//# words in file
	
	
	/* mainMenu - 
	 * Displays the menu, and gathers which game to play
	 */
	public static void mainMenu() throws FileNotFoundException {
		int game;
		boolean badinput = true;
		System.out.println("Please enter the game mode you would like:\n1. Classic MASTERMIND\n2. Advanced MASTERMIND\n3. Word MASTERMIND\n4. Number MASTERMIND\n5. EXTREME MASTERMIND\nPlease enter choice: ");
		do {
			game = keyboard.nextInt();
			if(game >= 1 || game <= 5) 
				badinput = false;
			else 
				System.out.println("Must enter 1 - 5 for game mode.");
		} while(badinput);
		gameSetter(game);
	}
	
	
	/* colorGenerator - 
	 * Generates the computers colors based on valid colors
	 * Returns game string with chosen colors
	 */
	public static String colorGenerator(int size, int colorSpace) { 
		String  gameString ="";
		for(int x = 0; x < size; x++) 
			gameString += validColors[rand.nextInt(colorSpace)].charAt(0);
		return gameString;
	}
	
	
	/* wordGenerator - 
	 * Generates the computers word based on valid words
	 * Returns game string with chosen colors
	 */
	public static String wordGenerator(int size) { 
		String gameString = validWords[rand.nextInt(validWords.length)];
		return gameString;
	}
	
	
	/* wordDictionaryLoader
	 * Loads the 4-letter word dictionary
	 */
	public static void wordDictionaryLoader(int size) throws FileNotFoundException{
		int x = 0;
		String filename = "dictionary4.txt", curWord = "", inputString;
		/*
		switch(size) {
			case 3: filename = "dictionary3.txt";
				break;
			case 5: filename = "dictionary5.txt";
				break;
		}
		*/
		File file = new File(filename);
		Scanner inputFile = new Scanner(file);
		inputString = inputFile.nextLine();
		for(int y = 0; y < inputString.length(); y++) {
			curWord += inputString.charAt(y);
			if(curWord.length() == size) {
				validWords[x++] = curWord;
				curWord = "";
			}
		}
		inputFile.close();
	}
	
	
	/* numberGenerator - 
	 * Generates the computers numbers
	 * Returns game string with chosen numbers
	 */
	public static String numberGenerator(int size) { 
		String  gameString ="", numbers = "0123456789";
		for(int x = 0; x < size; x++) 
			gameString += numbers.charAt(rand.nextInt(10));
		return gameString;
	}
	
	
	/* letterGenerator - 
	 * Generates the computers random letters, with chance of uppercase depending on difficulty
	 * Returns game string with chosen numbers
	 */
	public static String letterGenerator(int size, int difficulty) { 
		String  gameString ="", letters = "abcdefghijklmnopqrstuvwxyz";
		for(int x = 0; x < size; x++) 
			gameString += ( rand.nextInt(2) == 0 && difficulty > 1 ? Character.toUpperCase(letters.charAt(rand.nextInt(letters.length()))) : letters.charAt(rand.nextInt(letters.length()))) ;
		return gameString;
	}
	
	
	/* playerInput - 
	 * Handles input validation and creating players color guess string
	 * Returns players guess string with chosen colors
	 */
	public static String playerInput(int size, int colorSpace) {
		String playerString = "";
		char curChoice;
		int x = 0;
		boolean badinput;
		System.out.printf("Please enter one of %d colors: ", colorSpace);
		for(int y = 0; y < colorSpace; y++) {
			System.out.print(validColors[y]);
			if (y == colorSpace-2)
				System.out.print(" or ");
			else if (y != colorSpace - 1)
				System.out.print(", ");
			else
				System.out.println();
		}
		for(int i = 0; i < size; i++) {
			badinput = true;
			do {
				System.out.printf("Color %d: ", ++x);
				curChoice = keyboard.next().charAt(0);
				curChoice = Character.toUpperCase(curChoice);
				for(int z = 0; z < colorSpace; z++) {
					if(curChoice == validColors[z].charAt(0)) {
						playerString += curChoice;
						badinput = false;
					}
				}
			} while(badinput);
		}
		return playerString;
	}
	
	
	/* playerWordInput - 
	 * Handles input validation and creating players word guess string
	 * Returns players guess string with chosen letters
	 */
	public static String playerWordInput(int size) {
		String playerString = "";
		char curChoice;
		int x = 0;
		System.out.printf("Please enter %d letters: \n", size);
		for(int i = 0; i < size; i++) {
			System.out.printf("Letter %d: ", ++x);
			curChoice = keyboard.next().charAt(0);
			playerString += Character.toUpperCase(curChoice);
		}
		return playerString;
	}
	
	
	/* playerNumberInput - 
	 * Handles input validation and creating players guess number string
	 * Returns players guess string with chosen numbers
	 */
	public static String playerNumberInput(int size) {
		String playerString = "";
		char curChoice;
		int x = 0;
		boolean badinput;
		System.out.println("Please enter a number: ");
		for(int i = 0; i < size; i++) {
			badinput = true;
			do {
				System.out.printf("Choice %d: ", ++x);
				curChoice = keyboard.next().charAt(0);
				if(curChoice >= 0 || curChoice <= 9) {
					playerString += curChoice;
					badinput = false;
				}
				else 
					System.out.println("Must be a number.");
			} while(badinput);
		}
		return playerString;
	}
	
	
	/* playerLetterInput - 
	 * Handles creating players guess letter string
	 * Returns players guess string with chosen letters
	 */
	public static String playerLetterInput(int size) {
		String playerString = "";
		char curChoice;
		int x = 0;
		System.out.printf("Please enter %d letters: \n", size);
		for(int i = 0; i < size; i++) {
			System.out.printf("Letter %d: ", ++x);
			curChoice = keyboard.next().charAt(0);
			playerString += Character.toUpperCase(curChoice);
		}
		return playerString;
	}
	
	
	/* checkGameString -
	 * Handles checking of the two strings;
	 * Compares the players string with the games string one char at a time, and increments
	 * either the match score or the miss score
	 * Also handles the score feedback for # matches and misses.
	 * Returns the matched score (the miss score doesn't actually matter in considering a win)
	 */
	public static int checkGameString(String gameString, String playerString, int size) {
		System.out.printf("checking player: %s\ngame: %s\n", playerString, gameString);
		int score = 0, missed = 0, matched = 0;
		int[] uniqueMatches = new int[size];
		
		for(int x = 0; x < size; x++) {  //matches
			if(playerString.charAt(x) == gameString.charAt(x)) {
				matched++;
				if(uniqueMatches[x] != x) {
					uniqueMatches[x] = x;
					score += 10;
				}
			}
			//misses????
			//must count misses ONCE
			//copy new string, replace chars as you check? seems wasteful
			//just checking elements of player with elements of string, no matter the complication, produces doubles or miscounts.
			
			
		}
		if(matched == size)
			gameWon = true;
		System.out.printf("\nYou got %d items in the right place, and matched %d items in the wrong place.\n",matched, missed);
		return score;
	}
	
	//boolean to track game status - avoids returning matches to determine win/loss
	static boolean gameWon = false;
	
	/* classicMastermindGame - 
	 * Calls the gameGenerator to create the 4 computers colors, then calls the 4 playerInput 
	 * and compares the two, looping until the player either wins or runs out of 12 chances. 
	 */
	public static int classicMastermindGame(int difficulty) {
		int chances = 12, size = 4, colorSpace = 6, score;
		boolean stillGuessing = true;
		String gameString, playerString;
		switch (difficulty) {
		case 1: chances = 16; colorSpace = 5;
			break;
		case 3: chances = 8; colorSpace = 8;
			break;
		}
		System.out.printf("\n\t\tWelcome to MASTERMIND!\n\nThe goal of this game is to guess which %d colors the computer\nhas in its mind. If you can guess all %d, and in the right order,\nyou win! You only have %d chances though, so if you run out\nthen its Game Over.\n\n\nAre you ready for MASTERMIND!\n", size, size, chances);
		gameString = colorGenerator(size, colorSpace);
		do {
			playerString = playerInput(size, colorSpace);
			score = checkGameString(gameString, playerString, size);
			if(gameWon) {
				stillGuessing = false;
			}
			else
				System.out.printf("%d guesses remaining.\n\n", --chances);
			if(chances == 0) 
				stillGuessing = false;
		} while(stillGuessing);
		if(gameWon) 
			System.out.println("You won!");
		else
			System.out.println("You lose.");
		return score;
	}
	
	
	/* advancedMastermindGame - 
	 * Calls the gameGenerator to create 5 computers colors, then calls the 5 playerInput 
	 * and compares the two, looping until the player either wins or runs out of chances. 
	 * Uses larger color and guess space.
	 */
	public static int advancedMastermindGame(int difficulty) {
		int chances = 10, size = 5, colorSpace = 8, score;
		boolean stillGuessing = true;
		String gameString, playerString;
		switch (difficulty) {
			case 1: chances = 12; colorSpace = 6;
				break;
			case 3: chances = 8; colorSpace = 10;
				break;
		}
		System.out.printf("\n\t\tWelcome to Advanced MASTERMIND!\n\nThe goal of this game is to guess which %d colors the computer\nhas in its mind. If you can guess all %d, and in the right order,\nyou win! You only have %d chances though, so if you run out\nthen its Game Over.\n\n\nLets play MASTERMIND!\n", size, size, chances);
		gameString =colorGenerator(size, colorSpace);
		do {
			playerString = playerInput(size, colorSpace);
			score = checkGameString(gameString, playerString, size);
			if(gameWon) {
				stillGuessing = false;
			}
			else
				System.out.printf("%d guesses remaining.\n\n", --chances);
			if(chances == 0) 
				stillGuessing = false;
		} while(stillGuessing);
		if(gameWon) 
			System.out.println("You won!");
		else
			System.out.println("You lose.");
		
		return score;
	}
	
	
	/* wordMastermindGame - 
	 * Calls the gameGenerator to create the computer word, then calls the playerInput 
	 * and compares the two, looping until the player either wins or runs out of chances. 
	 */
	public static int wordMastermindGame(int difficulty) throws FileNotFoundException {
		int chances = 12, size = 4, score;
		boolean stillGuessing = true;
		String gameString, playerString;
		switch (difficulty) {
			case 1: chances = 15; //size = 3;
				break;
			case 3: chances = 8;  //size = 5;
				break;
		}
		wordDictionaryLoader(size);
		System.out.printf("\n\t\tWelcome to Word MASTERMIND!\n\nThe goal of this game is to guess which %d letter word the computer\nhas in its mind. If you can guess all %d, and in the right order,\nyou win! You only have %d chances though, so if you run out\nthen its Game Over.\n\n\nLets play MASTERMIND!\n", size, size, chances);
		gameString = wordGenerator(size);
		do {
			playerString = playerWordInput(size);
			score = checkGameString(gameString, playerString, size);
			if(gameWon) {
				stillGuessing = false;
			}
			else
				System.out.printf("%d guesses remaining.\n\n", --chances);
			if(chances == 0) 
				stillGuessing = false;
		} while(stillGuessing);
		if(gameWon) 
			System.out.println("You won!");
		else
			System.out.println("You lose.");
		
		return score;
	}
	
	
	/* numberMastermindGame - 
	 * Calls the gameGenerator to create the 5 computers numbers, then calls the playerInput 
	 * and compares the two, looping until the player either wins or runs out of chances. 
	 */
	public static int numberMastermindGame(int difficulty) {
		int chances = 8, size = 5, score;
		boolean stillGuessing = true;
		String gameString, playerString;
		switch (difficulty) {
			case 1: chances = 12;
				break;
			case 3: chances = 5;
				break;
		}
		System.out.printf("\n\t\tWelcome to Number MASTERMIND!\n\nThe goal of this game is to guess which %d numbers the computer\nhas in its mind. If you can guess all %d, and in the right order,\nyou win! You only have %d chances though, so if you run out\nthen its Game Over.\n\n\nLets play MASTERMIND!\n", size, size, chances);
		gameString = numberGenerator(size);
		do {
			playerString = playerNumberInput(size);
			score = checkGameString(gameString, playerString, size);
			if(gameWon) {
				stillGuessing = false;
			}
			else
				System.out.printf("%d guesses remaining.\n\n", --chances);
			if(chances == 0) 
				stillGuessing = false;
		} while(stillGuessing);
		if(gameWon) 
			System.out.println("You won!");
		else
			System.out.println("You lose.");
		
		return score;
	}
	
	
	/* advancedMastermindGame - 
	 * Calls the gameGenerator to create 5 computers colors, then calls the 5 playerInput 
	 * and compares the two, looping until the player either wins or runs out of chances. 
	 * Uses larger color and guess space.
	 */
	public static int extremeMastermindGame(int difficulty) {
		int chances = 10, size = 7, score;
		boolean stillGuessing = true;
		String gameString, playerString;
		switch (difficulty) {
			case 1: chances = 12; size = 6;
				break;
			case 3: chances = 8; size = 8;
				break;
		}
		System.out.printf("\n\t\tWelcome to EXTREME MASTERMIND!\n\nThe goal of this game is to guess which %d letters the computer\nhas in its mind. If you can guess all %d, and in the right order,\nyou win! You only have %d chances though, so if you run out\nthen its Game Over.\n\n\nLets play MASTERMIND!\n", size, size, chances);
		gameString = letterGenerator(size, difficulty);
		do {
			playerString = playerLetterInput(size);
			score = checkGameString(gameString, playerString, size);
			if(gameWon) {
				stillGuessing = false;
			}
			else
				System.out.printf("%d guesses remaining.\n\n", --chances);
			if(chances == 0) 
				stillGuessing = false;
		} while(stillGuessing);
		if(gameWon) 
			System.out.println("You won!");
		else
			System.out.println("You lose.");
		
		return score;
	}
	
	
	/* gameSetter - 
	 * Handles which game is played and what difficulty, and handles score management
	 */
	public static void gameSetter(int game) throws FileNotFoundException {
		int difficulty, classicScore, advScore, wordScore, numberScore, extremeScore, gameTotal;
		String filename = "scores.txt";
		boolean badinput = true;
		//grab difficulty, pipe to game
		
		//grab scores from file
		File file = new File(filename);
		if(file.exists()) {
			Scanner inputFile = new Scanner(file);
			gameTotal = inputFile.nextInt();
			inputFile.nextLine();
			classicScore = inputFile.nextInt();
			inputFile.nextLine();
			advScore = inputFile.nextInt();
			inputFile.nextLine();
			wordScore = inputFile.nextInt();
			inputFile.nextLine();
			numberScore = inputFile.nextInt();
			inputFile.nextLine();
			extremeScore = inputFile.nextInt();
			inputFile.close();
		}
		else {
			gameTotal = 0;
			classicScore = 0;
			advScore = 0;
			wordScore = 0;
			numberScore = 0;
			extremeScore = 0;
		}
		
		System.out.println("Please enter the difficulty level you would like:\n1. Easy\n2. Normal\n3. Hard\nPlease enter choice: ");
		do {
			difficulty = keyboard.nextInt();
			if(difficulty >= 1 || difficulty <= 3) 
				badinput = false;
			else 
				System.out.println("Must enter 1, 2, or 3 for difficulty.");
		} while(badinput);
		switch(game) {
			case 1:				//Classic MASTERMIND
				classicScore += classicMastermindGame(difficulty) + (gameWon ? 30*difficulty : 0);
				break;
			case 2:				//Adv. MASTERMIND
				advScore += advancedMastermindGame(difficulty) + (gameWon ? 50*difficulty : 0);
				break;
			case 3:				//Word MASTERMIND
				wordScore += wordMastermindGame(difficulty) + (gameWon ? 60*difficulty : 0);
				break;
			case 4: 			//Number MASTERMIND
				numberScore += numberMastermindGame(difficulty) + (gameWon ? 75*difficulty : 0);
				break;
			case 5:				//Extreme MASTERMIND
				extremeScore += extremeMastermindGame(difficulty) + (gameWon ? 100*difficulty : 0);
				break;
		}
		gameWon = false;
		//output scores
		PrintWriter outputFile = new PrintWriter(filename);
		outputFile.println(++gameTotal);
		outputFile.println(classicScore);
		outputFile.println(advScore);
		outputFile.println(wordScore);
		outputFile.println(numberScore);
		outputFile.println(extremeScore);
		outputFile.close();
	}
}