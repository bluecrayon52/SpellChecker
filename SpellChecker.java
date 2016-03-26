
package SpellChecker;

import java.util.Scanner;
import java.io.*; 
/*  
 * Author: Nathaniel Clay Arnold
 * Program 4 - SpellChecker
 * CSC230-02 Spring 2016
 */
public class SpellChecker {
    
    private BagInterface<String> correctWords; 
    

    public SpellChecker(){
         correctWords = new DoublyLinkedBag<>(); 
    }
    
    public boolean setDictionary(File correctWordFile){
        
      try{
      Scanner readFile = new Scanner(correctWordFile);  
      
         while(readFile.hasNext()){
            correctWords.add(readFile.nextLine());
         }
      }
      
      catch(FileNotFoundException e){
      return false; 
      }
     
      return true; 
    }
    
    public boolean checkSpelling(String word){
        
      word = word.toLowerCase(); 
      
      return correctWords.contains(word);    
    }
    
    public int count(){
        return correctWords.getCurrentSize();     
    }
   
}
