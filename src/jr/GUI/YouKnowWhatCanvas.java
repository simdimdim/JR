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
    GraphicsContext gb = getGraphicsContext2D();
    public YouKnowWhatCanvas(){
        gb.setFill(Color.GREEN);
        gb.setStroke(Color.RED);
    }
    public void draw(double x, double y, double w, double h){
        gb.fillRect(x, y, w, h);
    }
    public double getW(){
        return gb.getCanvas().getWidth();
    }
    public double getH(){
        return gb.getCanvas().getHeight();
    }
    public Coords getCell(Board board,double x,double y){
        int col = board.getX();
        int row = board.getY();
        double width = getW();
        double height = getH();
        double avgcellx = ( width / col );
        double avgcelly = ( height / row );
        int w=0;
        int h=0;
        
        return new Coords(w,h);
    }
    public void redraw(Board board){
        int col = board.getX();
        int row = board.getY();
        double width = getW();
        double height = getH();
        double avgcellx = ( width / col );
        double avgcelly = ( height / row );
        gb.clearRect(0,0,width,height);
        for (int x=1; x<col;x++){
            draw(x*avgcellx,0,1,height);
        }
        for (int y=1; y<row;y++){
            draw(0,y*avgcelly,width,1);
        }
    }
}
