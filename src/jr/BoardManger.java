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
public class BoardManger{
    boolean running;
    Board cur;
    Board next;
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param x height in number of cells 
     * @param y width in number of cells 
     */ 
    public BoardManger(int x, int y) {
        cur = new Board(x,y);
        next = new Board(x,y);
        running=true;
        cur.empty();
        next.empty();
    }

    public void check(int x,int y){
        short cons=0;
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                if (w<0||h<0) continue;
                if (w==x&&h==y) continue;
                if (cur.get(w,h)!=null) cons++;
            }
        }
        cur.board[x][y]=new Cell(this,x,y);
        cur.board[x][y].cons=cons;
        if ((cons==2||cons==3)&&cur.get(x,y)!=null)
            cur.board[x][y].alive=true;
    }
    
    public void step(){
        
//        Needs a way to check the queue for Point X
//        class Queue needs to be be altered 
//        for the above condition to be met
        
        cur.queue.stream().forEach((cor)->{
            System.out.print(cor.toString());
            /*
            if (!next.queue.contains(cor)){
                for (int x=cor.x-1;x<=cor.x+1;x++){
                    for (int y=cor.y-1;y<=cor.y+1;y++){
                        if (x<0||y<0) continue;
                        if (cur.get(x,y)!=null) check(x,y);
                        next.queue.add(cor);
                        if (x==cor.x&&y==cor.y) 
                            cur.board[x][y]=cur.get(x,y);
                    }
                }
            }*/
        });
        //System.out.println(queue.cur);
        //System.out.println(next.queue);
        endstep();
        //System.out.println("after endstep \n"+next.ccheck());
    }
    public void endstep(){
        cur.queue.clear();
        filter();
        //System.out.println("acheck \n"+cur.acheck());
        //System.out.println("ccheck \n"+cur.acheck());
        cur.put(next);
        next.empty();
    }
    public void filter(){
        //System.out.print("next.queue \n");
        //System.out.println(next.queue.get(0).x+" "+next.queue.get(0).y);
        next.queue.stream().forEach(cor -> {
            /*
            cur.board[cor.x][cor.y]=
                    cur.board[cor.x][cor.y].check(this,cor.x,cor.y);
            if (!cur.queue.contains(cor)&&curboard[cor.x][cor.y].alive) cur.queue.add(cor);
           */ 
        });
        //System.out.print("queue.cur \n");
        //System.out.println(cur.queue.toString());
        next.queue.clear();
    }
}