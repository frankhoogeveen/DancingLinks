/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku;

import static javax.management.Query.value;

/**
 *
 * this represents one atomic part of the solution
 * as a value/location pair
 * 
 * @author frank
 * @param <L> the type of the locations
 * @param <V> the type of the value
 */
public class SudokuAtom<L extends SudokuLocation, V extends SudokuValue> {

    private final L location;
    private final V value;

    
    public SudokuAtom(L location, V value){
        this.location = location;
        this.value = value;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(location.toString());
        sb.append(value.toString());
        
        return sb.toString();
    }
}
