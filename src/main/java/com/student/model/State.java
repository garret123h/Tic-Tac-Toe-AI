package model;

import java.util.Arrays;

public class State {
    private int[][] state = new int[3][3];
    
    public boolean checkWonState() {
      boolean resultState = false;
      //Check for diagnal win
      if ((state[0][0] == state[1][1] && state[1][1] == state[2][2] && state[2][2] != 0) ||
            (state[0][2] == state[1][1] && state[1][1] == state[2][0] && state[2][0] != 0)) {
              resultState = true;
      }
      //Check for horizontal and vertical win
      for(int i = 0; i < state.length; i++) {
        int[] currentRow = state[i];
        int[] currentColumn = {state[0][i],state[1][i],state[2][i]};
        if ((currentRow[0] != 0 && Arrays.stream(currentRow).distinct().count()== 1)
                      || (currentColumn[0] != 0 
                        && Arrays.stream(currentColumn).distinct().count() == 1)) {
          resultState = true;
        }
      }         
      return resultState;
    }

    public boolean checkDrawState() {
      boolean resultState = false;
      for (int i = 0; i < state.length; i++) {
        int[] currentRow = state[i];
        if (Arrays.asList(currentRow).contains(0)) {
          resultState = true;
        }
      }
      return resultState;
    }

    public void insert(int row, int col, int val) {
      if (val != 1 && val != -1) {
        throw new IllegalArgumentException();
      }
      state[row][col] = val;
    } 
}
