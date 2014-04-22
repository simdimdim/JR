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
    int width;
    int height;
    boolean running;
    Cell[][] tobeboard;
    Cell[][] curboard;
    List<int[]> pq = new ArrayList(); //to be curboarded
    List<int[]> tq = new ArrayList(); //for the next step
    
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
    
    public void check(int x,int y){
        short cons=0;
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                if (w<0||h<0) continue;
                if (w==x&&h==y) continue;
                if (curboard[w][h]!=null) cons++;
            }
        }
        tobeboard[x][y]=new Cell(this,x,y);
        tobeboard[x][y].cons=cons;
        if ((cons==2||cons==3)&&curboard[x][y]!=null)
            tobeboard[x][y].alive=true;
    }
    
    public void step(){
        pq.stream().forEach((int[] xy) -> {
            if (!tq.contains(xy)){
                for (int x=xy[0]-1;x<=xy[0]+1;x++){
                    for (int y=xy[1]-1;y<=xy[1]+1;y++){
                        if (x<0||y<0) continue;
                        if (curboard[x][y]!=null) check(x,y);
                        tq.add(xy);
                        if (x==xy[0]&&y==xy[1]) 
                            tobeboard[x][y]=curboard[x][y];
                    }
                }
            }
        });
        //System.out.println(pq);
        //System.out.println(tq);
        endstep();
        System.out.println("after endstep \n"+this.toString());
    }
    public void endstep(){
        pq.clear();
        filter();
        System.out.println(" \n"+this.toString());
        System.out.println("tobeboard \n"+this.tobe());
        ecurboard();
        curboard=tobeboard;
        etobeboard();
    }
    public void filter(){
        System.out.print("tq \n");
        System.out.println(tq.get(0)[0]+" "+tq.get(0)[1]);
        tq.stream().forEach((int[] id) -> {
            tobeboard[id[0]][id[1]]=
                    tobeboard[id[0]][id[1]].check(this,id[0],id[1]);
            if (!pq.contains(id)&&curboard[id[0]][id[1]].alive) pq.add(id);
            
        });
        System.out.print("pq \n");
        System.out.println(pq);
        tq.clear();
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
                //else out += "1";
                else out += curboard[x][y].cons;
            }
            out += "\n";
        }
        return out;  
    }
    public String tobe(){
       String out = "";
        //wait call here
        for (int x = 0;x<=width-1;x++){
            for (int y = 0; y<= height-1; y++){
                if (tobeboard[x][y]==null)out +='0';
                /*else {
                    if (tobeboard[x][y].alive) {
                        out += "1";}
                    else {
                        out +='0';}
                }  */ 
                else out += tobeboard[x][y].cons;
            }
            out += "\n";
        }
        return out; 
    }
} 
