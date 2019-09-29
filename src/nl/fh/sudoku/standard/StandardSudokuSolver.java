/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku.standard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.sudoku.SudokuSolver;

/**
 *
 * @author frank
 */
public class StandardSudokuSolver extends SudokuSolver<StandardLocation, StandardValue>{
    private static final int NINE = 9;
    private static final int THREE = 3;
    
    public StandardSudokuSolver(){
        super();
    }

    private void resetStandard() {
        super.reset();
        
        // reset the values
        for(int ival = 1; ival <= NINE; ival++){
            this.addValue(StandardValue.getInstance(ival));
        }
        
        // reset the locations
        for(int ix = 1; ix <= NINE; ix++){
            for(int iy = 1; iy <= NINE; iy++){
                this.addLocation(StandardLocation.getInstance(ix, iy));
            }
        }
        
        // reset the conditions on rows, columns and blocks
        for(int n = 1; n<= NINE; n++){
            resetRow(n);
            resetColumn(n);
        }
        
        for(int nx = 0; nx < THREE; nx++){
           for(int ny = 0; ny < THREE; ny++){
               resetBlock(nx,ny);
           }
        }
    }

    private void resetRow(int nrow) {
        Set<StandardLocation> condition = new HashSet<StandardLocation>();
        for(int ncol = 1; ncol <= NINE; ncol++){
            condition.add(StandardLocation.getInstance(ncol, nrow));
        }
        this.addCondition(condition);
    }

    private void resetColumn(int ncol) {
        Set<StandardLocation> condition = new HashSet<StandardLocation>();
        for(int nrow = 1; nrow <= NINE; nrow++){
            condition.add(StandardLocation.getInstance(ncol, nrow));
        }
        this.addCondition(condition);
    }

    private void resetBlock(int nx, int ny) {
        Set<StandardLocation> condition = new HashSet<StandardLocation>();
        for(int ix = 1; ix <= THREE; ix++){
            for(int iy = 1; iy <= THREE; iy++){
                condition.add(StandardLocation.getInstance(nx*THREE + ix, ny*THREE + iy));
            }
        }
        this.addCondition(condition);
    }
    /**
     * 
     * @param puzzle
     * @return  an array of solutions
     */
    public int[][][] solve(int[][] puzzle) {
        resetStandard();
        
        // add the givens from the puzzle
        for(int ix = 1; ix <= NINE; ix++){
            for(int iy = 1; iy <= NINE; iy++){
                if(puzzle[ix-1][iy-1] != 0){
                    StandardLocation loc = StandardLocation.getInstance(ix, iy);
                    StandardValue val = StandardValue.getInstance(puzzle[ix-1][iy-1]);
                    this.addGiven(loc, val);
                }
            }
        }
        
        // call the generic sudoku solver
        Set<Map<StandardLocation, StandardValue>> solutionMaps = this.solve();
        
        // reformat the solutions found 
        int[][][] result = new int[solutionMaps.size()][NINE][NINE];
        int count = 0;
        for(Map<StandardLocation, StandardValue> map : solutionMaps){
            for(int ix = 1; ix <= NINE; ix++){
                for(int iy = 1; iy <= NINE; iy++){
                    StandardLocation loc = StandardLocation.getInstance(ix, iy);
                    StandardValue val = map.get(loc);
                    result[count][ix-1][iy-1] = val.getValue();
                }
            }
            count++;
        }
        return result;
    }
}
