package com.example.lab9.Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.example.lab9.Main;

/**
 * @author zhsyy
 * @version 1.0
 * @date 2023/5/16 21:25
 */

public class Controller {
    private static List<Element[]> map;

    private static Player player;

    public static void readMap(String path){
        map = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            int y_axis = 0;
            while ((str = in.readLine()) != null) {
                String[] row = str.split(" ");
                Element[] elements = new Element[row.length];
                for (int x_axis = 0; x_axis < row.length; x_axis++) {
                    /* 
                     * WRITE YOUR CODE HERE 
                    */
                   switch(row[x_axis]){
                    case "0":elements[x_axis] = new Empty(new EntityIcons("src/main/resources/com/example/lab9/img/empty.png", 100, 100));break;
                    case "1":elements[x_axis] = new Wall(new EntityIcons("src/main/resources/com/example/lab9/img/wall.png", 100, 100));break;

                    case "2":elements[x_axis] = new Player(x_axis, y_axis, new EntityIcons("src/main/resources/com/example/lab9/img/character.png", 100, 100));
                            player = (Player)elements[x_axis];
                            elements[x_axis] = new Empty(new EntityIcons("src/main/resources/com/example/lab9/img/empty.png", 100, 100));break;
                   }

                   /* 
                     * CODE WRITTEN 
                    */
                }
                map.add(elements);
                y_axis++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawMap(GraphicsContext gc) {
        /*
         * WRITE YOUR CODE HERE
         * 
         * you may use class GraphicsContext's method drawImage()
         *  
         */
        int width = 500;
        int height = 500;
        final int entityWidth = Main.entityWidth;
        final int entityHeight = Main.entityHeight;
       
        int y_axis = 0;
        for (Element[] row : map) {
            int x_axis = 0;
            for (Element element : row) {
                
                int xMapToCanvas = x_axis * entityWidth;
                int yMapToCanvas = y_axis * entityHeight;
                Image image = element.getEntityIcons().getImage();
                gc.drawImage(image, xMapToCanvas, yMapToCanvas, entityWidth, entityHeight);
                x_axis++;
            }
            y_axis++;
        }

        Image image = player.getEntityIcons().getImage();
        int xMapToCanvas = player.getPos_x() * entityWidth;
        int yMapToCanvas = player.getPos_y() * entityHeight;
        gc.drawImage(image, xMapToCanvas, yMapToCanvas, entityWidth, entityHeight);
         /**
          * CODE WRITTEN
          */
    }

    public static boolean click(int x, int y){
        /*
         * WRITE YOUR CODE HERE
         */
        
        if(map.get(y)[x].getClass() == Empty.class && 
         Math.abs(player.getPos_x() - x) +Math.abs( player.getPos_y() - y) == 1){
           EntityIcons playerEntityIcons = player.getEntityIcons();
           player = new Player(x, y, playerEntityIcons);
            

            return true;
            
        }



        /**
         * CODE WRITTEN
         */

        
        
      

        
        return false;
    }
}
