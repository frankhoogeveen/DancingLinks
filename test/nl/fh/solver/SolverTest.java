package nl.fh.solver;


import nl.fh.colStrategy.TakeFirstColumnAvailable;
import nl.fh.solutionProcessor.MultiProcessor;
import nl.fh.solutionProcessor.SolutionDisplay;
import nl.fh.solutionProcessor.SolutionStore;
import org.junit.Test;
import static org.junit.Assert.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frank
 */
public class SolverTest {
    public SolverTest(){
        
    }
    
    @Test
    public void EmptySolverTest(){
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, proc);

        solver.solve();
        
        //
        // note that the empty set is counted as a solution
        //
        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void OneLinkSolverTest(){
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs,  proc);

        solver.addLink("rowA", "col1");
        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void TwoLinkSolverTest(){
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs,  proc);

        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col1");
        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void ThreeLinkSolverTest(){
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        
        MultiProcessor proc = new MultiProcessor();
        proc.add(new SolutionDisplay());
        proc.add(new SolutionStore());
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs,  proc);

        
        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col2");
        solver.addLink("rowB", "col1");

        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void FourLinkSolverTest(){
        TakeFirstColumnAvailable cs = new TakeFirstColumnAvailable();
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(cs, proc);

        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col2");
        solver.addLink("rowB", "col1");
        solver.addLink("rowA", "col1");
        solver.solve();

        assertEquals(2, proc.getCount());  
    }
}
