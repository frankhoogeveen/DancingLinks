/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;

import nl.fh.solver.LinksSolver;


/**
 *
 *  Links of a two dimensional circular (toroidal) double linked list
 * 
 * @author frank
 */
public abstract class AbstractNode {
    // the nearest neighbours
    AbstractNode left;
    AbstractNode right;
    AbstractNode up;
    AbstractNode down;
    
    // the context in which the links are used
    // this enables human readable prints of this link
    private static LinksSolver context;

    /**
     * 
     * @param context in which the links are used
     * 
     * The context determines the formatting of this link
     */
    public static void setContext(LinksSolver context) {
        AbstractNode.context = context;
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
    
    public void removeVertical(){
        this.up.down = this.down;
        this.down.up = this.up;
    }
    
    public void restoreVertical(){
        this.up.down = this;
        this.down.up = this;
    }

    public void removeHorizontal(){  
        this.left.right = this.right;
        this.right.left = this.left;
    }
    
    public void restoreHorizontal(){
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
    void insertLeft(AbstractNode other){
        other.left = this.left;
        other.right = this;
        
        this.left.right = other;
        this.left = other;
    }
    
    /**
     * Insert an other link to the right of this
     * @param other 
     */
    void insertRight(AbstractNode other){
        other.right = this.right;
        other.left = this;
        
        this.right.left = other;
        this.right = other;
    }
    
    /**
     * Insert an other link above this
     * @param other 
     */
    void insertAbove(AbstractNode other){
        other.up =this.up;
        other.down = this;
        
        this.up.down = other;
        this.up = other;
    }
    
    /**
     * Insert another link below this
     * @param other 
     */
    void insertBelow(AbstractNode other){
        other.down = this.down;
        other.up = this;
        
        this.down.up = other;
        this.down = other;
    }
    
    /////////////////////////////////////////
    //
    // getters and setters
    //
    /////////////////////////////////////////
    
    public AbstractNode getLeft() {
        return left;
    }

    void setLeft(AbstractNode left) {
        this.left = left;
    }

    public AbstractNode getRight() {
        return right;
    }

    void setRight(AbstractNode right) {
        this.right = right;
    }

    public AbstractNode getUp() {
        return up;
    }

    void setUp(AbstractNode up) {
        this.up = up;
    }

    public AbstractNode getDown() {
        return down;
    }

    void setDown(AbstractNode down) {
        this.down = down;
    }    

    /**
     *
     *  @return the header of the column in which this link is found
     */
     public abstract ColHeader findColumn();
     
    /**
     *
     *  @return the header of the row in which this link is found
     */
     public abstract RowHeader findRow();
    
    /////////////////////////////////////////
    //
    // checks used for debugging and unit testing
    //
    /////////////////////////////////////////
    /**
     *  @param n the maximum number of steps before we should return to this
     *  throws an exception if the link is not circular in all directions
     */
    void checkNode(long n) throws Exception{
        checkUp(n);
        checkDown(n);
        checkLeft(n);
        checkRight(n);
        
        checkReflexivity();
        checkForNullNeighbor();
    }

    private void checkUp(long n) throws Exception {
        AbstractNode current = this;
        for(long m = 0; m < n; m++){
            current = current.up;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkDown(long n) throws Exception {
        AbstractNode current = this;
        for(long m = 0; m < n; m++){
            current = current.down;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkLeft(long n) throws Exception {
        AbstractNode current = this;
        for(long m = 0; m < n; m++){
            current = current.left;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkRight(long n) throws Exception {
        AbstractNode current = this;
        for(long m = 0; m < n; m++){
            current = current.right;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    void checkReflexivity() throws Exception{
        boolean ok = true;
        ok &= (this == this.up.down);
        ok &= (this == this.down.up);
        ok &= (this == this.left.right);
        ok &= (this == this.right.left);
        if(!ok){
            throw new Exception("non reflexive link" + this.toString());
        }
    }

     void checkForNullNeighbor() throws Exception{
        boolean foundNull = false;
        
        foundNull |= (left == null);
        foundNull |= (right == null);
        foundNull |= (up == null);
        foundNull |= (down == null);
        
        if(foundNull){
            throw new Exception("Node has null neigbour" + this.toString());
        }
    }
    
}
