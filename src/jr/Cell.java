/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import static jr.Main.*;

/**
 *
 * @author thedoctor
 */
public class Cell {
    boolean state=false;
    short cons=1;
    int ID;
    short board;
    public Cell(int ID, short board){
        this.ID=ID;
        this.board=board;
    }
    public void spawn(int height){
        int var;
        for(int neigh = -1; neigh <= 1; neigh++){
            for (int fut= -height; fut<=height;fut+=height){
                var=fut+neigh+ID;
                if (tobe.containsKey(var)){
                    if (var!=ID){
                    tobe.get(var).iter();
                    }
                }
                else{
                    tobe.put(var, new Cell(var, board));
                }
            }
        }
    }
    void check(){
        if (cons==2||cons==3){state=true;}
        else{die();}
    }
    public void iter(){cons++;}
    public void die(){tobe.remove(ID);}
}