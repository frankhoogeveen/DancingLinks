/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.link;

import nl.fh.dancing.LinksSolver;


/**
 *
 *  Links of a two dimensional circular (toroidal) double linked list
 * 
 * @author frank
 */
public class Link {
    // the nearest neighbours
    Link left;
    Link right;
    Link up;
    Link down;
     
    // the headers of rows and columns
    Link col;
    Link row;
    
    private static LinksSolver context;

    /**
     * 
     * @param context in which the links are used
     * 
     * The context determines the formatting of this link
     */
    public static void setContext(LinksSolver context) {
        Link.context = context;
    }
    
    @Override
    public String toString(){
        if(context == null){
            return Long.toString(this.hashCode());
        }
        return context.format(this);
    }
    
    /////////////////////////////////////////
    //
    // the code to hide and restore rows and columns
    //
    /////////////////////////////////////////
    public void hideColumn(){
        Link current = this;
        System.out.println(this);  //TODO remove
        do {
            System.out.println(current);
            System.out.println(Link.context); //TODO remove
            current.removeHorizontal();
            current = current.down;
        } while(current != this);
    }
    
    public void restoreColumn(){
        Link current = this.up;
        do {
            current.restoreHorizontal();
            current = current.up;
        } while(current != this.up);
    }
    
    void removeVertical(){
        this.up.down = this.down;
        this.down.up = this.up;
    }
    
    void restoreVertical(){
        this.up.down = this;
        this.down.up = this;
    }
    

    public void hideRow(){   
        Link current = this;
        do {
            
            System.out.println(this);
            System.out.println(current);  // TODO remove
            
            current.removeVertical();
            
            System.out.println(this);
            System.out.println(current);  // TODO remove
            
            current = current.right;
        } while(current != this);
    }
    
    public void restoreRow(){
        Link current = this.left;
        do {
            current.restoreVertical();
            current = current.left;
        } while(current != this.left);
    }

    void removeHorizontal(){
        System.out.println(this);
        System.out.println(this.left);
        System.out.println(this.right);
        System.out.println(Link.context);  //TODO remove
        
        this.left.right = this.right;
        this.right.left = this.left;
        
        System.out.println(this);
        System.out.println(this.left);
        System.out.println(this.right);
        System.out.println(Link.context);  //TODO remove
        
    }
    
    void restoreHorizontal(){
        this.left.right = this;
        this.right.left = this;
    }
    

    /////////////////////////////////////////
    //
    // the code to set up the links
    //
    /////////////////////////////////////////

    
    /**
     *  Insert an other link to the left of this
     * 
     * @param other 
     */
    void insertLeft(Link other){
        other.left = this.left;
        other.right = this;
        
        other.row = this.row;
        
        this.left.right = other;
        this.left = other;
    }
    
    /**
     * Insert an other link to the right of this
     * @param other 
     */
    void insertRight(Link other){
        other.right = this.right;
        other.left = this;
        
        other.row = this.row;
        
        this.right.left = other;
        this.right = other;
    }
    
    /**
     * Insert an other link above this
     * @param other 
     */
    void insertAbove(Link other){
        other.up =this.up;
        other.down = this;
        
        other.col = this.col;
        
        this.up.down = other;
        this.up = other;
    }
    
    /**
     * Insert another link below this
     * @param other 
     */
    void insertBelow(Link other){
        other.down = this.down;
        other.up = this;
        
        other.col = this.col;
        
        this.down.up = other;
        this.down = other;
    }

    
    /////////////////////////////////////////
    //
    // getters and setters
    //
    /////////////////////////////////////////
    
    public Link getCol() {
        return col;
    }

    public Link getRow() {
        return row;
    }

    final void setCol(Link col) {
        if(!col.isColumnHeader()){
            throw new IllegalArgumentException("non circular link");
        }
        this.col = col;
    }

    final void setRow(Link row) {
        if(! row.isRowHeader()){
            throw new IllegalArgumentException("non circular link");
        }
        this.row = row;
    }
    
