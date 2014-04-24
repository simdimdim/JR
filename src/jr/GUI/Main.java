/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jr.Board;
import static jr.Controls.*;
import jr.Coords;

/**
 *
 * @author thedoctor
 */
public class Main extends Application {
    static public HashMap<Integer,Board> all = new HashMap<>();
    @Override
    public void start(Stage primaryStage) {
        Set<Coords> input = new HashSet();
        Button btn = new Button();
        btn.setText("Next Step");
        Button create = new Button();
        create.setText("Create a board");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(0,input);}
        });

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                create(5,5);}
        });
        AnchorPane root = new AnchorPane();
        root.getChildren().add(btn);
        root.getChildren().add(create);
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
