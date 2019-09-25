/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;



/**
 *
 *  Link that represents the connection between a row and a column
 * 
 * @author frank
 */
public class LinkNode extends AbstractNode {
    
    RowHeaderNode row;
    ColHeaderNode col;
    
    @Override
    ColHeader findColumn() {        
        return this.up.findColumn();
    }

    @Override
    RowHeader findRow() {
        return this.left.findRow();
    }
 
}
