/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku;

import java.util.Objects;

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

    public L getLocation() {
        return location;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.location);
        hash = 13 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuAtom<?, ?> other = (SudokuAtom<?, ?>) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(location.toString());
        sb.append(value.toString());
        
        return sb.toString();
    }
}
