/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nl.fh.exact.ExactHittingSolver;

/**
 *
 * @author frank
 * @param <L>  the type of locations used
 * @param <V>  the type of values used
 */
public class SudokuSolver<L extends SudokuLocation, V extends SudokuValue> {
    private Set<V> values = null;
    private Set<L> locations =  null;
    private Set<Set<L>> conditions = null;
    private Map<L, V> givens = null;
    
    public SudokuSolver(){
        reset();
        checkState();
    }

    public final void reset() {
        values = new HashSet<V>();
        locations = new HashSet<L>();
        conditions = new HashSet<Set<L>>();
        givens = new HashMap<L, V>();
    }
    
    /**
     * method to ensure that this solver is in a legal state
     */
    private void checkState() {
        for(Set<L> condition : conditions){
            checkCondition(condition);
        }
        
        checkGivens();
    }
    
    private void checkCondition(Set<L> condition) {
        // the condition should have as many locations a there are values
        if(condition.size() != values.size()){
            throw new IllegalArgumentException();
        }
        
        // the conditions should not introduce new locations
       if(!locations.containsAll(condition)){
           throw new IllegalArgumentException();
       }
    }

    private void checkGivens() {
        // the givens should not introduce new values or locations
        if(!locations.containsAll(givens.keySet())){
            throw new IllegalArgumentException();
        }
        if(!values.containsAll(givens.values())){
            throw new IllegalArgumentException();
        }
    }
    
    public void addValue(V value){
        this.values.add(value);
        checkState();
    }
    
    public void addLocation(L location){
        this.locations.add(location);
        checkState();
    }
    
    public void addCondition(Set<L> condition){
        this.conditions.add(condition);
        checkState();
    }
    
    public void addGiven(L loc, V val){
        this.givens.put(loc, val);
        checkState();
    }
    
    /**
     * 
     * @return the solutions of the Sudoku
     */
    public Set<Map<L,V>> solve(){
        // set up an exact hitting solver
        ExactHittingSolver solver = new ExactHittingSolver();
        
        // translate the sudoku problem into an exact hitting problem
        
        // each location should have exactly one value
        for(L loc : locations){
            Set<SudokuAtom<L,V>> set = new HashSet<SudokuAtom<L,V>>();
            for(V val : values){
                set.add(new SudokuAtom<L,V>(loc, val));
            }    
            solver.addSet(set);
        }
        
        // enforce the givens
        for(L loc : givens.keySet()){
            Set<SudokuAtom<L,V>> set = new HashSet<SudokuAtom<L,V>>();
            set.add(new SudokuAtom<L,V>(loc, givens.get(loc)));
            solver.addSet(set);
        }
        
        
        //enforce the condtions
        for(Set<L> condition : conditions){
            for(V val : values){
                Set<SudokuAtom<L,V>> set = new HashSet<SudokuAtom<L,V>>();
                for(L loc : condition){
                    set.add(new SudokuAtom<L,V>(loc, val));
                }
                solver.addSet(set);
            }
        }
        
        // use the exact hitting solver
        Set<Set<SudokuAtom<L,V>>> atomSets = solver.solve();
        
        // convert the result to a more conveniant format
        Set<Map<L,V>> result = new HashSet<Map<L,V>>();
        for(Set<SudokuAtom<L,V>> atomSet : atomSets){
            Map<L,V> solution = new HashMap<L,V>();
            for(SudokuAtom atom : atomSet){
                solution.put((L)atom.getLocation(), (V)atom.getValue());
            }
            result.add(solution);
        }
        return result;
    }
}
  

