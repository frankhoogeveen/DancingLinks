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

/**
 *
 * @author frank
 * @param <L>  the type of locations used
 * @param <V>  the type of values used
 */
public class SudokuSolver<L extends SudokuLocation, V extends SudokuValue> {
    private Set<V> values;
    private Set<L> locations;
    private Set<Set<L>> conditions;
    private Map<L, V> givens;
    
    public SudokuSolver(){
        values = new HashSet<V>();
        locations = new HashSet<L>();
        conditions = new HashSet<Set<L>>();
        givens = new HashMap<L, V>();
        
        checkState();
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
        // translate the sudoku problem into an exact hitting problem
        
        
    }
        
}
  

