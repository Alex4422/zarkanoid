package com.company;

import java.awt.*;

/**
 * Element: class
 * Title: MapGenerator
 * Description: manages the appearance and the behaviour of the level
 */
public class MapGenerator {

    /**
     * Element: variable
     * Title: map
     * Description: the area where are the bricks
     */
    public int map[][];
    /**
     * Element: variable
     * Title: brickWidth
     * Description: defines the width of a brick
     */
    public int brickWidth;
    /**
     * Element: variable
     * Title: brickHeight
     * Description: defines the height of a brick
     */
    public int brickHeight;

    /**
     * Element: constructor
     * Title: MapGenerator
     * Description: initializes the surface of the bricks
     *
     * @param row
     * @param col
     */
    public MapGenerator(int row, int col) {

        map = new int[row][col];
        //horizontal
        for (int i = 0; i < map.length; i++) {
            //vertical
            for (int j = 0; j < map[0].length; j++) {
                //we fill in the grid
                map[i][j] = 1;
            }
        }
        //dimensions of brick
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    /**
     * Element: method
     * Title: draw
     * Description: color the surface of bricks
     * @param g
     */
    public void draw(Graphics2D g) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    //We colorize the surface
                    g.setColor(Color.BLACK);
                    //We fill out the surface. N.B.: you must multiply not add between i & brickHeight
                    g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);

                    //We will separate the area in bricks
                    g.setStroke(new BasicStroke(3));
                    //The limits between the limits are red here
                    g.setColor(Color.RED);
                    //We draw these lines
                    g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    /**
     * Element: method
     * Title: setBrickValue
     * Description: set the appearance of a brick, or to be concrete, will be destroyed by the ball
     *
     * @param value
     * @param row
     * @param col
     */
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}