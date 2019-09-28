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
import nl.fh.solutionProcessor.SolutionProcessor;
import nl.fh.solutionProcessor.SolutionStore;

/**
 *
 *  context in which a dancing links problem is set up
 * 
 * @author frank
 * @param <R> type of row objects
 * @param <C> type of column objects
 */
public class LinksSolver<R, C> {

    private LinksMapper<R, C> mapper;
    private NodeTable table;
    private Stack<Node> currentPartialSolution;
    private SolutionProcessor solutionProcessor;

    /**
     * 
     */
    public LinksSolver(){
        initialize();
    }
    
    private void initialize(){
        this.mapper = new LinksMapper<R, C>();
        this.table = new NodeTable();
        this.currentPartialSolution = new Stack<Node>();

        this.table.setContext(this);
    }
    
    /**
     *  this construction exists the enable the direct use of solveRecursively()
     *  without calling solve()
     * @param proc solution processor
     */
    LinksSolver(SolutionProcessor proc) {
        initialize();
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
    public Set<Set<R>> solve(){
        this.solutionProcessor = new SolutionStore();
        
        // solve the link level
        solveRecursively();
        
        // convert to the original objects
        Set<Set<R>> result = new HashSet<Set<R>>();
        List<Set<Node>> solutions = ((SolutionStore)solutionProcessor).getSolutions();
        
        for(Set<Node> nodeSet : solutions){
            Set<R> solution = new HashSet<R>();
            for(Node node : nodeSet){
                solution.add(this.mapper.getRowObject(node));
            }
            result.add(solution);
        }
        return result;
    }
    
    
    /**
     * the recursive solution at the level of links
     */
    public void solveRecursively(){
        
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
            currentPartialSolution.push(pivot.row);
            
            Node runner = pivot.right;
            do{
                if(!runner.isRowHeader()){
                    cover(runner.col);
                }
                runner = runner.right;
            }while(runner != pivot);
            
            solveRecursively();
            
            runner = pivot.left;
            do{
                if(!runner.isRowHeader()){
                    uncover(runner.col);
                }
                runner = runner.left;
            }while(runner != pivot);

            currentPartialSolution.pop();
            pivot = pivot.down;
        }
        
        uncover(chosenColumn);
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
