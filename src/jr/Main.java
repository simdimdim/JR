package jr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Main {
    boolean running=true;
    int height = 10;
    int board=0;
    static Map<Integer,Board> all = new HashMap();
    
    public void run(){
        create();
        while (running){
            if (!all.get(board).process.isEmpty()){
                Iterator<Map.Entry<Integer,Cell>> iter = 
                        all.get(board).process.entrySet().iterator();
                while (iter.hasNext()&&running){
                    Map.Entry<Integer,Cell> entry = iter.next();
                    entry.getValue().spawn(height);
                }
            }
            else{
                all.get(board).process.clear();
                all.get(board).process.putAll(all.get(board).tobe);
            }
        }
    }
    private void create(){
        board=all.size(); //needs work (for deleting boards)
        all.put(board, new Board());
    }
}