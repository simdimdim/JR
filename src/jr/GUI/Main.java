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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jr.Board;

/**
 *
 * @author thedoctor
 */
public class Main extends Application {

    // <board>
    public static HashMap<Integer, Board> boards = new HashMap<>();
    private static int id = 0;

    /**
     * Generates unique id for new board.
     *
     * @return
     */
    public static int generateNewBoardId() {
        id++;
        return id - 1;
    }

    /**
     * Adds board with specified id.
     *
     * @param _id index.
     * @param board Board.
     * @param queue inputs queue source for the board.
     * @return index of the board.
     */
    public static int addBoard( int _id, Board board ) {
        boards.put(_id, board);
        return _id;
    }
    // </board>

    @Override
    public void start( Stage stage ) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        launch(args);
    }
}
