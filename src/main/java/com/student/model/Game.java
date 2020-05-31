package model;

import view.GUI;
import view.MouseClicked;
import javax.swing.JPanel;
import java.util.Random;

public class Game implements Runnable{

  Player playerLeft;
  Player playerRight;
  String threadName;
  boolean waitForClick = false;
  Random rand = new Random();
  public Turn currentTurn = new Turn();  
  State currentState = new State();


  public Game( Player playerLeftSelected, Player playerRightSelected, String threadName) {
    playerLeft = playerLeftSelected;
    playerRight = playerRightSelected;
    this.threadName = threadName;
  }

  @Override
  public void run() {
    
    while(!currentState.checkWonState()){
      switch (currentTurn.getTurn()) {
        case "LEFT":
          if (playerLeft instanceof Player) {
            waitForUser();
          }
          break;
        case "RIGHT":
          if (playerRight instanceof Player) {
            waitForUser();
          }

      }
      currentTurn.switchTurns();
    }

  }

  private void waitForUser() {
    waitForClick = false; 
    synchronized( this ) { 
      while(!waitForClick)
        try {
            wait();
          }catch(Exception e) {
          }
        }
    }

   
  public void setFlag() {
    waitForClick = true;
  }
  public void start() {
       Thread t = new Thread(this, threadName);
       t.start();
  }
 }
