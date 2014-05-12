/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import jr.GUI.Main;

/**
 *
 * @author thedoctor
 */
public class Controls {

static void step( int n, Queue queue ) {
    if ( Main.boards.containsKey(n) ) {
        Board board = Main.boards.get(n);
        //if (board.running){
        board.step();
        //}
    }
}
public Coords getSize( int n ) {
    return new Coords(Main.boards.get(n).size);
}
/**
 * Creates a new board.
 *
 * @param width width of the new board
 * @param height height of the new board
 * @return the created board.
 */
static public Board create( int width, int height ) {
    // make board
    int id = Main.generateNewBoardId();
    Board board = new Board(width, height, id);
    Main.addBoard(id, board);
    return board;
}
}
