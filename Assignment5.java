/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

/**
 *
 * @author shish
 */
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Assignment5 {
 
`  static class ReviewEntry{
        private int sentenceID; //sentence id or index
        private double totalScore; //score divided by pharses
        private int phrases; //phrase private variable
    public ReviewEntry(){ //default constructor
      }
        public void setSentenceID(int sent){ //setter for ID
            this.sentenceID = sent;     
        }
        
        public void setTotalScore(double total){ //setter for Score
            this.totalScore = total;
        
        }
        public void setPhrases(int phrase){ //setter for phrase
            this.phrases = phrase;
        }
        public int getSentenceID(){ //getter for sentenceID
            return sentenceID; 
        }
        public int getPhrases(){//getter for phrases
            return phrases;
        }
        public double getTotalScore(){ //get Total Score
            return totalScore;
        }
    } //end class
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
         FileInputStream moviesj = null; //filesinput
         Scanner inFS = null;
         int count = 0;
         Scanner scnr = new Scanner(System.in);
   //   movies = new FileInputStream("movieReviews.tsv");
         System.out.println("Please input the file directory ");
         String fileInput = scnr.next();
         BufferedReader movies = null;
         try{
           movies=
          new BufferedReader(
          new FileReader(fileInput)); 
         }
         catch(FileNotFoundException e){
          System.out.println("That file directory was invalid please restart the code");
           return;
         }
        
        //imports the file from my downloads of "/Users/shish/Downsloads/movieReviews.tsv"
  
         ReviewEntry reviews[]; //array of object Reviews 
         reviews= new ReviewEntry[8544]; //array amount
         String header = movies.readLine(); //the first line is a table label
         String entry = movies.readLine();//first line of entries
         inFS = new Scanner(entry); //import that string to scanner

         int phrase=inFS.nextInt(); //intial phrase ID
         int sentence=1; //sentence tracker declaration

        int lastIndex = entry.length()-1;
        double score = Integer.parseInt(String.valueOf(entry.charAt(lastIndex)));
       //intial score is at the end of the line so I pulled the char at the end and parse it

        int prev=1;
        int skip = 1; //this value is skipping to reach sentence ID
        while(count < 8544){        //adding every sentence ID 
            while(sentence == prev){ //previous sentence is equal to current
 
               try {
                 score +=
                 Integer.parseInt(String.valueOf(entry.charAt(lastIndex)));
                 //adding to the total score
                 phrase++; //adding how many phrases there are in a line
                 entry=movies.readLine(); //moves onto the nextline in tsv
                 lastIndex = entry.length()-1; //reinitalize the lastIndex 
                 inFS = new Scanner(entry); //reinput the next line into the Scanner
                 prev = sentence; //makes the previous to current sentenceID
                 skip = inFS.nextInt(); //skip value reinitalize
    
                 sentence = inFS.nextInt(); //sentence value reinitalize

            }
            catch(NullPointerException e){
//        If the new line is null then we know its complete
//then you can finalize the operation and prompt the user
                 System.out.println("Completed Movie List Compilation");
                 lastIndex = 0;
                 score = score/phrase;
                 reviews[8543]=new ReviewEntry();
                 reviews[8543].setSentenceID(prev);
                 reviews[8543].setPhrases(phrase);
                 reviews[8543].setTotalScore(score);
      System.out.println("Which movie review would you like");
             int choice = scnr.nextInt()-1;
              System.out.print(
             "Movie Review Sentence: #"+
            reviews[choice].getSentenceID()
              );
               System.out.print(
           " containing "+
            reviews[choice].getPhrases()
                       +" phrases. "
               );
            double choiceScore = reviews[choice].getTotalScore();  
               System.out.print(
              "The rating is around "+
           choiceScore +". "
               );
               //DISPLAY INFORMATION
               //CHOICESCORE ANALYZER
               if (choiceScore < 2){
                   
                  System.out.println("The review is negative");
               }
               else if (choiceScore>=2 && choiceScore < 3){
                   System.out.println("The review is neutral");
               }
               else if(choiceScore > 3){
                    System.out.println("The review is positive");
               }
              return;
              
                
                
            } //end catch choice loop
         }//end sentence checker loop

    reviews[count]=new ReviewEntry(); //make sure the object declared isn't null
    reviews[count].setSentenceID(prev); 
    reviews[count].setPhrases(phrase);    
    score = score/phrase; //Operation to avg
    reviews[count].setTotalScore(score);
     phrase = 0; //sets to zero for the next sentence
     score =0;//resets the score for the next sentence
     prev = sentence; //next sentence shifter
     count++;
   } //end of array counter
        }//end main
}//end assignment5

