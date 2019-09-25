/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import nl.fh.colStrategy.TakeFirstColumnAvailable;
import nl.fh.node.AbstractNode;
import nl.fh.node.NodeTable;
import nl.fh.node.TableHeaderNode;

import nl.fh.rowStrategy.TakeAllRows;
import nl.fh.solutionProcessor.SolutionStore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class LinksSolverTest {
    
    public LinksSolverTest() {
    }

    /**
     * Test of addLink method, of class NodeTable.
     */
    @Test
    public void testHideRow() {
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        TakeAllRows rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs,  proc);

        solver.addLink("RowA", "col1");
        
        NodeTable table = solver.getTable();
        TableHeaderNode head = table.getTableHeader();
        AbstractNode row = head.getDown();
        AbstractNode col = head.getRight();
        AbstractNode link = row.getRight();
        
        solver.hideRow(link);
        solver.restoreRow();
        
        assertEquals(row, head.getUp());
        assertEquals(row, head.getDown());
        assertEquals(col, head.getRight());
        assertEquals(col, head.getLeft());
        
        assertEquals(head, row.getUp());
        assertEquals(head, row.getDown());
        assertEquals(link, row.getLeft());
        assertEquals(link, row.getRight());
        
        assertEquals(link, col.getUp());
        assertEquals(link, col.getDown());
        assertEquals(head, col.getLeft());
        assertEquals(head, col.getRight());
        
        assertEquals(col, link.getUp());
        assertEquals(col, link.getDown());
        assertEquals(row, link.getLeft());
        assertEquals(row, link.getRight());
    }

    /**
     * Test of addLink method, of class NodeTable.
     */
    @Test
    public void testHideCol() {
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        TakeAllRows rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs,  proc);

        solver.addLink("RowA", "col1");
        
        NodeTable table = solver.getTable();
        TableHeaderNode head = table.getTableHeader();
        AbstractNode row = head.getDown();
        AbstractNode col = head.getRight();
        AbstractNode link = row.getRight();
        
        solver.hideColumn(link);
        solver.restoreColumn();
        
        assertEquals(row, head.getUp());
        assertEquals(row, head.getDown());
        assertEquals(col, head.getRight());
        assertEquals(col, head.getLeft());
        
        assertEquals(head, row.getUp());
        assertEquals(head, row.getDown());
        assertEquals(link, row.getLeft());
        assertEquals(link, row.getRight());
        
        assertEquals(link, col.getUp());
        assertEquals(link, col.getDown());
        assertEquals(head, col.getLeft());
        assertEquals(head, col.getRight());
        
        assertEquals(col, link.getUp());
        assertEquals(col, link.getDown());
        assertEquals(row, link.getLeft());
        assertEquals(row, link.getRight());
    }
}
