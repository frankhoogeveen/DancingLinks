package nl.fh.dancing;

import nl.fh.link.LinksTable;
import nl.fh.link.Link;
import static org.junit.Assert.*;
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
public class HideRestoreTest {
  
    private void check(Link dlx, Link row, Link col, Link up, Link down, Link left, Link right){
        assertEquals(dlx.getRow(), row);
        assertEquals(dlx.getCol(), col); 
        assertEquals(dlx.getUp(), up); 
        assertEquals(dlx.getDown(), down); 
        assertEquals(dlx.getLeft(), left);
        assertEquals(dlx.getRight(), right);
    }
    
    @Test
    public void hideRowTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        headerB.hideRow();
        
        // the remaining table
        check(dlx, dlx, dlx, headerC, headerA, header2, header1);
        check(headerA, headerA, dlx, dlx, headerC, linkA2, linkA1);
        check(headerC, headerC, dlx, headerA, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(header2, dlx, header2, linkA2, linkA2, header1, dlx);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, header2, linkA1, headerA);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
        
        // the hidden row is unchanged
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
    }
    
    @Test
    public void hideRowTwiceTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        headerB.hideRow();
        headerA.hideRow();
        
        // the remaining table
        check(dlx, dlx, dlx, headerC, headerC, header2, header1);
        check(headerC, headerC, dlx, dlx, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkC1, dlx, header2);
        check(header2, dlx, header2, header2, header2, header1, dlx);
        check(linkC1, headerC, header1, header1, header1, headerC, headerC);
        
        // the hidden row is unchanged
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
        
        check(headerA, headerA, dlx, dlx, headerC, linkA2, linkA1);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, header2, linkA1, headerA);
        
    }
    
    @Test
    public void hideColumnTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        header1.hideColumn();
        
        // the remaining table
        check(dlx, dlx, dlx, headerC, headerA, header2, header2);
        check(headerA, headerA, dlx, dlx, headerB, linkA2, linkA2);
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(headerC, headerC, dlx, headerB, dlx, headerC, headerC);
        check(header2, dlx, header2, linkB2, linkA2, dlx, dlx);
        check(linkA2, headerA, header2, header2, linkB2, headerA, headerA);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);

        
        // the removed column
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
    }
    
    @Test
    public void restoreRowTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        headerB.hideRow();
        headerB.restoreRow();
        
        check(dlx, dlx, dlx, headerC, headerA, header2, header1);
        check(headerA, headerA, dlx, dlx, headerB, linkA2, linkA1);
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(headerC, headerC, dlx, headerB, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(header2, dlx, header2, linkB2, linkA2, header1, dlx);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, linkB2, linkA1, headerA);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
    }
    
    @Test
    public void restoreColumnTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        header1.hideColumn();
        header1.restoreColumn();
        
        check(dlx, dlx, dlx, headerC, headerA, header2, header1);
        check(headerA, headerA, dlx, dlx, headerB, linkA2, linkA1);
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(headerC, headerC, dlx, headerB, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(header2, dlx, header2, linkB2, linkA2, header1, dlx);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, linkB2, linkA1, headerA);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
    }
    
    @Test
    public void restoreRowColumnTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        headerB.hideRow();
        header1.hideColumn();
        header1.restoreColumn();
        headerB.restoreRow();
        
        check(dlx, dlx, dlx, headerC, headerA, header2, header1);
        check(headerA, headerA, dlx, dlx, headerB, linkA2, linkA1);
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(headerC, headerC, dlx, headerB, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(header2, dlx, header2, linkB2, linkA2, header1, dlx);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, linkB2, linkA1, headerA);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
    }
    
    @Test
    public void restoreRowTwiceTest(){
        LinksTable table = new LinksTable();
        Link dlx = table.getTableHeader();
        
        Link headerA = table.addRow();
        Link header1 = table.addCol();
        Link header2 = table.addCol();
        
        Link headerB = table.addRow();
        Link headerC = table.addRow();
        
        Link linkA1 = table.addLink(headerA, header1);
        Link linkA2 = table.addLink(headerA, header2);
        Link linkB2 = table.addLink(headerB, header2);  
        Link linkC1 = table.addLink(headerC, header1);
        
        headerA.hideRow();
        headerB.hideRow();
        headerB.restoreRow();
        headerA.restoreRow();
        
        check(dlx, dlx, dlx, headerC, headerA, header2, header1);
        check(headerA, headerA, dlx, dlx, headerB, linkA2, linkA1);
        check(headerB, headerB, dlx, headerA, headerC, linkB2, linkB2);
        check(headerC, headerC, dlx, headerB, dlx, linkC1, linkC1);
        check(header1, dlx, header1, linkC1, linkA1, dlx, header2);
        check(header2, dlx, header2, linkB2, linkA2, header1, dlx);
        check(linkA1, headerA, header1, header1, linkC1, headerA, linkA2);
        check(linkA2, headerA, header2, header2, linkB2, linkA1, headerA);
        check(linkB2, headerB, header2, linkA2, header2, headerB, headerB);
        check(linkC1, headerC, header1, linkA1, header1, headerC, headerC);
    }
}
