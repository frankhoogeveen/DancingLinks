/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.exact;

import java.util.Set;
import nl.fh.extended.ExtendedLinksSolver;

/**
 * Solver for the extended exact hitting problem
 * 
 * - create this ExtendedExactHittingSolver, specifying the type
 * - hand it a number of sets by calling addPrimarySet() or addSecondarySet()
 * - call solve() to get a set of solutions
 * 
 * a solution is a subset of the union of all sets that were arguments of addSet()
 * such that the intersection of the solution with each of the primary has exactly
 * one element, whereas the intersection of the solution with each of the secondary
 * sets has zero or one elements
 * 
 * @author frank
 */
public class ExtendedExactHittingSolver<T> {
    private final ExtendedLinksSolver<T, Set<T>> solver;
    
    public ExtendedExactHittingSolver(){
        this.solver = new ExtendedLinksSolver<T, Set<T>>();
    }
    
    /**
     * 
     * @param set a set that has to be hit exactly once by a solution
     */
    public void addPrimarySet(Set<T> set) {
        for(T t : set){
            solver.addLink(t, set);
        }
    }
    
     /**
     * 
     * @param set a set that has to be hit at most once by a solution
     */
    public void addSecondarySet(Set<T> set) {
        for(T t : set){
            solver.addLink(t, set);
        }
        solver.makeSecondary(set);
    }

    /**
     * 
     * @return  all solutions of the exact cover problem on the union of all sets added
     */
    public Set<Set<T>> solve() {
        return this.solver.solve();
    }
}
