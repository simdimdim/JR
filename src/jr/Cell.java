/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

//import static jr.Cell.State.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thedoctor
 */
public class Cell {
    //State alive = POTENT;
    boolean alive = false;
    short cons=1;
    List<Integer> ID = new ArrayList();
    
    public Cell(int height, int width){
        ID.add(height, width);
    }
    
    public void spawn(Board board,int height, int width){
        for(int x = width-1; x <= width+1; x++){
            for (int y= height-1; y<=height+1;y++){
                if (x==0 && y==0){
                    board.tobe[x][y].alive=(cons==2||cons==3);
                    board.pq.add(ID);}
                if (board.tobe[x][y]!=null)
                    board.tobe[x][y].iter();
                else board.tobe[x][y]=new Cell(x,y);
            }
        }
    }
    public void check(Board board,int x, int y){
        if (cons==2||cons==3){
            board.tobe[x][y].alive=true;
            board.tq.add(ID);
        }
        else {
            die(board);
        }
    }
    public void iter(){
        cons++;
    }
    public void deter(){
        cons--;
    }
    public void die(Board board){
        board.tobe[ID.get(0)][ID.get(1)]=null;
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