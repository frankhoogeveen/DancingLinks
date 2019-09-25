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
import nl.fh.node.BareNode;
import nl.fh.node.AbstractNode;
import nl.fh.node.ColHeader;
import nl.fh.node.ColHeaderNode;
import nl.fh.node.NodeTable;
import nl.fh.node.RowHeaderNode;
import nl.fh.node.MarkerNode;
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
    private final Stack<RowHeaderNode> currentPartialSolution;
    private final Stack<BareNode> hiddenNodes;
    
    private final ColStrategy colStrategy;
    private final RowStrategy rowStrategy;
    private final SolutionProcessor solutionProcessor;
    private final BareNode marker;
    private final BareNode marker2;
    private final BareNode marker3;
    /**
     *
     * @param cs column strategy
     * @param rs row strategy
     * @param proc solution processor
     */
    public LinksSolver(ColStrategy cs, RowStrategy rs, SolutionProcessor proc) {
        this.mapper = new LinksMapper<R, C>();
        this.table = new NodeTable();
        this.currentPartialSolution = new Stack<RowHeaderNode>();
        this.hiddenNodes = new Stack<BareNode>();
        this.marker = new MarkerNode();
        this.marker2 = new MarkerNode();
        this.marker3 = new MarkerNode();
        
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
        RowHeaderNode rowHeader = mapper.getRowHeader(row);
        if(rowHeader == null){
            rowHeader = table.addRow();
            mapper.addRow(row, rowHeader);
        }
        
        ColHeaderNode colHeader = mapper.getColHeader(col);
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
            Set<RowHeaderNode> solutionSet= new HashSet(this.currentPartialSolution);
            solutionProcessor.process(solutionSet);
            return;
        }
        
        // reached a dead end
        if(table.hasNoVisibleRows()){
           return;
        }      
        
        // no conclusion yet, reduce the table and continue the search
        ColHeaderNode chosenColumn = this.colStrategy.chooseColumn(table);
        List<RowHeaderNode> rowList = this.rowStrategy.determineRows(chosenColumn);
        
        for(RowHeaderNode chosenRow : rowList){
            currentPartialSolution.push(chosenRow);
            
            hideRowsAndColumns(chosenRow);
            solve();
            restoreRowsAndColumns();

            currentPartialSolution.pop();
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

    /**
     * 
     * @param node 
     * 
     *  hides the entire, currently visible row containing node
     */
    void hideRow(AbstractNode node) {
        // push a marker on the stack
        this.hiddenNodes.push(marker);
        
        // decouple the vertical links of all nodes in the row
        AbstractNode current = node;
        do{
            current.removeVertical();
            this.hiddenNodes.push(current);
            current = current.getRight();
        } while(current != node);
    }
    
        /**
     * 
     * @param node 
     * 
     *  hides the entire, currently visible row containing node
     *  and marks on the stack what has been hidden
     */
    void hideColumn(AbstractNode node) {
        // push a marker on the stack
        this.hiddenNodes.push(marker);
        
        // decouple the horizontal links of all nodes in the row
        AbstractNode current = node;
        do{
            current.removeHorizontal();
            this.hiddenNodes.push(current);
            current = current.getDown();
        } while(current != node);
    }

    /**
     * is the inverse of hideRow()
     */
    void restoreRow() {
        BareNode node = this.hiddenNodes.pop();
        while(node != marker){
            ((AbstractNode)node).restoreVertical();
            node = this.hiddenNodes.pop();
        }
    }
    
     /**
     * is the inverse of hideColumn()
     */
    void restoreColumn() {
        BareNode node = this.hiddenNodes.pop();
        while(node != marker){
            ((AbstractNode)node).restoreHorizontal();
            node = this.hiddenNodes.pop();
        }
    }

    private void hideRowsAndColumns(RowHeaderNode chosenRow) {
        
        AbstractNode currentHor = chosenRow.getRight();
        
        this.hiddenNodes.push(marker2);
        while(currentHor != chosenRow){
            ColHeaderNode currentCol = (ColHeaderNode) currentHor.findColumn();
            AbstractNode currentVert = currentCol.getDown();
            this.hiddenNodes.push(marker3);
            while(currentVert != currentCol){
                hideRow(currentVert);
                currentVert = currentVert.getDown();
            }
            hideColumn(currentCol);
            currentHor = currentHor.getRight();
        }
    }

    private void restoreRowsAndColumns() {
        
        BareNode next = this.hiddenNodes.pop();
        while(next != marker2){
            restoreColumn();
            next = this.hiddenNodes.pop();
            while(next != marker3){
                restoreRow();
                next = this.hiddenNodes.pop();
            }
            next = this.hiddenNodes.pop();
        }
    }
}
