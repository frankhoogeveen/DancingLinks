/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.nqueens;

import java.util.HashSet;
import java.util.Set;
import nl.fh.exact.ExtendedExactHittingSolver;

/**
 *
 *  class that solves the n queens problem
 * 
 * @author frank
 */
public class NqueensSolver {
    private static ExtendedExactHittingSolver<ChessLocation> solver = null;
    private static int size;
    
    public static Set<Set<ChessLocation>> solve(int size){
        if(size < 2) {
            throw new IllegalArgumentException();
        }
        
        NqueensSolver.size = size;
        ChessLocation.setSize(size);
        solver = new ExtendedExactHittingSolver<ChessLocation>();
        
        addRowConstraints();
        addColumnConstraints();
        addDiagonalConstraints();
        
        return solver.solve();
    }
    
    /**
     * 
     * @param size
     * @return the solutions to the n-queens problem, where 
     * only the se-nw diagonals of the queens are taken into account,
     * and the queens are not supposed to move along the sw-ne diagonals
     */
    public static Set<Set<ChessLocation>> solveHalf(int size){
        if(size < 2) {
            throw new IllegalArgumentException();
        }
        
        NqueensSolver.size = size;
        ChessLocation.setSize(size);
        solver = new ExtendedExactHittingSolver<ChessLocation>();
        
        addRowConstraints();
        addColumnConstraints();
        addHalfDiagonalConstraints();
        
        return solver.solve();
    }

    private static void addRowConstraints() {
        for(int irow = 1; irow <= size; irow++){
            Set<ChessLocation> row = new HashSet<ChessLocation>();
            for(int i = 1; i <= size; i++){
                row.add(ChessLocation.getInstance(irow, i));
            }
            // display(row);
            solver.addPrimarySet(row);
        }
    }
    
    private static void addColumnConstraints() {
        for(int icol = 1; icol <= size; icol++){
            Set<ChessLocation> col = new HashSet<ChessLocation>();
            for(int i = 1; i <= size; i++){
                col.add(ChessLocation.getInstance(i, icol));
            }
            // display(col);
            solver.addPrimarySet(col);
        }
    }

    private static void addDiagonalConstraints() {
        
        for(int sum = 3; sum <= (2 * size -1); sum++){
            Set<ChessLocation> diag1 = new HashSet<ChessLocation>();
            Set<ChessLocation> diag2 = new HashSet<ChessLocation>();
            for(int irow = 1; irow <= size; irow++){
                int icol = sum - irow;
                if((icol > 0) && icol <= size){
                    diag1.add(ChessLocation.getInstance(irow, icol));
                    diag2.add(ChessLocation.getInstance(irow, size+1-icol));
                }
            }
            // display(diag1);
            // display(diag2);
            
            solver.addSecondarySet(diag1);
            solver.addSecondarySet(diag2);
        }
    }
    
    private static void addHalfDiagonalConstraints() {
        
        for(int sum = 3; sum <= (2 * size -1); sum++){
            Set<ChessLocation> diag1 = new HashSet<ChessLocation>();
            for(int irow = 1; irow <= size; irow++){
                int icol = sum - irow;
                if((icol > 0) && icol <= size){
                    diag1.add(ChessLocation.getInstance(irow, icol));
                }
            }
            // display(diag1);
            
            solver.addSecondarySet(diag1);
        }
    }
    
    private static void display(Set<ChessLocation> group){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(ChessLocation item : group){
            sb.append(item.toString());
            sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
