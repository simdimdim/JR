/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;

/**
 *
 * @author thedoctor
 */
public class Board extends Point{
    Cell[][] board;
    Queue queue;
    public Board(int x, int y) {
        this.x=x ;
        this.y=y;
        board = new Cell[x][y];
        empty();
        queue= new Queue();
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
        for (int w = startw-1;w<=stopw-1;w++){
            for (int h = starth-1; h<= stoph-1; h++){
                board[w][h]=cell;}
        }
    }
/** Fill the board with cells
 * Constructor. Specified size can be changed later. 
 * @param cell Cell to fill the array with
 * @param start filling starting point (on both horizontal and vertical)
 * @param stop filling stopping point (on both horizontal and vertical)
 * <p>
 * Rectangle fillment
 **/
    public void fill(Cell cell, int start, int stop){
        for (int w = start-1;w<=start-1;w++){
            for (int h = start-1; h<= stop-1; h++){
                board[w][h]=cell;
            }
        }
    }
    public void fill(Cell cell){
        for (int w = 0;w<=x-1;w++){
            for (int h = 0; h<= y-1; h++){
                board[w][h]=cell;
            }
        }
    }
/** Wipe a board **/
    public void empty(){
        fill(null);
    }
    public Cell get(int x, int y){
        return board[x][y];
    }
    public Cell get(Cell cell){
        return board[cell.x][cell.y];
    }
    public Cell[][] get(){
        return board;
    }
    public void put(int x, int y, Cell cell){
        board[x][y]=cell;
    }
    public void put(Cell cell){
        board[cell.x][cell.y]=cell;
    }
    public void put(Cell[][] board){
        this.board=board;
    }
    public void put(Point point,Cell cell){
        board[point.x][point.y]=cell;
    }
    public void put(Board board){
        this.board=board.board;
    }
    public Board copy(){
        return this;
    }
    public Cell[][] getExtendedRemap(int width,int height){
        Cell[][] newboard = new Cell[x+2*width][y+2*height];
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                newboard[width+x][height+y]=board[w][h];
            }
        }
        return newboard;
    }
    public void extendandremap(int width,int height){
        Cell[][] newboard = new Cell[x+2*width][y+2*height];
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                newboard[width+x][height+y]=board[w][h];
            }
        }
        put(newboard);
    }
/**  
 * Prints the state of the board into human sensible String output.   
 * <p>  
 * Use for debug.  
 * @return string representation of the board  
 */  
    public String acheck(){
       String out = "";
        //wait call here
        for (int w = 0;w<=x-1;w++){
            for (int h = 0; h<= y-1; h++){
                if (board[w][h]==null)out +='0';
                else {if (board[w][h].alive) out += "1";
                    else out +='0';}  
            }
            out += "\n";
        }
        return out; 
    }
    public String ccheck() {
        String out = "";
        for (int w = 0;w<=x-1;w++){
            for (int h = 0; h<= y-1; h++){
                if (board[w][h]==null)out +='0';
                else out += board[w][h].cons;}
            out += "\n";}
        return out;  
    }
        @Override
    public String toString() {
        return getClass().getName() + "[width=" + x + ",height=" + y + "]";
    }
}