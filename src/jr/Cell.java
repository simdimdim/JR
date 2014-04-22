/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thedoctor
 */
public class Cell extends Point{
    boolean alive = true;
    int connections=1;
    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Cell nighbs(int cons){
        connections=cons;
        return this;
    }
    public List getNeighbours(){
        HashMap<Point> neighbours = new HashMap<>();
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                if (w<0||h<0) continue;
                if (w==x&&h==y) continue;
                neighbours.add(new Point(w,h));
            }
        }
        return neighbours;
    }
    public boolean notdead(){
        return (connections==3||(alive&&connections==2));
    }
    public int getStateAsInt() {
        return alive==true ? 1 : 0;
    }
}