/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

/**
 *
 * @author thedoctor
 */
public class Coords {
    public int x;
    public int y;

    public Coords(int x, int y){
        this.x=x;
        this.y=y;
        }
    public Coords(Coords p){
        x=p.x;
        y=p.y;
        }
    public Coords(){
        x=0;
        y=0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Coords)) {
            return false;
        }
        return ((this.x == ((Coords) o).x) && (this.y == ((Coords) o).y));
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }
}