import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Chart {
	private int keySpace;
	private ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","j","1","2","3","4","5","6","7","8","9","0"));
	private String[] chart;
	private Random rand = new Random();
	private String codeDigits;
	
	public Chart(String codeDigits) {
		this.codeDigits = codeDigits;
		this.keySpace = this.codeDigits.length()*this.codeDigits.length();
		this.chart = ChartMaker();
	}
	
	public Chart(String codeDigits, String[] givenChart) {
		this.codeDigits = codeDigits;
		this.chart = givenChart;
		this.keySpace = (givenChart.length > 25 ? 36 : 25);
	}
	
	private String[] ChartMaker() {
		int spot;
		chart = new String[this.keySpace];
		for(;this.keySpace < alphabet.size();) 
			alphabet.remove(this.keySpace);
		
		//fill chart with letters
		for(int i = 0; i < chart.length; i++) {
			spot = rand.nextInt(alphabet.size());
			chart[i] = alphabet.get(spot);
			alphabet.remove(spot);
		}
		return chart;
	}
	
	public void ChartPrint(PrintWriter outputFile) {
		for(int i = 0; i < chart.length; i++) 
			outputFile.printf("%s%s", chart[i], (i == chart.length-1? "" : ",\n"));
	}
	
	public String CodeReturn(char letter) {
		String returnCode = "";
		for(int i = 0; i < this.keySpace; i++)
			if(letter == chart[i].charAt(0)) 
				returnCode = codeDigits.charAt(i%codeDigits.length()) + "" + codeDigits.charAt(i/codeDigits.length());
		return returnCode;
	}
}
