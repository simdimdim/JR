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
import jr.Board;
import jr.Controls;

/**
 *
 * @author thedoctor
 */
public class FXMLController implements Initializable {
    /** guiboard data*/
    private Board board;
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
        System.out.println(guiboard);
        for (int x=0; x<board.size.x; x++){
            for (int y=0; y<board.size.y; y++){
                //set state
                //if (board.drawCheck(x, y)){but.setStyle("alive");}
                //else {but.setStyle("dead");}
                //set size
                guiboard.add(board.getGUICell(x, y),x,y);
            }
        }
    }
    
    @FXML
    private void nextstep() {
        board.step();
        /*code here for changing buttons
        Along the lines of (button from somelist ).change()
        */
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
