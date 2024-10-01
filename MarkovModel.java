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
//                  Codecademy - https://www.codecademy.com/resources/docs/java/map/containsKey
                    // for containsKey() method
//
///////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;

// CITE: JavaDocs - for most class and method comments
/**
 * A class that represents a Markov model for generating random text based on a sample text. 
 * The model uses a sliding window approach to analyze the occurrence of characters 
 * following a sequence of characters of length k.
 */
public class MarkovModel {

  /**
   * A map of substrings of length windowWidth to stacks 
   * containing the observed characters which follow that substring of characters.
   */
  private HashMap<String, MyStack<Character>> model;
  
  /**
   * The current windowWidth number of characters that the model will use 
   * to predict the next character.
   */
  private MyQueue<Character> currentQueue;
  
  /**
   * The number of characters to consider in a substring when generating new text.
   */
  private int windowWidth;
  
  /**
   * A boolean value indicating whether to shuffle the stacks during text generation.
   */
  private boolean shuffleStacks;
  
  /**
   * Constructs a MarkovModel with a specified order. 
   * This model will predict the next character in the generated text based on strings of length k.
   * @param k - the order of the Markov model (length of substrings to consider).
   * @param shuffle - whether this model should shuffle the stacks during the text generation phase.
   */
  public MarkovModel(int k, boolean shuffle) {
    windowWidth = k;
    shuffleStacks = shuffle;
    model = new HashMap<String, MyStack<Character>>();
    currentQueue = new MyQueue<Character>();
  }
  
  /**
   * Reads in the provided text and builds a model, 
   * which maps each k-length substring of the text to a stack 
   * containing all of the characters that follow that substring in the text. 
   * (See the writeup for more details.)
   * @param text - the text to be processed to build the model.
   */
  public void processText(String text) {
    String substring;
    char nextChar;
    for (int i = 0; i < text.length()-windowWidth; i++) {
      int end = i+windowWidth; // where our substring should end (exclusive)
      substring = text.substring(i, end);
      nextChar = text.charAt(end); // gets char after substring
      model.computeIfAbsent(substring, k -> new MyStack<>()).push(nextChar);
    }
  }
  
  /**
   * Initializes the current queue with the first k-letter substring from the text, 
   * setting the initial state for text generation.
   * @param text - the text from which to derive the initial queue state.
   */
  public void initializeQueue(String text) {
    for (int i = 0; i < windowWidth; i++) {
      currentQueue.enqueue(text.charAt(i));
    }
  }
  
  /**
   * Generates text of a specified length based on the model:
   * 1. Start with an empty string, for concatenating the results
   * 2. Add the current state of the queue to the string 
   * (you may assume initializeQueue() has already been called)
   * 3. Until the produced string is of the desired length:
   *    a. Get the current queue state, see if the HashMap has an entry for it, 
   * and add the next character from its stack to the output string
   *    b. If shuffleStacks is true, shuffle the stack 
   * corresponding to the current queue state before continuing
   *    c. Add the new char into the current queue, and maintain its length appropriately
   *    d. If the model doesn't have the current queue state, 
   * OR if the current queue state's stack has been emptied out:
   *        i. re-seed the model using the argument string text
   *        ii. add a newline to the output
   *        iii. and continue!
   * @param length - the desired length of the generated text.
   * @param text - the text to use for re-seeding the model if necessary.
   * @return the generate text
   */
  public String generateText(int length, String text) {
    // 1. start with empty String
    String t = "";
    
    // 2. add the current state of the queue to the string
    String currentState = currentQueue.toString();
    t += currentState;
    
    // 3. until the produced string is of the desired length:
    while (t.length() < length) {
      // a. set the current queue state, see if the HashMap has an entry for it, 
      // and add the next character from its stack to the output string
      if (model.containsKey(currentState) && !model.get(currentState).isEmpty()) {
        char newChar = model.get(currentState).pop();
        t += newChar;
        
        // b. if shuffleStacks is true, shuffle the stack
        // corresponding to the current queue state before continuing
        if (shuffleStacks) {
          model.get(currentState).shuffle();
        }
        
        // c. add the new char into the current queue, and maintain its length appropriately
        currentQueue.enqueue(newChar);
        currentQueue.maintainSize(currentState.length());

        currentState = currentQueue.toString(); // update currentState with the new queue state
        
      //  d. if the model doesn't have the current queue state, 
      // OR if the current queue state's stack has been emptied out:
      } else {
        // re-seed the model using the argument string text
        initializeQueue(text);
        t += "\n";
        currentState = currentQueue.toString(); // Update currentState after re-seeding
      }
    }
    return t;
  }  
}
