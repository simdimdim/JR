
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
        data = Controls.create(50,50);
        double width = data.size.x;
        double height = data.size.y;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                final int xf = x;
                final int yf = y;
                C cell = new C(new Coords(x, y));
                //set state
                cell.setCState(data.drawCheck(x, y));
                
                //set size
                cell.setMinSize((1/width)*20, (1/height)*20);
                cell.setPrefSize(board.getWidth(), board.getHeight());
                //add acions
                cell.setOnMouseClicked(event -> {
                    changeQueue(xf,yf);
                    // toggle dead/alive state on click
                    cell.toggleState();
                });
                board.add(cell, x, y);
            }
        }
    }
    @FXML
    private void nextstep() {
        data.step(input);
        input.clear();
        
//        System.out.println(board.getChildren().size());   // why does this print 2times more children?
        
        board.getChildren().forEach(n->{
            if(n instanceof C) {    // the grid lines are actually a Group and a child of the GridPane
                                    // so we need to avoid working with it
                C cell = (C)n;
                Coords pos = cell.getPos();
                boolean alive = data.drawCheck(pos.x, pos.y);
                cell.setCState(alive);
            }
        });
        
    }
    private void changeQueue(int x, int y){
        if (input.contains(new Coords(x,y))){input.remove(new Coords(x,y));}
        else input.add(new Coords(x,y));
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
