/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
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
    
    YouKnowWhatCanvas guiboard;
    @FXML
    Button create;
    @FXML
    Button play;
    @FXML
    Button stop;
    @FXML
    Button exit;
    @FXML Slider speedSlider;
    
    Timeline anim;

//other decs
    private Board board;

//functions declarations
    @FXML
    private void newboard() {
        board = Controls.create(100, 100);
        guiboard.drawAll(board);
    }

    @FXML
    private void play() {
        anim.play();
    }

    @FXML
    private void stop() {
        anim.pause();
    }

    @FXML
    private void exit() {
        System.exit(1);
    }

    void start( long d, long i ) {
        anim.play();
    }

    /**
     * Automatically called on object creation. Initialize everything here.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        guiboard = new YouKnowWhatCanvas();
        ap.getChildren().add(guiboard);
        AnchorPane.setBottomAnchor(guiboard, 0.0);
        AnchorPane.setTopAnchor(guiboard, 0.0);
        AnchorPane.setLeftAnchor(guiboard, 0.0);
        AnchorPane.setRightAnchor(guiboard, 50.0);
        
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
        
        // animation
        anim = new Timeline(new KeyFrame(Duration.millis(1), e -> {
             guiboard.drawChange(board, board.step());
        }));
        anim.setCycleCount(Timeline.INDEFINITE);
        // slider for animation speed control
        speedSlider.setMin(0);
        speedSlider.setMax(5);
//        speedSlider.valueProperty().bindBidirectional(anim.rateProperty());
    }
}
