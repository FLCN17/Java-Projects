import java.util.Scanner;

/**
 * @author cory cothrum
 * program to handle my many, many stats assignments
 * and produce the required numbers
 *
 */
public class StatsMaster {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}
	
	public static void menu() {
		int choice;
		String[] options = {"Confidence Intervals","+/- Std Deviations","Mean and Std. Devs"};
		for(int i = 0; i < options.length;) 
			System.out.printf("%d. %s\n", i+1, options[i++]);
		
		System.out.println("What are we doing?\n");
		choice = keyboard.nextInt(); keyboard.nextLine();
		switch(--choice){
		case 0: //confidence interval
			confidenceInterval();
			break;
		case 1: //+/- std devs
			stdDeviationRanks();
			break;
		case 2: //mean and std devs
			break;
		case 3: //
			break;
		case 4: //
			break;
		}
	}

	public static void confidenceInterval() {
		int sampleN1, sampleN2, degreesFreedom, tail; //sample sizes (2nd for independant), and tail is -1, 0, 1 (left, both, right)
		double stdDev1, stdDev2, mean1, mean2, aError, tError, tScore, ciScore, ciLow, ciHigh, ciDiff;
		String scoreType, depType;
		boolean dependant;
		
		System.out.println("Is this a dependant or independant sampling? (D/I)\n");
		depType = keyboard.nextLine();
		if(depType.toUpperCase().charAt(0) == 'D')
			dependant = true;
		else
			dependant = false;
		if(dependant) {
			
		}
		else { //independant, use welches t-dist
			scoreType = "T";
			System.out.println("Please enter the first mean:\n");
			mean1 = keyboard.nextDouble(); keyboard.nextLine();
			System.out.println("Please enter the second mean:\n");
			mean2 = keyboard.nextDouble(); keyboard.nextLine();
			System.out.println("Please enter the first standard Deviation:\n");
			stdDev1 = keyboard.nextDouble(); keyboard.nextLine();
			System.out.println("Please enter the second standard Deviation:\n");
			stdDev2 = keyboard.nextDouble(); keyboard.nextLine();
			System.out.println("Please enter the first 'n':\n");
			sampleN1 = keyboard.nextInt(); keyboard.nextLine();
			System.out.println("Please enter the second 'n':\n");
			sampleN2 = keyboard.nextInt(); keyboard.nextLine();
			System.out.println("Please enter the alpha Error:\n");
			aError = keyboard.nextDouble(); keyboard.nextLine();
			degreesFreedom = (sampleN1 - 1 < sampleN2 - 1 ? sampleN1 - 1 : sampleN2 -1);
			System.out.printf("Please enter the t-Error(a/2) for %d degrees freedom:\n", degreesFreedom);
			tError = keyboard.nextDouble(); keyboard.nextLine();
			ciDiff = (tError * Math.sqrt((Math.pow(stdDev1, 2)/sampleN1)+(Math.pow(stdDev2, 2)/sampleN2)));
			ciLow = (mean1 - mean2) - ciDiff;
			ciHigh = (mean1 - mean2) + ciDiff;
			ciScore = 1 - aError;
			System.out.printf("\nThe Confidence interval at %.2f %s is (%.12f, %.12f).", ciScore, "%", ciLow, ciHigh);
		}
	}
	
	public static void stdDeviationRanks() {
		double mean, stdDev, plus1Dev, plus2Dev, minus1Dev, minus2Dev;
		
		System.out.println("Please enter the mean:\n");
		mean = keyboard.nextDouble(); keyboard.nextLine();
		System.out.println("Please enter the std. Deviation:\n");
		stdDev = keyboard.nextDouble(); keyboard.nextLine();
		plus1Dev = mean + stdDev;
		plus2Dev = plus1Dev + stdDev;
		minus1Dev = mean - stdDev;
		minus2Dev = minus1Dev - stdDev;
		
		System.out.printf("-/+ 1 Deviation: (-)%.12f, (+)%.12f\n-/+ 2 Deviation: (-)%.12f, (+)%.12f", minus1Dev, plus1Dev, minus2Dev, plus2Dev);
	}
}
