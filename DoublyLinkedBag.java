
package SpellChecker;

/*  
 * Author: Nathaniel Clay Arnold
 * Program 4 - DoublyLinkedBag
 * CSC230-02 Spring 2016
 */
public final class DoublyLinkedBag<T> implements BagInterface<T> {
 private DoublyLinkedNode firstNode; 
 private DoublyLinkedNode lastNode; 
 private int CurrentSize; 
 
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
       
       if(isEmpty()){
        firstNode = newNode; 
        lastNode = newNode; 
            }
       
       // adding from the front of the list 
       // lastNode continues to point to first 'newNode' entered 
       
       else{
       newNode.setNextNode(firstNode); 
       firstNode.setPrevNode(newNode);
       firstNode = newNode; 
       }
       CurrentSize++; 
       
       return true; 
        }

	
    @Override
    public T remove(){
        
         T result = null; 
         
         if(firstNode !=null){
         result = firstNode.getData(); 
         
         // firstNode now points to second node in list 
         firstNode = firstNode.getNextNode(); 
         
         // if list is now empty 
         if(firstNode == null)
            lastNode = null; 
         
         // dereference node to be removed 
         else {
             // node to remove references nothing 
             firstNode.getPrevNode().setNextNode(null);
             
             // new firstNode dereferences node to remove 
             firstNode.setPrevNode(null);
         }
              
         CurrentSize--; 
         }
         
         return result; 
        }
   

    @Override
    public boolean remove(T anEntry){
      
       if(!contains(anEntry)) 
         return false; 
    
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
       // dereference node to be removed 
       removeThisNode.getPrevNode().setNextNode(removeThisNode.getNextNode());
       
       removeThisNode.getNextNode().setPrevNode(removeThisNode.getPrevNode());
       
       // node to be removed now references nothing 
       removeThisNode.setPrevNode(null);
       
       removeThisNode.setNextNode(null);
       
       return true; 
   }
	
	
    @Override
    public void clear(){
         while (!isEmpty())
             remove(); 
        }
	
	
    @Override
    public int getFrequencyOf(T anEntry){
        int frequency = 0; 
        DoublyLinkedNode currentNode = firstNode; 
        
        for(int i = 0; i < CurrentSize; i++){
          if(anEntry.equals(currentNode.getData())){
              frequency++; 
          }  
          currentNode = currentNode.getNextNode(); 
        }
        return frequency; 
        }
	
	
    @Override
    public boolean contains(T anEntry){
        
        return getReferenceTo(anEntry)!= null; 
    }
    
    private DoublyLinkedNode getReferenceTo(T anEntry){
        
        DoublyLinkedNode currentNode = firstNode; 
        
        if(isEmpty())
            return currentNode; 
        
        for(int i = 0; i < CurrentSize; i++){
           if(anEntry.equals(currentNode.getData())){
               return currentNode; 
           } 
           else currentNode = currentNode.getNextNode(); 
        }
           return currentNode; 
        }
       
        
    @Override
    public T[] toArray(){
          T[] result = (T[])new Object[CurrentSize]; // Unchecked Cast? 
          DoublyLinkedNode currentNode = firstNode; 
          
          if(isEmpty()) 
              return result; 
          
          for(int i = 0; i < CurrentSize; i++){ // try catch nullPointer? 
              
             result[i] = currentNode.getData(); 
             
             currentNode = currentNode.getNextNode(); 
          }
          return result; 
        }

//	public Object[] toArray(); // Alternate

private class DoublyLinkedNode{ // Private nested inner class 
    private T data; 
    private DoublyLinkedNode prev; 
    private DoublyLinkedNode next; 
    
   private DoublyLinkedNode(){ 
     this(null,null,null); 
   }
     
    
    
    private DoublyLinkedNode(T data){
      this(null, data, null); 
    }
   
     

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
