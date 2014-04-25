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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
    
    @FXML
    private void newboard(ActionEvent event) {
        Controls.create(5,5);
    }
    @FXML
    private void nextstep(ActionEvent event) {
        Controls.step(0,input);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
