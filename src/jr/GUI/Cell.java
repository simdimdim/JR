/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import jr.Coords;

/**
 *
 * @author thedoctor
 */
public class Cell extends Coords{
    Cell (int x, int y){
        this.x=x;
        this.y=y;
    }
    public void change(){
        //add remove itself from the queue of cells to be changed
    }
}