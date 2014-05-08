/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import jr.Board;
import jr.Controls;
import jr.Coords;

/**
 *
 * @author thedoctor
 */
public class FXMLController implements Initializable {
    Set<Coords> input = new HashSet();
    /** board data*/
    private Board data;
     
    //elements declaration
    @FXML
    GridPane board;
    @FXML 
    Button create;
    @FXML
    Button next;
    
    //functions declarations
    @FXML
    private void newboard() {
        data = Controls.create(15,15);
        adjustGrid();
    }
    @FXML
    private void nextstep() {
        Controls.step(0,input);
        input.clear();
    }
    
    /** Recreates the grid to fit new size*/
    private void adjustGrid() {
        double width = data.size.x;
        double height = data.size.y;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                final int xf = x;
                final int yf = y;
                Button but = new Button();
                but.setMinSize(1/width, 1/height);
                but.setPrefSize(board.getWidth(), board.getHeight());
                //the board was getting flipped for some reason so reversed yf and xf
                but.setOnMouseClicked(event -> {changeQueue(yf,xf);});
                board.add(but, x, y);
            }
        }
    }
    private void changeQueue(int x, int y){
        input.add(new Coords(x,y));
    }
    /**
     * Automatically called on object creation. Initialize everything here.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*input.add(new Coords(2, 1));
        input.add(new Coords(2, 2));
        input.add(new Coords(2, 3));*/
    } 
    
}
