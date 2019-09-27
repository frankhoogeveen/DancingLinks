package nl.fh.solver;


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
public class BasicSolverTest {
    public BasicSolverTest(){
        
    }
    
    @Test
    public void EmptySolverTest(){
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.solve();
        
        //
        // note that the empty set is counted as a solution
        //
        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void OneLinkSolverTest(){
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col1");
        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void TwoLinkSolverTest(){
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col1");
        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void ThreeLinkSolverTest(){
        
        MultiProcessor proc = new MultiProcessor();
        // proc.add(new SolutionDisplay());
        proc.add(new SolutionStore());
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        
        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col2");
        solver.addLink("rowB", "col1");

        solver.solve();

        assertEquals(1,proc.getCount());  
    }
    
    @Test
    public void FourLinkSolverTest(){
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col2");
        solver.addLink("rowB", "col2");
        solver.addLink("rowB", "col1");
        solver.addLink("rowA", "col1");
        solver.solve();

        assertEquals(2, proc.getCount());  
    }
    
        @Test
    public void SubKnuthTest(){
        
        MultiProcessor proc = new MultiProcessor();
        proc.add(new SolutionDisplay());
        proc.add(new SolutionStore());
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        
        solver.addLink("rowA", "col3");
        solver.addLink("rowA", "col5");
        solver.addLink("rowA", "col6");
        
        solver.addLink("rowB", "col1");
        solver.addLink("rowB", "col4");
        solver.addLink("rowB", "col7");
        /*
        solver.addLink("rowC", "col2");        
        solver.addLink("rowC", "col3");        
        solver.addLink("rowC", "col6");
 
        solver.addLink("rowD", "col1");        
        solver.addLink("rowD", "col4");   
        
        solver.addLink("rowE", "col2");        
        solver.addLink("rowE", "col7");
        
        solver.addLink("rowF", "col4");    
        solver.addLink("rowF", "col5");        
        solver.addLink("rowF", "col7");        
        */
        solver.solve();

        // solution expected is  AB
        assertEquals(1, proc.getCount());  
    }
    
    @Test
    public void SubKnuthTest2(){
        
        MultiProcessor proc = new MultiProcessor();
        proc.add(new SolutionDisplay());
        proc.add(new SolutionStore());
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col3");
        solver.addLink("rowA", "col5");
        solver.addLink("rowA", "col6");
        
        solver.addLink("rowB", "col1");
        solver.addLink("rowB", "col4");
        solver.addLink("rowB", "col7");
        
        solver.addLink("rowC", "col2");        
        solver.addLink("rowC", "col3");        
        solver.addLink("rowC", "col6");
 /*
        solver.addLink("rowD", "col1");        
        solver.addLink("rowD", "col4");   
        
        solver.addLink("rowE", "col2");        
        solver.addLink("rowE", "col7");
        
        solver.addLink("rowF", "col4");    
        solver.addLink("rowF", "col5");        
        solver.addLink("rowF", "col7");        
        */
        solver.solve();

        // solution expected is  none
        assertEquals(0, proc.getCount());  
    }
    
    @Test
    public void SubKnuthTest3(){
        
        MultiProcessor proc = new MultiProcessor();
        proc.add(new SolutionDisplay());
        proc.add(new SolutionStore());
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col3");
        solver.addLink("rowA", "col5");
        solver.addLink("rowA", "col6");
        
        solver.addLink("rowB", "col1");
        solver.addLink("rowB", "col4");
        solver.addLink("rowB", "col7");
        
        solver.addLink("rowC", "col2");        
        solver.addLink("rowC", "col3");        
        solver.addLink("rowC", "col6");
 
        solver.addLink("rowD", "col1");        
        solver.addLink("rowD", "col4");   
        /*
        solver.addLink("rowE", "col2");        
        solver.addLink("rowE", "col7");
        
        solver.addLink("rowF", "col4");    
        solver.addLink("rowF", "col5");        
        solver.addLink("rowF", "col7");        
        */
        solver.solve();

        // solution expected is none
        assertEquals(0, proc.getCount());  
    }
}
