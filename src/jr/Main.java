package jr;

import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    static int board=0;
    static List<BoardManger> all = new ArrayList();
    
    static public void run(){
        for (int s=0; s<4; s++) { 
            // use this instead of calling all.get[x] every time 
            BoardManger b = all.get(board);
            System.out.println(b.cur.queue);
            System.out.println(b.next.queue);
            // curboard cells 
            b.step();
            System.out.println("step "+s);
            
            // stop simulation if no cells remain or eventual input
            if(b.next.queue.isEmpty()) {
                /*
                System.out.println(b.toString());
                System.out.print("curboard \n");
                System.out.println(b.curboard);
                System.out.print("cur.queue \n");
                System.out.println(b.cur.queue);
                System.out.print("tobeboard \n ");
                System.out.println(b.tobeboard);
                System.out.println("next.queue \n "+b.next.queue);
                */
                break;
            }
        } 
    }
    static public void create(int width,int height){
        board=all.size(); //needs work (for deleting boards)
        all.add(new BoardManger(width,height));
        
        BoardManger b = all.get(board); 
        b.cur.queue.add(new Cell(b,2,2).add(b));
        b.cur.get(2,2).iter();
        b.cur.get(2,2).alive=true;
        b.cur.queue.add(new Cell(b,2,3).add(b));
        b.cur.get(2,3).iter();
        b.cur.get(2,3).alive=true;
        b.cur.queue.add(new Cell(b,2,1).add(b));
        b.cur.get(2,1).iter();
        b.cur.get(2,1).alive=true;
        //b.cur.queue.add(new Cell(b,3,3).add(b));
        //b.curboard[3][3].iter();
        //b.curboard[3][3].alive=true;
        run();
    }
    public static void main(String[] args) {
        Main.create(5,5);
    }
}