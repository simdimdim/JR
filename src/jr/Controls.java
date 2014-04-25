/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.util.Set;
import jr.GUI.Main;

/**
 *
 * @author thedoctor
 */
public class Controls {
    static public void step(int n, Set<Coords> queue){
        if (Main.all.containsKey(n)) {
            Board board = Main.all.get(n);
            if (board.running){
                board.step(queue);
            }
        }
    }
    public Coords getSize(int n){
        return new Coords(Main.all.get(n).size);
    }
   /** 
    * Creates a new board.
    * @param width x of the new board
    * @param height y of the new board
    * @return the created board.
    */
    static public Board create(int width,int height){
        // make board
        int id = Main.generateNewBoardId();
        Board b = new Board(width,height,id);
        Main.addBoard(id,b);
        
        //manually add cells
//        b.cur.put(1,1);
        b.cur.put(2,3);
        b.cur.put(2,1);
        b.cur.put(2,2);
        
        return b;
    }
}