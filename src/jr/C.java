/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import javafx.css.PseudoClass;
import javafx.scene.layout.Region;

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
    public C( int x, int y, Queue input ) {
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS);     // set css styleclass
        setState(false);                        // default to dead

        // On click
        setOnMouseClicked(event -> {
            setVisible(true);
            input.change(x, y);
            toggleState();
        });

        // cell sizes
        setMinSize(5, 5);
        setPrefSize(60, 60);
    }
    public C( Coords c, Queue input ) {
        setVisible(true);
        pseudoClassStateChanged(ALIVE, true);
        pseudoClassStateChanged(DEAD, false);
        getStyleClass().setAll(STYLECLASS); // set css styleclass
        setState(false);
        setOnMouseClicked(event -> {
            setVisible(true);
            input.change(c);
            toggleState();
        });

        //size
        setMinSize(4, 4);
        setPrefSize(2000, 2000);
    }
    public boolean getState() {
        return getPseudoClassStates().contains(ALIVE);
    }
    private void setState( boolean val ) {
        pseudoClassStateChanged(DEAD, !val); // reflect state by pseudoclass
        pseudoClassStateChanged(ALIVE, val);
    }
    public final void toggleState() {
        setState(!getState());
    }
}
