import java.util.Random;
import java.util.Scanner;

public class DieStats {

	public static void main(String[] args) {
		int choice, numDie, numSides, numRolls;
		boolean continueRun;
		String continueRunning = "";
		Scanner keyboard = new Scanner(System.in);
		String[] options = {"Die - Empirical","Die - Classical"};
		//handles calling of functions - menu
		do {
			System.out.println("\t-= Die Stats =-\n\nThis program allows the user to enter a number\nof dice, number of sides, and get the empirical\nand classical probabilities of both.");
			System.out.println("Options available:");
			for(int i = 0; i < options.length; i++) 
				System.out.printf("%d %s\n", i+1, options[i]);
			System.out.println("Please enter your choice: ");
			choice = keyboard.nextInt(); keyboard.nextLine();
			System.out.println("Please enter number of die to roll: ");
			numDie = keyboard.nextInt(); keyboard.nextLine();
			System.out.println("Please enter number of sides on those die: ");
			numSides = keyboard.nextInt(); keyboard.nextLine();
			switch(choice-1) {
			case 0: //empirical
				System.out.println("Please enter number of rolls: ");
				numRolls = keyboard.nextInt(); keyboard.nextLine();
				dieEmpirical(numSides, numDie, numRolls);
				break;
			case 1: //classical
				dieClassical(numSides, numDie);
				break;
			}
			System.out.println("Would you like to run again?");
			continueRunning = keyboard.nextLine();
			if(continueRunning.toUpperCase().charAt(0) != 'Y')
				continueRun = false;
			else
				continueRun = true;
		} while(continueRun);
		
		keyboard.close();
	}
	
	public static void dieEmpirical(int numSides, int numDie, int numRolls) {
		int[] tally = new int[((numSides * numDie)-(numDie - 1))];
		int roll = 0;
		Die[] dice = new Die[numDie];
		for(int i = 0; i < numDie; i++)
			dice[i] = new Die(numSides);
		for(int i = 0; i < tally.length; i++) //set your accumulator to 0
			tally[i] = 0;
		for(int i = 0; i < numRolls; i++) {//store the rolls IN that roll.....so the sum of (rand(0-5)+1 + rand(0-5)+1) - numDie
			for(int j = 0; j < numDie; j++)
				roll += dice[j].roll();
			tally[roll-numDie]++;
			roll = 0;
		}
		for(int i = 0; i < tally.length; i++)
			System.out.printf("Roll of %d: %d rolls\n", numDie + i , tally[i]);
		System.out.printf("Total rolls: %d\n", numRolls);
		for(int i = 0; i < tally.length; i++)
			System.out.printf("Percentage of total for roll of %d: %.4f%s\n", numDie + i , ((double)tally[i]/(double)numRolls)*100, "%");
	}
	
	
	public static void dieClassical(int numSides, int numDie) {
		
	}

}
