package nl.fh.solver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frank
 */
public class HideUnhideKnuthTest {
    
    public HideUnhideKnuthTest(){
    }
    
    @Test 
    public void NoHide(){
        NodeTable table = new NodeTable();
        
        Node head = table.getTableHeader();
        Node rowA = table.addRow();
        Node rowB = table.addRow();
        Node rowC = table.addRow();
        Node rowD = table.addRow();
        Node rowE = table.addRow();
        Node rowF = table.addRow();
        
        //note that the columns are in an order that is also used 
        //by the higher level tests of LinksSolver
        Node col3 = table.addCol();
        Node col5 = table.addCol();
        Node col7 = table.addCol();
        Node col1 = table.addCol();
        Node col4 = table.addCol();
        Node col2 = table.addCol();
        Node col6 = table.addCol();

        Node linkA3 = table.addLink(rowA, col3);
        Node linkA5 = table.addLink(rowA, col5);
        Node linkA7 = table.addLink(rowA, col7);
        Node linkB1 = table.addLink(rowB, col1);
        Node linkB4 = table.addLink(rowB, col4);
        Node linkB7 = table.addLink(rowB, col7);
        Node linkC2 = table.addLink(rowC, col2);
        Node linkC3 = table.addLink(rowC, col3);
        Node linkC6 = table.addLink(rowC, col6);
        Node linkD1 = table.addLink(rowD, col1);
        Node linkD4 = table.addLink(rowD, col4);
        Node linkE2 = table.addLink(rowE, col2);
        Node linkE7 = table.addLink(rowE, col7);
        Node linkF4 = table.addLink(rowF, col4);
        Node linkF5 = table.addLink(rowF, col5);
        Node linkF7 = table.addLink(rowF, col7);
        
        assertEquals(head.up, rowF);
        assertEquals(head.down, rowA);
        assertEquals(head.right, col3);
        assertEquals(head.left, col6);
        
        assertEquals(linkB7.up, linkA7);
        assertEquals(linkB7.down, linkE7);
        assertEquals(linkB7.left, rowB);
        assertEquals(linkB7.right, linkB1);
        
        linkB1.hideColumn();
        
        assertEquals(linkB7.up, linkA7);
        assertEquals(linkB7.down, linkE7);
        assertEquals(linkB7.left, rowB);
        assertEquals(linkB7.right, linkB4);
        
        linkB1.unhideColumn();
        
        assertEquals(linkB7.up, linkA7);
        assertEquals(linkB7.down, linkE7);
        assertEquals(linkB7.left, rowB);
        assertEquals(linkB7.right, linkB1);
    }
}
