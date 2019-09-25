/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;



/**
 *
 *  Link that represents the header of a column
 * 
 * @author frank
 */
public class ColHeaderNode extends AbstractNode implements ColHeader {

    @Override
    public ColHeader findColumn() {
        return this;
    }

    @Override
    public RowHeader findRow() {
        return TableHeaderNode.getInstance();
    }
    
}
