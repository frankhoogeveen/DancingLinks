/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.Objects;

/**
 *  a wrapper that contains either a (primary) row object, or a 
 *  column. But never both.
 * 
 * @author frank
 */
class ExtensionWrapper<R, C> {
    private R row;
    private C col;
 
    
    ExtensionWrapper(){
        
    }

    public R getRow() {
        return row;
    }

    /**
     * can only be called if the column has not been set
     * @param row 
     */
    public void setRow(R row) {
        this.row = row;
        if(this.col != null){
            throw new UnsupportedOperationException();
        }
    }

    public C getCol() {
        return col;
    }

    /**
     * can only be called if the row has bot been set
     * @param col 
     */
    public void setCol(C col) {
        this.col = col;
        if(this.row != null){
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * 
     * @return true if this wrapper wraps a column, false if it wraps a row
     * when neither column or row has been specified, an exception is thrown
     */
    public boolean isSecondary(){
        if((row == null) && (col == null)){
            throw new UnsupportedOperationException();
        }
        
        return (row == null);
    }
    
    @Override
    public String toString(){
        if(row != null){
            return row.toString();
        }
        
        return "secondary row: " + col.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.row);
        hash = 89 * hash + Objects.hashCode(this.col);
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
        final ExtensionWrapper<?, ?> other = (ExtensionWrapper<?, ?>) obj;
        if (!Objects.equals(this.row, other.row)) {
            return false;
        }
        if (!Objects.equals(this.col, other.col)) {
            return false;
        }
        return true;
    }
}
