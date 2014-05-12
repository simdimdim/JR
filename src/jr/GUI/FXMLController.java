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
import javafx.scene.layout.AnchorPane;
import jr.Board;
import jr.Controls;

/**
 *
 * @author thedoctor
 */
public class FXMLController implements Initializable {
    //elements declaration
    @FXML
    AnchorPane ap;
    @FXML
    YouKnowWhatCanvas guiboard;
    @FXML
    Button create;
    @FXML
    Button next;
    
    //other decs
    private Board board;

    //functions declarations
    @FXML
    private void newboard() {
        board = Controls.create(75, 45);
        guiboard.newBoard(board);  
    }

    @FXML
    private void nextstep() {
        board.step();
        guiUpdate();
    }
    private void guiUpdate() {
        board.getDifference().stream().forEach(key -> {
        });
    }

    /**
     * Automatically called on object creation. Initialize everything here.
     * @param url
     * @param rb
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        ap.widthProperty().addListener(
                (observableValue, oldValue, newValue)-> {
                    guiboard.setWidth(newValue.doubleValue());
                    guiboard.newBoard(board);
                });
        ap.heightProperty().addListener(
                (observableValue, oldValue, newValue)-> {
                    guiboard.setHeight(newValue.doubleValue());
                    guiboard.newBoard(board);
                });
        newboard();
        guiboard.setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();
            System.out.println(x+" "+y);
            board.changeQueue(guiboard.getCell(board,x,y));
        });
    }
}
