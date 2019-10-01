package nl.fh.sudoku;

import java.util.Arrays;
import nl.fh.categories.SlowTest;
import nl.fh.sudoku.standard.StandardSudokuSolver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author frank
 */
public class NrcSudokuTest {
    
    private void printResult(int[][][] solutions){
        System.out.println("==============");
        System.out.print(solutions.length);
        System.out.println(" solutions");
        for(int s = 0; s < solutions.length; s++){
            System.out.println("--------------");
            for(int ix = 0; ix < 9; ix++){
                for(int iy = 0; iy < 9; iy++){
                    System.out.print(solutions[s][ix][iy]);
                }
                System.out.println();
            }
        }
    }
 
    
    @Test
    @Category(SlowTest.class)
    public void NrcTest(){
        // the nrc sudoku of sept 30th, 2019
        int[][] puzzle = {
            {0,0,0, 0,0,6, 0,0,0},
            {0,0,0, 8,0,0, 0,6,0},
            {6,2,9, 0,0,0, 0,0,0},
            
            {0,0,0, 0,0,0, 0,3,2},
            {0,0,0, 0,0,0, 0,0,0},
            {0,8,0, 0,4,3, 0,5,0},
            
            {0,5,0, 9,1,4, 0,0,0},
            {0,0,2, 0,3,0, 0,0,0},
            {0,6,8, 7,0,0, 0,0,0}
         };
         
         int[][] expected = {
            {8,3,1, 4,2,6, 5,9,7},
            {5,7,4, 8,9,1, 2,6,3},
            {6,2,9, 3,7,5, 4,8,1},
            
            {4,1,6, 5,8,9, 7,3,2},
            {3,9,5, 2,6,7, 8,1,4},
            {2,8,7, 1,4,3, 9,5,6},
            
            {7,5,3, 9,1,4, 6,2,8},
            {9,4,2, 6,3,8, 1,7,5},
            {1,6,8, 7,5,2, 3,4,9}
         };
        
        StandardSudokuSolver solver = new StandardSudokuSolver(true);
        int[][][] solutions = solver.solve(puzzle);
        
        printResult(solutions);
        
        // apparently there is more than one solution :)
        assertEquals(8, solutions.length);
        
        // the expected solution should be amongst them
        boolean found = false;
        for (int[][] solution : solutions) {
            found |= Arrays.equals(expected, solution);
        }
        assertTrue(found);
    }    
}
