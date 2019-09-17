/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.rowStrategy;

import nl.fh.link.Link;
import java.util.List;

/**
 *
 * @author frank
 */
public interface RowStrategy {

    /**
     * 
     * @param chosenColumn a column header
     * @return a list of rows that connect to the chosen column 
     */
    public List<Link> determineRows(Link chosenColumn);
    
}
