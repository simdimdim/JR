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
            System.out.println(b.acheck(b.cur));
            System.out.println(b.ccheck(b.cur));
            System.out.println(b.cur.toString());
            System.out.println(b.cur.getPoint(2, 2));
            System.out.println("step :"+s);
            b.step();
            
            
            // stop simulation if no cells remain or eventual input
            if(b.next.isEmpty()) {
                System.out.println("Out of cells");
                break;
            }
        } 
    }
    static public void create(int width,int height){
        board=all.size(); //needs work (for deleting boards)
        all.add(new BoardManager(width,height));
        
        BoardManager b = all.get(board);
        //Add some cells
        b.cur.put(new Cell(2,1));
        System.out.println(b.cur.size());
        b.cur.put(new Cell(2,2));
        System.out.println(b.cur.size());
        b.cur.put(new Cell(2,3));
        System.out.println(b.cur.size());
        b.update();
        run();
    }
    public static void main(String[] args) {
        Main.create(5,5);
    }
}