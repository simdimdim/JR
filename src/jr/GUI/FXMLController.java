/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
        for ( int x = 0; x < board.size.x; x++ ) {
            guiboard.addColumn(x, new C(x, 0, board.getQueue()));
        }
        for ( int y = 0; y < board.size.y; y++ ) {
            guiboard.addRow(y, new C(y, 0, board.getQueue()));
        }
        /*                //set state                //if (board.onBoard(x, y)){but.setStyle("alive");}
         //else {but.setStyle("dead");}
         //set size
         if (board.onBoard(x,y)){
         board.toggle(x,y);
         }
         guiboard.add(board.getGCell(x, y),x,y);
         }
         }
         System.out.println(guiboard);*/
    }

    @FXML
    private void nextstep() {
        board.step();
        guiUpdate();
    }
    private void guiUpdate(){
        board.getDifference().stream().forEach(key->{
        // This needs fixing getting IndexOutofArray exception without it..
            if(key.x<board.getX() && key.y<board.getY()){
                ObservableList<Node> childrens = guiboard.getChildren();
                for(Node node : childrens) {
                    if(guiboard.getRowIndex(node) == key.y &&
                       guiboard.getColumnIndex(node) == key.x) {
                        if (node instanceof C){
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
