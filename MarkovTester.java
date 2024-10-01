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
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class tests the MyQueue and MyStack classes.
 */
public class MarkovTester {
  
  // STACK TESTERS
 
  /**
   * verify that adding things to the stack correctly increases the stack’s size
   * and that the ordering of all elements is correct
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testStackAdd() {
    MyStack<Integer> s = new MyStack<Integer>();
    s.push(1);
    s.push(2);
    s.push(3); 
    
    // size should be 3, order should be 3, 2, 1
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(3);
    expected.add(2);
    expected.add(1); 
    ArrayList<Integer> actual = s.getList();
    
    // checks the actual contents and size of our stack against our expected version
    if (!(expected.equals(actual))) return false;
    
    return true;
  }
  
  /**
   * verify that removing things from the stack (after adding them) correctly
   * decreases the stack’s size, and that the ordering of all remaining elements is correct
   * additionally, verify that a stack that has had elements added to it 
   * can become empty again later
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testStackRemove() {
    MyStack<Integer> s = new MyStack<Integer>();
    s.push(1);
    s.push(2);
    s.push(3); 
    
    // TEST 1: verify removing things correctly decrease size and ordering is correct
    if(!(s.pop().equals(3))) return false; // should remove last item added, 3
    
    // size should be 2, contents should be {2, 1}
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(2);
    expected.add(1); 
    
    ArrayList<Integer> actual = s.getList();
    
    // checks the actual contents and size of our stack against our expected version
    if (!(expected.equals(actual))) return false;
    
    // TEST 2: verify stack that has had elements added can become empty again later
    if(!(s.pop().equals(2))) return false; // removes 2
    if(!(s.pop().equals(1))) return false; // removes 1
    
    // our stack should be empty now
    if (!(s.isEmpty())) return false;
    
    return true;
  }
  
  /**
   * verify that calling shuffle() on the stack results in a stack that still
   * contains all of the same elements, but in any order that is different from the original order
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testStackShuffle() {
    
    // TEST 1: verify shuffle() on stack
    MyStack<Integer> s = new MyStack<Integer>();
    s.push(1);
    s.push(2);
    s.push(3);
    s.push(4);
    s.push(5);
    s.push(6);
    s.push(7);
    
    // contents of s are currently: {7, 6, 5, 4, 3, 2, 1}
    ArrayList<Integer> sBeforeShuffle = new ArrayList<Integer>();
    sBeforeShuffle.add(7);
    sBeforeShuffle.add(6);
    sBeforeShuffle.add(5);
    sBeforeShuffle.add(4);
    sBeforeShuffle.add(3);
    sBeforeShuffle.add(2);
    sBeforeShuffle.add(1);
    
    s.shuffle();
    ArrayList<Integer> sAfterShuffle = s.getList();
    
    // sAfterShuffle and sBeforeShuffle should NOT be equal
    if (sAfterShuffle.equals(sBeforeShuffle)) return false;
    
    // check that sAfterShuffle contains all our original elements
    if (!sAfterShuffle.contains(1) || !sAfterShuffle.contains(2) || !sAfterShuffle.contains(3) || 
        !sAfterShuffle.contains(4) || !sAfterShuffle.contains(5) || !sAfterShuffle.contains(6) || 
        !sAfterShuffle.contains(7)) return false;
    
    return true;
  }
  
  
  // QUEUE TESTERS
  
  /**
   * verify that adding things to the queue correctly increases the queue’s size
   * and that the ordering of all elements is correct
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testQueueAdd() {
    MyQueue<Integer> q = new MyQueue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    
    // size should be 3, order should be 1, 2, 3
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(1);
    expected.add(2);
    expected.add(3); 
    ArrayList<Integer> actual = q.getList();
    
    // checks the actual contents and size of our queue against our expected version
    if (!(expected.equals(actual))) return false;
    // double checks our queue's size variable
    if (q.size() != 3) return false;
    
    return true;
  }
  
  /**
   * verify that removing things from the queue (after adding them) correctly decreases 
   * the queue’s size, and that the ordering of all remaining elements is correct. 
   * this test should also verify that the custom method maintainSize(int) works as described
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testQueueRemove() {
    MyQueue<Integer> q = new MyQueue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3); 
    
    // TEST 1: verify removing things correctly decrease size and ordering is correct
    
    q.dequeue(); // should remove first item added, 1
    
    // size should be 2, contents should be {2, 3}
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(2);
    expected.add(3); 
    
    ArrayList<Integer> actual = q.getList();
    
    // checks the actual contents and size of our queue against our expected version
    if (!(expected.equals(actual))) return false;
    
    
    // TEST 2: verify that maintainSize(int) works as described
    
    // TEST 2a: using maintainSize() to decrease the queue size
    q.maintainSize(1); // should dequeue one item to get us to the requested size one
    
    // size should be 1, contents should be {3}
    ArrayList<Integer> expected2 = new ArrayList<Integer>();
    expected2.add(3);
    
    ArrayList<Integer> actual2 = q.getList();
    
    // checks the actual contents and size of our queue against our expected version
    if (!(expected2.equals(actual2))) return false;
    
    // TEST 2b: using maintainSize() should do nothing
    q.maintainSize(4); // we already are less than size 4, so do nothing
    
    // checks the actual contents and size of our queue against our expected version
    if (!(expected2.equals(actual2))) return false;
    
    return true;
  }
  
  
  // COMMON TESTS
  
  /**
   * verify that calling peek() on both a stack and a queue returns the correct element
   * AND does not make any modifications to the data structure
   * 
   * @return true if all test scenarios pass, false otherwise
   */
  public static boolean testPeek() {
    
    // STACK TEST
    MyStack<Integer> s = new MyStack<Integer>();
    s.push(1);
    s.push(2);
    s.push(3);
    
    // stack's peek() should return the most recently added item: 3
    if (!(s.peek().equals(3))) return false;
    
    // after peek(), the data structure should remain the same, aka {3, 2, 1}
    ArrayList<Integer> sExpected = new ArrayList<Integer>();
    sExpected.add(3);
    sExpected.add(2);
    sExpected.add(1); 
    ArrayList<Integer> sActual = s.getList();
    
    // checks the actual contents and size of our stack against our expected version
    if (!(sExpected.equals(sActual))) return false;
    
    // QUEUE TEST
    MyQueue<Integer> q = new MyQueue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    
    // queue's peek() should return the least recently added item: 1
    if (!(q.peek().equals(1))) return false;
    
    // after peek(), the data structure should remain the same, aka {1, 2, 3}
    ArrayList<Integer> qExpected = new ArrayList<Integer>();
    qExpected.add(1);
    qExpected.add(2);
    qExpected.add(3); 
    ArrayList<Integer> qActual = q.getList();

    // checks the actual contents and size of our queue against our expected version
    if (!(qExpected.equals(qActual))) return false;
    
    return true;
  }
  
  
  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testStackAdd(): " + testStackAdd());
    System.out.println("testStackRemove(): " + testStackRemove());
    System.out.println("testStackShuffle(): " + testStackShuffle());
    System.out.println("testQueueAdd(): " + testQueueAdd());
    System.out.println("testQueueRemove(): " + testQueueRemove());
    System.out.println("testPeek(): " + testPeek());
  }
}
