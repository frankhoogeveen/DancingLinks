/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import nl.fh.solver.Node;
import java.util.HashSet;
import java.util.Set;
import nl.fh.solver.LinksSolver;

/**
 * The concern of this class is to translate a given Exact Match problem 
 * to a set of properly connected links
 * 
 * @author frank
 */
public class NodeTable {
    
    final Node tableHeader;
    private LinksSolver context;

    public NodeTable(){
        tableHeader = new Node();
        
        tableHeader.row = tableHeader;
        tableHeader.col = tableHeader;
        
        tableHeader.up = tableHeader;
        tableHeader.down = tableHeader;
        tableHeader.left = tableHeader;
        tableHeader.right = tableHeader;
        
        Node.setContext(this);
    }

    /**
     * 
     * @param context in which the links are used
     * 
     * The context determines the formatting of this link
     */
    void setContext(LinksSolver context) {
        this.context = context;
    }
    
    /**
     * explicitly add a new empty row to the coincendence matrix
     * 
     * @return the row header of the new row
     */
    public Node addRow() {
        Node rowHeader = new Node();
        
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
    public Node addCol() {
        
        Node colHeader = new Node();
        
        colHeader.row = tableHeader;
        colHeader.col = colHeader;
        
        colHeader.up = colHeader;
        colHeader.down = colHeader;
        
        tableHeader.insertLeft(colHeader);
        
        return colHeader;
    }

    /**
     * Add a newLink between a row and a column. 
     * 
     * Adding of a newLink is idempotent, twice adding a newLink has the same effect
     * as adding the newLink once.
     * 
     * @param rowHeader header of the row
     * @param colHeader header of the column
     * @return the newLink between the two 
     * 
     * Throws an exception if the arguments are not headers (in the proper order)
     */
    public Node addLink(Node rowHeader, Node colHeader) {
        
        if(!rowHeader.isRowHeader() || !colHeader.isColHeader()){
            throw new IllegalArgumentException("addLink not called with headers");
        }
        
        // if they are already linked, do nothing
        Node existing = connection(rowHeader, colHeader);
        if(existing != null){
            return existing;
        }

        // otherwise a new newLink has to be inserted at the right spot
        Node newLink = new Node();
        newLink.row = rowHeader;
        newLink.col = colHeader;
        
        // find the left neighbour
        Node currentColumn = colHeader;
        Node connectingLink;
        do{
            currentColumn =  currentColumn.left;
            connectingLink =  connection(rowHeader, currentColumn);
        } while(connectingLink == null);
        connectingLink.insertRight(newLink);
        
        //find the right neighbour
        currentColumn = colHeader;
        do{
            currentColumn = (Node) currentColumn.right;
            connectingLink =  connection(rowHeader, currentColumn);
        } while(connectingLink == null);
        
        //avoid duplicate insertion when adding the first newLink in the row
        if(connectingLink.left != newLink){
            connectingLink.insertLeft(newLink);
        }
        
        //find the top neighbour
        Node currentRow = rowHeader;
        do{
            currentRow = currentRow.up;
            connectingLink =  connection(currentRow, colHeader);
        } while(connectingLink == null);
        connectingLink.insertBelow(newLink);
        
        // find the bottom neighbour
        currentRow = rowHeader;
        do{
            currentRow =  currentRow.down;
            connectingLink =  connection( currentRow, colHeader);
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
     * 
     * It is assumed that the code calling this method ensures that
     * rowHeader and colHeader are headers
     */
    private Node connection(Node rowHeader, Node colHeader) {
        
        Node current = rowHeader;
        do{
            if(current.col == colHeader){
                return current;
            }
            current = current.right;
        }while(current != rowHeader);
        
        return null;
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
        Set<Node> alreadyChecked = new HashSet<Node>();
        try {
            checkRecursively(tableHeader, n, alreadyChecked);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private void checkRecursively(Node link, long n,Set<Node> alreadyChecked) throws Exception {
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

    /**
     * 
     * @param node
     * @return a node formatted using the context of this table
     */
    String format(Node node) {
         return this.context.format(node);
    }

    /**
     * 
     * @return  the header node of this table
     * From here all non-covered nodes can be reached
     */
    public Node getTableHeader() {
        return tableHeader;
    }
    
}
