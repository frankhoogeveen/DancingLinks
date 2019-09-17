/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.dancing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import nl.fh.colStrategy.ColStrategy;
import nl.fh.link.Link;
import nl.fh.link.LinksTable;
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
    private final LinksTable table;
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
        this.table = new LinksTable();
        this.currentPartialSolution = new Stack<Link>();
        
        this.colStrategy = cs;
        this.rowStrategy = rs;
        this.solutionProcessor = proc;
        
    }

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
        //TODO remove
        System.out.println(this);
        
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
        
        
        for(Link currentRow : rowList){
            currentPartialSolution.push(currentRow);
            
            hideRowsAndColumns(currentRow);
            solve();
            restoreRowsAndColumns(currentRow);
            
            currentPartialSolution.pop();
        }
        
    }

    private void hideRowsAndColumns(Link currentRow) {
        Link rowHeader = currentRow.getRow();
        Link current = rowHeader.getRight();
        while(current != rowHeader){

            hideOverlappingRows(current);
            
            System.out.println(this);
            System.out.println("current " + current.toString()); //TODO remove
            
            current.getCol().hideColumn();
            
            System.out.println(this);
            System.out.println("current " + current.toString()); //TODO remove
            
            
            current = current.getRight(); 
        }
    }

    private void restoreRowsAndColumns(Link currentRow) {
        Link rowHeader = currentRow.getRow();
        Link current = rowHeader.getLeft();
        while(current != rowHeader){
            restoreOverlappingRows(current);
            
            current.getCol().restoreColumn();
            current = current.getLeft(); 
        }
    }

    private void hideOverlappingRows(Link link) {
        Link colHeader = link.getCol();
        Link current = colHeader.getDown();
        
        System.out.println("--------------------------------");
        System.out.println(this.mapper.shortDescriptionOf(link));
        System.out.println(this.mapper.shortDescriptionOf(colHeader));
        System.out.println(this.mapper.shortDescriptionOf(current));
        System.out.print("Table is good: ");
        System.out.println(this.table.isEveryLinkGood(10));  //TODO remove prints
        
        while(current != colHeader){
            current.hideRow();
            
            System.out.println("--------------------------------");
            System.out.println(this.mapper.shortDescriptionOf(current));
            System.out.print("Table is good: ");
            System.out.println(this.table.isEveryLinkGood(10));  //TODO remove prints   
            
            current = current.getDown();
        }
        
        System.out.println(this.mapper.shortDescriptionOf(current));
        System.out.println(this.table.isEveryLinkGood(10));  //TODO remove prints
    }

    private void restoreOverlappingRows(Link link) {
        Link colHeader = link.getCol();
        Link current = colHeader.getUp();
        while(current != colHeader){
            current.restoreRow();
            current = current.getUp();
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
    public String format(Link link){
        return this.mapper.shortDescriptionOf(link);
    }
    
}
