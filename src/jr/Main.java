package jr;

import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    static int board=0;
    static List<BoardManager> all = new ArrayList();
    
    static public void run(){
        for (int s=0; s<4; s++) { 
            // use this instead of calling all.get[x] every time 
            BoardManager b = all.get(board);
            
            // curboard cells 
            /*
            System.out.println("cur size:"+b.cur.size());
            System.out.println(b.acheck(b.cur));
            System.out.println(b.ccheck(b.cur));
            System.out.println(b.cur.toString());
            System.out.println(b.cur.getPoint(2, 2));
            System.out.println("step :"+s);
            */

            b.step();
            
            // stop simulation if no cells remain or eventual input
            if(b.next.board.isEmpty()) {
                System.out.println(b.next.board.size());
                System.out.println("Out of cells at step:"+s);
                break;
            }
        } 
    }
    static public void create(int width,int height){
        board=all.size(); //needs work (for deleting boards)
        all.add(new BoardManager(width,height));
        
        BoardManager b = all.get(board);
        //Add some cells
        b.cur.put(new Cell(1,2));
        b.cur.put(new Cell(2,2));
        b.cur.put(new Cell(3,2));
        b.next.put(new Cell(1,1));
        b.next.put(new Cell(1,2));
        b.next.put(new Cell(1,3));
        b.update();
        run();
    }
    public static void main(String[] args) {
        Main.create(5,5);
    }
}