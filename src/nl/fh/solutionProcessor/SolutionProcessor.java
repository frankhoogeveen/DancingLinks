/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solutionProcessor;

import java.util.Set;
import nl.fh.solver.Node;

/**
 * interface of objects that processes solutions found by the dancing links
 * 
 * @author frank
 */
public interface SolutionProcessor {

    /**
     * resets the solution processor
     */
    public void initialize();

    /**
     * method called when a solution has been found
     * 
     * @param currentSolution 
     */
    public void process(Set<Node> currentSolution);
    
    /**
     * 
     * @return the number of solutions processed since the last initialization
     */
    public long getCount();

    /**
     * 
     * @return true if the solution processor does not need more solutions
     * 
     * can be used to search for a limited number of solutions, a solution with 
     * particular properties, or to limit the time spent
     */
    public boolean isSatisfied();
    
}
