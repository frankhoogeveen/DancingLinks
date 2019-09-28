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
 *
 * @author frank
 */
public class SolutionStore implements SolutionProcessor {

    private ArrayList<Set<Node>> list;

    public SolutionStore(){
        initialize();
    }
    
    @Override
    public final void initialize() {
        this.list = new ArrayList<Set<Node>>();
    }

    @Override
    public void process(Set<Node> currentSolution) {
        System.out.println("processing new solution");
        for(Node n : currentSolution){
            System.out.println("   " + n.toString());
        }
        System.out.println();
        
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
    public List<Set<Node>> getSolutions(){
        return this.list;
    }
    
}
