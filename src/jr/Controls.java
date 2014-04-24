/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;


import java.util.HashSet;
import java.util.Set;
import static jr.GUI.Main.all;

/**
 *
 * @author thedoctor
 */
public class Controls {
    static public Set<Coords> step(int n, Set<Coords> queue){
        Set<Coords> output = new HashSet<>();
        if (all.containsKey(n)) {
            Board board =all.get(n);
            if (board.running){
                //main operations
                //Gets list of all mates
                output=board.step(queue);}
            System.out.println(board.toString(board.cur));
        }
        return output;  
    }
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