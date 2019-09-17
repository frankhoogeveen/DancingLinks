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
    
    private final Link tableHeader;

    public LinksTable(){
        tableHeader = new Link();
        tableHeader.col = tableHeader;
        tableHeader.row = tableHeader;
        tableHeader.up = tableHeader;
        tableHeader.down = tableHeader;
        tableHeader.left = tableHeader;
        tableHeader.right = tableHeader;
    }

    /**
     * 
     * @return the header link of this table
     */
    public Link getTableHeader() {
        return tableHeader;
    }
    
    /**
     * explicitly add a new empty row to the coincendence matrix
     * 
     * @return the row header of the new row
     */
    public Link addRow() {
        Link rowHeader = new Link();
         
        rowHeader.row = rowHeader;   
        rowHeader.col = tableHeader;
        
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
    public Link addCol() {
        
        Link colHeader = new Link();
        
        colHeader.row = tableHeader;
        colHeader.col = colHeader;
        
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
    public Link addLink(Link rowHeader, Link colHeader) {
        
        if(!rowHeader.isRowHeader() || !colHeader.isColumnHeader()){
            throw new IllegalArgumentException();
        }
        
        // if they are already linked, do nothing
        Link existing = rowHeader.getLinkInRow(colHeader);
        if(existing != null){
            return existing;
        }

        // other wise a new link has to be inserted at the right spot
        Link link = new Link();
        
        // find the left neighbour
        Link currentCol = colHeader;
        Link currentLink;
        do{
            currentCol = currentCol.left;
            currentLink =  currentCol.getLinkInColumn(rowHeader);
        } while(currentLink == null);
        currentLink.insertRight(link);
        
        //find the right neighbour
        currentCol = colHeader;
        do{
            currentCol = currentCol.right;
            currentLink =  currentCol.getLinkInColumn(rowHeader);
        } while(currentLink == null);
        
        //avoid duplicate insertion when adding the first link in the row
        if(currentLink.left != link){
            currentLink.insertLeft(link);
        }
        
        //find the top neighbour
        Link currentRow = rowHeader;
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

    /**
     * 
     * @param rowHeader
     * @param colHeader
     * @return true if the row and column, as defined by their headers, are linked
     *         otherwise false is returned
     */
    public boolean areLinked(Link rowHeader, Link colHeader) {
        
        if((rowHeader == null) || (colHeader == null)){
            return false;
        }
        
        // depending on the dimensions of the rows and columns,
        // it might be faster to use isLinkedInColumn
        return rowHeader.isLinkedInRow(colHeader);
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
        Set<Link> alreadyChecked = new HashSet<Link>();
        try {
            checkRecursively(tableHeader, n, alreadyChecked);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private void checkRecursively(Link link, long n,Set<Link> alreadyChecked) throws Exception {
        if(alreadyChecked.contains(link)){
            return;
        }
        
        link.checkReflexivity();
        link.checkIdempotency();
        link.checkCircularity(n);
        
        alreadyChecked.add(link);
        
        checkRecursively(tableHeader.up, n, alreadyChecked);
        checkRecursively(tableHeader.down, n, alreadyChecked);
        checkRecursively(tableHeader.left, n, alreadyChecked);
        checkRecursively(tableHeader.right, n, alreadyChecked);
    }
}
