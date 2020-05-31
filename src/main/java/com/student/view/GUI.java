package view;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import model.Game;
import model.Player;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 * @author Garret Tullio
 * @version 1
 */
public class GUI extends JPanel{


    /*Constraints for layout.*/
    private GridBagConstraints c = new GridBagConstraints();

    private Container obj = this;

    /*Panel to be passed by thread.*/
    private Object panel = this;

    /*Title for the North section of the GUI*/
    private JLabel title = new JLabel("\tTic Tac Toe");

    /*Containers for the select player options.*/
    private JPanel selectPlayer2Container = new JPanel();
    private JPanel selectPlayer1Container = new JPanel();

    /*Labels for selecting a player.*/
    private JLabel selectPlayer1 = new JLabel("Select Player 1");
    private JLabel selectPlayer2 = new JLabel("Select Player 2");

    /*Buttons for selecting player 1.*/
    private JRadioButton hmnButton1 = new JRadioButton("Player");
    private JRadioButton botButton1 = new JRadioButton("Bot    ");
    private JRadioButton ANNButton1 = new JRadioButton("ANN   ");
     ButtonGroup group1 = new ButtonGroup();

    /*Buttons for selecting player 2.*/
    private JRadioButton hmnButton2 = new JRadioButton("Player");
    private JRadioButton botButton2 = new JRadioButton("Bot    ");
    private JRadioButton ANNButton2 = new JRadioButton("ANN   ");
    private ButtonGroup group2 = new ButtonGroup();

    /*Button for controlling game.*/
    private JButton setGame = new JButton("Start Game");
    private JButton resetGame = new JButton("Reset Game");

    /*Label for display score of games.*/
    private JLabel scorePlayer1 = new JLabel("Score: ");
    private JLabel scorePlayer2 = new JLabel("Score: ");

    private JPanel topPanel = new JPanel();

    private JTextArea description = new JTextArea("Game of Tic Tac Toe is a functional game that uses " +
            "an artificial neural network as an opponent. The point of this is to explore deep learning \n" +
            "in a simple application like tic tac toe. The player can either select player 1 or player 2 \n" +
            "to be played by user/bot/neural network. The score keeps track of the loses/wins/draws. \n");


    private JLabel labelForCurrentTurn = new JLabel("");

    /*Slider for amount of matches and a check box.*/
    private JSlider matches = new JSlider(0, 50, 1);
    private JCheckBox autoPlay = new JCheckBox();
    private JLabel matchLabel = new JLabel("Select Amount of Matches");
    private JPanel autoPlayContainer = new JPanel();

    /*Slider for speed of the games. Interval between 10ms-3 seconds.*/
    private JSlider speed = new JSlider(0,3000,250);
    private JLabel speedLabel = new JLabel("Adjust Speed");
    private JPanel speedContainer = new JPanel();

    /*Value to keep track of the speed in any state.*/
    private int speedVal = 50;

    /*Value to keep track of the current amount of games.*/
    private int matchesToBePlayed = matches.getValue();

