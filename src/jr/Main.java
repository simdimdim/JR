package jr;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    static int board=0;
    static List<BoardManager> all = new ArrayList();
    
    static public void run(){
        for (int s=0; s<4; s++) {
            System.out.println("Step:"+s);
            BoardManager b = all.get(board);

            //main operations
            //Gets list of all mates
            b.cur.board.values().stream().flatMap(cell-> //Gets list of all mates
                cell.getNeighbours().stream()).distinct().forEach((Point p)->{
                    
                    //forEach mate if alive adds to next 
                    if(b.check(p)==2&&b.cur.board.containsKey(p)){b.next.put(p);}
                    else{
                        if (b.check(p)==3) {
                            b.next.put(p);}
                    }
                });
            System.out.println("board alive \n"+b.ccheck(b.cur));
            //System.out.println("next mates \n"+b.ccheck(b.next));
            
            // stop simulation if no cells remain or eventual input
            if(b.cur.board.isEmpty()) {
                System.out.println(b.next.board.size());
                System.out.println("Out of cells at step:"+s);
                break;
            }
            b.cur.empty();
            b.cur.putAll(b.next);
            b.next.empty();
        } 
    }
    static public void create(int width,int height){
        board=all.size(); //needs work (for deleting boards)
        all.add(new BoardManager(width,height));
        
        BoardManager b = all.get(board);
        
        //Add some cells
        b.cur.put(new Cell(1,1));
        b.cur.put(new Cell(1,2));
        b.cur.put(new Cell(2,1));
        b.cur.put(new Cell(2,2));
    }
    public static void main(String[] args) {
        Main.create(5,5);
        run();
    }
}