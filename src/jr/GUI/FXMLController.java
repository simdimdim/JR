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
    /** guiboard data*/
    private Board board;
    C[][] gboardarray = new C[board.size.x][board.size.y]; 
    //elements declaration
    @FXML
    GridPane guiboard;
    @FXML 
    Button create;
    @FXML
    Button next;
    
    //functions declarations
    @FXML
    private void newboard() {
        board = Controls.create(20,20);
        String stalive = "-fx-background-color: #a0ff00;";
        String stdead = "-fx-background-color: #000000;";
        board = Controls.create(100,100);
        double width = board.size.x;
        double height = board.size.y;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                final int xf = x;
                final int yf = y;
 
                Region but = new Region();

                C cell = new C(x, y);

                //set state
                if (board.drawCheck(x, y)){but.setStyle(stalive);}
                else {but.setStyle(stdead);}
                //set size
                but.setMinSize((1/width)*20, (1/height)*20);
                but.setPrefSize(guiboard.getWidth(), guiboard.getHeight());
                //add acions
                but.setOnMouseClicked(event -> {changeQueue(xf,yf);
                    // add change of style on click
                    //possible to add more conditions and wrap it up in a function later
                    if (but.getStyle().matches(stdead)){but.setStyle(stalive);}
                    else {but.setStyle(stdead);
                    }
                });
                guiboard.add(but, x, y);
            }
        }
    }
    @FXML
    private void nextstep() {
        board.step(input);
        input.clear();
        /*code here for changing buttons
        Along the lines of (button from somelist ).change()
        */
 
        board.getCurrent().stream().forEach(cell->{
            
                if (gboardarray[cell.x][cell.y].getState()){};
                gboardarray[cell.x][cell.y].setState(true);

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
