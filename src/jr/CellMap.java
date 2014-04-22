/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author thedoctor
 */
public class CellMap extends HashMap<Point, Cell>{
    /** for serialization... (so compiler doesn't complain) */ 
    private static final long serialVersionUID = 1L; 
    
    int width;
    int height;
    Point cellcoords= new Point();
    HashMap<Point,Cell> board;
    
    public CellMap(int x, int y) {
        width=x ;
        height=y;
        board = new HashMap<>();
    }
/** Fill the board with cells
 * Constructor. Specified size can be changed later. 
 * @param cell Cell to fill the array with
 * @param startw filling starting point (on the horizontal)
 * @param stopw filling stopping point (on the horizontal)
 * @param starth filling starting point (on the vertical)
 * @param stoph filling stopping point (on the vertical)
 **/
    public  void fill(Cell cell, int startw,int stopw, int starth, int stoph){
        for (int w = startw;w<=stopw-1;w++){
            for (int h = starth; h<= stoph-1; h++){
                if (w<0||h<0) continue;
                put(w,h,cell);
            }
        }
    }
/** Wipe a board **/
    public void empty(){
        board.clear();
    }
    public Cell get(int x, int y){
        cellcoords= new Point(x,y);
        return board.get(cellcoords);
    }
    public Point getCellPoint(Cell cell){
        return cell.getLocation();
    }
    public Point getPoint(int x, int y){
        return new Point(x,y);
    }
    public int getx(Cell cell){
        return cell.x;
    }
    public int gety(Cell cell){
        return cell.y;
    }
    public Cell getCell(Point point){
        return board.get(point);
    }
    public void put(int x, int y, Cell cell){
        cellcoords= new Point(x,y);
        board.put(cellcoords, cell);
    }
    public void put(Cell cell){
        board.put(cell.getLocation(), cell);
    }
    public void putAll(CellMap board){
        this.board=board.board;
    }
    public boolean contains(int x, int y){
        return board.containsKey(getPoint(x, y));
    }
    public boolean contains(Cell cell){
        return board.containsValue(cell);
    }
    public boolean getState(int x, int y){
        return contains(x,y)&&get(x,y).alive;
    }
    public CellMap copy(){
        return this;
    }
    public CellMap getExtendedRemap(int x,int y){
        CellMap newboard = new CellMap(x,y);
        board.values().stream().forEach((Cell cell)->{
            newboard.put(cell.x+x,cell.y+y,cell);
        });
        return newboard;
    }
    public void extendandremap(int x,int y){
        CellMap newboard = getExtendedRemap(x,y);
        putAll(newboard);
    }
/**  
 * Prints the state of the board into human sensible String output.   
 * <p>  
 * Use for debug.  
 * @return string representation of the board  
 */  
    @Override
    public String toString() {
//        return getClass().getName()+"[width="+width+",height="+height+"]";
        
        String out = "";
        for(int i = 1; i<height; i++) {
            for(int j = 1; j<width; j++) {
                Cell c = board.get(new Point(i, j));
                if(c==null)
                    out+= "x ";
                else
                 out += c.connections + " ";
            }
            out +="\n";
        }
        return out;
    }
}