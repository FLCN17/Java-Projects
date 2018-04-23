import java.util.Scanner;
/**
   This program tests the TriviaQuestion class.
   @author Maggie
   1/8/2018
*/

public class TriviaQuiz
{

	// Question data (to facilitate testing)
	public static String q2 = "Who was Alan Turing? ";
	public static String q2ans1 = "first-team midfielder for Manchester United";
	public static String q2ans2 = "the father of theoretical computer science";
	public static String q2ans3 = "the true name of the Red Baron";
	public static String q2ans4 = "19th century Scottish playwright";
	public static int q2correct = 2;
	public static int q2points = 10;
	public static String q3 = "Which 2017 release starred Gal Gadot? ";
	public static String q3ans1 = "The Last Jedi";
	public static String q3ans2 = "Wonder Woman";
	public static String q3ans3 = "Blade Runner 2049";
	public static String q3ans4 = "Molly's Game";
	public static int q3correct = 2;
	public static int q3points = 10;

   /**
       A method to exercise the TriviaQuestion class.
   */
   public static void main(String[] args)
   {
	  // Make three questions
	 TriviaQuestion question1, question2, question3; 
	  
	  // Create a scanner and variable for getting user input.
	  Scanner keyboard = new Scanner(System.in);
	  int answer;
	  
	  // Variable for receiving user's score.
	  double score;
	  
      // Create three questions.
      question1 = new TriviaQuestion();
	  question2 = new TriviaQuestion(q2, q2ans1, q2ans2, q2ans3, q2ans4, q2correct, q2points);
	  question3 = new TriviaQuestion(q3, q3ans1, q3ans2, q3ans3, q3ans4, q3correct, q3points);
	  
	  // Pose each question, get an answer, report points.
	  System.out.println(question1.poseQuestion());
	  System.out.print("Please enter the number of your answer: ");
	  answer = keyboard.nextInt();
	  score = question1.getScore(answer);
	  System.out.println("You scored " + score + " points.\n");
	
	  System.out.println(question2.poseQuestion());
	  System.out.print("Please enter the number of your answer: ");
	  answer = keyboard.nextInt();
	  score = question2.getScore(answer);
	  System.out.println("You scored " + score + " points.\n");
	  
	  question2.setQuestion("Who is J. M. Barrie?");
	  question2.setCorrectAnswer(4);

	  System.out.println(question3.poseQuestion());
	  System.out.print("Please enter the number of your answer: ");
	  answer = keyboard.nextInt();
	  score = question3.getScore(answer);
	  System.out.println("You scored " + score + " points.");
	  
   }
}