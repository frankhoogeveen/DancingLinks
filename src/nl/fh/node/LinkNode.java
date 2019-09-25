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
    
    @Override
    public ColHeader findColumn() {        
        return this.up.findColumn();
    }

    @Override
    public RowHeader findRow() {
        return this.left.findRow();
    }
 
}
