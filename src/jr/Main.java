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

            //main operations
            b.step();
            
            // stop simulation if no cells remain or eventual input
            if(b.cur.board.isEmpty()) {
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
        short m=1;
        short s=2;
        //b.cur.put(new Cell(1,1).live());
        b.cur.put(new Cell(2,3).live().nighbs(m));
        b.cur.put(new Cell(2,1).live().nighbs(m));
        b.cur.put(new Cell(2,2).live().nighbs(s));
    }
    public static void main(String[] args) {
        Main.create(5,5);
        run();
    }
}