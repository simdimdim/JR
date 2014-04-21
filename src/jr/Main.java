package jr;

import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    int board=0;
    int height = 20;
    int width = 20;
    static List<Board> all = new ArrayList();
    public void run(){ 
        create(); 
        for (int s=0; s<4; s++) { 
            // use this instead of calling all.get[x] every time 
            Board b = all.get(board); 
                         
            // process cells 
            b.step(); 

            // stop simulation if no cells remain or eventual input
            if(b.pq.isEmpty()) {
                break;
            }
        } 
    }
    private void create(){
        board=all.size()+1; //needs work (for deleting boards)
        all.add(new Board(width,height));
    }
}