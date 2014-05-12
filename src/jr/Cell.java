/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import jr.GUI.C;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thedoctor
 */
public class Cell extends Coords {

    boolean alive = true;
    final C gui;
    public Cell( int x, int y, Queue input ) {
        this.x = x;
        this.y = y;
        gui=new C(x,y,input);
    }
    public Cell( Coords p, Queue input ) {
        x = p.x;
        y = p.y;
        gui=new C(p,input);
    }
    /**
     * Returns a List Coords of neighbors
     *
     * @return List of up to 8 Coords() of the cell
     */
    public List<Coords> getNeighbours() {
        List<Coords> neighbours = new ArrayList<>();
        for ( int w = x - 1; w <= x + 1; w++ ) {
            for ( int h = y - 1; h <= y + 1; h++ ) {
                //to be removed or substitued with < width or height
                if ( w < 0 || h < 0 ) {
                    continue;
                }
                //if (w==x||h==y) continue;
                neighbours.add(new Coords(w, h));
            }
        }
        return neighbours;
    }
    public int getStateAsInt() {
        return alive == true ? 1 : 0;
    }
    public C getGui(){
        return gui;
    }
}
