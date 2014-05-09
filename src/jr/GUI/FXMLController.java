/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import jr.Board;
import jr.Controls;
import jr.Queue;

/**
 *
 * @author thedoctor
 */
public class FXMLController implements Initializable {
    /** guiboard data*/
    private Board board;
    private Queue input;
    C[][] guicellarray;
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
        board = Controls.create(80,80);
        guicellarray = new C[board.size.x][board.size.y]; 
        double width = board.size.x;
        double height = board.size.y;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                Region but = new Region();
                guicellarray[x][y] = new C(x, y);
                //set state
                if (board.drawCheck(x, y)){but.setStyle("alive");}
                else {but.setStyle("dead");}
                //set size
                but.setMinSize((1/width)*20, (1/height)*20);
                but.setPrefSize(guiboard.getWidth(), guiboard.getHeight());
                guiboard.add(but, x, y);
            }
        }
    }
    
    @FXML
    private void nextstep() {
        board.step(input);
        input.empty();
        /*code here for changing buttons
        Along the lines of (button from somelist ).change()
        */
        board.getCurrent().stream().forEach(cell->{
            
                if (guicellarray[cell.x][cell.y].getState()){};
                guicellarray[cell.x][cell.y].setState(true);

        });

    }
    
    /**
     * Automatically called on object creation. Initialize everything here.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        input.add(2, 1);
        input.add(2, 2);
        input.add(2, 3);
        */
    }  
}
