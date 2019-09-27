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
public class KnuthTest {
    public KnuthTest(){
        
    }

    
    @Test
    public void KnuthTest(){
        
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
        
        solver.addLink("rowE", "col2");        
        solver.addLink("rowE", "col7");
        
        solver.addLink("rowF", "col4");    
        solver.addLink("rowF", "col5");        
        solver.addLink("rowF", "col7");        

        solver.solve();

        // solution expected is  ADE
        assertEquals(1, proc.getCount());  
    }
}
