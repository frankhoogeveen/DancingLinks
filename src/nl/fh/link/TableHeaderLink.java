/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.link;


/**
 *
 * Link that represents the table header
 * 
 * @author frank
 */
public class TableHeaderLink extends AbstractLink {

    @Override
    ColHeaderLink findColumn() {    
        throw new IllegalArgumentException();
    }

    @Override
    RowHeaderLink findRow() {
        throw new IllegalArgumentException();
    }
}
