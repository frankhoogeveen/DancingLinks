/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.exact;

import java.util.Set;
import nl.fh.extended.ExtendedLinksSolver;


/**
 * class to solve the extended exact cover problem using the dancing links algorithm
 * 
 * - give this solver a number of sets by calling addSet()
 * - mark some of the elements as secondary by calling makeSecondary()
 * - call solve() to obtains a set of solutions
 * 
 * - each solution is a sub-collection of the sets added, with the property that
 * every primary element of the union of all sets occurs in exactly of set in the solution
 * While every secondary element occurs in at most one of the sets in the solution
 * 
 * 
 * @author frank
 * @param <T> the type of the elements of the sets
 */
public class ExtendedExactCoverSolver<T> {
    
    private final ExtendedLinksSolver<Set<T>, T> solver;
    
    public ExtendedExactCoverSolver(){
        this.solver = new ExtendedLinksSolver<Set<T>, T>();
    }
    
    
    public void addSet(Set<T> set) {
        for(T t : set){
            solver.addLink(set, t);
        }
    }
    
    public void markSecondary(T t){
        solver.makeSecondary(t);
    }

    /**
     * 
     * @return  all solutions of the extended exact cover problem on the union of all sets added
     */
    public Set<Set<Set<T>>> solve() {
        return this.solver.solve();
    }
}
