/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fh.solver;

import java.util.HashMap;
import java.util.Map;
import nl.fh.link.Link;
import nl.fh.link.LinksTable;

/**
 *
 * @author frank
 * @param <R> type of row objects
 * @param <C> type of column objects
 */
public class LinksMapper<R, C> {

    private final Map<R, Link> mapRowObjectToHeaderLink;
    private final Map<C, Link> mapColObjectToHeaderLink;
    
    private final Map<Link, R> mapHeaderLinkToRowObject;
    private final Map<Link, C> mapHeaderLinkToColObject;
    
    public LinksMapper(){
        this.mapColObjectToHeaderLink = new HashMap<C, Link>();
        this.mapRowObjectToHeaderLink = new HashMap<R, Link>();
        
        this.mapHeaderLinkToRowObject = new HashMap<Link, R>();
        this.mapHeaderLinkToColObject = new HashMap<Link, C>();
    }
    
    public void addRow(R row, Link header){
        if(!header.isRowHeader()){
            throw new IllegalArgumentException();
        }
        
        mapHeaderLinkToRowObject.put(header, row);
        mapRowObjectToHeaderLink.put(row, header);
    }
    
    public void addCol(C col, Link header){
        if(!header.isColumnHeader()){
            throw new IllegalArgumentException();
        }
        
        mapHeaderLinkToColObject.put(header, col);
        mapColObjectToHeaderLink.put(col, header);
    }
    
    
    public R getRowObject(Link link) {
        return mapHeaderLinkToRowObject.get(link.getRow());
    }

    public C getColObject(Link link) {
        return mapHeaderLinkToColObject.get(link.getCol());
    }
    
    public Link getRowHeader(R row){
        return mapRowObjectToHeaderLink.get(row);
    }
    
    public Link getColHeader(C col){
        return mapColObjectToHeaderLink.get(col);
    }
    
    
    
    /**
     * 
     * @param link
     * @return a string containing the headers that are linked
     */
    public String shortDescriptionOf(Link link){
        if(link.isTableHeader()){
            return "table header";
        }
        
        if(link.isRowHeader()){
            return "row header " + mapHeaderLinkToRowObject.get(link);
        }
        
        if(link.isColumnHeader()){
            return "col header " + mapHeaderLinkToColObject.get(link);
        }
        
        return mapHeaderLinkToRowObject.get(link.getRow()) + "/" + mapHeaderLinkToColObject.get(link.getCol());
    }
    
    /**
     * 
     * @param link
     * @return a description of the neighbors of each link
     */
    public String longDescriptionOf(Link link){
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
    public String longDescriptionOf(LinksTable table){
        StringBuilder sb = new StringBuilder();
                
        sb.append("\n==============================\n");
        
        Link currentRow = table.getTableHeader();
        do{
            Link currentCol = currentRow;
            do{
                sb.append(longDescriptionOf(currentCol));
                
                currentCol = currentCol.getRight();
            } while(currentCol != currentRow);
            
            currentRow = currentRow.getDown();
        } while(currentRow != table.getTableHeader());
        
        return sb.toString();
    }
}
