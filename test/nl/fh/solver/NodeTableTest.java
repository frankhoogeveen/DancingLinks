/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import nl.fh.solver.Node;
import nl.fh.solver.NodeTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class NodeTableTest {
    
    public NodeTableTest() {
    }

    /**
     * Test of addRow method, of class NodeTable.
     */
    @Test
    public void testAddRow() {
        NodeTable table = new NodeTable();
        Node row = table.addRow();
        Node head = table.tableHeader;
        
        assertEquals(head, row.up);
        assertEquals(head, row.down);
        assertEquals(row, row.left);
        assertEquals(row, row.right);
    }

    /**
     * Test of addCol method, of class NodeTable.
     */
    @Test
    public void testAddCol() {
        NodeTable table = new NodeTable();
        Node col = table.addCol();
        Node head = table.tableHeader;
        
        assertEquals(head, col.left);
        assertEquals(head, col.right);
        assertEquals(col, col.up);
        assertEquals(col, col.down);
    }

        /**
     * Test of addLink method, of class NodeTable.
     */
    @Test
    public void testFindRowColTest() {
        NodeTable table = new NodeTable();
        
        Node head = table.tableHeader;
        Node row = table.addRow();
        Node col = table.addCol();
        Node link = table.addLink(row, col);
        
        assertEquals(row, link.row);
        assertEquals(col, link.col);
        
    }
    
    
    /**
     * Test of addLink method, of class NodeTable.
     */
    @Test
    public void testAddLink() {
        NodeTable table = new NodeTable();
        
        Node head = table.tableHeader;
        Node row = table.addRow();
        Node col = table.addCol();
        Node link = table.addLink(row, col);
        
        assertEquals(row, head.up);
        assertEquals(row, head.down);
        assertEquals(col, head.right);
        assertEquals(col, head.left);
        
        assertEquals(head, row.up);
        assertEquals(head, row.down);
        assertEquals(link, row.left);
        assertEquals(link, row.right);
        
        assertEquals(link, col.up);
        assertEquals(link, col.down);
        assertEquals(head, col.left);
        assertEquals(head, col.right);
        
        assertEquals(col, link.up);
        assertEquals(col, link.down);
        assertEquals(row, link.left);
        assertEquals(row, link.right);
        
    }

    /**
     * Test of hasNoVisibleColumns method, of class NodeTable.
     */
    @Test
    public void testHasNoVisibleColumns() {
        NodeTable table = new NodeTable();
        
        assertTrue(table.hasNoVisibleColumns());
        
        Node row = table.addRow();
        assertTrue(table.hasNoVisibleColumns());
        
        Node col = table.addCol();
        assertFalse(table.hasNoVisibleColumns());
        
        Node link = table.addLink(row, col);
        assertFalse(table.hasNoVisibleColumns());
    }

    /**
     * Test of hasNoVisibleRows method, of class NodeTable.
     */
    @Test
    public void testHasNoVisibleRows() {
        NodeTable table = new NodeTable();
        
        assertTrue(table.hasNoVisibleRows());
        
        Node row = table.addRow();
        assertFalse(table.hasNoVisibleRows());
        
        Node col = table.addCol();
        assertFalse(table.hasNoVisibleRows());
        
        Node link = table.addLink(row, col);
        assertFalse(table.hasNoVisibleRows());
    }

    /**
     * Test of isEveryLinkGood method, of class NodeTable.
     */
    @Test
    public void testIsEveryLinkGood() {
        NodeTable table = new NodeTable();
        
        Node head = table.tableHeader;
        Node row = table.addRow();
        Node col = table.addCol();
        Node link = table.addLink(row, col);
        
        assertTrue(table.isEveryLinkGood(3));
    }
    
}
