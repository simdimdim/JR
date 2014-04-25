/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
     
    @FXML
    GridPane board;
    @FXML 
    Button create;
    @FXML
    Button next;
    
    /** board data*/
    private Board data;
    /** default size of cell */
    private static final int CELLSIZE = 10;
    private final Map<Coords,Button> cells = new HashMap();
    
    
    /**
     * Automatically called on object creation. Initialize everything here.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        input.add(new Coords(5, 5));
        input.add(new Coords(3, 5));
        input.add(new Coords(4, 5));
    }       
    
    @FXML
    private void newboard() {
        data = Controls.create(11,11);
        adjustGrid();
        displayCells();
    }
    @FXML
    private void nextstep() {
        Controls.step(0,input);
        displayCells();
    }
    
    /** Recreates the grid to fit new size*/
    private void adjustGrid() {
        int width = data.size.x;
        int height = data.size.y;
        
        board.getRowConstraints().clear();
        board.getColumnConstraints().clear();
        for(int x = 1; x<=width; x++) {
            RowConstraints row = new RowConstraints(CELLSIZE);
            board.getRowConstraints().add(row);
        }
        for(int y = 1; y<=height; y++) {
            ColumnConstraints column = new ColumnConstraints(CELLSIZE);
            board.getColumnConstraints().add(column);
        }
        
        for(int x = 1; x<=width; x++) {
            for(int y = 1; y<=height; y++) {
                Button cell = new Button();
                       cell.setMinSize(CELLSIZE, CELLSIZE); //button appears to be fkin stupid so set all three sizes
                       cell.setMaxSize(CELLSIZE, CELLSIZE); 
                       cell.setPrefSize(CELLSIZE, CELLSIZE);
                       cell.setVisible(false);
                cells.put(new Coords(x, y), cell);      // populate cell list for easy access 
                board.add(cell, x-1, y-1);             // populate the grid (grid starts at 0! watch out)
            }
        }
    }
    
    private void displayCells() {
        cells.values().forEach(child->child.setVisible(false));
        //get all alive cells
        // for each set appropriate guicell visible
        
        //test
        cells.entrySet().forEach(child->{
            if(Math.abs(child.getKey().x-6)==Math.abs(child.getKey().y-6)) // trolling double abs turns x=y into an X shape
                child.getValue().setVisible(true);
        });
    }
    
}
