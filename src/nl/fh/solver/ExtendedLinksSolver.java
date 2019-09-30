/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashSet;
import java.util.Set;
import nl.fh.solver.LinksSolver;

/**
 *
 *  solver for a  extended dancing links problem
 
  this solver is used by:
 
  - defining the type of wrappedRow and column objects 
  - for each link between a wrappedRow and column object aadLink() has to be called
  - set some columns secondary by calling makeSecondary
  - once all links have been setup, solve() is called
 
  solve() returns a set of solutions, each wrappedSolution is a set of wrappedRow nodes,
  such that each primary column links to exactly one wrappedRow in the wrappedSolution, 
  while each secondary column links to zero or one rows in the wrappedSolution.
 * 
 * @author frank
 * @param <R> type of wrappedRow objects
 * @param <C> type of column objects
 */
public class ExtendedLinksSolver<R,C> {
    private LinksSolver<ExtensionWrapper<R,C>, C> solver = null;
    
    public ExtendedLinksSolver(){
        this.solver = new LinksSolver<ExtensionWrapper<R,C>,C>();
    }
    
    /**
     * 
     * @param row
     * @param col 
     */
    public void addLink(R row , C col){
        ExtensionWrapper<R,C> wrapper = new ExtensionWrapper<R,C>();
        wrapper.setRow(row);
        solver.addLink(wrapper, col);
    }
    /**
     * makes a column secondary, i.e. the column could be linked zero or one
     * times to a wrappedRow object in the wrappedSolution
     * 
     * @param col
     */
    public void makeSecondary(C col){
        // add a single link between the col and a dummy wrappedRow
        ExtensionWrapper<R,C> wrapper = new ExtensionWrapper<R,C>();
        wrapper.setCol(col);
        solver.addLink(wrapper, col);
    }
    
    public Set<Set<R>> solve(){
        // let the solver do its job
        Set<Set<ExtensionWrapper<R, C>>> wrappedSolutions = solver.solve();
        
        // unwrap the result of the solver
        Set<Set<R>> result  = new HashSet<Set<R>>();
        
        for(Set<ExtensionWrapper<R, C>> wrappedSolution : wrappedSolutions){
            Set<R> unwrappedSolution = new HashSet<R>();
            for(ExtensionWrapper<R, C> wrappedRow : wrappedSolution){
                // only return the primary rows of the solution
                if(!wrappedRow.isSecondary()){
                    unwrappedSolution.add(wrappedRow.getRow());
                }
            }
            result.add(unwrappedSolution);
        }
        
        return result;
    }
    
    @Override
    public String toString(){
        return solver.toString();
    }
}
