package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Game;

/**
 * @author Garret Tullio
 * @version 1
 * Mouse Event class for GUI.
 * */
public class MouseClicked extends MouseAdapter {
  
  private int xCoordinate;
  private int yCoordinate;


  @Override
  public void mousePressed(MouseEvent event) {
    xCoordinate = event.getX();
    yCoordinate = event.getY();
  }

  public int getXCoordinate() {
    return xCoordinate;
  }
  public int getYCoordinate() {
    return yCoordinate;
  }


}
