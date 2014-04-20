package jr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Main {
    boolean running=true;
    int height = 10;
    int board=0;
    static Map<Integer,Board> all = new HashMap();
    
    private class Board{
       final Map<Integer,Cell> process = new HashMap();
       final Map<Integer,Cell> tobe = new HashMap(); 
       /** 
     * Prints the state of the board into human sensible String output.  
     * <p> 
     * Use for debug. 
     * @return string representation of the board 
     */ 
        @Override 
        public String toString() { 
            String out; 
            for(int y=1;y<=height;y++) { 
                for(x=1;x<=width; x++) { 
                    out += process.get(Cell.generateHash(x,y)).stateAsInt()+" "; 
                } 
                out += "\n"; 
            } 
            return out; 
        }
    }
    
    public void run(){
        create();
        while (running){
            if (!process.isEmpty()){
                Iterator<Map.Entry<Integer,Cell>> iter = process.entrySet().iterator();
                while (iter.hasNext()&&running){
                    Map.Entry<Integer,Cell> entry = iter.next();
                    entry.getValue().spawn(height);
                }
            }
            else{
                process=tobe;
            }
        }
    }
    private void create(){
        board=all.size(); //needs work (for deleting boards)
        all.put(board, new Board());
    }
}