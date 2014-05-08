/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thedoctor
 */
public class Cell extends Coords{
    boolean alive = true;
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Cell(Coords p){
        x=p.x;
        y=p.y;
    }
    /**Returns a List Coords of neighbors
     * 
     * @return List of up to 8 Coords() of the cell
     */
    public List<Coords> getNeighbours(){
        List<Coords> neighbours = new ArrayList<>();
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                //to be removed or substitued with < width or height
                if (w<0||h<0) continue; 
                //if (w==x||h==y) continue;
                neighbours.add(new Coords(w,h));
            }
        }
        return neighbours;
    }
    public int getStateAsInt() {
        return alive==true ? 1 : 0;
    }
}