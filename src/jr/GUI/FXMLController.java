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
import javafx.scene.layout.Region;
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
        data = Controls.create(20,20);
        String stalive = "-fx-background-color: #a0ff00;";
        String stdead = "-fx-background-color: #000000;";
        double width = data.size.x;
        double height = data.size.y;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                final int xf = x;
                final int yf = y;
                Region but = new Region();
                //set state
                if (data.drawCheck(x, y)){but.setStyle(stalive);}
                else {but.setStyle(stdead);}
                //set size
                but.setMinSize((1/width)*20, (1/height)*20);
                but.setPrefSize(board.getWidth(), board.getHeight());
                //add acions
                but.setOnMouseClicked(event -> {changeQueue(xf,yf);
                    // add change of style on click
                    //possible to add more conditions and wrap it up in a function later
                    if (but.getStyle().matches(stdead)){but.setStyle(stalive);}
                    else {but.setStyle(stdead);
                    }
                });
                board.add(but, x, y);
            }
        }
    }
    @FXML
    private void nextstep() {
        data.step(input);
        input.clear();
        /*code here for changing buttons
        Along the lines of (button from somelist ).change()
        */
        
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
