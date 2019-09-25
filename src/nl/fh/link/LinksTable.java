/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.link;

import java.util.HashSet;
import java.util.Set;

/**
 * The concern of this class is to translate a given Exact Match problem 
 * to a set of properly connected links.
 * 
 * @author frank
 */
public class LinksTable {
    
    private final TableHeaderLink tableHeader;

    public LinksTable(){
        tableHeader = new TableHeaderLink();
        tableHeader.up = tableHeader;
        tableHeader.down = tableHeader;
        tableHeader.left = tableHeader;
        tableHeader.right = tableHeader;
    }

    /**
     * 
     * @return the header link of this table
     */
    public TableHeaderLink getTableHeader() {
        return tableHeader;
    }
    
    /**
     * explicitly add a new empty row to the coincendence matrix
     * 
     * @return the row header of the new row
     */
    public RowHeaderLink addRow() {
        RowHeaderLink rowHeader = new RowHeaderLink();
        
        rowHeader.left = rowHeader;
        rowHeader.right = rowHeader;
    
        tableHeader.insertAbove(rowHeader);
        
        return rowHeader;
    }

    /**
     * explicitely add a new empty column to the coincendence matrix
     * 
     * @return a new column header
     */
    public ColHeaderLink addCol() {
        
        ColHeaderLink colHeader = new ColHeaderLink();
        
        colHeader.up = colHeader;
        colHeader.down = colHeader;
        
        tableHeader.insertLeft(colHeader);
        
        return colHeader;
    }

    /**
     * Add a link between a row and a column. 
     * 
     * Adding of a link is idempotent, twice adding a link has the same effect
     * as adding the link once.
     * 
     * @param rowHeader header of the row
     * @param colHeader header of the column
     * @return the link between the two 
     */
    public ConcreteLink addLink(RowHeaderLink rowHeader, ColHeaderLink colHeader) {
        
        // if they are already linked, do nothing
        ConcreteLink existing = connection(rowHeader, colHeader);
        if(existing != null){
            return existing;
        }

        // other wise a new link has to be inserted at the right spot
        ConcreteLink link = new ConcreteLink();
        
        // find the left neighbour
        AbstractLink current = colHeader;
        ConcreteLink currentLink;
        do{
            current = current.left;
            currentLink =  current.getLinkInColumn(rowHeader);
        } while(currentLink == null);
        currentLink.insertRight(link);
        
        //find the right neighbour
        current = colHeader;
        do{
            current = current.right;
            currentLink =  current.getLinkInColumn(rowHeader);
        } while(currentLink == null);
        
        //avoid duplicate insertion when adding the first link in the row
        if(currentLink.left != link){
            currentLink.insertLeft(link);
        }
        
        //find the top neighbour
        RowHeaderLink currentRow = rowHeader;
        do{
            currentRow = currentRow.up;
            currentLink =  currentRow.getLinkInRow(colHeader);
        } while(currentLink == null);
        currentLink.insertBelow(link);
        
        // find the bottom neighbour
        currentRow = rowHeader;
        do{
            currentRow = currentRow.down;
            currentLink =  currentRow.getLinkInRow(colHeader);
        } while(currentLink == null);
        
        //avoid duplicate insertion when adding the first link in the column
        if(currentLink.up != link){
            currentLink.insertAbove(link);
        }
        
        return link;
    }

    private ConcreteLink connection(RowHeaderLink rowHeader, ColHeaderLink colHeader) {
        AbstractLink current = rowHeader.right;
        while(current != rowHeader){
            ConcreteLink concrete = (ConcreteLink) current;
            if(concrete.findColumn() == colHeader){
                return concrete;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param rowHeader
     * @param colHeader
     * @return true if the row and column, as defined by their headers, are linked
     *         otherwise false is returned
     */
    public boolean areLinked(RowHeaderLink rowHeader, ColHeaderLink colHeader) {
        return connection(rowHeader, colHeader) != null;
    }

    /**
     * 
     * @return true if this table has no columns visible
     */
    public boolean hasNoVisibleColumns() {
        return (tableHeader.right == tableHeader);
    }
    
        /**
     * 
     * @return true if this table has no rows visible
     */
    public boolean hasNoVisibleRows() {
        return (tableHeader.down == tableHeader);
    }
    
    /**
     * checks that all links of the table are circular
     * @param n maximum number of steps checked for circularity
     * @return true if all links are circular
     */
    public boolean isEveryLinkGood(long n) {
        Set<AbstractLink> alreadyChecked = new HashSet<AbstractLink>();
        try {
            checkRecursively(tableHeader, n, alreadyChecked);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private void checkRecursively(AbstractLink link, long n,Set<AbstractLink> alreadyChecked) throws Exception {
        if(alreadyChecked.contains(link)){
            return;
        }
        
        link.checkReflexivity();
        link.checkCircularity(n);
        
        alreadyChecked.add(link);
        
        checkRecursively(link.up, n, alreadyChecked);
        checkRecursively(link.down, n, alreadyChecked);
        checkRecursively(link.left, n, alreadyChecked);
        checkRecursively(link.right, n, alreadyChecked);
    }
}
