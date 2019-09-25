/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashMap;
import java.util.Map;
import nl.fh.node.AbstractNode;
import nl.fh.node.ColHeaderNode;
import nl.fh.node.NodeTable;
import nl.fh.node.RowHeaderNode;
import nl.fh.node.TableHeaderNode;

/**
 *
 * @author frank
 * @param <R> type of row objects
 * @param <C> type of column objects
 */
public class LinksMapper<R, C> {

    private final Map<R, RowHeaderNode> mapRowObjectToHeaderLink;
    private final Map<C, ColHeaderNode> mapColObjectToHeaderLink;
    
    private final Map<RowHeaderNode, R> mapHeaderLinkToRowObject;
    private final Map<ColHeaderNode, C> mapHeaderLinkToColObject;
    
    public LinksMapper(){
        this.mapColObjectToHeaderLink = new HashMap<C, ColHeaderNode>();
        this.mapRowObjectToHeaderLink = new HashMap<R, RowHeaderNode>();
        
        this.mapHeaderLinkToRowObject = new HashMap<RowHeaderNode, R>();
        this.mapHeaderLinkToColObject = new HashMap<ColHeaderNode, C>();
    }
    
    public void addRow(R row, RowHeaderNode header){
        
        mapHeaderLinkToRowObject.put(header, row);
        mapRowObjectToHeaderLink.put(row, header);
    }
    
    public void addCol(C col, ColHeaderNode header){
        
        mapHeaderLinkToColObject.put(header, col);
        mapColObjectToHeaderLink.put(col, header);
    }
    
    
    public R getRowObject(AbstractNode link) {
        return mapHeaderLinkToRowObject.get(link.findRow());
    }

    public C getColObject(AbstractNode link) {
        return mapHeaderLinkToColObject.get(link.findColumn());
    }
    
    public RowHeaderNode getRowHeader(R row){
        return mapRowObjectToHeaderLink.get(row);
    }
    
    public ColHeaderNode getColHeader(C col){
        return mapColObjectToHeaderLink.get(col);
    }
    
    
    
    /**
     * 
     * @param link
     * @return a string containing the headers that are linked
     */
    public String shortDescriptionOf(AbstractNode link){
        if(link instanceof TableHeaderNode){
            return "table header";
        }
        
        if(link instanceof RowHeaderNode){
            return "row header " + mapHeaderLinkToRowObject.get((RowHeaderNode)link);
        }
        
        if(link instanceof ColHeaderNode){
            return "col header " + mapHeaderLinkToColObject.get((ColHeaderNode)link);
        }
        
        return mapHeaderLinkToRowObject.get((RowHeaderNode)link.findRow()) + "/" + mapHeaderLinkToColObject.get((ColHeaderNode)link.findColumn());
    }
    
    /**
     * 
     * @param link
     * @return a description of the neighbors of each link
     */
    public String longDescriptionOf(AbstractNode link){
        StringBuilder sb = new StringBuilder();
        
        sb.append("---");
        sb.append(shortDescriptionOf(link));
        sb.append("---\n");
        
        sb.append("row   :");
        sb.append(shortDescriptionOf((AbstractNode) link.findRow()));
        sb.append("\n");
        
        sb.append("col   :");
        sb.append(shortDescriptionOf((AbstractNode)link.findColumn()));
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
        
        AbstractNode currentRow = table.getTableHeader();
        do{
            AbstractNode currentCol = currentRow;
            do{
                sb.append(longDescriptionOf(currentCol));
                
                currentCol = currentCol.getRight();
            } while(currentCol != currentRow);
            
            currentRow = currentRow.getDown();
        } while(currentRow != table.getTableHeader());
        
        return sb.toString();
    }
}
