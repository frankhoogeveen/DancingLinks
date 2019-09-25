/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.Stack;
import nl.fh.colStrategy.ColStrategy;
import nl.fh.colStrategy.TakeFirstColumnAvailable;
import nl.fh.link.Link;
import nl.fh.rowStrategy.RowStrategy;
import nl.fh.rowStrategy.TakeAllRows;
import nl.fh.solutionProcessor.SolutionProcessor;
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
    
    private void check(Link dlx, Link row, Link col, Link up, Link down, Link left, Link right){
        assertEquals(dlx.getRow(), row);
        assertEquals(dlx.getCol(), col); 
        assertEquals(dlx.getUp(), up); 
        assertEquals(dlx.getDown(), down); 
        assertEquals(dlx.getLeft(), left);
        assertEquals(dlx.getRight(), right);
    }
    
    
    /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void setUpDoNothing()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");      
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        Link dlx = solver.getTable().getTableHeader();
        Link headerA = dlx.getDown();
        Link headerB = headerA.getDown();
        Link headerC = headerB.getDown();
        
        Link header1 = dlx.getRight();
        Link header2 = header1.getRight();
        
        Link linkA1 = headerA.getRight();
        Link linkA2 = linkA1.getRight();
        Link linkB2 = headerB.getRight();
        Link linkC1 = headerC.getRight();
        
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
        
    /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void rowAHideRestoreTest()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");      
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        Link dlx = solver.getTable().getTableHeader();
        Link headerA = dlx.getDown();
        Link headerB = headerA.getDown();
        Link headerC = headerB.getDown();
        
        Link header1 = dlx.getRight();
        Link header2 = header1.getRight();
        
        Link linkA1 = headerA.getRight();
        Link linkA2 = linkA1.getRight();
        Link linkB2 = headerB.getRight();
        Link linkC1 = headerC.getRight();
        
        Stack<Link> hiddenRowsCols = new Stack<Link>();
        
        solver.hideRowsAndColumns(headerA, hiddenRowsCols);
        solver.restoreRowsAndColumns(hiddenRowsCols);
        
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
    
    /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void rowBHideRestoreTest()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");      
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        Link dlx = solver.getTable().getTableHeader();
        Link headerA = dlx.getDown();
        Link headerB = headerA.getDown();
        Link headerC = headerB.getDown();
        
        Link header1 = dlx.getRight();
        Link header2 = header1.getRight();
        
        Link linkA1 = headerA.getRight();
        Link linkA2 = linkA1.getRight();
        Link linkB2 = headerB.getRight();
        Link linkC1 = headerC.getRight();
        
        Stack<Link> hiddenRowsCols = new Stack<Link>();
        
        solver.hideRowsAndColumns(headerB, hiddenRowsCols);
        solver.restoreRowsAndColumns(hiddenRowsCols);
        
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
    
        /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void rowCHideRestoreTest()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");      
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        Link dlx = solver.getTable().getTableHeader();
        Link headerA = dlx.getDown();
        Link headerB = headerA.getDown();
        Link headerC = headerB.getDown();
        
        Link header1 = dlx.getRight();
        Link header2 = header1.getRight();
        
        Link linkA1 = headerA.getRight();
        Link linkA2 = linkA1.getRight();
        Link linkB2 = headerB.getRight();
        Link linkC1 = headerC.getRight();
        
        Stack<Link> hiddenRowsCols = new Stack<Link>();
                
        solver.hideRowsAndColumns(headerC, hiddenRowsCols);
        solver.restoreRowsAndColumns(hiddenRowsCols);
        
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
