package jr;

import java.awt.Point;
import java.util.HashMap;

class Main {
    static boolean running=true;
    static HashMap<Integer,Board> all = new HashMap<>();
    
    static public void step(){
        running=false;
        for (Board board : all.values()){
            if (board.running){
                //main operations
                //Gets list of all mates
                board.cur.board.values().stream()
                        .flatMap(cell->cell.getNeighbours().stream())
                        .distinct()
                        .forEach((Point p)->{
                            
                        //forEach mate if alive adds to next 
                            if(board.cur.check(p)==2&&board.cur.contains(p)){
                                board.next.put(p);}
                            else{
                                if (board.cur.check(p)==3) {
                                    board.next.put(p);}
                            }
                        });
                
                // stop simulation if no cells remain
                if(board.cur.board.isEmpty()) {
                    board.running=false;
                    continue;}
            }
            System.out.print("board"+board.cur.acheck());
            board.cur.empty();
            board.cur.putAll(board.next);
            board.next.empty();
        }
    }
    static public void create(int width,int height){
        int n=all.size();
        all.put(n,new Board(width,height,n));
        Board b = all.get(n);
        b.cur.add(1,1);
        b.cur.add(1,2);
        b.cur.add(2,1);
        b.cur.add(2,2);
    }
    public static void main(String[] args) {
        create(5,5);
        while (running) {
            step();}
    }
}