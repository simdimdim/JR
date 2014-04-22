/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jr;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author thedoctor
 */
public class Queue extends ArrayList{
    List<Point> queue;
    Queue(){
        queue = new ArrayList();
    }
    public void get(){
        //
    }
    @Override
    public String toString(){
        Iterator<Point> it = iterator();
        if (! it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            Point e = it.next();
            sb.append("x="+e.x+"y="+e.y);
            if (! it.hasNext())
                return sb.append(']').toString();
            else sb.append("\n");
            //sb.append(',').append(' ');
        }
    }
}
