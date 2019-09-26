/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author frank
 * @param <R> type of row objects
 * @param <C> type of column objects
 * 
 * Object responsible for the mapping between row and column objects
 * as they appear e.g. in exact cover problems and the nodes.
 * 
 */
public class LinksMapper<R, C> {

    private final Map<R, Node> mapRowObjectToHeaderLink;
    private final Map<C, Node> mapColObjectToHeaderLink;
    
    private final Map<Node, R> mapHeaderLinkToRowObject;
    private final Map<Node, C> mapHeaderLinkToColObject;
    
    public LinksMapper(){
        this.mapColObjectToHeaderLink = new HashMap<C, Node>();
        this.mapRowObjectToHeaderLink = new HashMap<R, Node>();
        
        this.mapHeaderLinkToRowObject = new HashMap<Node, R>();
        this.mapHeaderLinkToColObject = new HashMap<Node, C>();
    }
    
    
    /**
     * 
     * @param row
     * @param header 
     * 
     * add a new row to the bidirectional mapping
     * throws an exception when a key or value is reused
     */
    public void addRow(R row, Node header){
        boolean wronk = false;
        wronk |= mapHeaderLinkToRowObject.containsKey(header);
        wronk |= mapHeaderLinkToRowObject.containsValue(row);
        wronk |= mapRowObjectToHeaderLink.containsKey(row);
        wronk |= mapRowObjectToHeaderLink.containsValue(header);
        if(wronk){
            throw new IllegalArgumentException("should not reuse key nor value");
        }
        
        mapHeaderLinkToRowObject.put(header, row);
        mapRowObjectToHeaderLink.put(row, header);
    }
    
    public void addCol(C col, Node header){
        boolean wronk = false;
        wronk |= mapHeaderLinkToColObject.containsKey(header);
        wronk |= mapHeaderLinkToColObject.containsValue(col);
        wronk |= mapColObjectToHeaderLink.containsKey(col);
        wronk |= mapColObjectToHeaderLink.containsValue(header);
        if(wronk){
            throw new IllegalArgumentException("should not reuse key nor value");
        }
        
        
        mapHeaderLinkToColObject.put(header, col);
        mapColObjectToHeaderLink.put(col, header);
    }
    
    
    public R getRowObject(Node link) {
        return mapHeaderLinkToRowObject.get(link.getRow());
    }

    public C getColObject(Node link) {
        return mapHeaderLinkToColObject.get(link.getCol());
    }
    
    public Node getRowHeader(R row){
        return mapRowObjectToHeaderLink.get(row);
    }
    
    public Node getColHeader(C col){
        return mapColObjectToHeaderLink.get(col);
    }
    
    
    
    /**
     * 
     * @param link
     * @return a string containing the headers that are linked
     */
    public String shortDescriptionOf(Node link){
        if(link.isColHeader() && link.isRowHeader()){
            return "table header";
        }
        
        if( link.isRowHeader()){
            return "row header " + mapHeaderLinkToRowObject.get((Node)link);
        }
        
        if(link.isColHeader()){
            return "col header " + mapHeaderLinkToColObject.get((Node)link);
        }
        
        return mapHeaderLinkToRowObject.get((Node)link.getRow()) + "/" + mapHeaderLinkToColObject.get((Node)link.getCol());
    }
    
    /**
     * 
     * @param link
     * @return a description of the neighbors of each link
     */
    public String longDescriptionOf(Node link){
        StringBuilder sb = new StringBuilder();
        
        sb.append("---");
        sb.append(shortDescriptionOf(link));
        sb.append("---\n");
        
        sb.append("row   :");
        sb.append(shortDescriptionOf(link.getRow()));
        sb.append("\n");
        
        sb.append("col   :");
        sb.append(shortDescriptionOf(link.getCol()));
        sb.append("\n");
        
        sb.append("left  :");
        sb.append(shortDescriptionOf(link.getLeft()));
        sb.append("\n");
        
        sb.append("right :");
        sb.append(shortDescriptionOf(link.getRight()));
        sb.append("\n");
        
        sb.append("up    :");
        sb.append(shortDescriptionOf(link.getUp()));
        sb.append("\n");
        
        sb.append("down  :");
        sb.append(shortDescriptionOf(link.getDown()));
        sb.append("\n");
                                    
        sb.append("\n");
        
        return sb.toString();
    }
    
    /**
     * 
     * @param table
     * @return a elaborate description of all links
     */
    public String longDescriptionOf(NodeTable table){
        StringBuilder sb = new StringBuilder();
                
        sb.append("\n==============================\n");
        
        Node currentRow = table.getTableHeader();
        do{
            Node currentCol = currentRow;
            do{
                sb.append(longDescriptionOf(currentCol));
                
                currentCol = currentCol.getRight();
            } while(currentCol != currentRow);
            
            currentRow = currentRow.getDown();
        } while(currentRow != table.getTableHeader());
        
        return sb.toString();
    }
}
