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
                if (var==ID) continue;   // leave out self
                if (tobe.containsKey(var)){
                    tobe.get(var).iter();
                }
                else{
                    tobe.put(var, new Cell(var));
                }
            }
        }
        
    }
    void check(){
        if (cons==2||cons==3)state=true;
        else die();
    }
    public void iter(){cons++;}
    public void die(){tobe.remove(ID);}
    
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
    return state==true ? 1 : 0;
    }
}