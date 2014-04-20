package jr;

import java.util.HashMap;
import java.util.Map;

class Main {
    boolean running=true;
    int height = 10;
    Map<Integer,Cell> process = new HashMap();
    static Map<Integer,Cell> tobe = new HashMap();
    public void run(){
    while (running){
        if (process.isEmpty()){
            process=tobe;
        }
        else{
            
        }
    }
    } 
}