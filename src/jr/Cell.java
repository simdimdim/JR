/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thedoctor
 */
public class Cell extends Point{
    //State alive = POTENT;
    boolean alive = true;
    int connections=0;
    
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Cell neighbours(int cons){
        connections=cons;
        return this;
    }
    public boolean notdead(){
//        System.out.println("Checking cons:"+connections);  //check connections
//        System.out.println("Checking alive:"+ //check state
//                ((!alive&&connections==3)
//                        ||(alive&&(connections==2||connections==3))));
        return ((!alive&&connections==3)||
                (alive&&(connections==2||connections==3)));
    }
    public boolean stayAlive(){
        return connections==2|| connections==3;
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
    
    public List<Cell> getNeighbours() {
        List<Cell> out = new ArrayList();
        for(int i=1; i<=9; i++) {
            int xx = (i-1)/3+1;
            int yy = i%3+1;
//            if(!(xx==2 && yy==2)) {
                Cell c = new Cell(x+xx-2, y+yy-2);
                out.add(c);
//            }
        }
        return out;
    }

    @Override
    public int hashCode() {
        return x*1000+y;
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
        return true;
    }
    
}