/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author thedoctor
 */
public class Queue {

    Set<Cell> queue;

    public Queue() {
        queue = new HashSet();
    }

    public void empty() {
        queue.clear();
    }

    public boolean isempty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    /**
     * Fill the board with cells
     * Constructor. Specified size can be changed later.
     *
     * @param alive
     * @param cell Cell to fill the array with
     * @param x1 filling starting point (on the horizontal)
     * @param x2 filling stopping point (on the horizontal)
     * @param y1 filling starting point (on the vertical)
     * @param y2 filling stopping point (on the vertical)
     *
     */
    public void fill( int x1, int x2, int y1, int y2 ) {
        for ( int x = min(x1, x2); x < max(x1, x2); x++ ) {
            for ( int y = min(y1, y2); y < max(y1, y2); y++ ) {
                add(x, y);
            }
        }
    }

    public void defill( int x1, int x2, int y1, int y2 ) {
        for ( int x = min(x1, x2); x < max(x1, x2); x++ ) {
            for ( int y = min(y1, y2); y < max(y1, y2); y++ ) {
                if ( contains(x, y) ) {
                    remove(x, y);
                }
            }
        }
    }

    public void change( int x, int y ) {
        if ( contains(x, y) ) {
            remove(x, y);
        }
        else {
            add(x, y);
        }
    }

    public void change( Cell c ) {
        if ( contains(c) ) {
            remove(c);
        }
        else {
            add(c);
        }
    }

    public void add( int x, int y ) {
        queue.add(new Cell(x, y));
    }

    public void add( Cell c ) {
        queue.add(c);
    }

    public void remove( int x, int y ) {
        queue.remove(new Coords(x, y));
    }

    public void remove( Cell c ) {
        queue.remove(c);
    }

    public boolean contains( int x, int y ) {
        return queue.contains(new Coords(x, y));
    }

    public boolean contains( Cell c ) {
        return queue.contains(c);
    }

    public Set<Cell> get() {
        return queue;
    }

}
