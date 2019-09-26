/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;


/**
 *
 * Links of a two dimensional circular (toroidal) double linked list
 * It is not the responsibility of this class to make sure that all 
 * connections are consistent. That should be done by the context.
 * 
 * @author frank
 */
public class Node{
    // the nearest neighbours
    Node left;
    Node right;
    Node up;
    Node down;
    
    Node row;
    Node col;
    
    // the context in which the links are used
    // this enables human readable prints of this link
    private static NodeTable context;

    /**
     * 
     * @param context in which the links are used
     * 
     * The context determines the formatting of this link
     */
    static void setContext(NodeTable context) {
        Node.context = context;
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
    
    void removeVertical(){
        this.up.down = this.down;
        this.down.up = this.up;
        
        System.out.println("removeVertical " + this.toString());
    }
    
    void restoreVertical(){
        this.up.down = this;
        this.down.up = this;
        
        System.out.println("restoreVertical " + this.toString());
    }

    void removeHorizontal(){  
        this.left.right = this.right;
        this.right.left = this.left;
        
        System.out.println("removeHorizontal " + this.toString());        
    }
    
    void restoreHorizontal(){
        this.left.right = this;
        this.right.left = this;
        
        System.out.println("restoreHorizontal " + this.toString());            
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
    void insertLeft(Node other){
        other.left = this.left;
        other.right = this;
        
        this.left.right = other;
        this.left = other;
    }
    
    /**
     * Insert an other link to the right of this
     * @param other 
     */
    void insertRight(Node other){
        other.right = this.right;
        other.left = this;
        
        this.right.left = other;
        this.right = other;
    }
    
    /**
     * Insert an other link above this
     * @param other 
     */
    void insertAbove(Node other){
        other.up =this.up;
        other.down = this;
        
        this.up.down = other;
        this.up = other;
    }
    
    /**
     * Insert another link below this
     * @param other 
     */
    void insertBelow(Node other){
        other.down = this.down;
        other.up = this;
        
        this.down.up = other;
        this.down = other;
    } 

    /////////////////////////////////////////
    //
    // code to recognize the headers
    //
    /////////////////////////////////////////
    /**
     * 
     * @return true if this node is a row header, or the table header
     * 
     */
    public boolean isRowHeader(){
        return this.row == this;
    }
    /**
     * 
     * @return true if this node is a column header, or the table header
     * 
     */
    public boolean isColHeader(){
        return this.col == this;
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
    public void checkNode(long n) throws Exception{
        checkUp(n);
        checkDown(n);
        checkLeft(n);
        checkRight(n);
        
        checkReflexivity();
        checkForNullNeighbor();
    }

    private void checkUp(long n) throws Exception {
        Node current = this;
        for(long m = 0; m < n; m++){
            current = current.up;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkDown(long n) throws Exception {
        Node current = this;
        for(long m = 0; m < n; m++){
            current = current.down;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkLeft(long n) throws Exception {
        Node current = this;
        for(long m = 0; m < n; m++){
            current = current.left;
            if(current == this){
                return;
            }
        }
        throw new Exception("non circular link" + this.toString());
    }
    
    private void checkRight(long n) throws Exception {
        Node current = this;
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

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getUp() {
        return up;
    }

    public Node getDown() {
        return down;
    }

    public Node getRow() {
        return row;
    }

    public Node getCol() {
        return col;
    }
     
}
