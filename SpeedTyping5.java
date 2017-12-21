/*
Name: Alex Garibaldi
Title: SpeedTyping5
Description: User types as many words as they can in a minute 
             and the program saves the high scores in a file
Input: words that the user types
Output: how many they got correct/ current high score/
        how close they are away from the high score
*/
import java.util.Scanner;
import java.io.*; 
import java.util.Random;
import javax.swing.JOptionPane;

public class SpeedTyping5
{
   //tells how many is correct
   public static int howManyCorrect() throws IOException
   {
      //start time
      double startTime = System.currentTimeMillis()/1000.0; 
      double currentTime = System.currentTimeMillis()/1000.0;
         
      Scanner scan = new Scanner(System.in);
                
      int correct = 0; //number of words the user got correct
      String typedWord; //word user typed
         
      //while time is less than 60 seconds
      while(currentTime-startTime <= 60.0)
      {
         String word = randomWord();
         //displays word, prompts them to type it back
         System.out.println("Type this word back as fast as you can: \n" 
         + word); 
            
         //scans user's typed word
         typedWord = scan.nextLine();
         //converts to lowercase
         typedWord = typedWord.toLowerCase();
            
         //if the word equals the word the user typed
         if(word.equals(typedWord))
         {
            //adds count each time user's word matched original word
            correct++;
         }
            
          //ends time it took for the user to type a word
          currentTime = System.currentTimeMillis()/1000.0;
       }
         //tells user how many words they got correct
         System.out.println("You got " + correct + 
         " words correct in 60 seconds!");
         
         return correct;
   }
   //picks a random word
   public static String randomWord() throws IOException
   {
       Random r = new Random();
       int num; 
       String word = ""; 

       //picks random number between 1 and 109583 inclusive
       num = r.nextInt(109583) + 1;
         
       //opens file for reading
       File f1 = new File("wordsEn.txt");
         
        //connects scanner object and file object
        Scanner inputFile = new Scanner(f1);

        //loop ends when i == num, assigns word to the word in wordsEn.txt
        for(int i = 1; i<= num; i++)    
        {
            word = inputFile.nextLine();
        }
            
        inputFile.close();
        return word;
   }
   //prints score to file
   public static void printScoreToFile (int correct) throws IOException
   {
        int currentScore = 0;
        int howClose = 0;
         
        //file object highscore.txt opens for input  
        File f1 = new File("highscore.txt");
        //scans highscore.txt
        Scanner inputFile = new Scanner(f1);
      
         while(inputFile.hasNext())
         {      
            //sets score  
            currentScore = inputFile.nextInt();
         }
         inputFile.close();
         
         //System.out.println("Current score: " + currentScore);
     
         //if correct > score, set correct to score & print score
         if(correct>currentScore) 
         {
            System.out.println("You got the new highest score!");
            //opens highscore.txt for output
            PrintWriter outputFile = new PrintWriter(f1);
            //prints new highest num in file highscore.txt
            outputFile.println(correct);
            //sets currentScore to the num correct
            currentScore = correct;
            
            outputFile.close();
         }
              
         //else tell them how close they were
         else
         {
            //outputFile.println(currentScore);
            howClose = currentScore - correct;
            System.out.println("You were " + howClose + 
            " words away from beating your top score");
         }
         //prints current score after whole process is done
         System.out.println("The current high score is: " + currentScore);
   } 
   //asks user if they want to play again
   public static boolean playAgain ()
   {
      boolean toReturn;
         
      int choice = JOptionPane.showConfirmDialog(null,
       "Would you like to play again?");
                  
      if (choice == JOptionPane.YES_OPTION)
         toReturn = true;
      else
         toReturn = false;
      //returns value of whether they would like to play again
      return toReturn;
   }
   public static void main (String [] args) throws IOException
   {
      int game = 1;
      int tries = 1;
      
      while(game <= tries)
      {
         //welcome message
         /*System.out.println("\nWelcome to the this Speed Typing game." +
         " Here you will type as many words as you can in 60 seconds");*/
         JOptionPane.showMessageDialog(null, "Welcome to this speed typing game! Here you will type as many words as you can in 60 seconds");
         
         //how many words user got correct
         int numCorrect = howManyCorrect();
         
         //prints score to file
         printScoreToFile(numCorrect);
         
         //calls to method if they want to play again
         boolean playAgain = playAgain();
         
         game++;
         
         if (playAgain() == true)
            tries++;
       }
   }
}