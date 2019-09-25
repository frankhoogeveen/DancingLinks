/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.link;



/**
 *
 *  Link that represents the header of a column
 * 
 * @author frank
 */
public class ColHeaderLink extends AbstractLink {

    @Override
    ColHeaderLink findColumn() {
        return this;
    }

    @Override
    RowHeaderLink findRow() {
        return this.up.findRow();
    }
    
}
