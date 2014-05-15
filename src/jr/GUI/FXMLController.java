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
import javafx.beans.value.ObservableValue;
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
    @FXML
    Button create;
    @FXML
    Button play;
    @FXML
    Button stop;
    @FXML
    Button exit;
    @FXML
    Slider speedSlider;

//other decs
    private Board board;
    Timeline anim;
    YouKnowWhatCanvas guiboard;
    double midway;
    double a;
    double b;

//functions declarations
    @FXML
    private void newboard() {
        board = Controls.create(400, 200);
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
        AnchorPane.setRightAnchor(guiboard, 0.0);

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
        anim = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            guiboard.drawChange(board);
        }));
        anim.setCycleCount(Timeline.INDEFINITE);
        // slider for animation speed control
        speedSlider.setMin(0);
        speedSlider.setMax(1);
        /*the part of the exp() scale in the middle of the slider
         * keep between 0 and 0.5 for more granularity on the slow speeds
         */
        midway = 0.18;  
        b = -( ( 2 * midway ) - 1 ) / Math.pow(midway, 2d);
        a = Math.log(Math.pow(( midway * b + 1 ), 2d));
        speedSlider.valueProperty().addListener((
                ObservableValue<? extends Number> ov, Number oldValue,
                Number newValue ) -> {
                    double val =
                           (( Math.exp(newValue.doubleValue() * a) - 1 ) / b );
                    anim.setRate(val);
                });
        speedSlider.setValue(0.12);
    }
}
