/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku.standard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author frank
 */
public class NrcSudokuSolver extends StandardSudokuSolver{
    private static final int THREE = 3;
    
    public NrcSudokuSolver(){
        super();
    }

    @Override
    public void reset() {
        // create a normal standard sudoku
        super.reset();
        
        // add the four NRC specific blocks
        resetNrcCondition(2,2);
        resetNrcCondition(2,6);
        resetNrcCondition(6,2);
        resetNrcCondition(6,6);
    }

    private void resetNrcCondition(int ix, int iy) {
        Set<StandardLocation> condition = new HashSet<StandardLocation>();
        for(int nx = 0; nx < THREE; nx++){
            for(int ny = 0; ny < THREE; ny++){
                condition.add(StandardLocation.getInstance(ix+nx, iy+ny));
            }
        }
        super.addCondition(condition);
    }
    
     /**
     * 
     * @param puzzle
     * @return  an array of solutions
     */
    @Override
    public int[][][] solve(int[][] puzzle) {
        reset();
        return solveWithoutReset(puzzle);
    }
}
