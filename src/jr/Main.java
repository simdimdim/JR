package jr;

import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    static int board=0;
    static List<Board> all = new ArrayList();
    
    static public void run(){
        for (int s=0; s<4; s++) { 
            // use this instead of calling all.get[x] every time 
            Board b = all.get(board);
            System.out.println(b.pq);
            System.out.println(b.tq);
            // curboard cells 
            b.step();
            
            // stop simulation if no cells remain or eventual input
            if(b.tq.isEmpty()) {
                /*
                System.out.println(b.toString());
                System.out.print("curboard \n");
                System.out.println(b.curboard);
                System.out.print("pq \n");
                System.out.println(b.pq);
                System.out.print("tobeboard \n ");
                System.out.println(b.tobeboard);
                System.out.println("tq \n "+b.tq);
                */
                System.out.println("step "+s);
                break;
            }
        } 
    }
    static public void create(int width,int height){
        board=all.size(); //needs work (for deleting boards)
        all.add(new Board(width,height));
        
        Board b = all.get(board); 
        b.pq.add(new Cell(b,2,1).add(b));
        b.curboard[2][1].iter();
        b.pq.add(new Cell(b,2,2).add(b));
        b.curboard[2][2].iter();
        b.pq.add(new Cell(b,1,1).add(b));
        b.curboard[1][1].iter();
        b.pq.add(new Cell(b,1,2).add(b));
        b.curboard[1][2].iter();
        run();
    }
    public static void main(String[] args) {
        Main.create(6,6);
    }
}