    public Link getLeft() {
        return left;
    }

    void setLeft(Link left) {
        this.left = left;
    }

    public Link getRight() {
        return right;
    }

    void setRight(Link right) {
        this.right = right;
    }

    public Link getUp() {
        return up;
    }

    void setUp(Link up) {
        this.up = up;
    }

    public Link getDown() {
        return down;
    }

    void setDown(Link down) {
        this.down = down;
    }    
    
    
    /////////////////////////////////////////
    //
    // information methods
    //
    /////////////////////////////////////////
    
    /**
     * 
     * @param colHeader
     * @return check if colHeader appears somewhere in the row containing this 
     */
    boolean isLinkedInRow(Link colHeader){
        return getLinkInRow(colHeader) != null;
    }
    
    /**
     *  if in the row of this, a link is linked to a specific column,
     *  the link is returned. Otherwise null.
     * 
     *  @param colHeader
     *  @return a link in the row of this, linking to colHeader 
     */
    Link getLinkInRow(Link colHeader){
       Link current = this;
            do{
                if(current.col == colHeader){
                    return current;
            }
            current = current.right;
        } while(current != this);
        
        return null;
    }
    
    /**
     * if in the column of this, a link is linked to a specific row,
     * that link is returned, otherwise null
     *  @param rowHeader the header of the row 
     *  @return the link connecting the column of this and the row header
     */
    Link getLinkInColumn(Link rowHeader){
        Link current = this;
        do{
            if(current.row == rowHeader){
                return current;
            }
            current = current.down;
        } while(current != this);
        
        return null;
    }
    
     /**
     * 
     * @param rowHeader
     * @return check if rowHeader appears somewhere in the column containing this 
     */
    boolean isLinkedInColumn(Link rowHeader){
        return getLinkInColumn(rowHeader) != null;
    }
    
    /**
     * @return true if this link is a column header
     */
    public boolean isColumnHeader(){
        return this.col == this;

    }
    
    /**
     *  @return true if this is a row header
     */
    public boolean isRowHeader(){
        return this.row == this;
    }
    
    /**
     * @return true if this is a table header
     */
    public boolean isTableHeader(){
        return isRowHeader() && isColumnHeader();
    }
    
    /**
     * @return true if one of the fields of this Link is null
     */
    public boolean hasNullNeighbor(){
        boolean foundNull = false;
        
        foundNull |= (row == null);
        foundNull |= (col == null);
        foundNull |= (left == null);
        foundNull |= (right == null);
        foundNull |= (up == null);
        foundNull |= (down == null);
        
        return foundNull;
    }

    /////////////////////////////////////////
    //
    // checks used for debugging and unit testing
    //
    /////////////////////////////////////////
    /**
     *  @param n the maximum number of steps before we should return to this
     *  throws an exception if the link is not circular in all directions
     */
    void checkCircularity(long n) throws Exception{
        checkUp(n);
        checkDown(n);
        checkLeft(n);
        checkRight(n);
    }

    private void checkUp(long n) throws Exception {
        Link current = this;
        for(long m = 0; m < n; m++){
            current = current.up;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link");
    }
    
    private void checkDown(long n) throws Exception {
        Link current = this;
        for(long m = 0; m < n; m++){
            current = current.down;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link");
    }
    
    private void checkLeft(long n) throws Exception {
        Link current = this;
        for(long m = 0; m < n; m++){
            current = current.left;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link");
    }
    
    private void checkRight(long n) throws Exception {
        Link current = this;
        for(long m = 0; m < n; m++){
            current = current.right;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link");
    }
    
    void checkReflexivity() throws Exception{
        boolean ok = true;
        ok &= (this == this.up.down);
        ok &= (this == this.down.up);
        ok &= (this == this.left.right);
        ok &= (this == this.right.left);
        if(!ok){
            throw new Exception("non reflexive link");
        }
    }
    
    void checkIdempotency() throws Exception{
        boolean ok = true;
        ok &= (this.col == this.col.col);
        ok &= (this.row == this.row.row);
    }
}
