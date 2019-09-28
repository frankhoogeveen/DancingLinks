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
 * a set of hide/unhide tests on a three by three matrix
 * 
 * @author frank
 */
public class HideUnhideThreeTest {
    
    public HideUnhideThreeTest(){
    }
    
    private void check(Node node, Node up, Node down, Node left, Node right){
        assertEquals(up, node.up);
        assertEquals(down, node.down);
        assertEquals(left, node.left);
        assertEquals(right, node.right);
    }
    
    @Test 
    public void NoHide(){
        NodeTable table = new NodeTable();
        
        Node head = table.getTableHeader();
        Node rowA = table.addRow();
        Node rowB = table.addRow();
        Node rowC = table.addRow();
        
        Node col1 = table.addCol();
        Node col2 = table.addCol();
        Node col3 = table.addCol();

        Node linkA1 = table.addLink(rowA, col1);
        Node linkA2 = table.addLink(rowA, col2);
        Node linkB2 = table.addLink(rowB, col2);
        Node linkB3= table.addLink(rowB, col3);
        Node linkC1 = table.addLink(rowC, col1);
        
        check(head, rowC, rowA, col3, col1);
        check(rowA, head, rowB, linkA2, linkA1);
        check(rowB, rowA, rowC, linkB3, linkB2);
        check(rowC, rowB, head, linkC1, linkC1);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkA2, col1, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, linkA1, rowA);
        check(linkB2, linkA2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC);
    }
    
    @Test 
    public void SingleRowHide(){
        NodeTable table = new NodeTable();
        
        Node head = table.getTableHeader();
        Node rowA = table.addRow();
        Node rowB = table.addRow();
        Node rowC = table.addRow();
        
        Node col1 = table.addCol();
        Node col2 = table.addCol();
        Node col3 = table.addCol();

        Node linkA1 = table.addLink(rowA, col1);
        Node linkA2 = table.addLink(rowA, col2);
        Node linkB2 = table.addLink(rowB, col2);
        Node linkB3= table.addLink(rowB, col3);
        Node linkC1 = table.addLink(rowC, col1);
        
        linkA1.hideRow();
        linkA1.unhideRow();
        
        check(head, rowC, rowA, col3, col1);
        check(rowA, head, rowB, linkA2, linkA1);
        check(rowB, rowA, rowC, linkB3, linkB2);
        check(rowC, rowB, head, linkC1, linkC1);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkA2, col1, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, linkA1, rowA);
        check(linkB2, linkA2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC);
    }
    
    @Test
    public void PivotA1Test(){
        NodeTable table = new NodeTable();
        
        Node head = table.getTableHeader();
        Node rowA = table.addRow();
        Node rowB = table.addRow();
        Node rowC = table.addRow();
        
        Node col1 = table.addCol();
        Node col2 = table.addCol();
        Node col3 = table.addCol();

        Node linkA1 = table.addLink(rowA, col1);
        Node linkA2 = table.addLink(rowA, col2);
        Node linkB2 = table.addLink(rowB, col2);
        Node linkB3= table.addLink(rowB, col3);
        Node linkC1 = table.addLink(rowC, col1);
        
        linkA1.hideColumn();  
        
        check(head, rowC, rowA, col3, col2);
        check(rowA, head, rowB, linkA2, linkA2);
        check(rowB, rowA, rowC, linkB3, linkB2);
        check(rowC, rowB, head, rowC, rowC);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkA2, head, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, rowA, rowA);
        check(linkB2, linkA2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC);   
        
        linkA1.hideRow();
        linkC1.hideRow();
        
        check(head, rowB, rowB, col3, col2);
        check(rowA, head, rowB, linkA2, linkA2);
        check(rowB, head, head, linkB3, linkB2);
        check(rowC, rowB, head, rowC, rowC);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkB2, head, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, rowA, rowA);
        check(linkB2, col2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC); 
            
        linkC1.unhideRow();
        linkA1.unhideRow();
        
        check(head, rowC, rowA, col3, col2);
        check(rowA, head, rowB, linkA2, linkA2);
        check(rowB, rowA, rowC, linkB3, linkB2);
        check(rowC, rowB, head, rowC, rowC);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkA2, head, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, rowA, rowA);
        check(linkB2, linkA2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC);   
        
        linkA1.unhideColumn();
        
        check(head, rowC, rowA, col3, col1);
        check(rowA, head, rowB, linkA2, linkA1);
        check(rowB, rowA, rowC, linkB3, linkB2);
        check(rowC, rowB, head, linkC1, linkC1);
        
        check(col1, linkC1, linkA1, head, col2);
        check(col2, linkB2, linkA2, col1, col3);
        check(col3, linkB3, linkB3, col2, head);
        
        check(linkA1, col1, linkC1, rowA, linkA2);
        check(linkA2, col2, linkB2, linkA1, rowA);
        check(linkB2, linkA2, col2, rowB, linkB3);
        check(linkB3, col3, col3, linkB2, rowB);
        check(linkC1, linkA1, col1, rowC, rowC);   
    }

}
