/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //GridModel gridModel = new GridModel(20, 20, 100);
        GridModel gridModel = new GridModel("src\\gameoflife\\resources\\pulsar.txt");
        GridView gridView = new GridView(gridModel);
        gridModel.addObserver(gridView);

        
        gridView.setAliveImage(new Image(getClass().getResourceAsStream("AliveUnitImage.png")));
        gridView.setDeadImage(new Image(getClass().getResourceAsStream("DeadUnitImage.png")));
        
        StackPane root = new StackPane();
        root.getChildren().add(gridView); 
       
        Scene scene = new Scene(root, gridModel.getNumRows() * 25, gridModel.getNumCols() * 25);
        
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                gridModel.tick();
                });
            }  
        }, 1000, 400); 
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
