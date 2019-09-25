/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import nl.fh.colStrategy.ColStrategy;
import nl.fh.node.AbstractNode;
import nl.fh.link.Link;
import nl.fh.node.NodeTable;
import nl.fh.rowStrategy.RowStrategy;
import nl.fh.solutionProcessor.SolutionProcessor;

/**
 *
 *  context in which a dancing links problem is set up
 * 
 * @author frank
 * @param <R> type of row objects
 * @param <C> type of column objects
 */
public class LinksSolver<R, C> {

    private final LinksMapper<R, C> mapper;
    private final NodeTable table;
    private final ColStrategy colStrategy;
    private final RowStrategy rowStrategy;
    private final SolutionProcessor solutionProcessor;
    private final Stack<Link> currentPartialSolution;

    /**
     *
     * @param cs column strategy
     * @param rs row strategy
     * @param proc solution processor
     */
    public LinksSolver(ColStrategy cs, RowStrategy rs, SolutionProcessor proc) {
        this.mapper = new LinksMapper<R, C>();
        this.table = new NodeTable();
        this.currentPartialSolution = new Stack<Link>();

        this.colStrategy = cs;
        this.rowStrategy = rs;
        this.solutionProcessor = proc;
        
    }

    /**
     *  Add a link between a row and a column object
     * 
     * @param row
     * @param col 
     */
    public void addLink(R row , C col) {
        // create the header links if they do not yet exist
        Link rowHeader = mapper.getRowHeader(row);
        if(rowHeader == null){
            rowHeader = table.addRow();
            mapper.addRow(row, rowHeader);
        }
        
        Link colHeader = mapper.getColHeader(col);
        if(colHeader == null){
            colHeader = table.addCol();
            mapper.addCol(col, colHeader);
        }
        
        // add the link to the column
        table.addLink(rowHeader, colHeader);
    }
    
     /**
     * 
     * Solve a dancing links exact matching problem
     * 
     */
    public void solve(){
        
        // if we have enough solutions (or time has run out), return
        if(solutionProcessor.isSatisfied()){
            return;
        }
        
        // reached a solution
        if(table.hasNoVisibleColumns()){
            Set<Link> solutionSet= new HashSet(this.currentPartialSolution);
            solutionProcessor.process(solutionSet);
            return;
        }
        
        // reached a dead end
        if(table.hasNoVisibleRows()){
           return;
        }      
        
        // no conclusion yet, reduce the table and continue the search
        Link chosenColumn = this.colStrategy.chooseColumn(table);
        List<Link> rowList = this.rowStrategy.determineRows(chosenColumn);
        Stack<Link> hiddenRowsCols = new Stack<Link>();
        
        for(Link currentRow : rowList){
            currentPartialSolution.push(currentRow);
            
            hideRowsAndColumns(currentRow, hiddenRowsCols);
            solve();
            restoreRowsAndColumns(hiddenRowsCols);
            
            currentPartialSolution.pop();
        }
        
    }

    /**
     * 
     * @param link 
     * Hide all rows that overlap with the link and all columns 
     * where this overlap takes place
     */
    void hideRowsAndColumns(Link link, Stack<Link> hiddenRowsCols) {
        Link current = link.getRow().getRight();
        while(!current.isRowHeader()){
            Link next = current.getRight();
            hideOverlappeningRows(current, hiddenRowsCols);
            current.hideColumn();
            hiddenRowsCols.push(current);
            current = next;
        }
    }
    
    void hideOverlappeningRows(Link current, Stack<Link> hiddenRowsCols) {
        Link current2 = current.getCol().getDown();
        while(!current2.isColumnHeader()){
            Link next = current2.getDown();
            current2.hideRow();
            hiddenRowsCols.push(current2);
            current2 = next;
        }
    }

    void restoreRowsAndColumns(Stack<Link> hiddenRowsCols) {
        while(!hiddenRowsCols.empty()){
            Link current = hiddenRowsCols.pop();
            if(current.isRowHeader()){
                current.restoreRow();
            } else if(current.isColumnHeader()){
                current.restoreColumn();
            } else {
                System.err.println("illegal state of the link stack");
                System.exit(-2);
            }
        }
    }
    
    @Override
    public String toString(){
        return this.mapper.longDescriptionOf(this.table);
    }
    
    /** 
     * 
     * @param link
     * @return the link formatted using the embedded mapper
     */
    public String format(AbstractNode link){
        return this.mapper.shortDescriptionOf(link);
    }
    
    /**
     * 
     * @return  the table for testing and debugging purposes
     */
    NodeTable getTable(){
        return this.table;
    }
}
