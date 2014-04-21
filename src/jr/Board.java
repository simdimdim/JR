/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 

import java.util.ArrayList;
import java.util.List;

/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
public class Board{
    final int width;
    final int height;
    boolean running;
    Cell[][] tobe;
    Cell[][] process;
    ArrayList<List> pq = new ArrayList(); //to be processed
    ArrayList<List> tq = new ArrayList(); //for the next step
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param H height in number of cells 
     * @param W width in number of cells 
     */ 
    public Board(int x, int y) {
        width=x ;
        height=y;
        running=true;
        tobe = new Cell[x][y];
        process = new Cell[x][y];
    }
    
    public void extendandremap(int x,int y){
        Cell[][] newtobe = new Cell[width+2*x][height+2*y];
        Cell[][] newprocess = new Cell[width+2*x][height+2*y];
        for (int w = 0;w<=width;w++){
            for (int h = 0; h<= height; h++){
                newtobe[width+x][height+y]=tobe[width][height];
                newprocess[width+x][height+y]=tobe[width][height];
            }
        }
        process=newprocess;
        tobe=newtobe;       
    }
    
    public void step(){
        for (int x = 0;x<=width;x++){
            for (int y = 0; y<= height; y++){
                if (running){break;}
                process[x][y].check(this,x,y);
            }
        }
        if (tq.isEmpty()) endstep();
    }
    public void endstep(){
        eprocess();
        process=tobe;
        etobe();
    }
    
    public void eprocess(){
        for (int w = 0;w<=width;w++){
            for (int h = 0; h<= height; h++){
                process[w][h]=null;
            }
        } 
    }
    public void etobe(){
        for (int w = 0;w<=width;w++){
            for (int h = 0; h<= height; h++){
                tobe[w][h]=null;
            }
        } 
    }

/**  
 * Prints the state of the board into human sensible String output.   
 * <p>  
 * Use for debug.  
 * @return string representation of the board  
 */  
    @Override  
    public String toString() {
        String out = "";
        //wait call here
        for (int x = 0;x<=width;x++){
            for (int y = 0; y<= height; y++){
                out += process[x][y].getStateAsInt();
            }
            out += "\n";
        }
        return out;  
    } 
} 