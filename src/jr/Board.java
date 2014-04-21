/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 

import java.util.ArrayDeque;
import java.util.Queue;

/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
public class Board{
    int width;
    int height;
    boolean running;
    Cell[][] tobeboard;
    Cell[][] curboard;
    Queue<int[]> pq = new ArrayDeque(); //to be curboarded
    Queue<int[]> tq = new ArrayDeque(); //for the next step
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param H height in number of cells 
     * @param W width in number of cells 
     */ 
    public Board(int x, int y) {
        width=x ;
        height=y;
        running=true;
        tobeboard = new Cell[x][y];
        curboard = new Cell[x][y];
        ecurboard();
        etobeboard();
    }
    
    public void extendandremap(int x,int y){
        Cell[][] newtobeboard = new Cell[width+2*x][height+2*y];
        Cell[][] newcurboard = new Cell[width+2*x][height+2*y];
        for (int w = 0;w<=width;w++){
            for (int h = 0; h<= height; h++){
                newtobeboard[width+x][height+y]=tobeboard[width][height];
                newcurboard[width+x][height+y]=tobeboard[width][height];
            }
        }
        curboard=newcurboard;
        tobeboard=newtobeboard;       
    }
    
    public void spawn(Board board,int x, int y){
        int[] ID = new int[2];
        for(int w = x-1; w <= x+1; w++){
            for (int h= y-1; h<=y+1;h++){
                if (w<0||h<0)continue;
                ID[0]=w;
                ID[1]=h;
                if (board.tobeboard[w][h]==null){
                    board.tobeboard[w][h]=new Cell(board,w,h);
                    if (!board.tq.contains(ID))board.tq.add(ID);
                }
                else {
                    if (w==x && h==y){
                        board.tobeboard[x][y]=new Cell(this,x,y);
                        board.tobeboard[x][y].cons=2;}
                    else board.tobeboard[w][h].iter();
                }
            }
        }    
    }
    
    public void step(){
        pq.stream().forEach((int[] id) -> {
            spawn(this,id[0],id[1]);
        });
        //System.out.println(pq);
        //System.out.println(tq);
        System.out.println(" \n"+this.toString());
        endstep();
        System.out.println("after endstep"+this.toString());
    }
    public void endstep(){
        pq.clear();
        filter();
        ecurboard();
        curboard=tobeboard;
        etobeboard();
    }
    public void filter(){
        tq.stream().forEach((int[] id) -> {
            curboard[id[0]][id[1]]=new Cell(this,id[0],id[1]);
            
            
        });
    }
    public void ecurboard(){
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                curboard[w][h]=null; 
            }
        } 
    }
    public void etobeboard(){
        for (int w = 0;w<=width-1;w++){
            for (int h = 0; h<= height-1; h++){
                tobeboard[w][h]=null;
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
        for (int x = 0;x<=width-1;x++){
            for (int y = 0; y<= height-1; y++){
                if (curboard[x][y]==null)out +='0';
                else out += curboard[x][y].cons;
            }
            out += "\n";
        }
        return out;  
    } 
} 