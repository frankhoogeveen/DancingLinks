package nl.fh.exact;




import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
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
public class ExtendedExactHittingTest {
    
    public ExtendedExactHittingTest(){
        
    }
    private void showSolutions(Set<Set<String>> solutions){   
         for(Set<String> solution : solutions){
            System.out.println("----solution----");
            for(String s : solution){
                System.out.println(s);
            }
        }
    }
    
    
    @Test
    public void noSecondaryTest(){
        ExtendedExactHittingSolver<String> solver = new ExtendedExactHittingSolver<String>();
        
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        //showSolutions(solutions);
        
        assertEquals(1, solutions.size());
        
        Set<String> expSet = new HashSet<String>(Arrays.asList("1", "2", "5"));

        for(Set<String> soln : solutions){
            assertEquals(expSet, soln);
        }
   }
    
    @Test
    public void singleSecondaryTest(){
        ExtendedExactHittingSolver<String> solver = new ExtendedExactHittingSolver<String>();
        
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSecondarySet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        // showSolutions(solutions);
        
        assertEquals(3, solutions.size());
        
        Set<String> expSet1 = new HashSet<String>(Arrays.asList("1", "2", "5"));
        Set<String> expSet2 = new HashSet<String>(Arrays.asList("3", "4"));
        Set<String> expSet3 = new HashSet<String>(Arrays.asList("4", "6"));
        Set<Set<String>> expSolution = new HashSet<Set<String>>(Arrays.asList(expSet1, expSet2, expSet3));
        
        assertEquals(expSolution, solutions);
   }
    
    @Test
    public void singleSecondaryTest2(){
        ExtendedExactHittingSolver<String> solver = new ExtendedExactHittingSolver<String>();
        
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addSecondarySet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        // showSolutions(solutions);
        
        assertEquals(1, solutions.size());
        
        Set<Set<String>> expSolution = new HashSet<Set<String>>();
        expSolution.add(new HashSet<String>(Arrays.asList("1", "2", "5")));
        
        assertEquals(expSolution, solutions);
   }
    
    @Test
    public void singleSecondaryTest3(){
        ExtendedExactHittingSolver<String> solver = new ExtendedExactHittingSolver<String>();
        
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSecondarySet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        // showSolutions(solutions);
        
        assertEquals(2, solutions.size());
        
        Set<Set<String>> expSolution = new HashSet<Set<String>>();
        expSolution.add(new HashSet<String>(Arrays.asList("1", "2", "5")));
        expSolution.add(new HashSet<String>(Arrays.asList("2", "4")));
        
        assertEquals(expSolution, solutions);
   }
    
    @Test
    public void multipleSecondaryTest(){
        ExtendedExactHittingSolver<String> solver = new ExtendedExactHittingSolver<String>();
        
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4", "7")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("1", "4")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("4", "5", "7")));
        solver.addSecondarySet(new HashSet<String>(Arrays.asList("3", "5", "6")));
        solver.addPrimarySet(new HashSet<String>(Arrays.asList("2", "3", "6", "7")));
        solver.addSecondarySet(new HashSet<String>(Arrays.asList("2", "7")));
        
        Set<Set<String>> solutions = solver.solve();
        // showSolutions(solutions);
        
        assertEquals(4, solutions.size());
        
        Set<Set<String>> expSolution = new HashSet<Set<String>>();
        expSolution.add(new HashSet<String>(Arrays.asList("1", "2", "5")));
        expSolution.add(new HashSet<String>(Arrays.asList("2", "4")));
        expSolution.add(new HashSet<String>(Arrays.asList("3", "4")));
        expSolution.add(new HashSet<String>(Arrays.asList("6", "4")));
                        
        assertEquals(expSolution, solutions);
   }
}
