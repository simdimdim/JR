/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

//import static jr.Cell.State.*;

import java.awt.Point;


/**
 *
 * @author thedoctor
 */
public class Cell extends Point{
    //State alive = POTENT;
    boolean alive = false;
    short cons=1;
    
    public Cell(BoardManger board,int height, int width){
        x=height;
        y=width;
    }

    public Cell check(BoardManger board,int x, int y){
        if (!alive&&cons==3) alive=true;
        if (alive&&(cons==2||cons==3)) alive=true;
        if (!alive) return null;
        else return this;
    }
    
    public void iter(){
        cons++;
    }
    public void deter(){
        cons--;
    }
    public void die(BoardManger board){
        board.next.put(x,y,null);
    }
    
    /**
    For resting purposes and maybe adding alive cells in the future
    **/
    public Point add(BoardManger board){
        alive=true;
        board.cur.put(this);
        board.cur.get(this).cons=2;
        return board.cur.get(x,y).getLocation();
    }
    
    public int getStateAsInt() {
        return alive==true ? 1 : 0;
    }
    /*
    public static enum State {
        ALIVE,
        POTENT;
        public static State neg(State s) {
            return s==ALIVE ? POTENT : ALIVE;
        }
    }*/
}