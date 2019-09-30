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
public class ExtendedExactCoverTest {
    
    private void display(Set<Set<Set<String>>> solns){
        
        StringBuilder sb = new StringBuilder();
        sb.append("===============================\n");
        for(Set<Set<String>> soln : solns){
            sb.append("----------\n");
            for(Set<String> line : soln){
                sb.append("[");
                for(String s : line){
                    sb.append(s);
                    sb.append(" ");
                }
                sb.append("]\n");
            }
        }
        System.out.println(sb);
    }
    
    @Test
    public void zeroSecondaryTest(){
        ExtendedExactCoverSolver<String> solver = new ExtendedExactCoverSolver<String>();
        
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
    
    @Test
    public void singleSecondaryTest(){
        ExtendedExactCoverSolver<String> solver = new ExtendedExactCoverSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        solver.markSecondary("2");
        
        Set<Set<Set<String>>> solutions = solver.solve();
        // display(solutions);
        
        assertEquals(2, solutions.size());
        
        Set<Set<String>> solution1 = new HashSet<Set<String>>();
        solution1.add(new HashSet<String>(Arrays.asList("1", "4")));
        solution1.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solution1.add(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solution2 = new HashSet<Set<String>>();
        solution2.add(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solution2.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        
        Set<Set<Set<String>>> expSolution = new HashSet<Set<Set<String>>>();
        expSolution.add(solution1);
        expSolution.add(solution2);
        
        assertEquals(expSolution, solutions);
    }
    
    @Test
    public void singleSecondaryTest2(){
        ExtendedExactCoverSolver<String> solver = new ExtendedExactCoverSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        solver.markSecondary("5");
        
        Set<Set<Set<String>>> solutions = solver.solve();
        // display(solutions);
        
        Set<Set<String>> solution1 = new HashSet<Set<String>>();
        solution1.add(new HashSet<String>(Arrays.asList("1", "4")));
        solution1.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solution1.add(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solution2 = new HashSet<Set<String>>();
        solution2.add(new HashSet<String>(Arrays.asList("1", "4")));
        solution2.add(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        
        Set<Set<Set<String>>> expSolution = new HashSet<Set<Set<String>>>();
        expSolution.add(solution1);
        expSolution.add(solution2);
        
        assertEquals(expSolution, solutions);
    }
    
    @Test
    public void singleSecondaryTest3(){
        ExtendedExactCoverSolver<String> solver = new ExtendedExactCoverSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        solver.markSecondary("6");
        
        Set<Set<Set<String>>> solutions = solver.solve();
        // display(solutions);
        
        Set<Set<String>> solution1 = new HashSet<Set<String>>();
        solution1.add(new HashSet<String>(Arrays.asList("1", "4")));
        solution1.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solution1.add(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<Set<String>>> expSolution = new HashSet<Set<Set<String>>>();
        expSolution.add(solution1);
        
        assertEquals(expSolution, solutions);
    }
    
    @Test
    public void multipleSecondaryTest(){
        ExtendedExactCoverSolver<String> solver = new ExtendedExactCoverSolver<String>();
        
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addSet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSet(new HashSet<String>(Arrays.asList("2", "7")));
        
        solver.markSecondary("2");
        solver.markSecondary("5");
        
        Set<Set<Set<String>>> solutions = solver.solve();
        // display(solutions);
        
        Set<Set<String>> solution1 = new HashSet<Set<String>>();
        solution1.add(new HashSet<String>(Arrays.asList("1", "4")));
        solution1.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solution1.add(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solution2 = new HashSet<Set<String>>();
        solution2.add(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solution2.add(new HashSet<String>(Arrays.asList("1", "4")));
        
        Set<Set<String>> solution3 = new HashSet<Set<String>>();
        solution3.add(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solution3.add(new HashSet<String>(Arrays.asList("1", "4", "7")));
        
        Set<Set<Set<String>>> expSolution = new HashSet<Set<Set<String>>>();
        expSolution.add(solution1);
        expSolution.add(solution2);
        expSolution.add(solution3);
        
        assertEquals(expSolution, solutions);
    }
}
