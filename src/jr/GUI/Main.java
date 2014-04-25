/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import java.util.HashMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jr.Board;

/**
 *
 * @author thedoctor
 */
public class Main extends Application {
    
    public static HashMap<Integer,Board> all = new HashMap<>();
    private static int id=0;
    
    /**
     * Generates unique id for new board.
     * @return 
     */
    public static int generateNewBoardId() {
        id++;
        return id-1;
    }
    /**
     * Adds board with specified id.
     * @return index of the board.
     */
    public static int addBoard(int _id, Board b) {
        all.put(_id, b);
        return _id;
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
