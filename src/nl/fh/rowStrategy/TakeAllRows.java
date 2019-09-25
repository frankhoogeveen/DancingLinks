/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.rowStrategy;

import java.util.ArrayList;
import java.util.List;
import nl.fh.node.AbstractNode;
import nl.fh.node.ColHeaderNode;
import nl.fh.node.RowHeaderNode;

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
    public List<RowHeaderNode> determineRows(ColHeaderNode chosenColumn) {
        List<RowHeaderNode> result = new ArrayList<RowHeaderNode>();
        
        AbstractNode current = chosenColumn.getDown();
        while(current != chosenColumn){
            result.add((RowHeaderNode) current.findRow());
            current = current.getDown();
        }
        
        return result;
    }
    
}
