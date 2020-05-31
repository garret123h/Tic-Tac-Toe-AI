package view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Arrays;


public class GameCanvas extends JPanel{

  private Rectangle[][] gridRectangles = new Rectangle[3][3];

         
  public void paint(Graphics g) {

        int scaleWidth = getWidth() / 3;
        int scaleHeight = getHeight() / 3;
        //X,Y,Width, height
        gridRectangles[0][0] = new Rectangle(scaleWidth*1, scaleHeight*2, scaleWidth, scaleHeight);
        gridRectangles[0][1] = new Rectangle(scaleWidth*2, scaleHeight*2, scaleWidth, scaleHeight);
        gridRectangles[0][2] = new Rectangle(scaleWidth * 3, scaleHeight * 2, scaleWidth, scaleHeight);
        gridRectangles[1][0] = new Rectangle(scaleWidth * 1, scaleHeight * 3, scaleWidth, scaleHeight);
        gridRectangles[1][1] = new Rectangle(scaleWidth*2, scaleHeight*3, scaleWidth, scaleHeight);
        gridRectangles[1][2] = new Rectangle(scaleWidth*3, scaleHeight*3, scaleWidth, scaleHeight);
        gridRectangles[2][0] = new Rectangle(scaleWidth*1, scaleHeight*4, scaleWidth, scaleHeight);
        gridRectangles[2][1] = new Rectangle(scaleWidth*2, scaleHeight*4, scaleWidth, scaleHeight);
        gridRectangles[2][2] = new Rectangle(scaleWidth*3, scaleHeight*4, scaleWidth, scaleHeight);


        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        //Draw the tic tac toe grid.
        g.setColor(Color.WHITE);
        g.drawLine(scaleWidth * 1, 0, scaleWidth * 1, scaleHeight * 3);
        g.drawLine(scaleWidth * 2, 0, scaleWidth * 2, scaleHeight * 3);
        g.drawLine(0, scaleHeight * 1, scaleWidth * 3, scaleHeight * 1);
        g.drawLine(0, scaleHeight * 2, scaleWidth * 3, scaleHeight * 2);

    }
    public void drawOShape(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);
        int scaleWidth = getWidth() / 3;
        int scaleHeight = getHeight() / 3;
        int xScaled = x * scaleWidth;
        int yScaled = y * scaleHeight;
        g.drawOval(xScaled , yScaled , scaleWidth  ,scaleHeight);
    }

    public void drawXShape(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);
        int scaleWidth = getWidth() / 3;
        int scaleHeight = getHeight() / 3;
        int xScaled = x * scaleWidth;
        int yScaled = y * scaleHeight;
        g.drawLine(xScaled,yScaled,xScaled + scaleWidth, yScaled + scaleHeight);
        g.drawLine(xScaled + scaleWidth, yScaled, xScaled, yScaled + scaleHeight);
    }
    public int[] getCoordinates(int xCoordinate, int yCoordinate) {
        for (int row = 0; row < gridRectangles.length; row ++) {
          for (int column = 0; column < gridRectangles[row].length; column++) {
            Rectangle currentRectangle = gridRectangles[row][column];
            if (currentRectangle.contains(xCoordinate,yCoordinate)) {
                return new int[]{row, column};
            }
          }
        }        
        return null;
    }

}
