package nl.fh.sudoku;

import nl.fh.categories.SlowTest;
import nl.fh.sudoku.standard.StandardSudokuSolver;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
public class SudokuTest {
    
    private void printResult(int[][][] solutions){
        System.out.println("==============");
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
    public void WikipediaTest(){
         int[][] puzzle = {
            {1,0,0, 0,0,0, 0,0,6},
            {0,0,6, 0,2,0, 7,0,0},
            {7,8,9, 4,5,0, 1,0,3},
            
            {0,0,0, 8,0,7, 0,0,4},
            {0,0,0, 0,3,0, 0,0,0},
            {0,9,0, 0,0,4, 2,0,1},
            
            {3,1,2, 9,7,0, 0,4,0},
            {0,4,0, 0,1,2, 0,7,8},
            {9,0,8, 0,0,0, 0,0,0}
         };
         
         int[][] expected = {
            {1,2,3, 7,8,9, 4,5,6},
            {4,5,6, 1,2,3, 7,8,9},
            {7,8,9, 4,5,6, 1,2,3},
            
            {2,3,1, 8,9,7, 5,6,4},
            {5,6,4, 2,3,1, 8,9,7},
            {8,9,7, 5,6,4, 2,3,1},
            
            {3,1,2, 9,7,8, 6,4,5},
            {6,4,5, 3,1,2, 9,7,8},
            {9,7,8, 6,4,5, 3,1,2}
         };
        
        StandardSudokuSolver solver = new StandardSudokuSolver();
        int[][][] solutions = solver.solve(puzzle);
        
        assertEquals(1, solutions.length);
        assertArrayEquals(expected, solutions[0]);
    }
    
    @Test
    public void SingleStarTest(){
         int[][] puzzle = {
            {6,7,0, 5,1,0, 0,3,4},
            {0,1,9, 2,0,4, 6,7,8},
            {0,3,0, 0,0,8, 5,0,0},
            
            {7,0,5, 8,9,0, 3,4,2},
            {0,0,1, 3,4,2, 7,0,5},
            {3,4,0, 7,0,0, 0,0,1},
            
            {1,8,0, 9,0,7, 0,0,3},
            {9,5,0, 4,2,0, 0,8,0},
            {0,2,3, 1,8,6, 9,5,7}
         };
         
         int[][] expected = {
            {6,7,8, 5,1,9, 2,3,4},
            {5,1,9, 2,3,4, 6,7,8},
            {2,3,4, 6,7,8, 5,1,9},
            
            {7,6,5, 8,9,1, 3,4,2},
            {8,9,1, 3,4,2, 7,6,5},
            {3,4,2, 7,6,5, 8,9,1},
            
            {1,8,6, 9,5,7, 4,2,3},
            {9,5,7, 4,2,3, 1,8,6},
            {4,2,3, 1,8,6, 9,5,7}
         };
        
        StandardSudokuSolver solver = new StandardSudokuSolver();
        int[][][] solutions = solver.solve(puzzle);
        
        // printResult(solutions);
        
        assertEquals(1, solutions.length);
        assertArrayEquals(expected, solutions[0]);
    }
    
    @Test
    public void TwoStarTest(){
         int[][] puzzle = {
            {0,9,4, 5,0,0, 0,0,0},
            {0,2,0, 8,0,0, 1,0,4},
            {0,0,0, 1,9,4, 5,2,3},
            
            {9,0,5, 0,0,2, 6,7,0},
            {0,0,0, 0,7,0, 9,1,0},
            {6,7,0, 9,1,5, 0,0,0},
            
            {2,4,1, 0,5,0, 7,8,0},
            {0,0,9, 7,8,6, 2,0,0},
            {0,0,6, 2,4,0, 3,5,9}
         };
         
         int[][] expected = {
            {1,9,4, 5,2,3, 8,6,7},
            {5,2,3, 8,6,7, 1,9,4},
            {8,6,7, 1,9,4, 5,2,3},
            
            {9,1,5, 4,3,2, 6,7,8},
            {4,3,2, 6,7,8, 9,1,5},
            {6,7,8, 9,1,5, 4,3,2},
            
            {2,4,1, 3,5,9, 7,8,6},
            {3,5,9, 7,8,6, 2,4,1},
            {7,8,6, 2,4,1, 3,5,9}
         };
        
        StandardSudokuSolver solver = new StandardSudokuSolver();
        int[][][] solutions = solver.solve(puzzle);
        
        // printResult(solutions);
        
        assertEquals(1, solutions.length);
        assertArrayEquals(expected, solutions[0]);
    }
    
    @Test
    @Category(SlowTest.class)
    public void ThreeStarTest(){
         int[][] puzzle = {
            {6,0,9, 7,5,0, 0,4,3},
            {7,0,0, 0,4,0, 0,1,0},
            {0,0,3, 0,0,0, 0,0,0},
            
            {1,0,0, 0,0,0, 0,0,2},
            {0,8,0, 4,3,0, 0,6,0},
            {4,0,0, 1,0,7, 0,8,5},
            
            {0,0,6, 0,0,0, 3,2,4},
            {0,7,1, 0,0,0, 5,0,0},
            {3,0,0, 5,0,0, 8,7,0}
         };
         
         int[][] expected = {
            {6,1,9, 7,5,8, 2,4,3},
            {7,5,8, 2,4,3, 6,1,9},
            {2,4,3, 6,1,9, 7,5,8},
            
            {1,6,7, 9,8,5, 4,3,2},
            {9,8,5, 4,3,2, 1,6,7},
            {4,3,2, 1,6,7, 9,8,5},
            
            {5,9,6, 8,7,1, 3,2,4},
            {8,7,1, 3,2,4, 5,9,6},
            {3,2,4, 5,9,6, 8,7,1}
         };
        
        StandardSudokuSolver solver = new StandardSudokuSolver();
        int[][][] solutions = solver.solve(puzzle);
        
        // printResult(solutions);
        
        assertEquals(1, solutions.length);
        assertArrayEquals(expected, solutions[0]);
    }   
}
