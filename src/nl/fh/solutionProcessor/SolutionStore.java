/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solutionProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nl.fh.link.Link;

/**
 *
 * @author frank
 */
public class SolutionStore implements SolutionProcessor {

    private ArrayList<Set<Link>> list;

    public SolutionStore(){
        initialize();
    }
    
    @Override
    public final void initialize() {
        this.list = new ArrayList<Set<Link>>();
    }

    @Override
    public void process(Set<Link> currentSolution) {
        this.list.add(currentSolution);
    }

    @Override
    public long getCount() {
        return this.list.size();
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }
    
    /**
     * 
     * @return the list of all solutions
     */
    public List getSolutions(){
        return this.list;
    }
    
}
