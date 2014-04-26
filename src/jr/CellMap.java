/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.util.HashMap;

/**
 *
 * @author thedoctor
 */
public class CellMap{
    /** for serialization... (so compiler doesn't complain) */ 
    private static final long serialVersionUID = 1L; 
    int width;
    int height;
    HashMap<Coords,BCell> board;
    
    public CellMap(int x, int y) {
        board = new HashMap<>();
        width=x ;
        height=y;
    }
/** Fill the board with cells
 * Constructor. Specified size can be changed later. 
 * @param cell BCell to fill the array with
 * @param startw filling starting point (on the horizontal)
 * @param stopw filling stopping point (on the horizontal)
 * @param starth filling starting point (on the vertical)
 * @param stoph filling stopping point (on the vertical)
 **/
    public  void fill(BCell cell, int startw,int stopw, int starth, int stoph){
        for (int w = startw;w<=stopw-1;w++){
            for (int h = starth; h<= stoph-1; h++){
                if (w<0||h<0) continue;
                put(w,h);
            }
        }
    }
/** Wipe a board **/
    public void empty(){
        board.clear();
    }
    public void remove(Coords key){
       board.remove(key);
    }
    public BCell get(int x, int y){
        return board.get(new Coords(x,y));
    }
    public Coords getCoords(BCell cell){
        return new Coords(cell.x,cell.y);
    }
    public BCell getCell(Coords cord){
        return board.get(cord);
    }
    public void put(int x, int y){
        board.put(new Coords(x,y), new BCell(x,y));
    }
    public void put(Coords cord){
        board.put(cord, new BCell(cord));
    }
    public void putAll(CellMap board){
        this.board.putAll(board.board);
    }
    public void removeAll(CellMap b){
        b.board.keySet().forEach((Coords c)->{
            if (contains(c))
                board.remove(c);
        });
    }
    public boolean contains(int x, int y){
        return board.containsKey(new Coords(x,y));
    }
    public boolean contains(Coords cord){
        return board.containsKey(cord);
    }
    public boolean getState(int x, int y){
        return contains(x,y)&&get(x,y).alive;
    }
    public boolean isEmpty(){
        return board.isEmpty();
    }
    public HashMap copy(){
        return this.board;
    }
    public CellMap getExtendedRemap(int x,int y){
        CellMap newboard = new CellMap(width+2*x,height+2*y);
        board.values().stream().forEach((BCell cell)->{
            newboard.put(cell.x+x,cell.y+y);
        });
        return newboard;
    }
    public void extendandremap(int x,int y){
        CellMap newboard = getExtendedRemap(x,y);
        putAll(newboard);
    }
    public short check(Coords cord){
        short neighbours = 0;
        for (int w=cord.x-1;w<=cord.x+1;w++){
            for (int h=cord.y-1;h<=cord.y+1;h++){
                if (w<0||h<0) continue;
                if (w==cord.x&&h==cord.y) continue;
                if (contains(w,h)){
                    neighbours++;
                }
            }
        }
        return neighbours; 
    }
}