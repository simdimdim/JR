/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thedoctor
 */
class Board{
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
    
