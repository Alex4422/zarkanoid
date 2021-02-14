package com.company;

import javax.swing.*;

/**
 * Author: Alex
 * Element: class, it is the entry point of the game
 * Title: Main
 * Aim of the class: class which allows to run the game
 */
public class Main {

    public static void main(String[] args) {

        //title log
        System.out.println("Welcome in Zarkanoid!");

        //creation of the window for the game
        JFrame window = new JFrame("Zarkanoid: shot the bricks! ");

        //call of the business logic
        window.add(new GamePlay());

        //Causes this Window to be sized to fit
        //the preferred size and layouts of its subcomponents.
        window.pack();

        //definitions of the measures of the window
        window.setBounds(10,10,700,600);

        //interdiction to resize the window
        window.setResizable(false);

        //When I press the cross, I close the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Do we display the window?
        window.setVisible(true);
    }
}
