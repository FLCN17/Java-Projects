import java.util.Scanner;
import java.io.*;

/** DieStats -
 * @author cory
 * 5/13/18
 * produces the classical and empirical probabilities of rolls of dice, as well as average roll. 
 * The number of sides can be different or the same.
 */
public class DieStats {

	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		int choice, numDie, numSides;
		long numRolls;
		boolean continueRun;
		String continueRunning = "";
		String[] options = {"Die - Empirical", "Die - Empirical - Combination", "Die - Classical", "Die - Classical - Combination"};
		FileWriter fwriter = new FileWriter("log.txt", true);
		PrintWriter logOut = new PrintWriter(fwriter);
		
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
			switch(choice-1) {
			case 0: //empirical
				System.out.println("Please enter number of sides on those die: ");
				numSides = keyboard.nextInt(); keyboard.nextLine();
				System.out.println("Please enter number of rolls: ");
				numRolls = keyboard.nextLong(); keyboard.nextLine();
				logOut.printf("Empirical - Rolls: %d\tDie: %d\tSides: %d", numRolls, numDie, numSides);
				dieEmpirical(numSides, numDie, numRolls, logOut);
				break;
			case 1: //empirical combo
				System.out.println("Please enter number of rolls: ");
				numRolls = keyboard.nextLong(); keyboard.nextLine();
				logOut.printf("Empirical Combo - Rolls: %d\tDie: %d", numRolls, numDie);
				dieEmpiricalCombo(numDie, numRolls, logOut);
				break;
			case 2: //classical
				System.out.println("Please enter number of sides on those die: ");
				numSides = keyboard.nextInt(); keyboard.nextLine();
				logOut.printf("Classical - Die: %d\tSides: %d", numDie, numSides);
				dieClassical(numSides, numDie, logOut);
				break;
			case 3: //classical combo
				logOut.printf("Classical Combo - Die: %d\t", numDie);
				dieClassicalCombo(numDie, logOut);
				break;
			}
			System.out.println("Would you like to run again?");
			continueRunning = keyboard.nextLine();
			if(continueRunning.toUpperCase().charAt(0) != 'Y')
				continueRun = false;
			else
				continueRun = true;
		} while(continueRun);
		logOut.close();
		keyboard.close();
	}
	
	/** dieEmpirical -
	 * 
	 * @param numSides
	 * @param numDie
	 * @param numRolls
	 */
	public static void dieEmpirical(int numSides, int numDie, long numRolls, PrintWriter logOut) {
		int[] tally = new int[((numSides * numDie)-(numDie - 1))];
		double xBar = 0;
		int roll = 0;
		Die[] dice = new Die[numDie];
		for(int i = 0; i < numDie; i++)
			dice[i] = new Die(numSides);
		for(int i = 0; i < tally.length; i++) //set your accumulator to 0
			tally[i] = 0;
		for(int i = 0; i < numRolls; i++) {//store the rolls IN that roll...
			for(int j = 0; j < numDie; j++)
				roll += dice[j].roll();
			tally[roll-numDie]++;
			roll = 0;
		}
		logOut.println("");
		for(int i = 0; i < tally.length; i++) {
			System.out.printf("Roll of %d: %d rolls\n", numDie + i , tally[i]);
			logOut.printf("%d,", tally[i]);
			xBar += (tally[i]*(numDie+i));
		}
		logOut.println("");
		System.out.printf("Total rolls: %d\n", numRolls);
		for(int i = 0; i < tally.length; i++)
			System.out.printf("Percentage of total for roll of %d: %.4f%s\n", numDie + i , ((double)tally[i]/(double)numRolls)*100, "%");
		System.out.printf("Average roll: %.4f\n", xBar/numRolls);
		
	}
	
	/** dieEmpiricalCombo - 
	 * 
	 * @param numDie
	 * @param numRolls
	 */
	public static void dieEmpiricalCombo(int numDie, long numRolls, PrintWriter logOut) {
		int totalSides = 0, numSides;
		double xBar = 0;
		int roll = 0;
		Die[] dice = new Die[numDie]; //easy to add new dice of new sides and roll them
		logOut.print("Sides: ");
		for(int i = 0; i < numDie; i++) {
			System.out.printf("How many sides for die #%d: ", i+1);
			numSides = keyboard.nextInt(); keyboard.nextLine();
			logOut.print(numSides + " ");
			dice[i] = new Die(numSides);
			totalSides += numSides;
		}
		
		int[] tally = new int[((totalSides)-(numDie - 1))];
		System.out.println("Working");
		for(int i = 0; i < tally.length; i++) //set your accumulator to 0
			tally[i] = 0;
		for(int i = 0; i < numRolls; i++) {//store the rolls IN that roll.....so the sum of (rand(0-5)+1 + rand(0-5)+1) - numDie
			for(int j = 0; j < numDie; j++)
				roll += dice[j].roll();
			tally[roll-numDie]++;
			if(numRolls%(numRolls/10)==0)
				System.out.print(". ");
			roll = 0;
		}
		logOut.println("");
		for(int i = 0; i < tally.length; i++) {
			System.out.printf("Roll of %d: %d rolls\n", numDie + i , tally[i]);
			logOut.printf("%d,", tally[i]);
			xBar += (tally[i]*(numDie+i));
		}
		logOut.println("");
		System.out.printf("Total rolls: %d\n", numRolls);
		for(int i = 0; i < tally.length; i++)
			System.out.printf("Percentage of total for roll of %d: %.4f%s\n", numDie + i , ((double)tally[i]/(double)numRolls)*100, "%");
		System.out.printf("Average roll: %.4f\n", xBar/numRolls);
		
	}
	
	/** dieClassical -
	 * 
	 * @param numSides
	 * @param numDie
	 */
	public static void dieClassical(int numSides, int numDie, PrintWriter logOut) {
		int[] results = new int[] {1}, die = new int[numDie];
		double xBar = 0;
		int totalOutcomes = 1, resultsLength = 1;
		//get sides of die
		for(int i = 0; i < numDie; i++) {
			die[i] = numSides;
			totalOutcomes *= die[i]; 
		}
		for(int i = 0; i < die.length; i++) {
			for(int j = 0; j <= i; j++) 
				resultsLength += die[j];
			resultsLength -= i+1;
			results = classicRoller(die[i], results, resultsLength);
			resultsLength = 1;
		}
		//get prob from all the results
		logOut.println("");
		for(int i = 0; i < results.length; i++) {
			System.out.printf("Classical percentage of total for roll of %d: %.4f%s\n", numDie + i , ((double)results[i]/(double)totalOutcomes*100), "%");
			logOut.printf("%d,", results[i]);
			xBar += (results[i]*(numDie+i));
			//do 68%/95%/99% values here too
			//grab middle, keep adding sides until it is greater then 65%, then 95%. output ranges that we hit at max, and figure out the mirror
		}
		logOut.println("");
		System.out.printf("Average roll: %.4f\n", xBar/totalOutcomes);
		
	}

	/** dieClassicalCombo -
	 * 
	 * @param numDie
	 */
	public static void dieClassicalCombo(int numDie, PrintWriter logOut) {
		int[] results = new int[] {1}, die = new int[numDie];
		double xBar = 0;
		int totalOutcomes = 1, resultsLength = 1;
		//get sides of die
		logOut.print("Sides: ");
		for(int i = 0; i < numDie; i++) {
			System.out.printf("Please enter sides for die #%d: ", i+1);
			die[i] = keyboard.nextInt(); keyboard.nextLine();
			logOut.print(die[i] + " ");
			totalOutcomes *= die[i]; 
		}
		for(int i = 0; i < die.length; i++) {
			for(int j = 0; j <= i; j++) 
				resultsLength += die[j];
			resultsLength -= i+1;
			results = classicRoller(die[i], results, resultsLength);
			resultsLength = 1;
		}
		//get prob from all the results
		logOut.println("");
		for(int i = 0; i < results.length; i++) {
			System.out.printf("Classical percentage of total for roll of %d: %.4f%s\n", numDie + i , ((double)results[i]/(double)totalOutcomes*100), "%");
			logOut.printf("%d,", results[i]);
			xBar += (results[i]*(numDie+i));
		}
		logOut.println("");
		System.out.printf("Average roll: %.4f\n", xBar/totalOutcomes);
	}
	
	/** classicalRoller -
	 * 
	 * @param sides
	 * @param array
	 * @param resultLength
	 */
	private static int[] classicRoller(int sides, int[] array, int resultLength) {
		int[] newArray = new int[resultLength]; 
		for(int i = 0; i < sides; i++) 
			for(int j = 0; j < array.length; j++) 
				newArray[i+j] += array[j];
		return newArray;
	}
}
