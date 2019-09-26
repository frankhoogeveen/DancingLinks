/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solutionProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nl.fh.solver.Node;

/**
 * groups several processors parallel
 * 
 * @author frank
 */
public class MultiProcessor implements SolutionProcessor {

    List<SolutionProcessor> procList = new ArrayList<SolutionProcessor>();
    long count = 0;
    
    
    public void add(SolutionProcessor proc){
        if(count > 0){
            throw new UnsupportedOperationException("cannot yet add processors, after process() has been called");
        }
        
        procList.add(proc);
    }
    
    
    @Override
    public void initialize() {
        for(SolutionProcessor proc : procList){
            proc.initialize();
        }
    }

    @Override
    public void process(Set<Node> currentSolution) {
        count++;
        for(SolutionProcessor proc : procList){
            proc.process(currentSolution);
        }
    }

    @Override
    public long getCount() {
        return count;
    }

    @Override
    public boolean isSatisfied() {
        boolean satisfied = true;
        for(SolutionProcessor proc : procList){
            satisfied &= proc.isSatisfied();
        }
        return satisfied;
    }
    
}
