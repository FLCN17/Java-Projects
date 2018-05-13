import java.util.Random;

/** Die -
 * simple, functional - create a die with sides, roll it to get a value, and poll it for those parameters
 * When rolled it will output the number and return it. also returns its range when asked
 */
public class Die {
	private int sides, value;
	private int[] range;
	private Random rand;
	
	public Die(int numSides) {
		this.sides = numSides;
		range = new int[numSides];
		for(int i = 0; i < numSides; i++) {
			range[i] = i+1;
		}
		this.rand = new Random();
	}
	
	public int roll() {
		this.value = this.rand.nextInt(this.sides)+1;
		return this.value;
	}
	
	public int getSides() {
		return this.sides;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int[] getRange() {
		return this.range;
	}
}
