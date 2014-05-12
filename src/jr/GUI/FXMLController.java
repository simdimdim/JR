/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import jr.Board;
import jr.Controls;

/**
 *
 * @author thedoctor
 */
public class FXMLController implements Initializable {

    /**
     * guiboard data
     */
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
        board = Controls.create(75, 45);
        List<ColumnConstraints> colconstr = new ArrayList();
        List<RowConstraints> rowconstr = new ArrayList();
        
        float minsize = 4;
        float prefsize = 60;
        
        for ( int x = 0; x < board.size.x; x++ ) {
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(minsize);
            column.setPrefWidth(prefsize);
            //column.setPercentWidth(100/board.size.x);
            colconstr.add(column);
        }
        for ( int y = 0; y < board.size.y; y++ ) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(minsize);
            row.setPrefHeight(prefsize);
            //row.setPercentHeight(100/board.size.y);  //needs some tweaking to work
            rowconstr.add(row);
        }
        guiboard.getColumnConstraints().addAll(colconstr);
        guiboard.getRowConstraints().addAll(rowconstr);
    }

    @FXML
    private void nextstep() {
        board.step();
        guiUpdate();
    }
    private void guiUpdate() {
        board.getDifference().stream().forEach(key -> {
            // This needs fixing getting IndexOutofArray exception without it..
            if ( key.x < board.getX() && key.y < board.getY() ) {
                ObservableList<Node> childrens = guiboard.getChildren();
                for ( Node node: childrens ) {
                    if ( guiboard.getRowIndex(node) == key.y &&
                         guiboard.getColumnIndex(node) == key.x ) {
                        if ( node instanceof C ) {
                            // FIXME
                            // node.toggleState();
                        }
                        break;
                    }
                }
            }
        });
    }

    /**
     * Automatically called on object creation. Initialize everything here.
     */
    @Override
    public void initialize( URL url, ResourceBundle rb ) {
        guiboard.setOnMousePressed(e -> {
            double x = e.getX();
            double y = e.getY();

            double width = guiboard.getWidth();
            double height = guiboard.getHeight();

            int col = board.getX();
            int row = board.getY();

            double gapx = guiboard.getHgap();
            double gapy = guiboard.getVgap();

            double avgecellx = ( width / col );
            double avgecelly = ( height / row );

            //avgecellx -= 2 * gapx;
            //avgecelly += gapy;
// FIXME math's wrong; It's wrong on the horizontal
            int cx = ( int ) Math.round(x / avgecellx);
            int cy = ( int ) Math.round(y / avgecelly);

            guiboard.add(new C(cx, cy, board.getQueue()), cx, cy);
        });
        newboard();
        /*
         input.add(2, 1);
         input.add(2, 2);
         input.add(2, 3);
         */
    }
}
