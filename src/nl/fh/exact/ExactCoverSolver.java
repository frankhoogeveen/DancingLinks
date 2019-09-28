/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.exact;

import java.util.Set;
import nl.fh.solver.LinksSolver;


/**
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
    Set<Set<Set<T>>> solve() {
        return this.solver.solve();
    }
}
