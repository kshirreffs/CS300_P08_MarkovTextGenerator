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

// CITE: JavaDocs - for most class and method comments
/**
 * A generic singly-linked queue implementation
 * Contains some additional methods to facilitate the workings of the Markov Model.
 */
public class MyQueue<T> implements QueueADT<T> {
  
  /**
   * A reference to the LinkedNode currently at the back of the queue
   * which contains the most-recently added value in the queue.
   */
  private LinkedNode<T> back;
  
  /**
   * A reference to the LinkedNode currently at the front of the queue
   * which contains the least-recently added value in the queue.
   */
  private LinkedNode<T> front;
  
  /**
   * The number of values currently present in the queue
   */
  private int size;

  /**
   * Add a new element to the back of the queue, assumed to be non-null.
   * @param value the value to add
   */
  @Override
  public void enqueue(T value) {
    LinkedNode<T> toAdd = new LinkedNode<T>(value);
    
    // (1) add to an empty list
    if (isEmpty()) {
      front = toAdd;
      back = toAdd;
    }
    
    // (2) add to a NON empty list
    else {
      // set current back's next pointer to our new node
      back.setNext(toAdd);
      // make our new back toAdd
      back = toAdd;
    }
    
    // appropriately increment size
    size++;
  }

  /**
   * Removes and returns the value added to this queue least recently
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public T dequeue() {
    if (isEmpty()) return null;
    
    T valueToRemove = front.getData();
    
    // set our front to the 2nd item if our size is greater than 2
    if (size > 2) {
      front = front.getNext();

    // if we only have 2 items and one is removed, we must set front and back to the 
    // remaining item
    } else if (size == 2) {
      front = front.getNext();
      back = front;
      
    // otherwise, we only have one item in our list (to remove), so we set front and back to null
    } else {
      front = null;
      back = null;
    }
    
    // appropriately decrement size
    size--;
    
    return valueToRemove;
  }

  /**
   * Accesses the value added to this queue least recently, without modifying the queue
   * @return the least recently-added value, or null if the queue is empty
   */
  @Override
  public T peek() {
    if (isEmpty()) return null;
    // if not empty, return our front node's value
    return front.getData();
  }

  /**
   * Returns true if this queue contains no elements.
   * @return true if the queue contains no elements, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return front == null && back == null;
  }

  /**
   * Returns the number of elements in the queue.
   * @return the number of elements in the queue
   */
  @Override
  public int size() {
    return size;
  }
  
  /**
   * Enforces a maximum size for this queue
   * If the queue is already smaller than the requested size, this method does nothing.
   * @param size - the maximum number of elements this queue should contain once the method has run
   */
  public void maintainSize(int size) {
    // if our max size is greater than our current size, we must dequeue appropriately
    if (size < this.size) {
      // tells us how much to remove to get to our requested size: 
      int numItemsToRemove = this.size - size;
      for (int i = 0; i < numItemsToRemove; i++) {
        dequeue();
      }
    }
  }
  
  /**
   * Creates a copy of contents of this queue in the order they're present, in ArrayList form. 
   * Should traverse the queue without removing any elements, 
   * and add the values (not the nodes!) to an ArrayList in the order they appear in the queue.
   * @return an ArrayList representation of the current state of this queue
   */
  public ArrayList<T> getList() {
    ArrayList<T> list = new ArrayList<>();
    LinkedNode<T> current = front;
    
    while (current != null) {
      list.add(current.getData());
      current = current.getNext();
    }
    
    return list;
  }
  
  /**
   * Concatenates the string representation of all values in this queue in order, 
   * from the front of the queue to the back. 
   * Does not separate values (no whitespace, no commas).
   */
  @Override
  public String toString() {
    String q = "";
    LinkedNode<T> current = front;
    for (int i = 0; i < size(); i++) {
      q += current.getData();
      current = current.getNext();
    }
    
    return q;
  }

}
