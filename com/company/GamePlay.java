package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
/**
 * Author: Alex
 * Element: class
 * Title: GamePlay
 * Aim of the class: class which makes the business logic of the game
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    //variables for the gameplay

    /**
     * name: play
     * type: boolean
     * description: marker to say us if the th game is started or not
     */
    private boolean play = false;
    //score of the player
    /**
     *
     */
    private int score = 0;
    //bricks to shoot
    /**
     *
     */
    private int totalBricks = 21;
    //timer for the game
    /**
     *
     */
    private Timer timer;
    //initiation of the timer with the delay
    /**
     *
     */
    private int delay = 8;
    //position of the player, here the pedal
    /**
     *
     */
    private  int playerX = 310;
    //position of ball in the 2D space
    /**
     *
     */
    private int ballposX = 120;
    /**
     *
     */
    private int ballposY = 350;
    //Where will the ball go ?
    /**
     *
     */
    private int ballXdir = -1;
    /**
     *
     */
    private int ballYdir = -2;
    /**
     * name: map
     * type: MapGenerator
     * description: declaration of the area where is the bricks
     */
    private MapGenerator map;

    //constructor of the gameplay, we init the game
    /**
     * Element: constructor
     * Title: GamePlay
     * Aim of the constructor: initialise the game with different values
     *  addKeyListener
     *  setFocusable
     *  setFocusTraversalKeysEnabled
     *  timer
     */
    public GamePlay(){
        //test !
        // col is the number of the bricks
        map = new MapGenerator(3,8);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //package used here : javax.swing.Timer
        timer = new Timer(delay,this);
        timer.start();
    }


    /**
     * Element: method
     * Title: paint
     * Description: initialisation of the display of the ball, the pedal and different of the visual game.
     * We will also control the things here
     * @param g
     */
    public void paint(Graphics g){

        //setting of the background
        g.setColor(Color.white);
        g.fillRect(1,1,692,592);

        //We display the map of bricks now
        map.draw((Graphics2D)g);

        //check!
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //setting of the pedal
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        //setting of the ball
        g.setColor(Color.green);
        g.fillOval(ballposX,ballposY, 20, 20);

        //setting of the score
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 25));

        //update of score
        g.drawString(" " + score, 590, 30);

        //if we success the game, we destroyed all the bricks
        if (totalBricks <= 0){
            //we stop the game with the boolean
            play = false;
            //we reset the directions of the balls
            ballXdir = 0;
            ballYdir = 0;
            //we display the message of success + the score
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You won! Score: " + score, 190, 300);

            //we display the message of the choice to begin again the game
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to start a new game!", 230, 350);

        }

        //management of the state game over
        //if the ball falls under the pedal, that is we loose...
        if (ballposY > 570){
            //we stop the game with the boolean
            play = false;
            //we reset the directions of the balls
            ballXdir = 0;
            ballYdir = 0;
            //we display the message of Game over + the score
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);

            //we display the message of the choice to begin again the game
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to restart!", 230, 350);
        }

        //useful to release the resources
        g.dispose();

    }

    /**
     * Element: method
     * Title: actionPerformed
     * Description: define the logic, for the movement, of the ball and pedal with limits and
     * intersections
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //check the role
        timer.start();

        //The game is started
        if(play){
            if(new Rectangle(ballposX, ballposY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8))){
                //If the ball touches the pedal, it bounces
                ballYdir = -ballYdir;
            }

            //interaction with the bricks
            for(int i = 0; i<map.map.length; i++){
                for(int j =0; j<map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        //setting of the dimensions for the brickRect which interacts with the rectangle of the ball
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        //creation of imaginary rectangles for the whole bricks and one for the ball for the interactions
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        //we check if the ball intersects with the brick "or the rectangle"
                        if (ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            //I decrease the number of the bricks
                            totalBricks--;
                            //I increase the score of the player
                            score+=5;

                            //TODO tests
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                //when we hit a brick, the brick is destroyed -and- the direction of the ball X changes
                                ballXdir = -ballXdir;
                            }else{
                                //when we hit a brick, the brick is destroyed -and- the direction of the ball Y changes
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }

            //The ball can move horizontally
            ballposX += ballXdir;
            //The ball can move vertically
            ballposY += ballYdir;

            //If the ball touches the vertical left limit we inverse the direction of the ball
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            //If the ball touches the roof, the top limit of the window, we inverse the direction of the ball
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            //If the ball touches the vertical right limit we inverse the direction of the ball
            if(ballposX > 670){
               ballXdir =-ballXdir;
            }
        }

        //we refresh the window
        repaint();
    }

    /**
     *
     * @param keyEvent
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    /**
     *
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        //If the player press the right key
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            // if the player reaches the border of the window of the game
            if(playerX >= 600){
                //then he is blocked at the position x=600
                playerX = 600;
            }else{
                moveRight();
            }
        }

        //If the player press the left key
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            // if the player reaches the border of the window of the game
            if(playerX <= 10){
                //then he is blocked at the position x=10
                playerX = 10;
            }else{
                moveLeft();
            }
        }

        //if we press the touch Enter when we will finish the game
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            //if the level is finished
            if (!play){
                //we restart the game with the boolean play
                play = true;
                //we put the ball at a particular place
                ballposX = 120;
                ballposY = 350;
                //we set the direction of the ball - CHECK!
                ballXdir = -1;
                ballYdir = -2;
                //We reset the score at 0
                score = 0;
                //We reset the number of bricks
                totalBricks = 21;
                //We create a new map
                map = new MapGenerator(3,7);

                //we repaint the map back to the initial state of the game
                repaint();
            }
        }

    }

    /**
     *
     */
    public void moveRight(){

        play = true;
        playerX += 20;
    }

    /**
     *
     */
    public void moveLeft(){

        play = true;
        playerX -= 20;
    }


    /**
     *
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
