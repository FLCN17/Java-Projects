/** Player -
 * Handles players - names, scores, flags for busted or holding, and their dice
 * Dice - created by array, based on num chosen.
 * handles rolls (and validation)
 * 
 */
public class Player {
	private String name;
	private int total;
	private boolean bust, doneRolling;
	private Die[] playerDice;
	
	public Player(String name) {
		this.name = name;
		this.bust = false; this.doneRolling = false;
		this.total = 0;
	}
	
	public void setDice(int dice, int sides) {
		//create dice
		if(dice < 1)
			dice = 1;
		playerDice = new Die[dice];
		for (int i = 0; i < dice; i++)
			playerDice[i] = new Die(sides);
	}
	
	public void roll(int dice) {
		if(dice < 0) {
			if(dice == -1)
				this.doneRolling = true;
			dice = 0;
		}
		else if(dice > playerDice.length)
			dice = playerDice.length;
		for(int i = 0; i < dice; i++) {
			this.total += playerDice[i].roll();
			System.out.printf("You rolled a %d\n", playerDice[i].getValue());
		}
	}
	
	public void roll(int numDice, int gameLimit) {
		int dice = 0, rollCeiling, sides = playerDice[0].getSides();
		if(this.total == gameLimit) {
			dice = 0;
			this.doneRolling = true;
		}
		else {
			rollCeiling = gameLimit - this.total;
			dice = rollCeiling / ( rollCeiling >= sides ? sides : sides / 2);
			if(dice > numDice)
				dice = numDice;
			//System.out.println(dice);
			for(int i = 0; i < dice; i++) 
				this.total += playerDice[i].roll();
		}
		if(dice == 0)
			this.doneRolling = true;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void showTotal() {
		System.out.printf("Total for %s: %d\n", this.name, this.total);
	}
	
	public boolean getBust(int gameLimit) {
		if(this.total > gameLimit) {
			this.bust = true;
		}
		return this.bust;
	}
	
	public boolean getDoneRolling() {
		return this.doneRolling;
	}
	
	public String getName() {
		return this.name;
	}
}