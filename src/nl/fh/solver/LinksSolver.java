/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
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
    
    private final SolutionProcessor solutionProcessor;

    /**
     *
     * @param proc solution processor
     */
    public LinksSolver(SolutionProcessor proc) {
        this.mapper = new LinksMapper<R, C>();
        this.table = new NodeTable();
        this.currentPartialSolution = new Stack<Node>();

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
        
        // no conclusion yet, reduce the table and continue the search
        

        // choose the column
        Node chosenColumn = table.getTableHeader().right;
        
        //cover the chose column
        cover(chosenColumn);
        
        Node pivot = chosenColumn.down;
        while(pivot != chosenColumn){
            
            pushPartialSolution(pivot.row);
            // currentPartialSolution.push(pivot.row);
            
            Node runner = pivot.right;
            do{
                if(!runner.isRowHeader()){
                    cover(runner.col);
                }
                runner = runner.right;
            }while(runner != pivot);
            
            solve();
            
            runner = pivot.left;
            do{
                if(!runner.isRowHeader()){
                    uncover(runner.col);
                }
                runner = runner.left;
            }while(runner != pivot);
            
            popPartialSolution();
            //currentPartialSolution.pop();
            pivot = pivot.down;
        }
        
        uncover(chosenColumn);
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

    private void pushPartialSolution(Node node) {
        System.out.println("push " + node.toString());
        this.currentPartialSolution.push(node);
    }

    private void popPartialSolution() {
        Node node = this.currentPartialSolution.pop();
        System.out.println("pop  " + node.toString());
    }

    private void cover(Node col) {
        if(!col.isColHeader()){
            throw new IllegalArgumentException();
        }
        
        col.removeHorizontal();
        
        Node descender = col.down;
        while(descender != col){
            Node horizontal = descender.right;
            while(horizontal != descender){
                horizontal.removeVertical();
                horizontal = horizontal.right;
            }
             descender = descender.down;
        }
    }

    private void uncover(Node col) {
        if(!col.isColHeader()){
            throw new IllegalArgumentException();
        }
        
        Node ascender = col.up;
        while(ascender != col){
            Node horizontal = ascender.left;
            while(horizontal != ascender){
                horizontal.restoreVertical();
                horizontal = horizontal.left;
            }
            ascender = ascender.up;
        }
        col.restoreHorizontal();
    }
}
