package nl.fh.sudoku;

import nl.fh.sudoku.standard.StandardSudokuSolver;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Based on 
 *  https://www.telegraph.co.uk/news/science/science-news/9359579/Worlds-hardest-sudoku-can-you-crack-it.html
 * @author frank
 */
public class HardSudokuTest {
    
    @Test
    public void WikipediaTest(){
         int[][] puzzle = {
            {8,0,0, 0,0,0, 0,0,0},
            {0,0,3, 6,0,0, 0,0,0},
            {0,7,0, 0,9,0, 2,0,0},
            
            {0,5,0, 0,0,7, 0,0,0},
            {0,0,0, 0,4,5, 7,0,0},
            {0,0,0, 1,0,0, 0,3,0},
            
            {0,0,1, 0,0,0, 0,6,8},
            {0,0,8, 5,0,0, 0,1,0},
            {0,9,0, 0,0,0, 4,0,0}
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
        
        for(int ix = 0; ix < 9; ix++){
            for(int iy = 0; iy < 9; iy++){
                System.out.print(solutions[0][ix][iy]);
            }
            System.out.println();
        }
        
        assertEquals(1, solutions.length);
        assertArrayEquals(expected, solutions[0]);
    }
}
