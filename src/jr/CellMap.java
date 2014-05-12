/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author thedoctor
 */
public class CellMap {

/**
 * for serialization... (so compiler doesn't complain)
 */
private static final long serialVersionUID = 1L;
int width;
int height;
Set<Cell> board;

public CellMap( int x, int y ) {
    board = new HashSet<>();
    width = x;
    height = y;
}
/**
 * Fill the board with cells
 * Constructor. Specified size can be changed later.
 *
 * @param cell Cell to fill the array with
 * @param startw filling starting point (on the horizontal)
 * @param stopw filling stopping point (on the horizontal)
 * @param starth filling starting point (on the vertical)
 * @param stoph filling stopping point (on the vertical)
 *
 */
public void fill( Cell cell, int startw, int stopw, int starth, int stoph ) {
    for ( int w = startw; w <= stopw - 1; w++ ) {
        for ( int h = starth; h <= stoph - 1; h++ ) {
            if ( w < 0 || h < 0 ) {
                continue;
            }
            put(w, h);
        }
    }
}
/**
 * Wipe a board *
 */
public void empty() {
    board.clear();
}
public void remove( Cell c ) {
    board.remove(c);
}
public Cell get( int x, int y ) {
    if ( board.contains(new Cell(x, y)) ) {
        return new Cell(x, y);
    }
    else {
        return null;
    }
}
public Cell getCell( Cell c ) {
    if ( board.contains(c) ) {
        return new Cell(c);
    }
    else {
        return null;
    }
}
public Set<Cell> getCells() {
    return board;
}
public void put( int x, int y ) {
    board.add(new Cell(x, y));
}
public void put( Cell c ) {
    board.add(new Cell(c));
}
public void putAll( CellMap board ) {
    this.board.addAll(board.board);
}
public void removeAll( CellMap b ) {
    b.board.forEach(( Cell c ) -> {
        if ( contains(c) ) {
            board.remove(c);
        }
    });
}
public boolean contains( int x, int y ) {
    return board.contains(new Cell(x, y));
}
public boolean contains( Cell c ) {
    return board.contains(c);
}
public boolean getState( int x, int y ) {
    return contains(x, y) && get(x, y).alive;
}
public boolean isEmpty() {
    return board.isEmpty();
}
public CellMap getExtendedRemap( int x, int y ) {
    CellMap newboard = new CellMap(width + 2 * x, height + 2 * y);
    board.stream().forEach(( Cell c ) -> {
        newboard.put(c.x + x, c.y + y);
    });
    return newboard;
}
public void extendandremap( int x, int y ) {
    CellMap newboard = getExtendedRemap(x, y);
    putAll(newboard);
}
public short check( Cell c ) {
    short neighbours = 0;
    for ( int w = c.x - 1; w <= c.x + 1; w++ ) {
        for ( int h = c.y - 1; h <= c.y + 1; h++ ) {
            // drop cells from up and left borders
            if ( w < 0 || h < 0 ) {
                continue;
            }
            // drop cells from bottom and right borders
            if ( w > width - 1 || h > height - 1 ) {
                continue;
            }
            // ignore self
            if ( w == c.x && h == c.y ) {
                continue;
            }
            if ( contains(w, h) ) {
                neighbours++;
            }
        }
    }
    return neighbours;
}
}
