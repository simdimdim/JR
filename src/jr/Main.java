package jr;

import java.util.ArrayList;
import java.util.List;

class Main {
    boolean running=true;
    int board=0;
    int height = 10;
    static List<Board> all = new ArrayList();
    public void run(){ 
        create(); 
        while (running){ 
             
            // use this instead of calling all.get[x] every time 
            Board b = all.get(board); 
                         
            // process cells 
            b.process.values().stream().forEach(cell -> { 
                cell.spawn(b.height); 
            }); 
             
            // prepare for next cycle 
            b.process.clear(); 
            b.process.putAll(b.tobe); 
 
            // stop simulation if no cells remain or eventual input
            if(b.process.isEmpty()) {
                break;}
        } 
    }
    private void create(){
        board=all.size()+1; //needs work (for deleting boards)
        all.add(new Board(50,50));
    }
}