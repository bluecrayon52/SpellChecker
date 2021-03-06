package SpellChecker;

import java.util.Scanner;
import java.io.*;
/*  
 * Author: Nathaniel Clay Arnold
 * Program 4 - Program4 
 * CSC230-02 Spring 2016
 */

public class Program4 {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        
        //Instantiate a SpellChecker 
        SpellChecker dictionary = new SpellChecker();
        boolean loop;  
        
        // set dictionary 
        
        // dictionary FileNotFoundException loop 
        do{
        loop = false;    
        System.out.print("Enter a dictonary file path: ");
        File dictionaryFile = new File(kb.nextLine());
        
        // FileNotFoundException caught by setDirectory returns false 
        if(!dictionary.setDictionary(dictionaryFile)){
            
            System.out.println("File not Found. ");  
            loop = true; 
        }
            
        }while(loop == true); 

        // set word list file 
        
        // word list FileNotFoundException loop
        do{
            
        loop = false; 
        
        System.out.print("Enter a word list file path: ");
        
        File wordListFile = new File(kb.nextLine());
        
        // controls print statement for no misspellings
        Boolean misspelled = false;  
       
        // read word list and check for misspellings 
        try (Scanner readFile = new Scanner(wordListFile)){ 
            
            // check for any misspelled words before requesting output file 
            while (readFile.hasNext()){
                if (!dictionary.checkSpelling(readFile.nextLine())) {
                    misspelled = true;
                    break; // short cercuit while loop 
                }
            }
            
            // print statement if no words are mispelled 
            if (!misspelled) {
                System.out.println("No Misspelled words in word list file.");
                
                // close file 
                readFile.close();
               
            } 
            
            else /* call seperate method to handle seperate 
                    FileNotFoundException within seperate do while loop. */ 
                
                setOutPutFile(wordListFile, dictionary);  
                 
            } catch (FileNotFoundException e){
                
                System.out.println("File Not Found. ");
                loop = true; 
        } 
            

    }while(loop == true); 
        
 }
    
public static void setOutPutFile(File wordListFile, SpellChecker dictionary) 
        throws FileNotFoundException{ // throws error back to Program4 
        
        Scanner readFile = new Scanner(wordListFile); // new readfile Scanner
        Scanner kb = new Scanner(System.in); 
        boolean loop;  
        boolean innerLoop; 
        String filename; 
        String choice; // for overwriting existing files 
        
        System.out.println("Misspellings Found.");
        
    // loop for FileNotFoundException   
    do{
        
        // loop for files that already exist. 
        do{
        loop = false; 
        System.out.print("Enter an output file path for misspelled words: ");
        filename = kb.nextLine(); 
        
       // make sure file does not exist.  
       File file = new File(filename); 
       
       if(file.exists()){
           System.out.println("The file "+filename+" already exists.");
           
           // loop for invalid input 
           do{
           innerLoop = false; 
           System.out.print("Do you want to overwrite this file?(Y/N): ");
           choice = kb.nextLine(); 
           
                 if(choice.equalsIgnoreCase("y")){
                    System.out.println("Overwriting file.");
                    loop = false; 
                } 
                 
                else if(choice.equalsIgnoreCase("n")){
                    loop = true; 
                }
                
                else {
              System.out.println("Invalid input");
                    innerLoop = true; 
               }
                 
            }while(innerLoop == true); 
          }
       
        }while(loop == true); 
        
        
        // creates an output file 
        try (PrintWriter outputfile = new PrintWriter(filename)){ 
            
         // write misspelled words to output file 
            while (readFile.hasNext()) {
              
              /* use same line for read and write, 
               prevent extra token consumption */ 
              String currentLine = readFile.nextLine();
              
                if(!dictionary.checkSpelling(currentLine)){
                    outputfile.println(currentLine); 
                }
            }
                
                System.out.println("Misspelled words have been add to "+
                        filename+".");
                
                   //close file being checked 
                   readFile.close(); 
                   
                   //close file holding misspeled words 
                   outputfile.close(); 
            
            
            /*If the given string does not denote an existing, 
              writable regular file and a new regular file of that name 
              cannot be created, or if some other error occurs 
              while opening or creating the file */
            
        }catch(FileNotFoundException e){
            System.out.println("No such file can be created."); 
            loop = true; 
            }
        
     } while(loop == true); 
  }
}
