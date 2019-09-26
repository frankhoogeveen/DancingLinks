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
    private final Stack<Node> currentPartialSolution;
    
    private final ColStrategy colStrategy;
    private final SolutionProcessor solutionProcessor;

    /**
     *
     * @param cs column strategy
     * @param rs row strategy
     * @param proc solution processor
     */
    public LinksSolver(ColStrategy cs, SolutionProcessor proc) {
        this.mapper = new LinksMapper<R, C>();
        this.table = new NodeTable();
        this.currentPartialSolution = new Stack<Node>();
        
        this.colStrategy = cs;
        this.solutionProcessor = proc;
        
        this.table.setContext(this);
        
    }

    /**
     *  Add a link between a row and a column object
     * 
     * @param row
     * @param col 
     */
    public void addLink(R row , C col) {
        // create the header links if they do not yet exist
        Node rowHeader = mapper.getRowHeader(row);
        if(rowHeader == null){
            rowHeader = table.addRow();
            mapper.addRow(row, rowHeader);
        }
        
        Node colHeader = mapper.getColHeader(col);
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
            Set<Node> solutionSet= new HashSet(this.currentPartialSolution);
            solutionProcessor.process(solutionSet);
            return;
        }
        
        // reached a dead end
        if(table.hasNoVisibleRows()){
           return;
        }      
        
        // no conclusion yet, reduce the table and continue the search
        Node chosenColumn = this.colStrategy.chooseColumn(table);
        Node pivot = chosenColumn.down;
        while(pivot != chosenColumn){
            currentPartialSolution.push(pivot.row);
           
            // hide all involved col headers
            Node runner = pivot.row.right;
            while(runner != pivot.row){
                runner.col.removeHorizontal();
                runner = runner.right;
            }
            
            // hide all involved row headers
            runner = pivot.col.down;
            while(runner != pivot.col){
                runner.row.removeVertical();
                runner = runner.down;
            }
            
            solve();

            // restore row headers
            runner = pivot.col.up;
            while(runner != pivot.col){
                runner.row.restoreVertical();
                runner = runner.up;
            }
            
            // restore the column headers
            runner = pivot.row.left;
            while(runner != pivot.row){
                runner.col.restoreVertical();
                runner = runner.left;
            }
            
            currentPartialSolution.pop();
            
            pivot = pivot.down;
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
    public String format(Node link){
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
