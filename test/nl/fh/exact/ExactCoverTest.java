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
 * 
 * based on: https://en.wikipedia.org/wiki/Exact_cover#Detailed_example
 * @author frank
 */
public class ExactCoverTest {
    
    @Test
    public void wikipediaTest(){
        ExactCoverSolver<String> solver = new ExactCoverSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<Set<String>>> solutions = solver.solve();
        
        assertEquals(1, solutions.size());
        
        for(Set<Set<String>> soln : solutions){

            Set<String> expSet1 = new HashSet<String>(Arrays.asList("1", "4"));
            Set<String> expSet2 = new HashSet<String>(Arrays.asList("3", "5", "6"));
            Set<String> expSet3 = new HashSet<String>(Arrays.asList("2", "7"));

            assertEquals(3, soln.size());
            assertTrue(soln.contains(expSet1));
            assertTrue(soln.contains(expSet2));
            assertTrue(soln.contains(expSet3));        
        }
   }
}
