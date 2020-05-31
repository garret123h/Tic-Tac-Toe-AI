package model;

import java.util.Random;

public class Turn {
    
  static final int PLAYER_LEFT_MOVE = 0;
  static final int PLAYER_RIGHT_MOVE = 1;
  private int turn;

  /**
   * Default Constructor that assigns a random turn
   * */
  public Turn() {
    Random rand = new Random();
    turn = rand.nextInt(1);
  }
  
  public void switchTurns() {
    turn = (turn == 0)? 1: 0;
  }
  
  public String getTurn() {
    return (turn == 0)? "LEFT": "RIGHT";
  }

}
