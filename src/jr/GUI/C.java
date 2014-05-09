/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr.GUI;

import javafx.css.PseudoClass;
import javafx.scene.layout.Region;
import jr.Coords;

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
    
    public C(int x, int y){
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
        this.setOnMouseClicked(event -> {
            //input.change(x,y);
            // add change of style on click
            // possible to add more conditions and wrap it up in a function later
            if (!getState()){setStyle("alive");}
            else {setStyle("dead");}
        });
    }
    public C(Coords c){
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
    }
    
    public void setState(boolean val) {
        pseudoClassStateChanged(DEAD, !val); // reflect state by pseudoclass
        pseudoClassStateChanged(ALIVE, val);}
    public boolean getState() {
        return getPseudoClassStates().contains(ALIVE);}
    public void toggleState() {
        setState(!getState());}
}
