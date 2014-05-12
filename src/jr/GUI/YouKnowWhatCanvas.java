/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr.GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jr.Board;
import jr.Coords;

/**
 *
 * @author Plutonium_
 */
public class YouKnowWhatCanvas extends Canvas {
    double border;
    GraphicsContext gb = getGraphicsContext2D();
    public YouKnowWhatCanvas() {
        gb.setFill(Color.BLACK);
        gb.setStroke(Color.GREY);
        gb.setLineWidth(border);
        
        border = 1.2;
    }
    public double getBorders(){
        return border;
    }
    public void setBorders(double x){
        border = x;
    }
    public void draw(double modifier, double x, double y, double w, double h ) {
        gb.fillRect(x+ modifier, y+ modifier, w- modifier, h- modifier);
    }
    public double getW() {
        return gb.getCanvas().getWidth();
    }
    public double getH() {
        return gb.getCanvas().getHeight();
    }
    public Coords getCell( Board board, double x, double y ) {
        int col = board.getX();
        int row = board.getY();
        double width = getW();
        double height = getH();
        double avgcellx = ( width / col );
        double avgcelly = ( height / row );
        int w = (int)Math.floor(x/avgcellx);
        int h = (int)Math.floor(y/avgcelly);;
        Coords c = new Coords(w, h);
        if (board.inQueue(c)){
            gb.setFill(Color.BLACK);
            draw(border,w*avgcellx,h*avgcelly,avgcellx,avgcelly);
        }
        else {
            gb.setFill(Color.GREEN);
            draw(border,w*avgcellx,h*avgcelly,avgcellx,avgcelly);
        }
        gb.setFill(Color.BLACK);
        return c;
    }
    public void borders( Board board ) {
        int col = board.getX();
        int row = board.getY();
        double width = getW();
        double height = getH();
        double avgcellx = width / col;
        double avgcelly =  height / row;
        for ( int x = 0; x < col; x++ ) {
            gb.strokeLine(x * avgcellx, 0, x * avgcellx, height);
        }
        for ( int y = 0; y < row; y++ ) {
            gb.strokeLine(0, y * avgcelly, width, y * avgcelly );
        }
        gb.strokeLine(width-1, 0, width-1, height-1);
        gb.strokeLine(0, height-1, width-1, height-1);
    }
    public void emptyFill(Board board){
        int col = board.getX();
        int row = board.getY();
        double width = getW();
        double height = getH();
        double avgcellx = width / col;
        double avgcelly =  height / row;
        gb.clearRect(0, 0, width, height);
        gb.clearRect(0, 0, width, height);
        for ( int x = 0; x < col; x++ ) {
            for ( int y = 0; y < row; y++ ) {
                draw(border , x*avgcellx, y * avgcelly, avgcellx, avgcelly);
            }
        }
    }
    public void newBoard(Board board){
        borders(board);
        emptyFill(board);
    }
}
