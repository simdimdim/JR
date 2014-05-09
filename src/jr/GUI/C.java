/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import javafx.css.PseudoClass;
import javafx.scene.layout.Region;
import jr.Coords;
import jr.Queue;

/**
 *
 * @author Plutonium_
 */
public class C extends Region {
    private static final String STYLECLASS = "grid-cell";
    
    private static final PseudoClass DEAD = 
            PseudoClass.getPseudoClass("dead");
    private static final PseudoClass ALIVE = 
            PseudoClass.getPseudoClass("alive");
   
    public C(int x, int y, Queue input){
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
        this.setOnMouseClicked(event -> {
            input.change(x,y);
            // add change of style on click
            // possible to add more conditions and wrap it up in a function later
            if (!getState()){setStyle(":alive");}
            else {setStyle(":dead");}
        });
        
        //size
        setMinSize(3, 3);
        setPrefSize(2000, 2000);
    }
    public C(Coords c, Queue input){
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
        this.setOnMouseClicked(event -> {
            input.change(c);
            // add change of style on click
            // possible to add more conditions and wrap it up in a function later
            if (!getState()){setStyle("alive");}
            else {setStyle("dead");}
        });
        
        //size
        setMinSize((1/c.x)*20, (1/c.y)*20);
        setPrefSize(2000*c.x, 2000*c.y);
    }
    
    public void setState(boolean val) {
        pseudoClassStateChanged(DEAD, !val); // reflect state by pseudoclass
        pseudoClassStateChanged(ALIVE, val);}
    public boolean getState() {
        return getPseudoClassStates().contains(ALIVE);}
    public void toggleState() {
        setState(!getState());}
}
