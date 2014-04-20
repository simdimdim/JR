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
    short cons=0;
    int ID;
    public Cell(int x){
        this.ID=x;
    }
    public void spawn(){
        int var;
        for(int neigh = -1; neigh < 2; neigh+=1){
            for (int fut= -this.ID; fut<=this.ID;fut+=this.ID){
                var=fut+neigh;
                if (tobe.containsKey(var)){
                    tobe.get(var).iter();
                }
                else{
                    tobe.put(var, new Cell(var));
                }
            }
        }
    }
    public void move(){
        tobe.put(this.ID, this);
        process.remove(this.ID, this);
    }
    public void iter(){
        this.cons+=1;
    }
    public void kill(){
        this.state = false;
    }
    public void die(){
        tobe.remove(this.ID, this);
    }
}
