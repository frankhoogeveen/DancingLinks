/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.link;



/**
 *
 *  Link that represents the connection between a row and a column
 * 
 * @author frank
 */
public class ConcreteLink extends AbstractLink {

    @Override
    ColHeaderLink findColumn() {
        return this.up.findColumn();
    }

    @Override
    RowHeaderLink findRow() {
        return this.left.findRow();
    }
 
}
