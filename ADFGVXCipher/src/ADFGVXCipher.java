import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ADFGVXCipher {

	public static void main(String[] args) throws IOException {
		int menuSelect;
		Chart codeChart = null;
		Scanner keyboard = new Scanner(System.in);
		PrintWriter chartFile = new PrintWriter("ENCRYPTCHART.txt");
		String codeDigits = "", message, phrase, newChart = "", chartFileLoc;
		String[] cipherText = null;
		boolean validInput = false;
		//encryption or decryption
		do {
			System.out.println("Would you like to\n1. Encrypt\n2. Decrypt\nPlease select option: ");
			menuSelect = keyboard.nextInt(); keyboard.nextLine();
		} while(menuSelect != 1 && menuSelect != 2);
		//old chart or new chart?
		do {
			System.out.println("Are you creating a new chart?\n(Y/N): ");
			newChart = keyboard.nextLine();
			if(newChart.toLowerCase().equals("y") || newChart.toLowerCase().equals("n"))
				validInput = true;
		} while(!validInput);
		do {
			System.out.println("Please enter the enciphering digits.\ne.g. ADFGX(5) or ADFGVX(6)\n");
			codeDigits = keyboard.nextLine();
		} while(codeDigits.length() < 5 || codeDigits.length() > 6);
		if(newChart.equals("y")) {
			//make new chart
			codeChart = new Chart(codeDigits);
			codeChart.ChartPrint(chartFile);
			chartFile.close();
		}
		else {
			//get location of chart file, throw into constructor
			System.out.println("Please enter the path to chart file \n(If in root folder, just name without file extension)\n");
			chartFileLoc = keyboard.nextLine(); chartFileLoc += ".txt";
			//load chart from file, line by line
		}
		//save new chart to file to export
		
		//get message
		System.out.println("Please enter message: "); message = keyboard.nextLine();
		System.out.println("Please the encryption phrase: "); phrase = keyboard.nextLine();
		
		switch(menuSelect) {
		case 1: //encrypt
			cipherText = Encryption(message, phrase, codeChart);
			break;
		case 2: //decrypt
			break;
		}
		
		//System.out.print(codeDigits.charAt(0) == 65);
		for(int i = 0; i < cipherText.length; i++)
			System.out.printf("%s", cipherText[i]);
		
		keyboard.close();
	}
	
	public static String[] Encryption(String clearText, String secretPhrase, Chart codeChart) {
		String[] cipherText = new String[clearText.length()], sortedPhrase = new String[clearText.length()];
		//strip whitespace
		clearText.replace(" ", "");
		//get Cartesian coords
		for(int i = 0; i < clearText.length(); i++)
			cipherText[i] = codeChart.CodeReturn(clearText.toLowerCase().charAt(i));
		//perform phrase sorting
		
		
		
		return cipherText;
	}
	
	public static String Decryption(String cipherText, Chart codeChart) {
		String plainText = "";
		
		
		return plainText;
	}
	
	private static String sortingMethod(String secretPhrase) {
		String sortedPhrase = "";
		//sort - .toCharArray()
		
		return sortedPhrase;
	}

}
