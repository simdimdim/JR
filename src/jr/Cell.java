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
    boolean alive = true;
    short mates=0;
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Cell(Point p){
        x=p.x;
        y=p.y;
    }
    public Cell nighbs(short mate){
        mates=mate;
        return this;
    }
    /**Returns a List Points of neighbors
     * 
     * @return List of up to 8 Points() of the cell
     */
    public List<Point> getNeighbours(){
        List<Point> neighbours = new ArrayList<>();
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                if (w<0||h<0) continue;
                neighbours.add(new Point(w,h));
            }
        }
        return neighbours;
    }
    public boolean notdead(){
        return (mates==3||(alive&&mates==2));
    }
    public int getStateAsInt() {
        return alive==true ? 1 : 0;
    }
}