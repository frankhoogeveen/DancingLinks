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
public interface ColStrategy {
    /**
     * 
     * @param table
     * @return the next column that will be considered
     */
    public RowHeaderNode chooseColumn(NodeTable table);
    
}
