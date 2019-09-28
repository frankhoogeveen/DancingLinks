package nl.fh.solver;


import java.util.Set;
import nl.fh.solutionProcessor.MultiProcessor;
import nl.fh.solutionProcessor.SolutionDisplay;
import nl.fh.solutionProcessor.SolutionStore;
import nl.fh.solver.LinksSolver;
import nl.fh.solver.Node;
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
public class CurrentWorkingTest {
    public CurrentWorkingTest(){
        
    }
    
    @Test
    public void OneLinkSolverTest(){
        SolutionStore proc = new SolutionStore();
        
        LinksSolver<String, String> solver = new LinksSolver<String, String>(proc);

        solver.addLink("rowA", "col1");
        solver.solve();

        assertEquals(1,proc.getCount());  
        
        Set<Node> soln = proc.getSolutions().get(0);
        assertEquals(1, soln.size());
        
        // debug
        System.out.println("soln:");
        for(Node n : soln){
            System.out.println(n);
        }
        System.out.println();
        
        System.out.println(solver.toString());
        //TODO end of debug
        
        assertFalse(soln.contains(solver.getTable().tableHeader));
        assertTrue(soln.contains(solver.getTable().tableHeader.down));
    }
 
}
