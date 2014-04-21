/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
 
package jr; 
 
/** 
 * Area for the Cells. 
 * 
 * @author thedoctor 
 */ 
class Board{ 
     
    final int width ; 
    final int height; 
    Cell[][] process; 
    Cell[][] tobe; 

    /**  
     * Constructor. Specified size can be changed later. 
     * @param H height in number of cells 
     * @param W width in number of cells 
     */ 
    public Board() {
        width = 20; 
        height = 20; 
        this.tobe = new Cell[width][height];
        this.process = new Cell[width][height];  
    } 
    public Board(int x, int y) { 
        this.tobe = new Cell[x][y];
        this.process = new Cell[x][y];
        width = x; 
        height = y; 
    }
    public void extendandremap(int x,int y,int extend){
        Cell[][] newtobe = new Cell[width+2*x][height+2*y];
        Cell[][] newprocess = new Cell[width+2*x][height+2*y];
        for (int w = 0;w<=width;w++){
            for (int h = 0; h<= height; h++){
                newtobe[width+x][height+y]=tobe[width][height];
                newprocess[width+x][height+y]=tobe[width][height];
            }
        }
        process=newprocess;
        tobe=newtobe;       
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
            //wait call here
            for (int w = 0;w<=width;w++){
                for (int h = 0; h<= height; h++){
                    if(process[w][h].alive){out +='1';}
                    else{out +='0';}
                }
                out += "\n";
            }
            return out;  
        } 
    } 