/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

//import static jr.Cell.State.*;

/**
 *
 * @author thedoctor
 */
public class Cell {
    //State alive = POTENT;
    boolean alive = false;
    short cons=1;
    int[] xy = new int[2];
    
    public Cell(Board board,int height, int width){
        xy[0]=height;
        xy[1]=width;
    }

    public Cell check(Board board,int x, int y){
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
    public void die(Board board){
        
        board.tobeboard[xy[0]][xy[1]]=null;
    }
    
    /**
    For resting purposes and maybe adding alive cells in the future
    **/
    public int[] add(Board board){
        alive=true;
        board.curboard[xy[0]][xy[1]]=this;
        board.curboard[xy[0]][xy[1]].cons=2;
        return board.curboard[xy[0]][xy[1]].xy;
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