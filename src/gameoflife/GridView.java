package gameoflife;

import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GridView extends GridPane implements Observer {
    private GridModel gridModel;
    private Button[][] gridButton;
    private Image aliveImage;
    private Image deadImage;
    
    public GridView(GridModel gridModel){
        this.gridModel = gridModel;
        
        gridButton = new Button[gridModel.getNumRows()][gridModel.getNumCols()];
        this.setPadding(new Insets(0, 10, 0, 10));
        for (int i = 0; i < gridModel.getNumRows(); i++){
            for (int j = 0; j < gridModel.getNumCols(); j++){
                gridButton[i][j] = new Button("");
                gridButton[i][j].setMinSize(25.0,25.0);
                gridButton[i][j].setMaxSize(25.0,25.0);
                gridButton[i][j].setPrefSize(25.0,25.0);
                this.add(gridButton[i][j], j, i);
            }
        }
        updateGridView();
    }
  
    public void updateGridView(){
        for (int i = 0; i < gridModel.getNumRows(); i++){
            for (int j = 0; j < gridModel.getNumCols(); j++){
                boolean isAlive = gridModel.locationAlive(i, j);
                if (isAlive){
                    ImageView aliveImageView = new ImageView(aliveImage);
                    aliveImageView.setFitWidth(15);
                    aliveImageView.setFitHeight(15);
                    gridButton[i][j].setGraphic(aliveImageView);
                }
                else{;
                    ImageView deadImageView = new ImageView(deadImage);
                    deadImageView.setFitWidth(15);
                    deadImageView.setFitHeight(15);
                    gridButton[i][j].setGraphic(deadImageView);
                }
            }
        }
    }
    
    public void update(Observable os, Object obj){
       updateGridView();
    }
    
    public void setAliveImage(Image aliveImage){
        this.aliveImage = aliveImage;
    }
    public void setDeadImage(Image deadImage){
        this.deadImage = deadImage;
    }
    
}