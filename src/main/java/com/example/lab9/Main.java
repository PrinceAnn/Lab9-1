package com.example.lab9;

import com.example.lab9.Entity.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * @author zhsyy
 * @version 1.0
 * @date 2023/5/16 21:14
 */

public class Main extends Application {

    public final static int entityWidth = 100;//此处添加了public修饰符
    public final static int entityHeight = 100;//此处添加了public修饰符

    @Override
    public void start(Stage stage) {
        Controller.readMap("src/main/resources/com/example/lab9/map/map.txt");
        int width = 500;
        int height = 500;
        Canvas canvas = new Canvas(width, height);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Controller.drawMap(gc);
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        scene.setOnMousePressed(mouseEvent -> {
            int[] clickPos = getClickPos(mouseEvent.getX(), mouseEvent.getY(), entityWidth, entityHeight);
            if (Controller.click(clickPos[0], clickPos[1])){
                Controller.drawMap(gc);
            }
        });
        pane.getChildren().add(canvas);
        stage.setTitle("Lab9");
        stage.setScene(scene);
        stage.show();
    }

    public static int[] getClickPos(double click_x, double click_y, int entityWidth, int entityHeight){
        int[] pos = new int[2];
        /*
         * WRITE YOUR CODE HERE
         */
        pos[0] = (int)Math.floor(click_x / entityWidth);
        pos[1] = (int)Math.floor(click_y/entityHeight);
         /**
          * CODE WRITTEN
          */
        return pos;
    }



    public static void main(String[] args) {
        launch();
    }

}
