/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jr.Board;
import jr.Cell;

/**
 *
 * @author Plutonium_
 */
public class YouKnowWhatCanvas extends Canvas {

double border;
private int col = 1;
private int row = 1;
private double width;
private double height;
private double avgcellx = ( width / col );
private double avgcelly = ( height / row );
GraphicsContext gb = getGraphicsContext2D();

public YouKnowWhatCanvas() {
    gb.setFill(Color.BLACK);
    gb.setStroke(Color.GREY);
    gb.setLineWidth(border);
    border = 1;
    width = getW();
    height = getH();
}

//Basic
public double getBorders() {
    return border;
}
public void setBorders( double x ) {
    border = x;
}
private void draw( double modifier, Cell c ) {
    gb.fillRect(c.x * avgcellx + modifier, c.y * avgcelly + modifier,
                avgcellx - modifier, avgcelly - modifier);
}
private void draw( double modifier, double x, double y ) {
    gb.fillRect(x + modifier, y + modifier, avgcellx - modifier, avgcelly -
                                                                 modifier);
}
public final double getW() {
    return gb.getCanvas().getWidth();
}
public final double getH() {
    return gb.getCanvas().getHeight();
}

// utility
public Cell getCell( Board board, double x, double y ) {
    int w = ( int ) Math.floor(x / avgcellx);
    int h = ( int ) Math.floor(y / avgcelly);
    Cell c = new Cell(w, h);
    changeQueue(board, c);
    return c;
}
private void borders() {
    for ( int x = 0; x < col; x++ ) {
        gb.strokeLine(x * avgcellx, 0, x * avgcellx, height);
    }
    for ( int y = 0; y < row; y++ ) {
        gb.strokeLine(0, y * avgcelly, width, y * avgcelly);
    }
    gb.strokeLine(width - 1, 0, width - 1, height - 1);
    gb.strokeLine(0, height - 1, width - 1, height - 1);
}
private void changeQueue( Board b, Cell c ) {
    if ( b.inQueue(c) ) {
        black();
        draw(border, c.x * avgcellx, c.y * avgcelly);
    }
    else {
        if ( !b.onBoard(c) ) {
            green();
            draw(border, c.x * avgcellx, c.y * avgcelly);
        }
        else {
            black();
            draw(border, c.x * avgcellx, c.y * avgcelly);
        }
    }
    borders();
    borders();
    borders();
    borders();
}
private void changeBoard( Board b, Cell c ) {
    if ( b.onBoard(c) ) {
        green();
        draw(border, c.x * avgcellx, c.y * avgcelly);
    }
    else {
        black();
        draw(border, c.x * avgcellx, c.y * avgcelly);
    }
    borders();
    borders();
    borders();
    borders();
}
public void refresh( Board b ) {
    col = b.getX();
    row = b.getY();
    width = getW();
    height = getH();
    avgcellx = ( width / col );
    avgcelly = ( height / row );
    reDraw(b);
}

//Major
public void emptyFill() {
    gb.clearRect(0, 0, width, height);
    black();
    for ( int x = 0; x < col; x++ ) {
        for ( int y = 0; y < row; y++ ) {
            draw(border, x * avgcellx, y * avgcelly);
        }
    }
}
public void drawAll( Board b ) {
    green();
    b.getBoard().forEach(( Cell c ) -> {
        draw(border, c.x * avgcellx, c.y * avgcelly);
    });
    b.getQueue().get().forEach(( Cell c ) -> {
        draw(border, c.x * avgcellx, c.y * avgcelly);
    });
    borders();
    borders();
    borders();
    borders();
}
public void reDraw( Board b ) {
    emptyFill();
    drawAll(b);
    borders();
    borders();
    borders();
    borders();
}
public void drawChange( Board b, Set<Cell> s ) {
    s.forEach(c -> {
        changeBoard(b, c);
    });
}

//shortening
private void green() {
    gb.setFill(Color.GREEN);
}
private void black() {
    gb.setFill(Color.BLACK);
}
}
