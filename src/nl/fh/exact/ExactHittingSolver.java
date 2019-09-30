/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.exact;

import java.util.Set;
import nl.fh.solver.LinksSolver;

/**
 * Solver for the exact hitting problem
 * @author frank
 */
public class ExactHittingSolver<T> {
    private final LinksSolver<T, Set<T>> solver;
    
    public ExactHittingSolver(){
        this.solver = new LinksSolver<T, Set<T>>();
    }
    
    /**
     * 
     * @param set a set that has to be hit exactly once by a solution
     */
    public void addSet(Set<T> set) {
        for(T t : set){
            solver.addLink(t, set);
        }
    }

    /**
     * 
     * @return  all solutions of the exact cover problem on the union of all sets added
     */
    public Set<Set<T>> solve() {
        return this.solver.solve();
    }
}
