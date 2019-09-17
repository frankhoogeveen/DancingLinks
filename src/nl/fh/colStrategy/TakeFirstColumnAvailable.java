/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.colStrategy;

import nl.fh.link.LinksTable;
import nl.fh.link.Link;

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
    public Link chooseColumn(LinksTable table) {
        
        if(table.hasNoVisibleColumns()){
            throw new IllegalArgumentException();
        }
        
        return table.getTableHeader().getRight();
    }
    
}
