
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
                  if(!correctWords.add(readFile.nextLine().trim().toLowerCase())){
                      
                    // If words cannot be added(add always returns true)
                      System.out.println("Could not add words to Dictionary.");
                      System.exit(0);
                  }
              }
             // show that the words have been successfully added to DL list 
              System.out.println(Arrays.toString(correctWords.toArray()));
              readFile.close();
              
    }catch(FileNotFoundException e){  
      return false; 
      }
     
      return true; 
    }
    
    public boolean checkSpelling(String word){
      
      // remove potential white space and compare in lowercase 
      return correctWords.contains(word.trim().toLowerCase());    
    }
    
    public int count(){
        return correctWords.getCurrentSize();     
    }
   
   /* public void test(String word){
      
      // tests the contains and Remove anEntry method 
        System.out.println(correctWords.contains(word));
        correctWords.remove(word); 
     // test toArray method 
        System.out.println(Arrays.toString(correctWords.toArray()));
     // test remove method 
        System.out.println(correctWords.remove()); 
        System.out.println(Arrays.toString(correctWords.toArray()));
     //test getFrequencyOf anyEntry method 
        System.out.println(correctWords.getFrequencyOf(word));
     // test clear method 
        correctWords.clear(); 
        System.out.println(Arrays.toString(correctWords.toArray()));
    } */
}
