package nl.fh.sudoku;

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
public class SudokuTest {
    
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
        /*
        SudukoGrid grid = new StandardSudokuGrid(puzzle);
        SudokuSolver solver = new SudokuSolver();
        
        Set<SudokuGrid> solutions = solver.solve(grid);
        
        assertEquals(1, solutions.size());
        
        for(SudokuGrid solution : solutions){
            assertEquals(expected, solution);
        }

*/
    }
}
