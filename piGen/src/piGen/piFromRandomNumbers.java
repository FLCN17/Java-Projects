/** @author cory cothrum
 *  4/1/2018 - 4/2/2018
 *  Approximates pi from a run of random number checks;
 *  pi can be approximated by the sqrt of 6 over the frequenecy of co-primes in random numbers
 *  logs results of each experiment and average over runs.
 * 
 */
package piGen;
import java.io.*;
import java.util.Random;


public class piFromRandomNumbers {

	/* Ceiling - size of number to generate (0 - n-1) (tends to high end of bound) - larger number, more time, more precision (slightly)
	 * Limit - number of "dice rolls" per run - larger number, more time, more precision (greater)
	 * Runs - number of experiments. Averages at end - larger number, exponentially more time (depending on limit and ceiling), more precision (greatest)
	 * 
	 * process - for each run: for the limit: grabs two "rolls", divides it by n>1 until 
	 * it finds a common divisor. This resets the check loop, increments the coPrime acc. if no common divisor was found,
	 * and outputs info on the experiment result.
	 * 
	 * At the end of all runs, it outputs the averages and how "off" we are to actual PI, and outputs to file.
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String mainFilename = "output.txt", output, border;			//main output - per experiement and total over runs.
		//String auxFileName = "auxoutput.txt";						//used for more granular data, possible automatic scraping and analysis for graphing
		File mainFile = new File(mainFilename); //File auxFile = new File(auxFileName);
		PrintWriter mainPrintFile = new PrintWriter(mainFile); //PrintWriter auxPrintFile = new PrintWriter(auxFile);
		
		Random rand = new Random();
		boolean prime = true; //technically proving numbers /aren't/ coPrime in tests, so set true to be safe
		int ceiling = 100000, runs = 100, num1, num2, rangeLimit;
		double limit = 100000, coPrimeScore, piTotal = 0, piApprox, piTrue, piReal;
		border = "===================";
		mainPrintFile.printf("Limit = %.0f\nNumber Ceiling = %d\n", limit, ceiling); 
		//auxPrintFile.printf("Limit = %.0f\nNumber Ceiling = %d\n", limit, ceiling);
		for(int r = runs; r-- > 0;) {
			coPrimeScore = 0;
			output = "Run " + (runs - r) + ":\n";
			System.out.print(output); //auxPrintFile.println(output);
			System.out.print("Working");
			int l = (int)(limit);
			for(; l > 0; l--) {
				if(l % (limit/10) == 0) 
					System.out.print(".");	//every dot is 10%!
				prime = true;
				num1 = rand.nextInt(ceiling); //auxPrintFile.print("Number 1: " + num1 + " || ");
				num2 = rand.nextInt(ceiling); //auxPrintFile.print("Number 2: " + num2 + "\n");
				rangeLimit = (num1 > num2 ? num1/2 : num2/2);
				for(int x = 2; x < rangeLimit; x++)
					if (num1 % x == 0 && num2 % x == 0) {
						prime = false;
						x = rangeLimit;
					}
				if(prime)
					coPrimeScore++;
				//auxPrintFile.print("Prime: " + prime + "\n");
			}
			piApprox = Math.pow((6/coPrimeScore / limit), 0.5)*limit;
			piTotal += piApprox;
			System.out.printf("\nOut of %.0f numbers chosen randomly, %.0f were coprime. This allows us to approximate pi to %.16f.\n", limit, coPrimeScore, piApprox);
			mainPrintFile.printf("Pi approx %d: %.16f\n", (runs - r), piApprox); //auxPrintFile.printf("Pi approx %d: %.16f\n", ((runs - r) + 1), piApprox);
		}
		piTrue = piTotal/(double)(runs);
		piReal = Math.PI;
		System.out.printf("%s\nAfter %d runs, and out of a total of %.0f numbers chosen, pi is approx. %.16f.\nThis is off by %.16f, as PI is actually %.16f", border, runs, limit*runs, piTrue, (piReal - piTrue), piReal);
		mainPrintFile.printf("Runs: %d  |  piAverage = %.16f\nOff by %.16f\n**********************\n", runs, piTrue, (piReal - piTrue));
		mainPrintFile.close();
		//auxPrintFile.close();
	}
}