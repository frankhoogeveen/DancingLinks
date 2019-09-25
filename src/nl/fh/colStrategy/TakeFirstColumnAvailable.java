/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.colStrategy;

import nl.fh.node.NodeTable;
import nl.fh.node.RowHeaderNode;

/**
 *
 * @author frank
 */
public class TakeFirstColumnAvailable implements ColStrategy {

    /**
     * strategy to take the first column that can be found in the table
     */
    public TakeFirstColumnAvailable() {
    }

    @Override
    public RowHeaderNode chooseColumn(NodeTable table) {
        
        if(table.hasNoVisibleColumns()){
            throw new IllegalArgumentException();
        }
        
        return (RowHeaderNode) table.getTableHeader().getRight();
    }
    
}
