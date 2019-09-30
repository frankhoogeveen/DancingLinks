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
 * 
 * - create this ExactHittingSolver, specifying the type
 * - hand it a number of sets by calling addSet
 * - call solve() to get a set of solutions
 * 
 * a solution is a subset of the union of all sets that were arguments of addSet()
 * such that the intersection of the solution with each of the addSets has exactly
 * one element
 * 
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
