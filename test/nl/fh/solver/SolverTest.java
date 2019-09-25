/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver
        ;

import nl.fh.solver.LinksSolver;
import java.util.List;
import java.util.Set;
import nl.fh.solutionProcessor.SolutionStore;
import nl.fh.colStrategy.TakeFirstColumnAvailable;
import nl.fh.colStrategy.ColStrategy;
import nl.fh.link.Link;
import nl.fh.rowStrategy.TakeAllRows;
import nl.fh.rowStrategy.RowStrategy;
import nl.fh.solutionProcessor.SolutionProcessor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class SolverTest {
    
    public SolverTest() {
    }

     /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_1()  {
       
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        solver.solve();

        assertEquals(1, proc.getCount());
        
        List<Set<Link>> solutions = proc.getSolutions();
        Set<Link> solution = solutions.get(0);
        assertEquals(0, solution.size());
    }
    
     /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_2()  {
        
       
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        solver.addLink("RA", "C1");
        solver.solve();

        assertEquals(1, proc.getCount());
        
        List<Set<Link>> solutions = proc.getSolutions();
        Set<Link> solution = solutions.get(0);
        assertEquals(1, solution.size());
    }    
    
     /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_3()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");
        solver.solve();

        assertEquals(1, proc.getCount());
        
        List<Set<Link>> solutions = proc.getSolutions();
        Set<Link> solution = solutions.get(0);
        assertEquals(1, solution.size());
    } 
     /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_4(){
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");
        solver.addLink("RB", "C2");
        solver.solve();

        assertEquals(1, proc.getCount());
        
        List<Set<Link>> solutions = proc.getSolutions();
        Set<Link> solution = solutions.get(0);
        assertEquals(1, solution.size());
    }     
    /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_5()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
              
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        solver.solve();

        assertEquals(1, proc.getCount());
    }
    
    /**
     * Test of the DLX algorithm on a very small coincendence matrix
     */
    @Test
    public void testSmallCoincedenceMatrix_6()  {
        
        ColStrategy cs = new TakeFirstColumnAvailable();
        RowStrategy rs = new TakeAllRows();
        SolutionProcessor proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, rs, proc);
        Link.setContext(solver);
        
        solver.addLink("RA", "C1");
        solver.addLink("RA", "C2");      
        solver.addLink("RB", "C2");
        solver.addLink("RC", "C1");
        
        solver.solve();

        assertEquals(2, proc.getCount());
    }
    
}
