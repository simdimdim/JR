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
    public boolean state;
    public C(int x, int y){
        setVisible(true);
        state = true;
        getStyleClass().setAll(STYLECLASS); // set css styleclass
    }
    public C(Coords c){
        setVisible(true);
        state = true;
        getStyleClass().setAll(STYLECLASS); // set css styleclass
    }
    
    public void setState(boolean val) {
        state=val;}
    public boolean getState() {
        return state;}
    public void toggleState() {
        setState(!getState());}
}
