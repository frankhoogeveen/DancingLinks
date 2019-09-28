package nl.fh.exact;




import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class ExactHittingTest {
    
    public ExactHittingTest(){
        
    }
    
    @Test
    public void wikipediaTest(){
        ExactHittingSolver<String> solver = new ExactHittingSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        
        assertEquals(1, solutions.size());
        

        Set<String> expSet = new HashSet<String>(Arrays.asList("1", "2", "5"));

        for(Set<String> soln : solutions){
            assertEquals(expSet, soln);
        }
   }
}
