/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import javafx.scene.control.Button;
import jr.Coords;

/**
 *
 * @author thedoctor
 */
public class GCell extends Button{
    Coords location;
    GCell (int x, int y){
        location.x=x;
        location.y=y;
    }
    public void change(){
        //add remove itself from the queue of cells to be changed
    }
}