    public GUI() {
        setLayout(new GridBagLayout());


        title.setFont(new Font("Calibri", Font.BOLD, 40));

        topPanel.setLayout(new GridLayout(2, 1));
        description.setEnabled(false);
        title.setHorizontalAlignment(JLabel.CENTER);

        //Adjust constraints for the title.
        c.gridx = 0;
        c.gridy = 0;
        topPanel.add(description);
        topPanel.add(title);
        c.gridwidth = 3;
        add(topPanel, c);
        c.gridwidth = 1;

        //Set layout for select players containers.
        c.gridy = 1;
        selectPlayer1Container.setLayout(new GridLayout(4,1));
        selectPlayer1Container.add(selectPlayer1);
        selectPlayer2Container.setLayout(new GridLayout(4, 1));
        selectPlayer2Container.add(selectPlayer2);


        //Create the buttons and place them in ButtonGroup and container..
        selectPlayer1Container.setFocusable(false);
        selectPlayer2Container.setFocusable(false);
        group1.add(hmnButton1);
        group1.add(botButton1);
        group1.add(ANNButton1);
        hmnButton1.setSelected(true);
        selectPlayer1Container.add(hmnButton1);
        selectPlayer1Container.add(botButton1);
        selectPlayer1Container.add(ANNButton1);
        c.gridx = 0;
        add(selectPlayer1Container, c);
        //Repeat the same thing for the second player.
        group2 = new ButtonGroup();
        group2.add(hmnButton2);
        group2.add(botButton2);
        group2.add(ANNButton2);
        hmnButton2.setSelected(true);
        selectPlayer2Container.add(hmnButton2);
        selectPlayer2Container.add(botButton2);
        selectPlayer2Container.add(ANNButton2);
        c.gridx = 2;
        c.gridy = 1;
        add(selectPlayer2Container, c);


        //place button for starting/reseting game.
        c.gridx = 0;
        c.gridy = 2;
        add(setGame, c);
        c.gridy = 3;
        add(resetGame, c);

        //Place the labels for the score for each player.
        c.gridy = 4;
        scorePlayer1.setBorder(BorderFactory.createBevelBorder(1));
        add(scorePlayer1, c);
        c.gridx = 2;
        scorePlayer2.setBorder(BorderFactory.createBevelBorder(1));
        add(scorePlayer2, c);

        //Create game Graphics object and place in center for Panel.
        GameCanvas game = new GameCanvas();
        game.setPreferredSize(new Dimension(600,400));
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 7;
        add(game, c);
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 5;

        //Add the auto match slider. Default will be unchecked.
        autoPlayContainer.setLayout(new GridLayout(1,3));
        autoPlayContainer.setFocusable(false);
        matches.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() == matches) {
                    matchesToBePlayed = matches.getValue();
                }
            }
        });
        matches.setMajorTickSpacing(10);
        matches.setPaintTicks(true);
        matches.setPaintLabels(true);
        autoPlayContainer.add(matches);
        c.gridx = 1;
        c.gridy = 8;
        c.gridheight = 1;
        add(autoPlayContainer, c);


        //Add the speed slider. Default will be at 10ms.
        speedContainer.setLayout(new GridLayout(1, 2));
        speedContainer.setFocusable(false);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() == speed) {
                    speedVal = speed.getValue();
                }
            }
        });
        speed.setMajorTickSpacing(750);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speedContainer.add(speed);
        c.gridy = 9;
        add(speedContainer, c);



        //Add action listener for start game button.
        setGame.setFocusable(false);
        setGame.addActionListener(e -> {
          Game newGame = new Game(getPlayerLeftSelectedOption(), 
              getPlayerRightSelectedOption(), "Game Thread");
  
          newGame.start();
          
          addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
              synchronized(newGame) {
                 Rectangle gameBounds = game.getBounds();
                 int xCoordinate = e.getX();
                 int yCoordinate = e.getY();
                 int[] adjustedCoordinates = game.getCoordinates(xCoordinate,yCoordinate);  
                 if (gameBounds.contains(xCoordinate, yCoordinate) && adjustedCoordinates != null) {
                  switch (newGame.currentTurn.getTurn()) {
                      case "LEFT":
                        game.drawOShape(adjustedCoordinates[1],adjustedCoordinates[0]);
                        break;
                      case "RIGHT": 
                        game.drawXShape(adjustedCoordinates[1], adjustedCoordinates[0]);
                   }
                  
                 }
                    newGame.setFlag();
                    newGame.notify();
                }
            }
          });
          

        }); 
        
    }  

    
     private Player getPlayerLeftSelectedOption() {
            ButtonModel buttonSelected = group1.getSelection();
            //Set player 1 to selected.
            if (buttonSelected.equals(hmnButton1.getModel())) {
              return new Player();
            } 
            return null;
        }
      private  Player getPlayerRightSelectedOption() {
            ButtonModel buttonSelected = group2.getSelection();
            if (buttonSelected.equals(hmnButton2.getModel())) {
              return new Player();
            } 
            return null;
        }


    public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setSize(1000,1000);
      frame.add(new GUI());
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    

}

