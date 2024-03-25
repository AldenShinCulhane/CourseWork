/*
 * candyCruncher.java
 * Alden Shin-Culhane
 * 10/22/2018
 * Candy Cruncher game based off of the popular mobile app "Candy Crush", able to 
 * load any board of any sized dimensions, find groups of any shape made up of 3 or more
 * letters, drop letters downwards if needed, and run until the best case scenario with the
 * highest possible score is found while outputting the board's progression and the swaps
 * being made.  
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class candyCruncher{
  static int rows;
  static int cols;
  static int maxScore = 0;
  static String dash = "- ";
  static String filename = "board.txt";
  
  public static void main(String[]args)throws Exception {

    // read from file
    String[][] board = boardLoad();
    
    // print initial board
    System.out.println("Initial Board:");
    printBoard(board);
    
    // find moves
    ArrayList<int[][]> swap = new ArrayList<int[][]>();
    ArrayList<String[][]> progress = new ArrayList<String[][]>();
    progress.add(board);
    findAllMoves(board, 0, progress, swap);
  }
  
  public static String[][] boardLoad() throws Exception {  // loads the initial board
    File myFile = new File (filename);
    Scanner input = new Scanner (myFile);
    
    if (input.hasNext()){
      rows = input.nextInt();
    }
    if (input.hasNext()){
      cols = input.nextInt();
    }
   
    // loads board into a 2d array
    String[][] initialBoard = new String[rows][cols];   
    while (input.hasNext()){
      for (int i = 0; i < rows; i++){
        String line = input.next();
        
        for (int j = 0; j < cols; j++){
          initialBoard[i][j] = line.substring(j,j+1);
        }
      }  
    }
    input.close();
    return initialBoard;
  }
  
  public static String[][] applyGravity(String[][] board, int[][] points) { // replaces letters with dashes & applies gravity
    // creates a board duplicate to be edited 
    String[][] newBoard = copy(board);
    
    // if points need to be erased, replace points with a dash
    for (int i = 0; i < points.length; i++) {
      newBoard[points[i][0]][points[i][1]] = dash;
    }
    
    // gravity
    for (int i = 0; i < board.length-1; i++) {
      for (int j = 0; j < board[i].length; j++) {
        
        // if there's an empty space below a point, drop point down and replace with an empty space
        if (newBoard[i+1][j].equals(dash)) {
          newBoard[i+1][j] = newBoard[i][j];
          newBoard[i][j] = dash;
        }
      }
    }
    return newBoard;
  }
  
  public static void findAllMoves(String[][] board, int score, ArrayList<String[][]> progress, ArrayList<int[][]>swap) {
    
    // create a list to hold all possible moves
    ArrayList<int[][]> moves = new ArrayList<int[][]>();
   
    // create a list to hold all coresponding boards 
    ArrayList<String[][]> boards = new ArrayList<String[][]>();

    // create a list to hold successful swaps
    ArrayList<int[][]> swaps = new ArrayList<int[][]>();

    // iterate through board, swapping horizontally
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length-1; j++) {
        
        // horizontal swap 
        String temp = board[i][j];
        board[i][j] = board[i][j+1];
        board[i][j+1] = temp;
        
        // test first position
        int[][] coords1 = calcScore(copy(board),i,j,board[i][j]);
        
        // only test second position if the letters are different
        int[][] coords2 = new int[0][];
        if (!board[i][j].equals(board[i][j+1])) {
          coords2 = calcScore(copy(board),i,j+1,board[i][j+1]);
        }
        
        // check is score is high enough to count
        if (coords1.length >= 3 || coords2.length >= 3) {
          
          // create a pairs array to hold valid swaps 
          ArrayList<int[]> pairs = new ArrayList<int[]>();
          
          // add to pairs array if first coordinate is a valid swap
          if (coords1.length >= 3) {
            for (int pos = 0; pos < coords1.length; ++pos) {
              pairs.add(coords1[pos]);
            }
          }
          
          // add to pairs array if second coordinate is a valid swap
          if (coords2.length >= 3) { 
            for (int pos = 0; pos < coords2.length; ++pos) {
              pairs.add(coords2[pos]);
            }
          }
          
          // store in moves/boards/swaps data structures
          int[][] coords = new int[pairs.size()][];
          moves.add(pairs.toArray(coords));
          boards.add(copy(board));
          swaps.add(new int[][] {{i,j}, {i,j+1}});
        }
        
        // undo swap to restore board
        temp = board[i][j];
        board[i][j] = board[i][j+1];
        board[i][j+1] = temp;
      }
    }
    
    // iterate through board, swapping vertically
    for (int i = 0; i < board.length-1; i++) {
      for (int j = 0; j < board[i].length; j++) {
        
        // vertical swap
        String temp = board[i][j];
        board[i][j] = board[i+1][j];
        board[i+1][j] = temp; 
        
        // test first position
        int[][] coords1 = calcScore(copy(board),i,j,board[i][j]);
        
        // only test second position if the letters are different
        int[][] coords2 = new int[0][];
        if (!board[i][j].equals(board[i+1][j])) {
          coords2 = calcScore(copy(board),i+1,j,board[i+1][j]);
        }
        
        // check if score is high enough to count
        if (coords1.length >= 3 || coords2.length >= 3) {
          
          // create a pairs array to hold valid swaps 
          ArrayList<int[]> pairs = new ArrayList<int[]>();
          
          // add to pairs array if first coordinate is a valid swap
          if (coords1.length >= 3) {
            for (int pos = 0; pos < coords1.length; ++pos) {
              pairs.add(coords1[pos]);
            }
          }
          
          // add to pairs array if second coordinate is a valid swap
          if (coords2.length >= 3) { 
            for (int pos = 0; pos < coords2.length; ++pos) {
              pairs.add(coords2[pos]);
            }
          }
          
          // store in moves/boards/swaps data structures
          int[][] coords = new int[pairs.size()][];
          moves.add(pairs.toArray(coords));
          boards.add(copy(board));
          swaps.add(new int[][] {{i,j}, {i+1,j}});
        }
        
        // undo swap to restore board
        temp = board[i][j];
        board[i][j] = board[i+1][j];
        board[i+1][j] = temp; 
      }
    }
    
    if (moves.size() > 0) {
      for (int i = 0; i < moves.size(); i++) {
        int[][] coords = moves.get(i);
        int[][] pair = swaps.get(i);
        String[][] thisBoard = boards.get(i);
        String[][] newBoard = applyGravity(thisBoard,coords);
        
        // track progress
        progress.add(copy(newBoard));
        swap.add(pair);
        
        // recurssive call
        findAllMoves(copy(newBoard), (coords.length+score), progress, swap);
        
        // unwind progress
        swap.remove(swap.size()-1);
        progress.remove(progress.size()-1);
      }
    } else {
      
      // recurssive base case - highest score found so far
      if (score > maxScore) {
        maxScore = score;
        System.out.printf("---------- High Score: %d ----------\n", maxScore);
        for (int i = 0; i < progress.size(); i++) {
          if (i > 0) {
            int[][] pairs = swap.get(i-1);
            System.out.printf("Swap (%d,%d) <-> (%d,%d):\n",
                              pairs[0][0], pairs[0][1], pairs[1][0], pairs[1][1] );
          }          
          printBoard(progress.get(i));
        }
      }
    }
  }
  
  public static int[][] calcScore(String[][]board, int i, int j, String letter) { // calculates score
    
    if (letter.equals(dash)){
      return new int [0][];
    }
    
    // mark matching letters with a dot
    if (board[i][j].equals(letter)) {
      board[i][j] = ".";
    }
    
    // check below a point
    if ((i < board.length-1) && (board[i+1][j].equals(letter))) {
      calcScore(board, i+1, j, letter);
    }
    
    // check above a point
    if ((i > 0) && (board[i-1][j].equals(letter))) {
      calcScore(board, i-1, j, letter);
    }
    
    // check right of a point
    if ((j < board[i].length-1) && (board[i][j+1].equals(letter))) {
      calcScore(board, i, j+1, letter);
    }
    
    // check left of a point
    if ((j > 0) && (board[i][j-1].equals(letter))) {
      calcScore(board, i, j-1, letter);
    }
    return coords(board, ".");
  }
  
  public static int[][] coords (String[][] board, String letter) {   // find the occurrences of letters given
    ArrayList<int[]> pairs = new ArrayList<int[]>();
    for (int i = 0; i < board.length; ++i) {
      for (int j = 0; j < board[i].length; ++j) {
        if (board[i][j].equals(letter)) {
          pairs.add(new int[] {i,j});
        }
      }
    }
    int[][] coords = new int[pairs.size()][];
    return pairs.toArray(coords);
  }
  
  public static String [][] copy( String[][] array ) { // makes a copy of a 2D String array 
    String [][] clone = new String[array.length][];
    for (int i = 0; i < array.length; i++) {
      clone[i] = new String[array[i].length];
      clone[i] = Arrays.copyOf(array[i], array[i].length);
    }
    return clone;
  }
  
  public static void printBoard(String[][] board){ // prints any board given
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        System.out.print(board[i][j]+" ");
      }
      System.out.print("\n");
    }
    System.out.println();
  } 
}
