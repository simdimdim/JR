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
                add(w,h,cell);
            }
        }
    }
/** Wipe a board **/
    public void empty(){
        board.clear();
    }
    public Cell get(int x, int y){
        return board.get(new Point(x,y));
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
    public void add(int x, int y, Cell cell){
        cellcoords= new Point(x,y);
        board.put(cellcoords, cell);
    }
    public void add(int x, int y){
        board.put(new Point(x,y), new Cell(x,y));
    }
    public void put(Point p){
        board.put(new Point(p.x,p.y), new Cell(p));
    }
    public void putAll(CellMap board){
        this.board.putAll(board.board);
    }
    public boolean contains(int x, int y){
        return board.containsKey(new Point(x,y));
    }
    public boolean contains(Point cell){
        return board.containsKey(cell);
    }
    public boolean getState(int x, int y){
        return contains(x,y)&&get(x,y).alive;
    }
    public HashMap copy(){
        return this.board;
    }
    public CellMap getExtendedRemap(int x,int y){
        CellMap newboard = new CellMap(x,y);
        board.values().stream().forEach((Cell cell)->{
            newboard.add(cell.x+x,cell.y+y,cell);
        });
        return newboard;
    }
    public void extendandremap(int x,int y){
        CellMap newboard = getExtendedRemap(x,y);
        putAll(newboard);
    }
    public short check(Point p){
        short neighbours = 0;
        for (int w=p.x-1;w<=p.x+1;w++){
            for (int h=p.y-1;h<=p.y+1;h++){
                if (w<0||h<0) continue;
                if (w==p.x&&h==p.y) continue;
                if (contains(w,h)){
                    neighbours++;
                }
            }
        }
        return neighbours; 
    }
    /*For debuging purposes*/
    public String acheck(){
       String out = "";
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                if (!contains(w,h))out +='0';
                else {
                    if (getState(w,h)) out += "1";
                    else out +='0';}  
            }
            out += "\n";
        }
        return out; 
    }
    public String ccheck(){
        String out = "";
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                if (contains(w,h))out += check(get(w,h));
                else out +="0";
            }
            out += "\n";}
        return out;  
    }
    
/**  
 * Prints the state of the board into human sensible String output.   
 * <p>  
 * Use for debug.  
 * @return string representation of the board  
 */  
    @Override
    public String toString() {
        return getClass().getName()+"[width="+width+",height="+height+"]";
    }
}