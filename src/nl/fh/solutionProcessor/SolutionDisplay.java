/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solutionProcessor;

import java.util.Set;
import nl.fh.solver.Node;

/**
 *
 * @author frank
 */
public class SolutionDisplay implements SolutionProcessor {

    private long count;

    public SolutionDisplay() {
    }

    @Override
    public void initialize() {
        this.count = 0;
    }

    @Override
    public void process(Set<Node> currentSolution) {
        this.count += 1;
        
        StringBuilder sb = new StringBuilder();
        sb.append("--- solution found ---\n");
        for(Node l : currentSolution){
            sb.append(l.toString());
            sb.append("\n");
        }
        sb.append("----------------------\n");
        System.out.println(sb.toString());
    }

    @Override
    public long getCount() {
        return this.count;
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }
}
