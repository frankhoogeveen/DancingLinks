/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;



/**
 *
 *  Link that represents the header of a row
 * 
 * @author frank
 */
public class RowHeaderNode extends AbstractNode implements RowHeader {

    @Override
    public ColHeader findColumn() {
        return TableHeaderNode.getInstance();
    }

    @Override
    public RowHeader findRow() {
        return this;
    }
}
