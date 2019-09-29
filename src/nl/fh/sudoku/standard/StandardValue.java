/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku.standard;

import nl.fh.sudoku.SudokuValue;

/**
 *
 * @author frank
 */
public class StandardValue implements SudokuValue {
    private static final int NINE = 9;
    private static StandardValue[] VALUES = null;
    private final int val;
    
    private StandardValue(int val){
        this.val = val;
    }
    
    /**
     *
     * @param val a number in the range 1..9
     * @return the (flyweight) instance corresponding to the input
     */
    public static StandardValue getInstance(int val){
        if(VALUES == null){
            VALUES = new StandardValue[NINE];
            for(int ival = 1; ival <= NINE; ival++){
                VALUES[ival-1] = new StandardValue(ival);
            }
        }
        return VALUES[val-1];
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("V");
        sb.append(val);
        return sb.toString();
    }

    public int getValue() {
        return this.val;
    }
    
}
