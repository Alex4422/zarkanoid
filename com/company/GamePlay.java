package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        //timer = new java.util.Timer(delay, this);
        timer.start();
    }

    /**
     * initialisation of the display of the ball, the pedal.
     * We will also control the things here
     * @param g
     */
    public void print(Graphics g){

        //setting of the background
        g.setColor(Color.GRAY);
        g.fillRect(1,1,692,592);

        //check!
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,562);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,562);

        //setting of the pedal
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 8);

        //setting of the ball
        g.setColor(Color.green);
        g.fillOval(ballposX,ballposY, 20, 20);
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

    }

    /**
     *
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
