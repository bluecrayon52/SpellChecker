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
        SpellChecker dictionary = new SpellChecker();

        // set dictionary 
        System.out.print("Enter a dictonary file: ");
        File dictionaryFile = new File(kb.nextLine());
        dictionary.setDictionary(dictionaryFile);

        // set word list file 
        System.out.print("Enter a word list file: ");
        
        File wordListFile = new File(kb.nextLine());

        // read word list and check for misspellings 
        try {
            Scanner readFile = new Scanner(wordListFile); // TE

            Boolean misspelled = false;

            while (readFile.hasNext()) {
                if (!dictionary.checkSpelling(readFile.nextLine())) {
                    misspelled = true;
                    break;
                }
            }

            if (!misspelled) {
                System.out.println("No Misspelled words in word list file.");
            } else {
                
                setOutPutFile(readFile, dictionary); 
               
            }  
            
        } catch (FileNotFoundException e) {

        }
    }
    public static void setOutPutFile(Scanner readFile, SpellChecker dictionary){
        
        Scanner kb = new Scanner(System.in); 
        
        System.out.print("Misspellings Found, Enter an outputfile: ");
        
        try{
            PrintWriter outputfile = new PrintWriter(kb.nextLine()); // TE

            while (readFile.hasNext()) {
                if(!dictionary.checkSpelling(readFile.nextLine())){
                    outputfile.println(readFile.nextLine()); 
                }
            }
        } catch(FileNotFoundException e){
        
    }
    }
}
