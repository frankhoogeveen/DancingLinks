/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.exact;

import java.util.Set;
import nl.fh.solver.LinksSolver;


/**
 * class to solve the exact cover problem using the dancing links algorithm
 * 
 * - give this solver a number of sets by calling addSet()
 * - call solve() to obtains a set of solutions
 * 
 * - each solution is a sub-collection of the sets added, with the property that
 * every element of the union of all sets occurs in exactly of set in the solution
 * (i.e. the solution forms a partition of the union of all added sets
 * 
 * 
 * @author frank
 */
public class ExactCoverSolver<T> {
    
    private final LinksSolver<Set<T>, T> solver;
    
    public ExactCoverSolver(){
        this.solver = new LinksSolver<Set<T>, T>();
    }
    
    
    public void addSet(Set<T> set) {
        for(T t : set){
            solver.addLink(set, t);
        }
    }

    /**
     * 
     * @return  all solutions of the exact cover problem on the union of all sets added
     */
    public Set<Set<Set<T>>> solve() {
        return this.solver.solve();
    }
}
