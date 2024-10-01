//////////////// FILE HEADER //////////////////////////
//
// Title:    P08 Text Generator
// Course:   CS 300 Spring 2024
//
// Author:   Katelyn Shirreffs
// Email:    kshirreffs@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         NONE
// Online Sources:  JavaDocs - https://cs300-www.cs.wisc.edu/sp24/p08/doc/package-summary.html
                    // for most class and method comments
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;

// CITE: JavaDocs - for most class and method comments
/**
 * A generic singly-linked stack implementation
 * which contains some additional methods to facilitate the workings of the Markov Model. 
 * As such, this stack may NOT always strictly adhere to the LIFO protocols of standard stacks!
 */
public class MyStack<T> implements StackADT<T> {
  
  /**
   * A reference to the LinkedNode currently at the top of the stack
   * which is null when the stack is empty.
   */
  private LinkedNode<T> top;

  /**
   * Add a new element to the top of this stack, assumed to be non-null.
   * @param value the value to add
   */
  @Override
  public void push(T value) {
    LinkedNode<T> toAdd = new LinkedNode<T>(value);
    
    // (1) add to an empty list
    if (isEmpty()) {
      top = toAdd;
    }
    
    // (2) add to a NON empty list
    else {
      // update the new node's next pointer to be our current top
      toAdd.setNext(top);
      // set our new node to top
      top = toAdd;
    }    
  }

  /**
   * Removes and returns the value added to this stack most recently
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public T pop() {
    if (isEmpty()) return null;
    
    T valueToRemove = top.getData();
    
    // set our top to the 2nd item, if there is one
    if (top.getNext() != null) {
      top = top.getNext();
      
    // otherwise, we only have one item in our list, so we set top to null
    } else {
      top = null;
    }
    
    return valueToRemove;
  }

  /**
   * Accesses the value added to this stack most recently, without modifying the stack
   * @return the most recently-added value, or null if the stack is empty
   */
  @Override
  public T peek() {
    if (isEmpty()) return null;
    // if not empty, return our first node's value
    return top.getData();
  }

  /**
   * Returns true if this stack contains no elements.
   * @return true if the stack contains no elements, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }
  
  /**
   * Creates a copy of the current contents of this stack in the order they are present here
   * in ArrayList form
   * This method should traverse the stack without removing any elements, 
   * and add the values (not the nodes!) to an ArrayList in the order they appear in the stack, 
   * with the top of the stack at index 0.
   * @return an ArrayList representation of the current state of this stack
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = top;
    
    while (current != null) {
        list.add(current.getData());
        current = current.getNext();
    }
    
    return list;
  }
  
  /**
   * Randomly reorder the contents of this stack:
   * 1. Create an ArrayList representation of all of the elements of this stack, in order
   * 2. Use Collections.shuffle() to create a new random ordering of the contents
   * 3. REPLACE the current contents of the stack with the contents in new order from the ArrayList
   * IMPORTANT: By the conventions established in getList(), 
   * the top of the stack is at index 0 in the ArrayList representation!
   */
  public void shuffle() {
    ArrayList<T> list = this.getList();
    Collections.shuffle(list); // shuffles our original list
    
    // make sure we have an actual list to repopulate the stack with
    if (list.size() > 0) {
      // replace our stack with the shuffled list
      // first replace our top
      top = new LinkedNode<T>(list.get(0));
      // then replace the other items in the stack (if any)
      LinkedNode<T> curr = top;
      for (int i = 1; i < list.size(); i++) {
        curr.setNext(new LinkedNode<T>(list.get(i)));
        curr = curr.getNext();
      }
    }
  }
}
