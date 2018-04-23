import java.time.Instant;
/**
 * @author cory cothrum
 * Handles question generation and field setting. 
 */
public class TriviaQuestion {
	private final int HUNDRED = 10, NINETY = 15, SEVENTYFIVE = 20, FIFTY = 25, THIRTY = 30, TEN = 35;
	private int correctAnswer, points;
	private String question, answer1, answer2, answer3, answer4;
	private boolean posed;
	private Instant start;
	
	public TriviaQuestion(String newQuestion, String newAnswer1, String newAnswer2, String newAnswer3, String newAnswer4, int newCorrectAnswer, int newPoints) {
		this.start = null;
		this.posed = false;
		this.question = newQuestion;
		this.answer1 = newAnswer1;
		this.answer2 = newAnswer2;
		this.answer3 = newAnswer3;
		this.answer4 = newAnswer4;
		this.correctAnswer = newCorrectAnswer;
		this.points = newPoints;
	}
	
	public TriviaQuestion() {
		this.question = "Who /really/ invented the first re-programmable digital computer?";
		this.answer1 = "Alan Turing";
		this.answer2 = "Raul Rojas";
		this.answer3 = "Konrad Zuse";
		this.answer4 = "Linus Torvald";
		this.correctAnswer = 3;
		this.points = 10;
		this.start = null;
		this.posed = false;
	}
	
	public double getScore(int answer) {
		double score, now = Instant.now().getEpochSecond(), timeElapsed = now - this.start.getEpochSecond();
		if(answer == this.correctAnswer && this.posed) {
			if(timeElapsed <= HUNDRED)
				score = this.points;
			else if(timeElapsed <= NINETY)
				score = this.points * 0.9;
			else if(timeElapsed <= SEVENTYFIVE)
				score = this.points * 0.75;
			else if(timeElapsed <= FIFTY)
				score = this.points * 0.5;
			else if(timeElapsed <= THIRTY)
				score = this.points * 0.3;
			else
				score = this.points * 0.1;
		}
		else
			score = 0;
		return score;
	}
	
	public String poseQuestion() {
		String output = this.question + "\n" + this.answer1 + "\n" + this.answer2 + "\n" + this.answer3 + "\n" + this.answer4;
		this.posed = true;
		this.start = Instant.now();
		return output;
	}
	
	public void setQuestion(String newQuestion) {
		if(!posed)
			this.question = newQuestion;
	}
	
	public void setAnswer(String newAnswer, int num) {
		if(!posed)
			switch(num) {
				case 1: this.answer1 = newAnswer; break;
				case 2: this.answer2 = newAnswer; break;
				case 3: this.answer3 = newAnswer; break;
				case 4: this.answer4 = newAnswer; break;
			}
	}
	
	public void setCorrectAnswer(int num) {
		if(!posed)
			this.correctAnswer = num;
	}
	
	public void setPoints(int newPoints) {
		if(!posed)
			this.points = newPoints;
	}
}
