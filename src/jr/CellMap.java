/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import java.util.Collection;
import java.util.HashMap;
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
    HashMap<Coords, Cell> board;

    public CellMap( int x, int y ) {
        board = new HashMap<>();
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
    public void remove( Coords key ) {
        board.remove(key);
    }
    public Cell get( int x, int y ) {
        return board.get(new Coords(x, y));
    }
    public Cell getCell( Coords cord ) {
        return board.get(cord);
    }
    public Set<Coords> getCoords() {
        return board.keySet();
    }
    public Collection<Cell> getCells() {
        return board.values();
    }
    public void put( int x, int y ) {
        board.put(new Coords(x, y), new Cell(x, y));
    }
    public void put( Coords cord ) {
        board.put(cord, new Cell(cord));
    }
    public void putAll( CellMap board ) {
        this.board.putAll(board.board);
    }
    public void removeAll( CellMap b ) {
        b.board.keySet().forEach(( Coords c ) -> {
            if ( contains(c) ) {
                board.remove(c);
            }
        });
    }
    public boolean contains( int x, int y ) {
        return board.containsKey(new Coords(x, y));
    }
    public boolean contains( Coords cord ) {
        return board.containsKey(cord);
    }
    public boolean getState( int x, int y ) {
        return contains(x, y) && get(x, y).alive;
    }
    public boolean isEmpty() {
        return board.isEmpty();
    }
    public HashMap copy() {
        return this.board;
    }
    public CellMap getExtendedRemap( int x, int y ) {
        CellMap newboard = new CellMap(width + 2 * x, height + 2 * y);
        board.values().stream().forEach(( Cell cell ) -> {
            newboard.put(cell.x + x, cell.y + y);
        });
        return newboard;
    }
    public void extendandremap( int x, int y ) {
        CellMap newboard = getExtendedRemap(x, y);
        putAll(newboard);
    }
    public short check( Coords cord ) {
        short neighbours = 0;
        for ( int w = cord.x - 1; w <= cord.x + 1; w++ ) {
            for ( int h = cord.y - 1; h <= cord.y + 1; h++ ) {
                // drop cells from up and left borders
                if ( w < 0 || h < 0 ) {
                    continue;
                }
                // drop cells from bottom and right borders
                if ( w > width - 1 || h > height - 1 ) {
                    continue;
                }
                // ignore self
                if ( w == cord.x && h == cord.y ) {
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
