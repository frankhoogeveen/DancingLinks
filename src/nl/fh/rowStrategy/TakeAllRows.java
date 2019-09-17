/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.rowStrategy;

import nl.fh.link.Link;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frank
 */
public class TakeAllRows implements RowStrategy {

    /**
     *  strategy to return all rows matching a given column
     */
    public TakeAllRows() {
    }

    @Override
    public List<Link> determineRows(Link chosenColumn) {
        List<Link> result = new ArrayList<Link>();
        
        if(!chosenColumn.isColumnHeader()){
            throw new IllegalArgumentException();
        }
        
        Link current = chosenColumn.getDown();
        while(!current.isColumnHeader()){
            result.add(current.getRow());
            current = current.getDown();
        }
        
        return result;
    }
    
}
