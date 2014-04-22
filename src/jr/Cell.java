/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;


/**
 *
 * @author thedoctor
 */
public class Cell extends Point{
    //State alive = POTENT;
    boolean alive = true;
    int connections=1;
    
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