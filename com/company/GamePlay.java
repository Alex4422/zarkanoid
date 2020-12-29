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

    //The game is started or not ?
    /**
     *
     */
    private boolean play = false;
    //score of the player
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
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //package used here : javax.swing.Timer
        timer = new Timer(delay,this);
        timer.start();
    }

    /**
     * initialisation of the display of the ball, the pedal.
     * We will also control the things here
     * @param g
     */
    public void paint(Graphics g){

        //setting of the background
        g.setColor(Color.GRAY);
        g.fillRect(1,1,692,592);

        //check!
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,562);

        //setting of the pedal
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        //setting of the ball
        g.setColor(Color.green);
        g.fillOval(ballposX,ballposY, 20, 20);

        g.dispose();
    }


    /**
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

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
