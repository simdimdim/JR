/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 * Area for the Cells.
 *
 * @author thedoctor
 */
public class Board {

    public boolean running;
    CellMap cur;
    CellMap next;
    Queue input;
    public int id;
    /* Size of the board. Initialized to 1, to avoid dividing by 0 errors */
    public final Coords size = new Coords(1, 1);

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

    public Set<Cell> step() {
        next.empty();       // clean
        //System.out.println(toString(cur));  // check output
        change();           // applies input
        applylogic();       // applies GoL logic
        Set<Cell> diff = Sets.symmetricDifference(
                  cur.board, next.board).immutableCopy();
        cur.empty();        // empty current board
        cur.putAll(next);   // copy next step to current
        input.empty();      // make way for new input
        // stop simulation if no cells remain
        if ( cur.isEmpty() ) {
            stop();
        }
        if ( diff.isEmpty() ) {
            stop();
        }
        return diff;
    }

    void change() {
        input.get().stream().forEach(( Cell p ) -> {
            if ( cur.contains(p) ) {
                cur.remove(p);
            }
            else {
                cur.put(p);
            }
        });
    }

    void applylogic() {
        cur.board.stream()
                .flatMap(cell ->
                        cell.getNeighbours().stream())
                .distinct()
                .forEach(( Cell c ) -> {
                    //forEach mate if alive adds to next
                    if ( cur.check(c) == 2 && cur.contains(c) ) {
                        next.put(c);
                    }
                    else {
                        if ( cur.check(c) == 3 ) {
                            next.put(c);
                        }
                    }
                });
    }

    public int getX() {
        return size.x;
    }

    public int getY() {
        return size.y;
    }

    public void fill( boolean alive, int startw, int stopw, int starth,
            int stoph ) {
        cur.fill(alive, startw, stopw, starth, stoph);
    }

    public void stop() {
        running = false;
    }

    public void toQueue( int x, int y ) {
        input.add(x, y);
    }

    public void toQueue( Cell c ) {
        input.add(c);
    }

    public Queue getQueue() {
        return input;
    }

    public boolean inQueue( int x, int y ) {
        return input.contains(x, y);
    }

    public boolean inQueue( Cell c ) {
        return input.contains(c);
    }

    public void changeQueue( Cell c ) {
        if ( inQueue(c) ) {
            input.remove(c);
        }
        else {
            input.add(c);
        }
    }

    public boolean onBoard( int x, int y ) {
        return cur.contains(x, y);
    }

    public boolean onBoard( Cell c ) {
        return cur.contains(c);
    }

    public Set<Cell> getBoard() {
        return cur.getCells();
    }

    public Board resize( int x, int y ) {
        cur.extendandremap(x, y);
        next.extendandremap(x, y);
        return this;
    }

    /**
     * Prints the state of the board
     * into human sensible String output.
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
     * Prints the state of the board
     * into human sensible String output.
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
