/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;

import java.util.HashSet;
import java.util.Set;

/**
 * The concern of this class is to translate a given Exact Match problem 
 * to a set of properly connected links.
 * 
 * @author frank
 */
public class NodeTable {
    
    private final TableHeaderNode tableHeader;

    public NodeTable(){
        TableHeaderNode.reset();
        tableHeader = TableHeaderNode.getInstance();
    }

    /**
     * 
     * @return the header newLink of this table
     */
    public TableHeaderNode getTableHeader() {
        return tableHeader;
    }
    
    /**
     * explicitly add a new empty row to the coincendence matrix
     * 
     * @return the row header of the new row
     */
    public RowHeaderNode addRow() {
        RowHeaderNode rowHeader = new RowHeaderNode();
        
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
    public ColHeaderNode addCol() {
        
        ColHeaderNode colHeader = new ColHeaderNode();
        
        colHeader.up = colHeader;
        colHeader.down = colHeader;
        
        tableHeader.insertLeft(colHeader);
        
        return colHeader;
    }

    /**
     * Add a newLink between a row and a column. 
     * 
     * Adding of a newLink is idempotent, twice adding a newLink has the same effect
 as adding the newLink once.
     * 
     * @param rowHeader header of the row
     * @param colHeader header of the column
     * @return the newLink between the two 
     */
    public LinkNode addLink(RowHeaderNode rowHeader, ColHeaderNode colHeader) {
        
        // if they are already linked, do nothing
        AbstractNode existing = connection(rowHeader, colHeader);
        if(existing != null){
            return (LinkNode) existing;
        }

        // otherwise a new newLink has to be inserted at the right spot
        LinkNode newLink = new LinkNode();
        
        // find the left neighbour
        AbstractNode currentColumn = colHeader;
        AbstractNode connectingLink;
        do{
            currentColumn =  currentColumn.left;
            connectingLink =  connection(rowHeader, (ColHeader) currentColumn);
        } while(connectingLink == null);
        connectingLink.insertRight(newLink);
        
        //find the right neighbour
        currentColumn = colHeader;
        do{
            currentColumn = (AbstractNode) currentColumn.right;
            connectingLink =  connection(rowHeader, (ColHeader) currentColumn);
        } while(connectingLink == null);
        
        //avoid duplicate insertion when adding the first newLink in the row
        if(connectingLink.left != newLink){
            connectingLink.insertLeft(newLink);
        }
        
        //find the top neighbour
        AbstractNode currentRow = rowHeader;
        do{
            currentRow = currentRow.up;
            connectingLink =  connection((RowHeader) currentRow, colHeader);
        } while(connectingLink == null);
        connectingLink.insertBelow(newLink);
        
        // find the bottom neighbour
        currentRow = rowHeader;
        do{
            currentRow =  currentRow.down;
            connectingLink =  connection((RowHeader) currentRow, colHeader);
        } while(connectingLink == null);
        
        //avoid duplicate insertion when adding the first newLink in the column
        if(connectingLink.up != newLink){
            connectingLink.insertAbove(newLink);
        }
        
        return newLink;
    }

    /**
     * 
     * @param rowHeader
     * @param colHeader
     * @return  the node linking row and column
     * returns null, if there is no link
     */
    private AbstractNode connection(RowHeader rowHeader, ColHeader colHeader) {
        
        AbstractNode current = (AbstractNode) rowHeader;
        do{
            if(current.findColumn() == colHeader){
                return current;
            }
            current = current.getRight();
        }while(current != rowHeader);
        
        return null;
    }
    
    /**
     * 1
     * @param rowHeader
     * @param colHeader
     * @return true if the row and column, as defined by their headers, are linked
     *         otherwise false is returned
     */
    boolean areLinked(RowHeaderNode rowHeader, ColHeaderNode colHeader) {
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
        Set<AbstractNode> alreadyChecked = new HashSet<AbstractNode>();
        try {
            checkRecursively(tableHeader, n, alreadyChecked);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private void checkRecursively(AbstractNode link, long n,Set<AbstractNode> alreadyChecked) throws Exception {
        if(alreadyChecked.contains(link)){
            return;
        }
        
        link.checkNode(n);
        
        alreadyChecked.add(link);
        
        checkRecursively(link.up, n, alreadyChecked);
        checkRecursively(link.down, n, alreadyChecked);
        checkRecursively(link.left, n, alreadyChecked);
        checkRecursively(link.right, n, alreadyChecked);
    }
}
