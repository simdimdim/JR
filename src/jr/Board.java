/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 
 
import java.util.HashMap; 
import java.util.Map; 
import static jr.Main.all;
 
/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
class Board{ 
     
    int width = 50; 
    int height = 50; 
       final Map<Integer,Cell> process = new HashMap(); 
       final Map<Integer,Cell> tobe = new HashMap(); 
        
       /**  
        * Constructor. Specified size can be changed later. 
        * @param H height in number of cells 
        * @param W width in number of cells 
        */ 
       public Board(int H, int W) { 
           width = W; 
           height = H; 
       } 
        
       /**  
     * Prints the state of the board into human sensible String output.   
     * <p>  
     * Use for debug.  
     * @return string representation of the board  
     */  
        @Override  
        public String toString() {
            String out = "";
            process.values().stream().forEach(cell -> { 
                //to finish 
            });  
                out += "\n";   
            return out;  
        } 
    } 