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
    
    public short check(CellMap board, Point p){
        short neighbours = 0;
        for (int w=p.x-1;w<=p.x+1;w++){
            for (int h=p.y-1;h<=p.y+1;h++){
                if (w<0||h<0) continue;
                if (w==p.x&&h==p.y) continue;
                if (board.board.containsKey(p))neighbours++;
            }
        }
        return neighbours; 
    }
    public void step(){
        cur.board.values().stream().flatMap(cell-> //Gets list of all mates
                cell.getNeighbours().stream()).distinct().forEach((Point p)->{
                    
                    //forEach potential alive cell
                    //checks alive neighbours and adds to next if alive
                    if(!cur.board.containsKey(p)){
                        if(cur.get(p).notdead()){next.put(p);}
                    }
                    else{
                        if (check(cur,p)==3) {
                            next.put(p);}
                    }
                });
        endstep();
    }
    public void update(){
        next.board.values().stream().forEach((Cell cell)->{
            cell.nighbs(check(next,cell));
            if (cell.notdead())cell.alive=true; 
        });  
    }
    public void endstep(){
        debug();
        cur.empty();
        update();
        cur.putAll(next);
        next.empty();
    }
    public String acheck(CellMap board){
       String out = "";
        //wait call here
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
                if (board.contains(w,h))out += board.get(w,h).getStateAsInt();
                else out +="0";
            }
            out += "\n";}
        return out;  
    }
    public void debug(){
        System.out.println("board alive \n"+acheck(cur));
        System.out.println("board mates \n"+ccheck(cur));
        System.out.println("next alive \n"+acheck(next));
        System.out.println("next mates \n"+ccheck(next));
    }
}