/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
    Button play;
    @FXML
    Button stop;
    @FXML
    Button exit;

//other decs
    private Board board;
    private Timer timer = new Timer();
    //public long gen = 0;

//functions declarations
    @FXML
    private void newboard() {
        board = Controls.create(400, 200);
        guiboard.drawAll(board);
    }

    @FXML
    private void play() {
        start(0, 5);
    }

    @FXML
    private void stop() {
        //
    }

    @FXML
    private void exit() {
        System.exit(1);
    }

    void start( long d, long i ) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if ( board.running ) {
                    Platform.runLater(() -> {
                        guiboard.drawChange(board, board.step());/*
                        gen++;
                        System.out.println(gen);*/
                    });
                }
                else {
                    cancel();
                    //timer.purge();
                }
            }
        }, d, i);
    }

    /**
     * Automatically called on object creation. Initialize everything here.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        ap.widthProperty().addListener(
                ( observableValue, oldValue, newValue ) -> {
                    guiboard.setWidth(newValue.doubleValue());
                    guiboard.refresh(board);
                });
        ap.heightProperty().addListener(
                ( observableValue, oldValue, newValue ) -> {
                    guiboard.setHeight(newValue.doubleValue());
                    guiboard.refresh(board);
                });
        newboard();
        guiboard.setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();
            board.changeQueue(guiboard.getCell(board, x, y));
        });
    }
}
