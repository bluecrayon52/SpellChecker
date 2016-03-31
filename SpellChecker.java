
package SpellChecker;

import java.util.Scanner;
import java.io.*; 
import java.util.Arrays;
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
          
    try (Scanner readFile = new Scanner(correctWordFile)) {
        
              while(readFile.hasNext()){
                  if(!correctWords.add(readFile.nextLine().toLowerCase())){
                      System.out.println("Could not add words to Dictionary.");
                      System.exit(0);
                  }
              }
              System.out.println(Arrays.toString(correctWords.toArray()));
              readFile.close();
              
    }catch(FileNotFoundException e){  
      return false; 
      }
     
      return true; 
    }
    
    public boolean checkSpelling(String word){
        
      String words = word.toLowerCase(); 
      
      return correctWords.contains(words);    
    }
    
    public int count(){
        return correctWords.getCurrentSize();     
    }
   
}
