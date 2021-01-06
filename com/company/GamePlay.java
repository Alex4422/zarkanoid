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
