import java.util.Random;
import java.util.Scanner;
/** ROCK PAPER SCISSORS -
 * @author cory cothrum
 * 
 * Fast and loose implementation of a python RPS game. plays a nice Normal game with some thought, 
 * with plans for a hard that tracks prograss over time and matches
 * 
 * NEEDS CLEANING AND EXPANSION - this only EXISTS. it does not exist PROPERLY. also may not actually WORK
 *
 */

public class RockPaperScissors {
	static int winHumanTotal, winCPUTotal, matchTotal; //saved between calls, overall score for length of program
	static Random rand = new Random();
	static Scanner keyboard = new Scanner(System.in); //must I??
	
	public static void Game(int rounds, int difficulty) {
		int gameWinsHuman = 0, gameWinsCPU = 0, matchResult = 0; //wins for current game, added to totals
		for (int i = 0; i < rounds; i++) {
			//for rounds, run the difficulty (switch call?)
			switch (difficulty) {
			case 1: matchResult = EasyGame();
				break;
			case 2: matchResult = NormalGame();
				break;
			case 3: matchResult = HardGame();
				break;
			}
			System.out.println("You" + (matchResult == 0 ? " tied with " : (matchResult == 1 ? " lost to " : " beat "))  +  "the cpu.");
			switch(matchResult) {
			case 1: //win - human
				gameWinsHuman++;
				break;
			case 2: //win - cpu
				gameWinsCPU++;
				break;
			}
		}
		//determine winner here
		
		//show 'lifetime' results
		
	}
	
	private static int EasyGame() {
		int result, cpuHand, playerHand;
		//get cpu hand
		cpuHand = CPUEasy();
		//get player hand
		playerHand = PlayerHand();
		//check cpu vs player hand
		result = CheckHands(cpuHand, playerHand);
		return result;
	}
	
	private static int NormalGame() {
		int result, cpuHand, playerHand, lastState = 0, lastResult = 0, lastCPUHand = 0, lastPlayerHand = 0;
		//get cpu hand
		cpuHand = CPUNormal(lastState, lastResult, lastCPUHand, lastPlayerHand);
		if(lastState == 0)
			lastState++;
		lastCPUHand = cpuHand;
		//get player hand
		playerHand = PlayerHand();
		lastPlayerHand = playerHand;
		//check cpu vs player hand
		result = CheckHands(cpuHand, playerHand);
		lastResult = result;
		return result;
	}
	
	private static int HardGame() { //really just normal difficulty...
		int result, cpuHand, playerHand, lastState = 0, lastResult = 0, lastCPUHand = 0, lastPlayerHand = 0;
		//get cpu hand
		cpuHand = CPUHard(lastState, lastResult, lastCPUHand, lastPlayerHand);
		if(lastState == 0)
			lastState++;
		lastCPUHand = cpuHand;
		//get player hand
		playerHand = PlayerHand();
		lastPlayerHand = playerHand;
		//check cpu vs player hand
		result = CheckHands(cpuHand, playerHand);
		lastResult = result;
		return result;
	}
	
	private static int CPUEasy() {
		return rand.nextInt(3); //just return rock, paper, scissors randomly
	}
	
	private static int CPUNormal(int lastState, int lastResult, int lastCPUHand, int lastPlayerHand) {
		int hand;
		//50/50 rock first, so do that OR paper.
		if(lastState == 0) {
			if(rand.nextInt(2)==1)
				hand = 0;
			else
				hand = 1;
		}
		else {
			if(lastResult == 1) { //win
				//even chance after winning of either: same hand, opponent countering, so play counter to that
				if(rand.nextInt(2)==1)
					hand = lastCPUHand;
				else
					hand = (lastCPUHand == 0 ? 2 : (lastCPUHand == 1 ? 0 : 1));
			}
			else if (lastResult == 2) { //lose
				//play the unplayed choice out of the last two played
				hand = (lastCPUHand == 0 && lastPlayerHand == 1 ? 2 : (lastCPUHand == 0 && lastPlayerHand == 2 ? 1 : 0));
			}
			else //tie
				hand = CPUEasy();
		}
		return hand;
	}
	
	private static int CPUHard(int lastState, int lastResult, int lastCPUHand, int lastPlayerHand) {
		int hand;
		//50/50 rock first, so do that OR paper.
				if(lastState == 0) {
					if(rand.nextInt(2)==1)
						hand = 0;
					else
						hand = 1;
				}
				else {
					if(lastResult == 1) { //win
						//even chance after winning of either: same hand, opponent countering, so play counter to that
						if(rand.nextInt(2)==1)
							hand = lastCPUHand;
						else
							hand = (lastCPUHand == 0 ? 2 : (lastCPUHand == 1 ? 0 : 1));
					}
					else if (lastResult == 2) { //lose
						//play the unplayed choice out of the last two played
						hand = (lastCPUHand == 0 && lastPlayerHand == 1 ? 2 : (lastCPUHand == 0 && lastPlayerHand == 2 ? 1 : 0));
					}
					else //tie
						hand = CPUEasy();
				}
		return hand;
	}
	
	private static int PlayerHand() {
		int hand;
		do {
			System.out.println("Please enter your hand: 1. Rock, 2. Paper, or 3. Scissors:\n");
			hand = keyboard.nextInt()-1;
		}while(hand < 0 || hand > 2);
		return hand;
	}
	
	private static int CheckHands(int cpuHand, int playerHand) {
		int result;
		if(cpuHand == playerHand)
			result = 0; //tied
		else if((cpuHand == 0 && playerHand == 2) || (cpuHand == 1 && playerHand == 0) || (cpuHand == 2 && playerHand == 1))
			result = 1; //cpu wins
		else
			result = 2; //player wins
		return result;
	}
}