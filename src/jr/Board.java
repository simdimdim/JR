/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 

/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
public class Board{
    boolean running;
    int number;
    CellMap cur;
    CellMap next;
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param x height in number of cells 
     * @param y width in number of cells 
     * @param number board ID
     */ 
    public Board(int x, int y, int number) {
        cur = new CellMap(x,y);
        next = new CellMap(x,y);
        running=true;
        cur.empty();
        next.empty();
        this.number=number;
    }   
}