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
import javafx.scene.layout.GridPane;
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
        int width = data.size.x;
        int height = data.size.y;
        for (int x=0;x<width;x++){
            for (int y=0; y<height;y++){
                final int xf = x;
                final int yf = y;
                Button but = new Button();
                but.setOnMouseClicked(event -> {input(xf,yf);});
                board.add(but,x,y);
            }
        }
    }
    private void input(int x, int y){
        input.add(new Coords(x,y));
    }
    /**
     * Automatically called on object creation. Initialize everything here.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        input.add(new Coords(5, 5));
        input.add(new Coords(3, 5));
        input.add(new Coords(4, 5));
    } 
    
}
