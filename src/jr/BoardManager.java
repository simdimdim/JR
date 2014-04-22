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
public class BoardManager{
    boolean running;
    final CellMap cur;
    final CellMap next;
    
    /**  
     * Constructor. Specified size can be changed later. 
     * @param x height in number of cells 
     * @param y width in number of cells 
     */ 
    public BoardManager(int x, int y) {
        cur = new CellMap(x,y);
        next = new CellMap(x,y);
        running=true;
        cur.empty();
        next.empty();
    }
    public short neighbourCheck(int x, int y){
        short con=0;
        for (int w=x-1;w<=x+1;w++){
            for (int h=y-1;h<=y+1;h++){
                if (w<0||h<0) continue;
                if (w==x&&h==y) continue;
                if (cur.getState(w,h)) con++;
            }
        }
        return con;
    }
    public void endstep(){
        /*
        System.out.println("1cur size:"+cur.board.size());
        System.out.println("1board alive \n"+acheck(cur));
        System.out.println("1board connections \n"+ccheck(cur));
        System.out.println("1next alive \n"+acheck(next));
        System.out.println("1next connections \n"+ccheck(next));
        */
        cur.empty();
        cur.putAll(next);
        next.empty();
        update();
    }
    public void step(){
//        cur.board.values().stream().forEach((Cell cell)->{
//            //System.out.print("step : "+cell); //cells to be processed
//            if (cell.notdead()){}
//                next.put(cell);
//        });
        System.out.println(cur.toString());
        cur.board.values().stream()
                .flatMap((Cell t) -> t.getNeighbours().stream())    // get all potent locations
                .distinct()                                         // filter duplicates
                .forEach(c->{                                       // check
                    System.out.println(c.x+""+c.y);
                    // get neighbour count
                    c.connections=0;
                    for(Cell n: c.getNeighbours()) {
                        if(cur.board.containsKey(n.getLocation()))
                            c.connections++;
//                        System.out.println(n.x + " "+ n.y + " " + cur.board.containsKey(n.getLocation()));
                    }
                    
                    System.out.println("potential " + c.getLocation().x + " "+c.getLocation().y + " " + c.connections);
                    
                    // handle alive cells alive
                    if(cur.board.containsKey(c.getLocation()) && c.stayAlive())
                        next.board.put(c.getLocation(), new Cell(c.x, c.y));
                    else
                        if(c.connections==3)
                            next.board.put(c.getLocation(), new Cell(c.x, c.y));
                            
                });
        update();
        System.out.println(next.toString());
        endstep();
    }
    public void update(){
        cur.board.values().stream().forEach((Cell cell)->{
            cell.neighbours(neighbourCheck(cell.x,cell.y));
            if (cell.notdead())cell.alive=true; 
            //System.out.println(cell.getStateAsInt()); //check if updating
        });  
    }
    public String acheck(CellMap board){
       String out = "";
        //wait call here
        for (int w = 0;w<=board.width-1;w++){
            for (int h = 0; h<= board.height-1; h++){
                if (!board.contains(w,h))out +='0';
                else {
                    if (board.getState(w,h)) out += "1";
                    else out +='0';}  
            }
            out += "\n";
        }
        return out; 
    }
    public String ccheck(CellMap board){
        String out = "";
        for (int w = 0;w<=board.width-1;w++){
            for (int h = 0; h<= board.height-1; h++){
                if (board.contains(w,h))out += board.get(w,h).getStateAsInt();
                else out +="0";
            }
            out += "\n";}
        return out;  
    }
}