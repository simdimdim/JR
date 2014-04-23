/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 

import java.awt.Point;

/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
public class BoardManager{
    boolean running;
    CellMap cur;
    CellMap next;
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param x height in number of cells 
     * @param y width in number of cells 
     */ 
    public BoardManager(int x, int y) {
        cur = new CellMap(x,y);
        next = new CellMap(x,y);
        running=true;
        cur.empty();
        next.empty();
    }   
    public short check(Point p){
        short neighbours = 0;
        for (int w=p.x-1;w<=p.x+1;w++){
            for (int h=p.y-1;h<=p.y+1;h++){
                if (w<0||h<0) continue;
                if (w==p.x&&h==p.y) continue;
                if (cur.contains(w,h)){
                    neighbours++;
                }
            }
        }
        return neighbours; 
    }
    public String acheck(CellMap board){
       String out = "";
        for (int w = 0;w<=board.width-1;w++){
            for (int h = 0; h<= board.height-1; h++){
                if (!board.contains(w,h))out +='0';
                else {
                    if (board.getState(w,h)) out += "1";
                    else out +='0';}  
            }
            out += "\n";
        }
        return out; 
    }
    public String ccheck(CellMap board){
        String out = "";
        for (int w = 0;w<=board.width-1;w++){
            for (int h = 0; h<= board.height-1; h++){
                if (board.contains(w,h))out += check(board.get(w,h));
                else out +="0";
            }
            out += "\n";}
        return out;  
    }
    public String echeck(CellMap board){
        String out = "";
        for (int w = 0;w<=board.width-1;w++){
            for (int h = 0; h<= board.height-1; h++){
                if (board.contains(w,h))out += "0";
                else out +="x";
            }
            out += "\n";}
        return out;  
    }
}