/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.node;


/**
 *
 * Link that represents the table header and is used as a 
 * unique token in the stack of the solver
 * 
 * @author frank
 */
public class TableHeaderNode extends AbstractNode implements RowHeader,ColHeader{

    private static TableHeaderNode instance;
    
    /**
     * 
     * @return a singleton instance of the TableHeaderNode
     */
    static TableHeaderNode getInstance(){
        if(instance == null){
            instance = new TableHeaderNode();
            instance.up = instance;
            instance.down = instance;
            instance.left = instance;
            instance.right = instance;
        }
        
        return instance;
    }

    /**
     * reset the TableHeader to an empty table
     */
    static void reset() {
        instance = null;
    }
    
    /**
     * constructor made private to make this a singleton
     */
    private TableHeaderNode(){
        
    }
    
    @Override
    ColHeader findColumn() {    
        return this;
    }

    @Override
    RowHeader findRow() {
        return this;
    }
}
