/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 

import java.util.Set;

/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
public class Board{
    boolean running;
    CellMap cur;
    CellMap next;
    public int number;
    public Coords size;
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param x size.y in number of cells 
     * @param y size.x in number of cells 
     * @param n board ID
     */ 
    public Board(int x, int y, int n) {
        size.x=x ;
        size.y=y;
        cur = new CellMap(x,y);
        next = new CellMap(x,y);
        running=true;
        cur.empty();
        next.empty();
        this.number=n;
    }
    public Set<Coords> step(Set<Coords> queue){
        change(queue); // applies input
        applylogic();
        cur.empty();
        cur.putAll(next);
        next.empty();
        // stop simulation if no cells remain
        if(cur.isEmpty()) {
            stop();}

        return cur.board.keySet();
    }
    void change(Set<Coords> queue){
        queue.stream().forEach((Coords p)->{
            if (cur.contains(p)){
                cur.remove(p);}
            else{
                cur.put(p);}
        }); 
    }
    void applylogic(){
        cur.board.values().stream()
                .flatMap(cell->cell.getNeighbours().stream())
                .distinct()
                .forEach((Coords p)->{
                    //forEach mate if alive adds to next
                    if(cur.check(p)==2&&cur.contains(p)){
                        next.put(p);}
                    else{
                        if (cur.check(p)==3) {
                            next.put(p);}
                    }
                });
    }
    public void stop(){
        running=false;
    }
    public Board resize(int x, int y){
        cur.extendandremap(x, y);
        next.extendandremap(x, y);
        return this;
    }
    /**  
 * Prints the state of the board into human sensible String output.   
 * <p>  
 * Use for debug. 
 * @param board for board for processing  
 * @return string representation of the board  
 */  
    public String toString(CellMap board) {
        if (board==null){board=cur;}
        //System.out.println(board);
        String out = "";
        for (int w = 0;w<=size.x-1;w++){
            for (int h = 0; h<= size.y-1; h++){
                if (board.contains(w,h))out += board.check(cur.get(w,h));
                //if (cur.contains(w,h))out += "1"; //simple alive check
                else out +="0";
            }
            out += "\n";}        //System.out.println(cur);
        return out; 
    }
}