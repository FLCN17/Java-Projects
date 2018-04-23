import java.util.Scanner;
/**
   This program tests the TriviaQuestion class.
   @author Maggie
   1/8/2018
   
   TAKEN OVER BY 
   @author cory cothrum
   4/22/2018
*/

public class TriviaQuiz
{
	//for questions in file
	//grab, throw into arrays
	
	

   /**
       A method to exercise the TriviaQuestion class.
   */
   public static void game()
   {
	   Scanner keyboard = new Scanner(System.in);
	   TriviaQuestion[] questions;
	   int numQuestions, totalQuestions = 0, answer;
	  
	   System.out.println("How many questions?");
	   numQuestions = keyboard.nextInt(); keyboard.nextLine();
	   questions = new TriviaQuestion[totalQuestions];
	   
	   // Variable for receiving user's score.
	   double score;
	  
	   // Pose each question, get an answer, report points.
	   for(int i = 0; i < numQuestions; i++) {
		   System.out.println(questions[i].poseQuestion());
		   System.out.print("Please enter your answer: ");
		   answer = keyboard.nextInt();
		   score = questions[i].getScore(answer);
		   System.out.println("You scored " + score + " points.\n");
	   }
   }
}