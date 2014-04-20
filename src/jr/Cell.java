/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

//import static jr.Cell.State.*;
import static jr.Main.*;

/**
 *
 * @author thedoctor
 */
public class Cell {
    //State alive = POTENT;
    boolean alive = false;
    short cons=1;
    final int ID;
    final int board;
    public Cell(int ID, int board){
        this.ID=ID;
        this.board=board;
    }   
    public void spawn(int height){
        int var;
        for(int neigh = -1; neigh <= 1; neigh++){
            for (int fut= -height; fut<=height;fut+=height){
                var=fut+neigh+ID;
                if (all.get(board).tobe.containsKey(var)){
                    all.get(board).tobe.get(var).iter();
                }
                else{
                    all.get(board).tobe.put(var, new Cell(var,board));
                }
            }
        }
        all.get(board).tobe.get(ID).alive=cons==2||cons==3;
        all.get(board).process.remove(ID);
    }
    public void check(){
        if (ID<0){die();}
        if (cons==2||cons==3){
            all.get(board).tobe.get(ID).alive=true;
        }
        else {
            die();
        }
    }
    public void iter(){
        cons++;
    }
    public void deter(){
        cons--;
    }
    public void die(){
        all.get(board).tobe.remove(ID);
    }
    
    public int generateHash(int x, int y){
        return x+y*x;
    }
    
    @Override 
    public int hashCode() {
        return ID;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        return this.ID !=other.ID;
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