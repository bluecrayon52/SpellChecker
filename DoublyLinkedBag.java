
package SpellChecker;

/*  
 * Author: Nathaniel Clay Arnold
 * Program 4 - DoublyLinkedBag
 * CSC230-02 Spring 2016
 */

public final class DoublyLinkedBag<T> implements BagInterface<T> {
 private DoublyLinkedNode firstNode; //points to first node 
 private DoublyLinkedNode lastNode; // points to last node,used in remove methods 
 private int CurrentSize; 
 
 // constructor 
 public DoublyLinkedBag(){
     
     firstNode = null;
     lastNode = null; 
     CurrentSize = 0; 
 }
    @Override
    public int getCurrentSize(){
    return CurrentSize; 
 }

    @Override
    public boolean isEmpty(){
       return CurrentSize == 0; 
        }
	
    @Override
    public boolean add(T newEntry){
       DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);  
       
       // both first and last point to same node if there is only one 
       if(isEmpty()){
        firstNode = newNode;
        lastNode = newNode; 
        }
       
       // adding from the front of the list 
       else{ newNode.setNextNode(firstNode);
             firstNode.setPrevNode(newNode); 
             firstNode = newNode;
            }
                 CurrentSize++; 
                 return true;       
        } 
      
   // removes a node from the front of the DLlist 
    @Override
    public T remove(){
        
         T result = null;
         
         // check to see if list is already empty 
         if(firstNode !=null){
         result = firstNode.getData(); 
         
         /*firstNode now points to second node in list
           or is null*/ 
         firstNode = firstNode.getNextNode(); 
         
         //if null, the list is now empty 
         if(firstNode == null)
            lastNode = null;
              
       // otherwise, dereference node to be removed 
         else{
         // node to remove references nothing 
         firstNode.getPrevNode().setNextNode(null);
             
        // new firstNode dereferences node to remove 
        firstNode.setPrevNode(null);
         }
              
         CurrentSize--; 
         }
         
         return result; 
        }
   
    // removes the first instance of anEntry if found 
    @Override
    public boolean remove(T anEntry){
        
       /* checks if list contains anEntry
       and also if it's empty(see getFrequencyOf method) */
       if(!contains(anEntry)) 
         return false; 
    
     // set a reference pointer to node that contains anEntry in data
     DoublyLinkedNode removeThisNode = getReferenceTo(anEntry);  
     
     // anEntry on fistNode
    if(removeThisNode.getPrevNode()==null){  
         
         // firstNode now points to second node in list  
         firstNode = firstNode.getNextNode(); 
         
         // if list is now empty 
         if(firstNode == null)
            lastNode = null; 
         
         // dereference node to be removed 
         else {
             // node to be removed now references nothing 
             firstNode.getPrevNode().setNextNode(null);
             
             // new firstNode dereferences node to remove 
             firstNode.setPrevNode(null);
         }
              
         CurrentSize--; 
         return true; 
     }
     
     // anEntry on lastNode 
     if(removeThisNode.getNextNode()==null){
        
         // lastNode now points to second to last node in list 
         lastNode = lastNode.getPrevNode(); 
         
         // if list is now empty 
         if(lastNode == null) 
             firstNode = null; 
         
         //dereference node to be removed 
         else{
             // node to be removed now references nothing 
             lastNode.getNextNode().setPrevNode(null);
             
             //new lastNode dereferences node to remove 
             lastNode.setNextNode(null);
         } 
         
         CurrentSize--; 
         return true; 
         
     }
    // anEntry is somewhere in the middle 
     
       // dereference node to be removed 
       removeThisNode.getPrevNode().setNextNode(removeThisNode.getNextNode());
       
       removeThisNode.getNextNode().setPrevNode(removeThisNode.getPrevNode());
       
       // node to be removed now references nothing 
       removeThisNode.setPrevNode(null);
       
       removeThisNode.setNextNode(null);
       
       CurrentSize--; 
       return true; 
   }
	
	
    @Override
    public void clear(){
         while (!isEmpty())
             remove(); 
        }
	
   // return number of occurances of anEntry  	
    @Override
    public int getFrequencyOf(T anEntry){
        int frequency = 0; 
        DoublyLinkedNode currentNode = firstNode; 
        int count = 0; 
        // loop through list nodes, avoid nullPointerException 
        while(count< CurrentSize && currentNode !=null){
          if(anEntry.equals(currentNode.getData())){
              frequency++; 
          }  
          currentNode = currentNode.getNextNode(); 
          count++; 
        }
        return frequency; 
        }
	
    // check DL list to see if any node's data matches anEntry 
    @Override
    public boolean contains(T anEntry){
        
        // false if reference is null (empty list or reaches lastNode) 
        return getReferenceTo(anEntry)!= null; 
    }
    
    private DoublyLinkedNode getReferenceTo(T anEntry){
        
        DoublyLinkedNode currentNode = firstNode; 
        
        // an empty list has null firstode  
        if(isEmpty())
            return currentNode; 
        
        // loop through DL list and compare anEntry to data    
        int count = 0;
            
            while(count < CurrentSize && currentNode !=null){
               
   // code for testing 
   // System.out.print(currentNode.getData());
   // System.out.println(" "+anEntry+" "+anEntry.equals(currentNode.getData()));
               
                // return a reference to node with matching data 
                if(anEntry.equals(currentNode.getData())){
                    return currentNode; 
                } 
                // increment node and counter 
                currentNode = currentNode.getNextNode(); 
                count++;
                
                }
      // returns lastnode.getNextNode = null if loop reaches the end of list 
           return currentNode; 
    }
       
        
    @Override
    public T[] toArray(){
        
          T[] result = (T[])new Object[CurrentSize];  
          DoublyLinkedNode currentNode = firstNode; 
          
          // nullPointerException 
          if(isEmpty()) 
              return result; 
         
          // loop through DL list and populate Array 
          int count = 0; 
          while(count < CurrentSize && currentNode!=null){ 
              
             result[count] = currentNode.getData(); 
             
             // increment node and counter 
             currentNode = currentNode.getNextNode();
             count++;
          }
          return result; 
        }



private class DoublyLinkedNode{ // Private nested inner class 
    private T data; 
    private DoublyLinkedNode prev; 
    private DoublyLinkedNode next; 
   
   // no argument constructor 
   private DoublyLinkedNode(){ 
     this(null,null,null); // passed to full constructor 
   }
  // data only constructor 
    private DoublyLinkedNode(T data){
      this(null, data, null); // passed to full constructor 
    }
  // full constructor initializes all three node fields  
    private DoublyLinkedNode(DoublyLinkedNode prev, T data, DoublyLinkedNode next){
     this.data = data; 
     this.prev = prev; 
     this.next = next; 
    }
    
    private T getData(){
        return data; 
    }
    
    private DoublyLinkedNode getPrevNode(){
        return prev;
    }
    
    private DoublyLinkedNode getNextNode(){
        return next; 
    }
    
     private void setData(T newData){
        data = newData; 
    }
     
    private void setPrevNode(DoublyLinkedNode prev){
        this.prev = prev; 
    }
    
    private void setNextNode(DoublyLinkedNode next){
        this.next = next; 
    }

}

}
