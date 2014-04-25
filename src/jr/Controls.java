/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.util.Set;
import static jr.GUI.Main.all;

/**
 *
 * @author thedoctor
 */
public class Controls {
    static public void step(int n, Set<Coords> queue){
        if (all.containsKey(n)) {
            Board board =all.get(n);
            if (board.running){
                board.step(queue);
            }
        }
    }
    public Coords getSize(int n){
        return new Coords(all.get(n).size);
    }
    /*creates a new board
    * @param width x of the new board
    * @param height y of the new board
    */
    static public void create(int width,int height){
        int n=all.size();
        all.put(n,new Board(width,height,n));
        //manually add cells
        Board b = all.get(n);
        //b.cur.put(1,1);
        b.cur.put(2,3);
        b.cur.put(2,1);
        b.cur.put(2,2);
    }
}