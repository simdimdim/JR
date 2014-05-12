/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import jr.GUI.C;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 * Area for the Cells.
 *
 * @author thedoctor
 */
public class Board {

    boolean running;
    CellMap cur;
    CellMap next;
    Queue input;
    public int id;
    /*Size of the board. Initialized to 0.*/
    public final Coords size = new Coords(0, 0);

    /**
     * Constructor. Specified size can be changed later.
     *
     * @param x size.y in id of cells
     * @param y size.x in id of cells
     * @param n board ID
     */
    public Board( int x, int y, int n ) {
        size.x = x;
        size.y = y;
        cur = new CellMap(x, y);
        next = new CellMap(x, y);
        input = new Queue();
        running = true;
        this.id = n;
    }

    public void step() {
        //System.out.println(toString(cur));  // check output
        change();           // applies input
        applylogic();       // applies GoL logic
        cur.empty();        // empty current board
        cur.putAll(next);   // copy next step to current
        next.empty();       // clean
        input.empty();      // make way for new input
        // stop simulation if no cells remain
        if ( cur.isEmpty() ) {
            stop();
        }
    }
    void change() {
        input.get().stream().forEach(( Coords p ) -> {
            if ( cur.contains(p) ) {
                cur.remove(p);
            }
            else {
                cur.put(p,this.input);
            }
        });
    }
    void applylogic() {
        cur.board.values().stream()
                .flatMap(cell ->
                        cell.getNeighbours().stream())
                .distinct()
                .forEach(( Coords p ) -> {
                    //forEach mate if alive adds to next
                    if ( cur.check(p) == 2 && cur.contains(p) ) {
                        next.put(p,input);
                    }
                    else {
                        if ( cur.check(p) == 3 ) {
                            next.put(p, input);
                        }
                    }
                });
    }
    public Set<Coords> getDifference() {
        return Sets.symmetricDifference(
                cur.board.keySet(), next.board.keySet());
    }
    public int getX() {
        return size.x;
    }
    public int getY() {
        return size.y;
    }
    public void stop() {
        running = false;
    }
    public void toQueue( int x, int y ) {
        input.add(x, y);
    }
    public Queue getQueue() {
        return input;
    }
    public boolean inQueue( int x, int y ) {
        return input.contains(x, y);
    }
    public boolean onBoard( int x, int y ) {
        return cur.contains(x, y);
    }
    public C getguicell(int x, int y){
        return cur.get(x, y).getGui();
    }
    public Board resize( int x, int y ) {
        cur.extendandremap(x, y, input);
        next.extendandremap(x, y,input );
        return this;
    }
    /**
     * Prints the state of the board into human sensible String output.
     * <p>
     * Use for debug.
     *
     * @param b for board for processing
     * @return string representation of the board
     */
    public String toString( CellMap b ) {
        CellMap board;
        if ( b != null ) {
            board = b;
        }
        else {
            board = cur;
        }
        //System.out.println(board);
        String out = "";
        for ( int h = 0; h < size.y; h++ ) {
            for ( int w = 0; w < size.x; w++ ) {
                if ( board.contains(w, h) ) {
                    out += board.check(cur.get(w, h));
                }
                //if (cur.contains(w,h))out += "1"; //simple alive check
                else {
                    out += "0";
                }
            }
            out += "\n";
        }        //System.out.println(cur);
        return out;
    }
    /**
     * Prints the state of the board into human sensible String output.
     * <p>
     * Use for debug.
     *
     * @param board for board for processing
     * @return string representation of the board
     */
    @Override
    public String toString() {
        //System.out.println(board);
        String out = "";
        for ( int h = 0; h < size.y; h++ ) {
            for ( int w = 0; w < size.x; w++ ) {
                if ( cur.contains(w, h) ) {
                    out += "1"; //simple alive check
                }
                else {
                    out += "0";
                }
            }
            out += "\n";
        }        //System.out.println(cur);
        return out;
    }
}