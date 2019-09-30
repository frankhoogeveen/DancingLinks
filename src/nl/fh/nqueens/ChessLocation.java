/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.nqueens;

/**
 *
 * @author frank
 */
public class ChessLocation {
    private static int size = 8;
    private static ChessLocation[][] LOCATIONS = null;
    private final int x;
    private final int y;
    
    private ChessLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     *  changes the size of the chessboard and resets all the locations
     * @param size 
     */
    static void setSize(int size){
        ChessLocation.size = size;
        ChessLocation.LOCATIONS = null;
    }
    
    /**
     *
     * @param x in the range 1..9 (1 is top , 8 is bottom)
     * @param y in the range 1..9 (1 is left, 8 is right)
     * @return the standard location using a flyweight design pattern
     */
    public static ChessLocation getInstance(int x, int y){
        
        if(LOCATIONS == null){
            LOCATIONS = new ChessLocation[size][size];
            for(int ix = 1; ix <= size; ix++){
                for(int iy = 1; iy <= size; iy++){
                    LOCATIONS[ix-1][iy-1] = new ChessLocation(ix,iy);
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.x;
        hash = 61 * hash + this.y;
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
        final ChessLocation other = (ChessLocation) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}
