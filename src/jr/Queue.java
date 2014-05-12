/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jr;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author thedoctor
 */
public class Queue {

Set<Cell> queue;
public Queue() {
    queue = new HashSet();
}
public void empty() {
    queue.clear();
}
public void change( int x, int y ) {
    if ( contains(x, y) ) {
        remove(x, y);
    }
    else {
        add(x, y);
    }
}
public void change( Cell c ) {
    if ( contains(c) ) {
        remove(c);
    }
    else {
        add(c);
    }
}
public void add( int x, int y ) {
    queue.add(new Cell(x, y));
}
public void add( Cell c ) {
    queue.add(c);
}
public void remove( int x, int y ) {
    queue.remove(new Coords(x, y));
}
public void remove( Cell c ) {
    queue.remove(c);
}
public boolean contains( int x, int y ) {
    return queue.contains(new Coords(x, y));
}
public boolean contains( Cell c ) {
    return queue.contains(c);
}
public Set<Cell> get() {
    return queue;
}
}
