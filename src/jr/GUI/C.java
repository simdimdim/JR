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
    private static final PseudoClass DEAD = PseudoClass.getPseudoClass("dead");
    private static final PseudoClass ALIVE = PseudoClass.getPseudoClass("alive");
    
    private final Coords pos;
    
    public C(Coords c){
        setVisible(true);
        pos = c;    // set final position
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
    }
    
    public void setCState(boolean val) {
        pseudoClassStateChanged(DEAD, !val); // reflect state by pseudoclass
        pseudoClassStateChanged(ALIVE, val);
    }
    public boolean getCState() {
        return getPseudoClassStates().contains(ALIVE);
    }
    
    public void toggleState() {
        setCState(!getCState());
    }
    
    public Coords getPos() {
        return pos;
    }
}
