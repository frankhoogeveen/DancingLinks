/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.sudoku.standard;

import nl.fh.sudoku.SudokuLocation;

/**
 *
 * @author frank
 */
public class StandardLocation implements SudokuLocation {
    private static final int NINE = 9;
    private static StandardLocation[][] LOCATIONS = null;
    private final int x;
    private final int y;
    
    private StandardLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     * @param x in the range 1..9 (1 is top , 9 is bottom)
     * @param y in the range 1..9 (1 is left, 9 is right)
     * @return the standard location using a flyweight design pattern
     */
    public static StandardLocation getInstance(int x, int y){
        
        if(LOCATIONS == null){
            LOCATIONS = new StandardLocation[NINE][NINE];
            for(int ix = 1; ix <= NINE; ix++){
                for(int iy = 1; iy <= NINE; iy++){
                    LOCATIONS[ix-1][iy-1] = new StandardLocation(ix,iy);
                }
            }
        }
        return LOCATIONS[x-1][y-1];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("R");
        sb.append(x);
        sb.append("C");
        sb.append(y);
        return sb.toString();
    }
}
